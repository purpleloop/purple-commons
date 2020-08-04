package purpleloop.commons.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Defines a generic map of lists.
 *
 * @param <K> type of the keys
 * @param <V> type of the elements
 */
public interface MapList<K, V> {

    /** @return The key set of the map */
    Set<K> keySet();

    /** @return number of keys, this can be different from size */
    int keySize();

    /** @return true if the map is empty, false if not */
    boolean isEmpty();

    /**
     * @return number of elements in all the lists, this can be different from
     *         keySize
     */
    int size();

    /**
     * @param key to access
     * @return the list associated to the given key
     */
    List<V> get(K key);

    /**
     * @param key to access
     * @return the list associated to the given key or null if the key is not mapped
     */
    List<V> getNullable(K key);
    
    /** @return All the values contained in all the lists (the order depends on the implementation). */
    List<V> allValues();

    /**
     * Adds a value to the list associated to the give key.
     * 
     * @param key the key to access
     * @param v the value to add
     */
    void putValue(K key, V v);

    /**
     * Adds a collection of values of a given key.
     * 
     * @param key the key to access
     * @param values the value to add
     * 
     */
    void putAllValues(K key, Collection<V> values);

    /** Removes all the entries of the map list. */
    void clear();

    /**
     * Removes all values associated to a given key.
     * 
     * @param key the key to remove
     */
    void removeKey(K key);

    /**
     * Removes all occurrences of the given object in the lists.
     * 
     * @param o object to remove
     * @return true there was at last one removal, false otherwise
     */
    boolean removeAll(Object o);

    /**
     * Tests if the map list contains the given object.
     * 
     * @param o object to find
     * @return true there was at last one occurence, false otherwise
     */
    boolean contains(Object o);

    /**
     * Tests if the map list contains all the given objects.
     * 
     * @param c collection of objects to find
     * @return true there was at last one occurrence of each object, false
     *         otherwise
     */
    boolean containsAll(Collection<?> c);

    /** @return iterator on each value contained in the lists (the order depends on the implementation) */
    Iterator<V> iterator();

    /** @return an array of each object contained in the lists  (the order depends on the implementation) */
    Object[] toArray();

    /**
     * @param array the array to fill, of the given type
     * @return an array of each object contained in the lists with the given
     *         type (the given array if it is enough large, otherwise a new
     *         array is allocated (the order depends on the implementation).
     */
    V[] toArray(V[] array);

}
