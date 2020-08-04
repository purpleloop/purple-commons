package purpleloop.commons.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of the map of lists based on a HashMap of ArrayLists.
 * 
 * @param <K> type of the keys
 * @param <V> type of the elements
 */
public class MapListImpl<K, V> implements MapList<K, V>, Iterable<V> {

    /** The internal map. */
    private Map<K, List<V>> map;

    /** Creates an empty table of lists. */
    public MapListImpl() {
        this.map = new HashMap<K, List<V>>();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public Set<K> keySet() {
        return this.map.keySet();
    }

    @Override
    public int keySize() {
        return map.size();
    }

    @Override
    public int size() {
        int size = 0;
        for (List<V> valueList : map.values()) {
            size += valueList.size();
        }

        return size;
    }

    @Override
    public List<V> allValues() {
        List<V> fullList = new ArrayList<V>();
        for (List<V> valueList : map.values()) {
            fullList.addAll(valueList);
        }

        return fullList;
    }

    @Override
    public boolean contains(Object o) {
        return allValues().contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return allValues().containsAll(c);
    }

    @Override
    public List<V> get(K key) {

        List<V> valueList = map.get(key);

        if (valueList == null) {
            valueList = Collections.emptyList();
        }

        return valueList;
    }

    @Override
    public List<V> getNullable(K key) {
        return map.get(key);
    }

    @Override
    public Iterator<V> iterator() {
        return allValues().iterator();
    }

    @Override
    public Object[] toArray() {
        return allValues().toArray();
    }

    @Override
    public V[] toArray(V[] a) {
        return allValues().toArray(a);
    }

    @Override
    public void putValue(K key, V v) {
        initListForKey(key).add(v);
    }

    @Override
    public void putAllValues(K key, Collection<V> values) {
        initListForKey(key).addAll(values);
    }

    /**
     * Initializes an empty list for the given key.
     * 
     * @param key the key to initialize
     * @return the new list mapped to the key
     */
    private List<V> initListForKey(K key) {
        List<V> currentValuesForKey = this.map.get(key);
        if (currentValuesForKey == null) {
            currentValuesForKey = new ArrayList<V>();
            this.map.put(key, currentValuesForKey);
        }
        return currentValuesForKey;
    }

    @Override
    public void clear() {
        this.map.clear();
    }

    @Override
    public void removeKey(K key) {
        this.map.remove(key);
    }

    @Override
    public boolean removeAll(Object o) {

        boolean removed = false;
        ArrayList<K> keysToRemove = new ArrayList<K>();

        for (K key : map.keySet()) {
            List<V> list = map.get(key);
            if (list.remove(o)) {
                removed = true;
            }

            // Marks empty list to be removed
            if (list.isEmpty()) {
                keysToRemove.add(key);
            }
        }

        // Cleanup empty lists
        for (K key : keysToRemove) {
            map.remove(key);
        }

        return removed;
    }

}
