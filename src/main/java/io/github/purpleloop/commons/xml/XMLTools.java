package io.github.purpleloop.commons.xml;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import io.github.purpleloop.commons.exception.PurpleException;

/**
 * Utility class for XML.
 * 
 * Named tools because XmlUtils is frequent... So we did'nt follow the naming of
 * other class (*utils) to prevent name collisions.
 */
public final class XMLTools {

	/** Class logger. */
	private static final Log LOG = LogFactory.getLog(XMLTools.class);

	/** Parameters for XML output. */
	public interface XMLOutputParameters {

		/** @return document output encoding */
		String getEncoding();

		/** @return name of the DTD */
		String getDocumentTypeDefinition();

		/** @return must indent the output */
		boolean isIndent();

	}

	/**
	 * Default XML output parameters (ISO-8859-15, no DTD, no indentation).
	 */
	public static final XMLOutputParameters DEFAULT_ISO8859_OUTPUT = new XMLOutputParameters() {

		public String getEncoding() {
			return "ISO-8859-15";
		}

		public String getDocumentTypeDefinition() {
			return null;
		}

		@Override
		public boolean isIndent() {
			return false;
		}

	};

	/**
	 * Default XML output parameters (UTF-8, no DTD, no indentation).
	 */
	public static final XMLOutputParameters DEFAULT_UTF8_OUTPUT = new XMLOutputParameters() {

		public String getEncoding() {
			return "UTF-8";
		}

		public String getDocumentTypeDefinition() {
			return null;
		}

		@Override
		public boolean isIndent() {
			return false;
		}

	};

	/** Private constructor. */
	private XMLTools() {
	}

