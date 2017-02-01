/**
 * @author Alejandro
 * @date 3/25/2015
 */

public class AvlSet<K extends Comparable<? super K>> {

    AvlTree<K> tree = new AvlTree<>();

    /**
     * Add a new element into the set Duplicates are ignored
     * @param e is the String to insert
     */
    public void add(K e) {
        tree.insert(e);
    }

    /**
     * Check if an element is in the set
     * @return true if the set contains the String
     * @param e is the String to look for
     */
    public boolean contains(K e) {
        return tree.contains(e);
    }

    /**
     * Check if the set is empty
     * @return true if empty
     */
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    public String printTree() {
        return tree.toString();
    }

}
