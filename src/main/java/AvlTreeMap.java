
/**
 * Implements an AVL tree.
 * @author Mark Allen Weiss
 * @modified Alejandro
 * @date 3/25/2015
 */

public class AvlTreeMap<K extends Comparable<? super K>, V> {

    /**
     * Construct the tree.
     */
    public AvlTreeMap() {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     *
     * @param x the item to insert.
     */
    public void insert(K x, V v) {
        root = insert(x, v, root);
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     *
     * @param x the item to remove.
     */
    public void remove(K x) {
        root = remove(x, root);
    }

    /**
     * Internal method to remove from a subtree.
     *
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private AvlNode<K, V> remove(K x, AvlNode<K, V> t) {
        if (t == null) {
            return t;   // Item not found; do nothing
        }
        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = remove(x, t.left);
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) // Two children
        {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }
        return balance(t);
    }

    /**
     * Find the smallest item in the tree.
     *
     * @return smallest item or null if empty.
     */
    public K findMin() {
        if (isEmpty()) {
            throw new UnderflowException();
        }
        return findMin(root).element;
    }

    /**
     * Find the largest item in the tree.
     *
     * @return the largest item of null if empty.
     */
    public K findMax() {
        if (isEmpty()) {
            throw new UnderflowException();
        }
        return findMax(root).element;
    }

    /**
     * Find an item in the tree.
     *
     * @param x the item to search for.
     * @return true if x is found.
     */
    public boolean contains(K x) {
        return contains(x, root);
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree() {
        if (isEmpty()) {
            System.out.println("Empty tree");
        } else {
            printTree(root);
        }
    }

    private static final int ALLOWED_IMBALANCE = 1;

    // Assume t is either balanced or within one of being balanced
    private AvlNode<K, V> balance(AvlNode<K, V> t) {
        if (t == null) {
            return t;
        }

        if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE) {
            if (height(t.left.left) >= height(t.left.right)) {
                t = rotateWithLeftChild(t);
            } else {
                t = doubleWithLeftChild(t);
            }
        } else if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE) {
            if (height(t.right.right) >= height(t.right.left)) {
                t = rotateWithRightChild(t);
            } else {
                t = doubleWithRightChild(t);
            }
        }

        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    public void checkBalance() {
        checkBalance(root);
    }

    private int checkBalance(AvlNode<K, V> t) {
        if (t == null) {
            return -1;
        }

        if (t != null) {
            int hl = checkBalance(t.left);
            int hr = checkBalance(t.right);
            if (Math.abs(height(t.left) - height(t.right)) > 1
                    || height(t.left) != hl || height(t.right) != hr) {
                System.out.println("OOPS!!");
            }
        }

        return height(t);
    }

    /**
     * Internal method to insert into a subtree.
     *
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private AvlNode<K, V> insert(K x, V v, AvlNode<K, V> t) {
        if (t == null) {
            return new AvlNode<>(x, v, null, null);
        }

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = insert(x, v, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, v, t.right);
        } else
            ;  // Duplicate; do nothing
        return balance(t);
    }

    /**
     * Internal method to find the smallest item in a subtree.
     *
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    private AvlNode<K, V> findMin(AvlNode<K, V> t) {
        if (t == null) {
            return t;
        }

        while (t.left != null) {
            t = t.left;
        }
        return t;
    }

    /**
     * Internal method to find the largest item in a subtree.
     *
     * @param t the node that roots the tree.
     * @return node containing the largest item.
     */
    private AvlNode<K, V> findMax(AvlNode<K, V> t) {
        if (t == null) {
            return t;
        }

        while (t.right != null) {
            t = t.right;
        }
        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     *
     * @param x is item to search for.
     * @param t the node that roots the tree.
     * @return true if x is found in subtree.
     */
    private boolean contains(K x, AvlNode<K, V> t) {
        while (t != null) {
            int compareResult = x.compareTo(t.element);

            if (compareResult < 0) {
                t = t.left;
            } else if (compareResult > 0) {
                t = t.right;
            } else {
                return true;    // Match
            }
        }

        return false;   // No match
    }

    public V getValue(K x) {
        return getValue(x, root);
    }

    private V getValue(K x, AvlNode<K, V> t) {
        while (t != null) {
            int compareResult = x.compareTo(t.element);

            if (compareResult < 0) {
                t = t.left;
            } else if (compareResult > 0) {
                t = t.right;
            } else {
                return t.v;
            }
        }
        return null;   // No match
    }

    /**
     * Internal method to print a subtree in sorted order.
     *
     * @param t the node that roots the tree.
     */
    private void printTree(AvlNode<K, V> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element + " " + t.v);
            printTree(t.right);
        }
    }

    /**
     * Return the height of node t, or -1, if null.
     */
    private int height(AvlNode<K, V> t) {
        return t == null ? -1 : t.height;
    }

    /**
     * Rotate binary tree node with left child. For AVL trees, this is a single rotation for case 1. Update heights, then return new root.
     */
    private AvlNode<K, V> rotateWithLeftChild(AvlNode<K, V> k2) {
        AvlNode<K, V> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    /**
     * Rotate binary tree node with right child. For AVL trees, this is a single rotation for case 4. Update heights, then return new root.
     */
    private AvlNode<K, V> rotateWithRightChild(AvlNode<K, V> k1) {
        AvlNode<K, V> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.right), k1.height) + 1;
        return k2;
    }

    /**
     * Double rotate binary tree node: first left child with its right child; then node k3 with new left child. For AVL trees, this is a double rotation for case 2. Update heights, then return new root.
     */
    private AvlNode<K, V> doubleWithLeftChild(AvlNode<K, V> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    /**
     * Double rotate binary tree node: first right child with its left child; then node k1 with new right child. For AVL trees, this is a double rotation for case 3. Update heights, then return new root.
     */
    private AvlNode<K, V> doubleWithRightChild(AvlNode<K, V> k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    private static class AvlNode<K, V> {

        // Constructors

        AvlNode(K theElement, V value) {
            this(theElement, value, null, null);
        }

        AvlNode(K theElement, V value, AvlNode<K, V> lt, AvlNode<K, V> rt) {
            element = theElement;
            left = lt;
            right = rt;
            height = 0;
            v = value;
        }

        K element;      // The key in the node
        V v;            // The value in the node
        AvlNode<K, V> left;         // Left child
        AvlNode<K, V> right;        // Right child
        int height;       // Height
    }

    /**
     * The tree root.
     */
    private AvlNode<K, V> root;
}
