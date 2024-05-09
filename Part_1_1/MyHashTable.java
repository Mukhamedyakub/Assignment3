package Part_1_1;

///** FIRST VERSION OF HASHTABLE
// * MyHashTable is a custom implementation of a hash table using generic types.
// * It utilizes chaining to handle collisions.
// *
// * @param <K> the type of keys maintained by this hash table
// * @param <V> the type of mapped values
// */
//public class MyHashTable<K, V> {
//    /**
//     * The array of chains.
//     */
//    private HashNode<K, V>[] chainArray;
//
//    /**
//     * Default initial capacity of hash table.
//     */
//    private static final int DEFAULT_CAPACITY = 11;
//
//    /**
//     * The number of key-value pairs stored in the hash table.
//     */
//    private int size;
//
//    /**
//     * Node class for entries in the hash table.
//     */
//    private static class HashNode<K, V> {
//        K key;
//        V value;
//        HashNode<K, V> next;
//
//        /**
//         * Constructor for a hash node.
//         * @param key the key
//         * @param value the value
//         */
//        public HashNode(K key, V value) {
//            this.key = key;
//            this.value = value;
//            this.next = null;
//        }
//    }
//
//    /**
//     * Constructs an empty hash table.
//     */
//    public MyHashTable() {
//        this(DEFAULT_CAPACITY);
//    }
//
//    /**
//     * Constructs an empty hash table with the specified initial capacity.
//     * @param capacity the initial capacity of the hash table
//     */
//    public MyHashTable(int capacity) {
//        chainArray = (HashNode<K, V>[]) new HashNode[capacity];
//        size = 0;
//    }
//
//    /**
//     * Returns the index for a key.
//     * @param key the key
//     * @return the index in the chain array
//     */
//    private int hash(K key) {
//        return (key.hashCode() & 0x7fffffff) % chainArray.length;
//    }
//
//    /**
//     * Adds a key-value pair to the hash table.
//     * @param key the key
//     * @param value the value to add
//     */
//    public void put(K key, V value) {
//        int index = hash(key);
//        for (HashNode<K, V> node = chainArray[index]; node != null; node = node.next) {
//            if (node.key.equals(key)) {
//                node.value = value;
//                return;
//            }
//        }
//        chainArray[index] = new HashNode<>(key, value, chainArray[index]);
//        size++;
//    }
//
//    /**
//     * Retrieves a value by key.
//     * @param key the key
//     * @return the value associated with the key, or null if not found
//     */
//    public V get(K key) {
//        int index = hash(key);
//        for (HashNode<K, V> node = chainArray[index]; node != null; node = node.next) {
//            if (node.key.equals(key)) {
//                return node.value;
//            }
//        }
//        return null;
//    }
//
//    /**
//     * Removes the key and its associated value from the hash table, if it is present.
//     * @param key the key
//     * @return the value associated with the key, or null if the key was not found
//     */
//    public V remove(K key) {
//        int index = hash(key);
//        HashNode<K, V> prev = null;
//        for (HashNode<K, V> node = chainArray[index]; node != null; node = node.next) {
//            if (node.key.equals(key)) {
//                if (prev != null) {
//                    prev.next = node.next;
//                } else {
//                    chainArray[index] = node.next;
//                }
//                size--;
//                return node.value;
//            }
//            prev = node;
//        }
//        return null;
//    }
//
//    /**
//     * Checks whether the hash table contains a specific value.
//     * @param value the value to find
//     * @return true if the value is found, false otherwise
//     */
//    public boolean contains(V value) {
//        for (int i = 0; i < chainArray.length; i++) {
//            for (HashNode<K, V> node = chainArray[i]; node != null; node = node.next) {
//                if (node.value.equals(value)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    /**
//     * Returns the key associated with a specific value.
//     * @param value the value to find the key for
//     * @return the key associated with the value, or null if not found
//     */
//    public K getKey(V value) {
//        for (int i = 0; i < chainArray.length; i++) {
//            for (HashNode<K, V> node = chainArray[i]; node != null; node = node.next) {
//                if (node.value.equals(value)) {
//                    return node.key;
//                }
//            }
//        }
//        return null;
//    }
//}

/**
 * MyHashTable is a custom implementation of a hash table using generic types.
 * It utilizes chaining to handle collisions.
 *
 * @param <K> the type of keys maintained by this hash table
 * @param <V> the type of mapped values
 */
public class MyHashTable<K, V> {
    /**
     * The array of chains.
     */
    protected HashNode<K, V>[] chainArray;

    /**
     * Default initial capacity of hash table.
     */
    private static final int DEFAULT_CAPACITY = 11;

    /**
     * The number of key-value pairs stored in the hash table.
     */
    private int size;

    /**
     * Node class for entries in the hash table.
     */
    protected static class HashNode<K, V> {
        K key;
        V value;
        public HashNode<K, V> next;

        /**
         * Constructor for a hash node.
         * @param key the key
         * @param value the value
         * @param next the next node in the chain
         */
        public HashNode(K key, V value, HashNode<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }


    /**
     * Constructs an empty hash table.
     */
    public MyHashTable() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs an empty hash table with the specified initial capacity.
     * @param capacity the initial capacity of the hash table
     */
    public MyHashTable(int capacity) {
        chainArray = (HashNode<K, V>[]) new HashNode[capacity];
        size = 0;
    }

    /**
     * Returns the index for a key.
     * @param key the key
     * @return the index in the chain array
     */
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % chainArray.length;
    }

    /**
     * Adds a key-value pair to the hash table.
     * @param key the key
     * @param value the value to add
     */
    public void put(K key, V value) {
        int index = hash(key);
        for (HashNode<K, V> node = chainArray[index]; node != null; node = node.next) {
            if (node.key.equals(key)) {
                node.value = value;  // Update the existing key's value
                return;
            }
        }
        // Add a new node at the start of the linked list for this bucket.
        chainArray[index] = new HashNode<>(key, value, chainArray[index]);
        size++;
    }


    /**
     * Retrieves a value by key.
     * @param key the key
     * @return the value associated with the key, or null if not found
     */
    public V get(K key) {
        int index = hash(key);
        for (HashNode<K, V> node = chainArray[index]; node != null; node = node.next) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    /**
     * Removes the key and its associated value from the hash table, if it is present.
     * @param key the key
     * @return the value associated with the key, or null if the key was not found
     */
    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> prev = null;
        for (HashNode<K, V> node = chainArray[index]; node != null; node = node.next) {
            if (node.key.equals(key)) {
                if (prev != null) {
                    prev.next = node.next;
                } else {
                    chainArray[index] = node.next;
                }
                size--;
                return node.value;
            }
            prev = node;
        }
        return null;
    }

    /**
     * Checks whether the hash table contains a specific value.
     * @param value the value to find
     * @return true if the value is found, false otherwise
     */
    public boolean contains(V value) {
        for (int i = 0; i < chainArray.length; i++) {
            for (HashNode<K, V> node = chainArray[i]; node != null; node = node.next) {
                if (node.value.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the key associated with a specific value.
     * @param value the value to find the key for
     * @return the key associated with the value, or null if not found
     */
    public K getKey(V value) {
        for (int i = 0; i < chainArray.length; i++) {
            for (HashNode<K, V> node = chainArray[i]; node != null; node = node.next) {
                if (node.value.equals(value)) {
                    return node.key;
                }
            }
        }
        return null;
    }
}