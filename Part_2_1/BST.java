package Part_2_1;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple implementation of a generic Binary Search Tree (BST).
 * This class supports basic operations such as insert (put), find (get), delete, and iteration over all keys.
 *
 * @param <K> the type of keys maintained by this tree, must extend Comparable
 * @param <V> the type of mapped values
 */
public class BST<K extends Comparable<K>, V> {
    private Node root; // Root of the binary search tree

    /**
     * Represents a node within the binary search tree.
     */
    private class Node {
        private K key;   // The key of the node
        private V value; // The value associated with the key
        private Node left, right; // Pointers to the left and right child

        /**
         * Node constructor initializes the key and value, and sets children to null.
         *
         * @param key   the key of the node
         * @param value the value associated with the key
         */
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
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
        return node;
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
        return node;
    }

    private Node min(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        return node;
    }

    /**
     * Returns an iterable over keys in the BST in in-order.
     *
     * @return an Iterable object to iterate over the keys
     */
    public Iterable<K> keys() {
        List<K> keys = new ArrayList<>();
        inorder(root, keys);
        return keys;
    }

    private void inorder(Node node, List<K> keys) {
        if (node == null) return;
        inorder(node.left, keys);
        keys.add(node.key);
        inorder(node.right, keys);
    }
}
