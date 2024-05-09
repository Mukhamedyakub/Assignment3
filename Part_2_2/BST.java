package Part_2_2;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * A generic Binary Search Tree (BST) with enhanced functionality.
 * This class supports operations to insert, find, delete, and iterate over key-value pairs in in-order.
 * Additionally, it maintains the size of the tree.
 *
 * @param <K> the type of keys maintained by this tree, must extend Comparable
 * @param <V> the type of mapped values
 */
public class BST<K extends Comparable<K>, V> implements Iterable<BST.Entry<K, V>> {
    private Node root; // Root of the binary search tree

    /**
     * Represents a node within the binary search tree.
     */
    private class Node {
        private K key;   // The key of the node
        private V value; // The value associated with the key
        private Node left, right; // Pointers to the left and right child
        private int size; // The size of the subtree rooted at this node

        /**
         * Node constructor initializes the key and value, and sets children to null.
         *
         * @param key   the key of the node
         * @param value the value associated with the key
         */
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.size = 1;
        }
    }

    /**
     * Inserts the specified key-value pair into the binary search tree.
     * If the tree already contains the specified key, the existing value is replaced with the new value.
     *
     * @param key the key
     * @param value the value to be associated with the key
     */
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) return new Node(key, value);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value);
        else if (cmp > 0) node.right = put(node.right, key, value);
        else node.value = value;
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    private int size(Node node) {
        return node == null ? 0 : node.size;
    }

    /**
     * Returns the size of the tree.
     *
     * @return the number of key-value pairs in the tree
     */
    public int size() {
        return size(root);
    }

    /**
     * Retrieves the value associated with the specified key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or null if no such key exists
     */
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node node, K key) {
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) node = node.left;
            else if (cmp > 0) node = node.right;
            else return node.value;
        }
        return null;
    }

    /**
     * Deletes the specified key and its associated value from this tree, if it exists.
     *
     * @param key the key to be deleted
     */
    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = delete(node.left, key);
        else if (cmp > 0) node.right = delete(node.right, key);
        else {
            if (node.right == null) return node.left;
            if (node.left == null) return node.right;
            Node t = node;
            node = min(t.right);
            node.right = deleteMin(t.right);
            node.left = t.left;
        }
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    private Node min(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    /**
     * Returns an iterator over elements of type {@code Entry<K, V>}.
     *
     * @return an Iterator that can be used to iterate over the entries in the BST in in-order
     */
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<Entry<K, V>> {
        private Stack<Node> stack = new Stack<>();

        public BSTIterator() {
            pushLeft(root);
        }

        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Entry<K, V> next() {
            if (!hasNext()) throw new NoSuchElementException();
            Node node = stack.pop();
            Entry<K, V> entry = new Entry<>(node.key, node.value);
            if (node.right != null) {
                pushLeft(node.right);
            }
            return entry;
        }
    }

    /**
     * Entry class used to return a key-value pair from the iterator.
     */
    public static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    /**
     * The main method demonstrates the use of the BST class.
     */
    /**
     * The main method demonstrates the use of the BST class.
     */
    public static void main(String[] args) {
        BST<Integer, String> tree = new BST<>();
        tree.put(3, "Three");
        tree.put(1, "One");
        tree.put(4, "Four");
        tree.put(2, "Two");

        // Display the elements of the tree before deletion
        System.out.println("Before deletion:");
        printTree(tree);

        // Delete a key from the BST
        tree.delete(3);  // Deleting the key "3"

        // Display the elements of the tree after deletion
        System.out.println("After deletion:");
        printTree(tree);
    }

    /**
     * Helper method to print all key-value pairs in the BST using in-order traversal.
     * @param tree The BST to print.
     */
    private static void printTree(BST<Integer, String> tree) {
        for (Entry<Integer, String> elem : tree) {
            System.out.println("Key is " + elem.getKey() + " and value is " + elem.getValue());
        }
        System.out.println("Size of tree: " + tree.size());
    }
}
