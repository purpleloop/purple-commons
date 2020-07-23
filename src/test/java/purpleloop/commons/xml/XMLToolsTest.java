package purpleloop.commons.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import purpleloop.commons.exception.PurpleException;

/** Tests cases for XML utilities. */
public class XMLToolsTest {

    /**
     * Test the reading of a document from a null stream.
     * 
     * @throws PurpleException in case of error
     */
    @Test
    public void getDocumentNullStreamTest() throws PurpleException {
        Document doc = XMLTools.getDocument((InputStream) null);
        assertNull(doc);
    }

    /**
     * Test the reading of a document from a stream.
     * 
     * @throws PurpleException in case of error
     * @throws UnsupportedEncodingException
     */
    @Test
    public void getDocumentTest() throws PurpleException, UnsupportedEncodingException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root />";
        Document doc = XMLTools.getDocument(new ByteArrayInputStream(xml.getBytes("UTF-8")));
        assertEquals("root", doc.getDocumentElement().getTagName());
    }

    /**
     * Test get all elements from a document.
     * 
     * @throws PurpleException in case of errors
     */
    @Test
    public void getAllElementsFromDocumentTest() throws PurpleException {
        Document animalDoc = getSampleAnimalsDocument();
        List<Element> childElements = XMLTools.getElementsByTagName(animalDoc, "cat");
        assertEquals(3, childElements.size());
        assertEquals("Kittie", childElements.get(0).getAttribute("name"));
        assertEquals("Kitty", childElements.get(1).getAttribute("name"));
        assertEquals("BabyCat", childElements.get(2).getAttribute("name"));
    }

    /**
     * Test get direct children elements with cat tag.
     * 
     * @throws PurpleException in case of errors
     */
    @Test
    public void getChildrenCatElementsTest() throws PurpleException {
        Document animalDoc = getSampleAnimalsDocument();
        List<Element> childElements = XMLTools.getChildElements(animalDoc.getDocumentElement(),
                "cat");
        assertEquals(2, childElements.size());
        assertEquals("Kittie", childElements.get(0).getAttribute("name"));
    }

    /**
     * Test get all children elements.
     * 
     * @throws PurpleException in case of errors
     */
    @Test
    public void getAllChildrenElementsTest() throws PurpleException {
        Document animalDoc = getSampleAnimalsDocument();
        List<Element> childElements = XMLTools.getChildElements(animalDoc.getDocumentElement());
        assertEquals(4, childElements.size());
        assertEquals("Doggy", childElements.get(0).getAttribute("name"));
        assertEquals("Kittie", childElements.get(1).getAttribute("name"));
        assertEquals("Kitty", childElements.get(2).getAttribute("name"));
        assertEquals("snails", childElements.get(3).getTagName());
    }

    /**
     * Test streaming of all children elements.
     * 
     * @throws PurpleException in case of errors
     */
    @Test
    public void getStreamingAllChildrenElementsTest() throws PurpleException {
        Document animalDoc = getSampleAnimalsDocument();
        Stream<Element> childElements = XMLTools
                .getChildElementsStream(animalDoc.getDocumentElement());
        assertEquals(4, childElements.count());
    }

    /**
     * Test unique mandatory descendant element.
     * 
     * @throws PurpleException in case of errors
     */
    @Test
    public void getUniqueElementTest() throws PurpleException {
        Document animalDoc = getSampleAnimalsDocument();
        Element lonelySnailElement = XMLTools.getUniqueElement(animalDoc.getDocumentElement(),
                "snail", true);
        assertNotNull(lonelySnailElement);
        assertEquals("Molly", lonelySnailElement.getAttribute("name"));
    }

    /**
     * Test unique mandatory descendant element - missing.
     * 
     * @throws PurpleException in case of errors
     */
    @Test(expected = PurpleException.class)
    public void getUniqueElementMissingMandatoryTest() throws PurpleException {
        Document animalDoc = getSampleAnimalsDocument();
        XMLTools.getUniqueElement(animalDoc.getDocumentElement(), "platypus", true);
    }

    /**
     * Test unique optional descendant element - missing.
     * 
     * @throws PurpleException in case of errors
     */
    @Test
    public void getUniqueElementMissingNullTest() throws PurpleException {
        Document animalDoc = getSampleAnimalsDocument();
        assertNull(XMLTools.getUniqueElement(animalDoc.getDocumentElement(), "platypus", false));

        // variation
        assertNull(XMLTools.getUniqueElementNullable(animalDoc.getDocumentElement(), "platypus"));
    }

    /**
     * Test unique descendant element - too many cats.
     * 
     * @throws PurpleException in case of errors
     */
    @Test(expected = PurpleException.class)
    public void getUniqueElementTooManyNullTest() throws PurpleException {
        Document animalDoc = getSampleAnimalsDocument();
        XMLTools.getUniqueElement(animalDoc.getDocumentElement(), "cat", false);
    }

    /**
     * Test unique mandatory child element.
     * 
     * @throws PurpleException in case of errors
     */
    @Test
    public void getUniqueChildElementTest() throws PurpleException {
        Document animalDoc = getSampleAnimalsDocument();
        Element lonelyogElement = XMLTools.getUniqueChildElement(animalDoc.getDocumentElement(),
                "dog", true);
        assertNotNull(lonelyogElement);
        assertEquals("Doggy", lonelyogElement.getAttribute("name"));
    }

    /**
     * Test unique mandatory child element - missing.
     * 
     * @throws PurpleException in case of errors
     */
    @Test(expected = PurpleException.class)
    public void getUniqueChildElementMissingMandatoryTest() throws PurpleException {
        Document animalDoc = getSampleAnimalsDocument();
        XMLTools.getUniqueChildElement(animalDoc.getDocumentElement(), "platypus", true);
    }

    /**
     * Test unique optional child element - missing.
     * 
     * @throws PurpleException in case of errors
     */
    @Test
    public void getUniqueChildElementMissingNullTest() throws PurpleException {
        Document animalDoc = getSampleAnimalsDocument();
        assertNull(
                XMLTools.getUniqueChildElement(animalDoc.getDocumentElement(), "platypus", false));

        // variation
        assertNull(
                XMLTools.getUniqueChildElementNullable(animalDoc.getDocumentElement(), "platypus"));
    }

    /**
     * Test unique child element - too many cats.
     * 
     * @throws PurpleException in case of errors
     */
    @Test(expected = PurpleException.class)
    public void getUniqueChildElementTooManyNullTest() throws PurpleException {
        Document animalDoc = getSampleAnimalsDocument();
        XMLTools.getUniqueChildElement(animalDoc.getDocumentElement(), "cat", false);
    }

    /**
     * Test is text leaf true.
     * 
     * @throws PurpleException in case of errors
     */
    @Test
    public void isTextLeafTrueTest() throws PurpleException {
        Document animalDoc = getSampleAnimalsDocument();
        assertTrue(XMLTools.hasTextContents(
                XMLTools.getUniqueElement(animalDoc.getDocumentElement(), "snail", true)));
    }

    /**
     * Test is text leaf false.
     * 
     * @throws PurpleException in case of errors
     */
    @Test
    public void hasTextContentsFalseTest() throws PurpleException {
        Document animalDoc = getSampleAnimalsDocument();
        assertFalse(XMLTools.hasTextContents(
                XMLTools.getUniqueElement(animalDoc.getDocumentElement(), "dog", true)));
    }

    /**
     * Test integer attribute - exists.
     * 
     * @throws PurpleException in case of errors
     */
    @Test
    public void getIntegerAttributeValueExistsTest() throws PurpleException {
        Document animalDoc = getSampleAnimalsDocument();

        Element dogElement = XMLTools.getUniqueChildElement(animalDoc.getDocumentElement(), "dog",
                true);

        assertEquals(5, XMLTools.getIntegerAttributeValue(dogElement, "age", 0));
    }

    /**
     * Test integer attribute - missing.
     * 
     * @throws PurpleException in case of errors
     */
    @Test
    public void getIntegerAttributeValueMissingTest() throws PurpleException {
        Document animalDoc = getSampleAnimalsDocument();

        Element snailElement = XMLTools.getUniqueElement(animalDoc.getDocumentElement(), "snail",
                true);

        assertEquals(125, XMLTools.getIntegerAttributeValue(snailElement, "age", 125));
    }

    /**
     * Test writing of document.
     * 
     * @throws PurpleException in case of errors
     * @throws UnsupportedEncodingException in case of errors
     */
    @Test
    public void writeDocumentTest() throws PurpleException, UnsupportedEncodingException {
        Document animalDoc = getSampleAnimalsDocument();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        XMLTools.writeDocument(animalDoc, outputStream, XMLTools.DEFAULT_UTF8_OUTPUT);
        String xmlResult = new String(outputStream.toByteArray(), "UTF-8");

        StringBuilder expectedXml = new StringBuilder();

        expectedXml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
        expectedXml.append("<animals>Here are the animals");
        expectedXml.append("<dog age=\"5\" name=\"Doggy\"/>");
        expectedXml.append("<cat name=\"Kittie\"/>");
        expectedXml.append("<cat name=\"Kitty\">");
        expectedXml.append("<cat name=\"BabyCat\"/>");
        expectedXml.append("</cat><snails>");
        expectedXml.append("<snail name=\"Molly\">");
        expectedXml.append("This is the story of Molly, a valorous snail.");
        expectedXml.append("</snail>");
        expectedXml.append("</snails>");
        expectedXml.append("</animals>");

        assertEquals(expectedXml.toString(), xmlResult);

    }

    /**
     * @return document sample, fun with animals (no real animals will be harmed
     *         in these tests).
     * 
     * 
     * @throws PurpleException in case of errors
     */
    private Document getSampleAnimalsDocument() throws PurpleException {
        Document doc = XMLTools.createDocument();
        Element animalsElement = doc.createElement("animals");
        doc.appendChild(animalsElement);

        Text introduction = doc.createTextNode("Here are the animals");
        animalsElement.appendChild(introduction);

        Element dogElement = doc.createElement("dog");
        dogElement.setAttribute("name", "Doggy");
        dogElement.setAttribute("age", "5");
        animalsElement.appendChild(dogElement);

        Element cat1Element = doc.createElement("cat");
        cat1Element.setAttribute("name", "Kittie");
        animalsElement.appendChild(cat1Element);

        Element cat2Element = doc.createElement("cat");
        cat2Element.setAttribute("name", "Kitty");
        animalsElement.appendChild(cat2Element);

        Element babyCatElement = doc.createElement("cat");
        babyCatElement.setAttribute("name", "BabyCat");
        cat2Element.appendChild(babyCatElement);

        Element snailsElement = doc.createElement("snails");
        animalsElement.appendChild(snailsElement);

        Element snailElement = doc.createElement("snail");
        snailElement.setAttribute("name", "Molly");
        snailsElement.appendChild(snailElement);

        Text mollyStory = doc.createTextNode("This is the story of Molly, a valorous snail.");
        snailElement.appendChild(mollyStory);

        return doc;
    }

}