	/**
	 * Parses an XML input stream returns the document.
	 * 
	 * @param xmlIs XML input stream
	 * @return the XML document
	 * @throws PurpleException in case of errors
	 */
	public static Document getDocument(InputStream xmlIs) throws PurpleException {

		if (xmlIs == null) {
			return null;
		}

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			return dbf.newDocumentBuilder().parse(xmlIs);

		} catch (ParserConfigurationException e) {
			LOG.error("XML parser configuration problem : ", e);
			throw new PurpleException("An error occured while configuring the XML parser.", e);
		} catch (SAXException e) {
			LOG.error("XML syntax problem : ", e);
			throw new PurpleException("An error occured while parsing the XML document.", e);
		} catch (IOException e) {
			LOG.error("Error while reading the XML stream : ", e);
			throw new PurpleException("An I/O error occured while reading the XML stream.", e);
		}

	}

	/**
	 * Parses an XML file returns the document.
	 * 
	 * @param xmlFile XML input file
	 * @return the XML document
	 * @throws PurpleException in case of errors
	 */
	public static Document getDocument(File xmlFile) throws PurpleException {

		if (xmlFile == null) {
			return null;
		}

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			return dbf.newDocumentBuilder().parse(xmlFile);

		} catch (ParserConfigurationException e) {
			LOG.error("XML parser configuration problem : ", e);
			throw new PurpleException("An error occured while configuring the XML parser.", e);
		} catch (SAXException e) {
			LOG.error("XML syntax problem : ", e);
			throw new PurpleException("An error occured while parsing the XML document.", e);
		} catch (IOException e) {
			LOG.error("Error while reading the XML file : ", e);
			throw new PurpleException("An I/O error occured while reading the XML file.", e);
		}

	}

	/**
	 * Lists all elements of the document having the given tag.
	 * 
	 * @param document document
	 * @param tagName  Tag name
	 * @return list of all elements found
	 */
	public static List<Element> getElementsByTagName(Document document, String tagName) {

		NodeList nodeList = document.getElementsByTagName(tagName);

		List<Element> allElementsWithTag = new ArrayList<>();
		int length = nodeList.getLength();
		for (int i = 0; i < length; i++) {
			allElementsWithTag.add((Element) nodeList.item(i));
		}

		return allElementsWithTag;
	}

	/**
	 * Lists DOM elements of the given tag that are direct children of the given
	 * element.
	 * 
	 * @param parentElement parent element
	 * @param tagName       tag name of the elements to be listed
	 * @return list of elements with the tag, children of the parent element
	 */
	public static List<Element> getChildElements(Element parentElement, String tagName) {

		List<Element> foundChildElements = new ArrayList<>();
		NodeList childNodes = parentElement.getChildNodes();
		Node childNode;
		Element childElement;

		for (int i = 0; i < childNodes.getLength(); i++) {
			childNode = childNodes.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				childElement = (Element) childNode;
				if (childElement.getTagName().equals(tagName)) {
					foundChildElements.add(childElement);
				}

			}
		}

		return foundChildElements;
	}

	/**
	 * Lists all DOM elements that are direct children of the given element.
	 * 
	 * @param parentElement parent element
	 * @return list of all elements that are children of the parent element
	 */
	public static List<Element> getChildElements(Element parentElement) {

		List<Element> elements = new ArrayList<>();

		NodeList childNodes = parentElement.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node childNode = childNodes.item(i);

			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				elements.add((Element) childNode);
			}
		}

		return elements;

	}

	/**
	 * Get a stream of children elements of a parent element.
	 * 
	 * @param parentElement the parent element
	 * @return a stream of children elements
	 * 
	 */
	public static Stream<Element> getChildElementsStream(Element parentElement) {
		return getChildElements(parentElement).stream();
	}

	/**
	 * Get a children element with the requested tag (direct or not) of the given
	 * element.
	 * 
	 * @param parentElement parent element
	 * @param tagName       tag name of the element to be obtained
	 * @return optional single children (direct or not) element with this tag
	 * @throws PurpleException in case of errors
	 */
	public static Optional<Element> getUniqueElement(Element parentElement, String tagName) throws PurpleException {
		NodeList nodeList = parentElement.getElementsByTagName(tagName);
		int length = nodeList.getLength();
		if (length == 0) {
			return Optional.empty();
		} else if (length == 1) {
			return Optional.of((Element) nodeList.item(0));
		} else {
			throw new PurpleException("More than one élément found with tag " + tagName + ".");
		}
	}

	/**
	 * Get a children element with the requested tag (direct or not) of the given
	 * element.
	 * 
	 * @param parentElement parent element
	 * @param tagName       tag name of the element to be obtained
	 * @param required      is the element mandatory ? (else null can be returned if
	 *                      the element is missing)
	 * @return single children (direct or not) element with this tag or null if is
	 *         could'nt be found
	 * @throws PurpleException in case of errors
	 * @deprecated use {@link #getUniqueElement(Element, String)} with no
	 *             requirement which returns an optional
	 */
	@Deprecated
	public static Element getUniqueElement(Element parentElement, String tagName, boolean required)
			throws PurpleException {

		Optional<Element> elementOptional = getUniqueElement(parentElement, tagName);
		if (elementOptional.isEmpty()) {
			if (required) {
				throw new PurpleException("No element with tag " + tagName + " found");
			} else {
				return null;
			}
		}

		return elementOptional.get();
	}

	/**
	 * Get a children element with the requested tag (direct or not) of the given
	 * element.
	 * 
	 * @param parentElement parent element
	 * @param tagName       tag name of the element to be obtained
	 * @return single children element (direct or not) with this tag, or null if is
	 *         could'nt be found
	 * @throws PurpleException in case of errors
	 * 
	 * @deprecated use {@link #getUniqueElement(Element, String)} which returns an
	 *             optional
	 */
	@Deprecated
	public static Element getUniqueElementNullable(Element parentElement, String tagName) throws PurpleException {
		return getUniqueElement(parentElement, tagName, false);
	}

	/**
	 * Get a children element with the requested tag (direct) of the given element.
	 * 
	 * @param parentElement parent element
	 * @param tagName       tag name of the element to be obtained
	 * @return optional single children element with this tag
	 * @throws PurpleException in case of errors
	 */
	@Deprecated
	public static Optional<Element> getUniqueChildElement(Element parentElement, String tagName)
			throws PurpleException {
		List<Element> childElements = getChildElements(parentElement, tagName);
		int length = childElements.size();
		if (length == 0) {
			return Optional.empty();
		} else if (length == 1) {
			return Optional.of(childElements.get(0));
		} else {
			throw new PurpleException("More than one élément found with tag " + tagName + ".");
		}

	}

	/**
	 * Get a children element with the requested tag (direct) of the given element.
	 * 
	 * @param parentElement parent element
	 * @param tagName       tag name of the element to be obtained
	 * @param required      is the element mandatory ? (else null can be returned if
	 *                      the element is missing)
	 * @return single children element with this tag or null if is could'nt be found
	 * @throws PurpleException in case of errors
	 * @deprecated use {@link #getUniqueChildElement(Element, String)} with no
	 *             requirement which returns an optional
	 */
	@Deprecated
	public static Element getUniqueChildElement(Element parentElement, String tagName, boolean required)
			throws PurpleException {
		Optional<Element> elementOptional = getUniqueChildElement(parentElement, tagName);
		if (elementOptional.isEmpty()) {
			if (required) {
				throw new PurpleException("No element with tag " + tagName + " found");
			} else {
				return null;
			}
		} else {
			return elementOptional.get();
		}

	}

	/**
	 * Get a children element with the requested tag (direct) of the given element.
	 * 
	 * @param parentElement parent element
	 * @param tagName       tag name of the element to be obtained
	 * @return single children element with this tag or null if is could'nt be found
	 * @throws PurpleException in case of errors
	 * @deprecated use getUniqueChildElement with no requirement which returns an
	 *             optional
	 */
	@Deprecated
	public static Element getUniqueChildElementNullable(Element parentElement, String tagName) throws PurpleException {
		return getUniqueChildElement(parentElement, tagName, false);
	}

	/**
	 * Tests whether the given element has a simple text node as children.
	 * 
	 * @param element element to test
	 * @return true if the element contains just a text node, false otherwise
	 */
	public static boolean hasTextContents(Element element) {
		NodeList childNodes = element.getChildNodes();
		if (childNodes.getLength() != 1) {
			return false;
		}

		Node childNode = childNodes.item(0);
		int childNodeType = childNode.getNodeType();

		return childNodeType == Node.TEXT_NODE;
	}

	/**
	 * Crates a new XML document (DOM).
	 * 
	 * @return the created document
	 * @throws PurpleException in case of problem
	 */
	public static Document createDocument() throws PurpleException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			return dbf.newDocumentBuilder().newDocument();

		} catch (ParserConfigurationException e) {
			throw new PurpleException("Failed to configure XML parser for document building", e);
		}
	}

	/**
	 * Logs all attributes of an element in debug mode.
	 * 
	 * @param element Element to trace
	 */
	public static void dumpAttributes(Element element) {
		NamedNodeMap namedNodeMap = element.getAttributes();
		for (int i = 0; i < namedNodeMap.getLength(); i++) {
			Attr attribute = (Attr) namedNodeMap.item(i);
			LOG.debug("- Attribute '" + attribute.getName() + "' = " + attribute.getValue());
		}
	}

	/**
	 * Reads an integer attribute value for an element, with a default if the
	 * attribute is missing.
	 * 
	 * @param element       XML element
	 * @param attributeName name of the attribute
	 * @param defaultValue  default value if the attribute is missing
	 * @return value for the attribute of default if the value is missing
	 */
	public static int getIntegerAttributeValue(Element element, String attributeName, int defaultValue) {
		String attributeValueStr = element.getAttribute(attributeName);

		if (attributeValueStr == null || attributeValueStr.equals("")) {
			return defaultValue;
		}
		return Integer.parseInt(attributeValueStr);
	}

	/**
	 * Writes an XML document to the given file.
	 * 
	 * Uses the default output parameters (link
	 * {XMLTools{@link #DEFAULT_ISO8859_OUTPUT}).
	 * 
	 * @param documentSource document to write
	 * @param destination    file where to write
	 * @throws PurpleException in case of problems
	 */
	public static void writeXmlFile(Document documentSource, File destination) throws PurpleException {
		writeXmlFile(documentSource, destination, DEFAULT_ISO8859_OUTPUT);
	}

	/**
	 * Writes an XML document to the given file.
	 * 
	 * @param documentSource document to write
	 * @param xmlParams      additional XML parameters
	 * @param destination    file where to write
	 * @throws PurpleException in case of errors
	 */
	public static void writeXmlFile(Document documentSource, File destination, XMLOutputParameters xmlParams)
			throws PurpleException {

		try (OutputStream oStream = new BufferedOutputStream(new FileOutputStream(destination));) {
			writeDocument(documentSource, oStream, xmlParams);
			oStream.flush();
		} catch (FileNotFoundException e) {
			LOG.error("The target file can't be created.");
			throw new PurpleException("Unable to create the target file " + destination.getAbsolutePath(), e);

		} catch (IOException e) {
			LOG.error("An error occured while writing to the file.");
			throw new PurpleException("Unable to write to the target file " + destination.getAbsolutePath(), e);
		}

	}

	/**
	 * Writes an XML document to the given stream.
	 * 
	 * Uses the default output parameters (link
	 * {XMLTools{@link #DEFAULT_ISO8859_OUTPUT}).
	 * 
	 * @param documentSource document to write
	 * @param outputStream   output stream where to write
	 * @throws PurpleException in case of problems
	 */
	public static void writeDocument(Document documentSource, OutputStream outputStream) throws PurpleException {
		writeDocument(documentSource, outputStream, DEFAULT_ISO8859_OUTPUT);
	}

	/**
	 * Writes an XML document to the given stream.
	 * 
	 * @param documentSource document to write
	 * @param outputStream   stream where to write
	 * @param xmlParams      additional XML parameters
	 * @throws PurpleException in case of problem
	 */
	public static void writeDocument(Document documentSource, OutputStream outputStream, XMLOutputParameters xmlParams)
			throws PurpleException {

		try {

			String xmlVersion = "1.0";
			String xmlEncoding = xmlParams.getEncoding();
			String documentType = xmlParams.getDocumentTypeDefinition();

			documentSource.setXmlVersion(xmlVersion);

			// Use a Transformer for output
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, xmlEncoding);

			if (xmlParams.isIndent()) {
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			}

			if (documentType != null) {
				transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, documentType);
			}

			DOMSource source = new DOMSource(documentSource);
			StreamResult result = new StreamResult(outputStream);
			transformer.transform(source, result);

		} catch (TransformerConfigurationException e) {
			throw new PurpleException("Problem during the configuration of the XML transformation", e);
		} catch (TransformerException e) {
			throw new PurpleException("Problem during the XML transformation", e);
		}

	}

}
