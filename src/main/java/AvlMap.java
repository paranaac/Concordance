/**
 * @author Alejandro
 * @date 3/25/2015
 */

public class AvlMap<K extends Comparable<? super K>, V> {

    AvlTreeMap<K, V> tree = new AvlTreeMap<>();

    /**
     * @param key is the item to look for
     * @return true if the key was found
     */
    public boolean containsKey(K key) {
        return tree.contains(key);
    }

    /**
     * Check if the set is empty
     * @return true if empty
     */
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    /**
     * @param key is the item to search
     * @return the value at a given key
     */
    public V get(K key) {
        return tree.getValue(key);
    }

    /**
     * Put a key and value pair in the map
     * @param key is the key to insert
     * @param value is the value to insert
     */
    public void put(K key, V value) {
        tree.insert(key, value);
    }

    public void printMap() {
        tree.printTree();
    }
}
