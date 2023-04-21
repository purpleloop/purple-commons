package io.github.purpleloop.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

/** Tests for MapList. */
class MapListTest {

    /** Test MapList isEmpty. */
    @Test
    void testEmpty() {

        MapListImpl<Boolean, String> sentences = new MapListImpl<Boolean, String>();
        assertTrue(sentences.isEmpty());
        assertEquals(0, sentences.keySize());
        assertEquals(0, sentences.size());

        sentences.putValue(true, "aa");
        assertFalse(sentences.isEmpty());
        assertEquals(1, sentences.keySize());
        assertEquals(1, sentences.size());

        sentences.putValue(true, "cc");
        assertFalse(sentences.isEmpty());
        assertEquals(1, sentences.keySize());
        assertEquals(2, sentences.size());
    }

    /** Test key set. */
    @Test
    void testKeySet() {
        MapListImpl<Boolean, String> sentences = new MapListImpl<Boolean, String>();
        sentences.putValue(true, "aa");
        sentences.putValue(false, "bb");
        sentences.putValue(true, "cc");

        assertEquals(2, sentences.keySize());
        Set<Boolean> keys = sentences.keySet();
        assertTrue(keys.contains(true));
        assertTrue(keys.contains(false));
    }

    /** Test maplist contains. */
    @Test
    void testContains() {

        MapListImpl<Boolean, String> sentences = new MapListImpl<Boolean, String>();
        sentences.putValue(true, "aa");
        assertTrue(sentences.contains("aa"));
        assertFalse(sentences.contains("bb"));

        assertTrue(sentences.containsAll(Arrays.asList("aa")));
        assertFalse(sentences.containsAll(Arrays.asList("aa", "bb")));

        sentences.putValue(true, "bb");

        assertTrue(sentences.contains("aa"));
        assertTrue(sentences.contains("bb"));

        assertTrue(sentences.containsAll(Arrays.asList("aa")));
        assertTrue(sentences.containsAll(Arrays.asList("aa", "bb")));

        sentences.putValue(false, "cc");
        assertTrue(sentences.containsAll(Arrays.asList("cc")));
        assertTrue(sentences.containsAll(Arrays.asList("aa", "bb", "cc")));

    }

    /** Test values for a given key. */
    @Test
    void testValuesForKey() {

        MapListImpl<Boolean, String> sentences = new MapListImpl<Boolean, String>();

        assertTrue(sentences.get(true).isEmpty());
        assertNull(sentences.getNullable(true));

        sentences.putValue(true, "aa");
        sentences.putValue(false, "bb");
        sentences.putValue(true, "cc");
        List<String> trueOnes = sentences.get(true);
        assertNotNull(trueOnes);
        assertEquals(2, trueOnes.size());
        assertEquals("aa", trueOnes.get(0));
        assertEquals("cc", trueOnes.get(1));

        List<String> falseOnes = sentences.get(false);
        assertNotNull(falseOnes);
        assertEquals(1, falseOnes.size());
        assertEquals("bb", falseOnes.get(0));
    }

    /** Test clearing map. */
    @Test
    void testClear() {
        MapListImpl<Boolean, String> sentences = new MapListImpl<Boolean, String>();
        sentences.putValue(true, "aa");
        sentences.putValue(false, "bb");
        sentences.putValue(true, "cc");
        assertFalse(sentences.isEmpty());
        sentences.clear();
        assertTrue(sentences.isEmpty());
    }

    /** Test iterator. */
    @Test
    void testIterator() {

        MapListImpl<Integer, String> sentences = new MapListImpl<Integer, String>();
        sentences.putValue(0, "aa");
        sentences.putValue(1, "bb");
        sentences.putValue(0, "cc");

        Iterator<String> iterator = sentences.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("aa", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("cc", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("bb", iterator.next());
        assertFalse(iterator.hasNext());
    }

    /** Test toArray. */
    @Test
    void testToArray() {

        MapListImpl<Integer, String> sentences = new MapListImpl<Integer, String>();
        sentences.putAllValues(0, Arrays.asList("aa", "cc"));
        sentences.putValue(1, "bb");

        Object[] array = sentences.toArray();
        assertNotNull(array);
        assertEquals(3, array.length);
        assertEquals("aa", array[0]);
        assertEquals("cc", array[1]);
        assertEquals("bb", array[2]);

        String[] arrayString = sentences.toArray(new String[2]);
        assertNotNull(arrayString);
        assertEquals(3, arrayString.length);
        assertEquals("aa", arrayString[0]);
        assertEquals("cc", arrayString[1]);
        assertEquals("bb", arrayString[2]);

    }

    /** Test remove. */
    @Test
    void testRemoves() {

        MapListImpl<Integer, String> sentences = new MapListImpl<Integer, String>();
        sentences.putAllValues(0, Arrays.asList("aa", "cc"));
        sentences.putAllValues(1, Arrays.asList("bb", "cc", "dd"));
        sentences.putAllValues(2, Arrays.asList("cc", "dd", "ee"));

        sentences.removeKey(1);
        assertEquals(2, sentences.keySize());
        assertTrue(sentences.keySet().contains(0));
        assertFalse(sentences.keySet().contains(1));
        assertTrue(sentences.keySet().contains(2));

        assertTrue(sentences.removeAll("cc"));
        assertFalse(sentences.removeAll("ff"));

        assertTrue(sentences.containsAll(Arrays.asList("aa", "dd", "ee")));
        assertFalse(sentences.containsAll(Arrays.asList("cc", "ff")));

        sentences.removeAll("aa");
        assertFalse(sentences.keySet().contains(0));
        assertEquals(1, sentences.keySize());
    }

}
