package Part_1_2;
import Part_1_1.MyHashTable;

/**
 * An extension of the MyHashTable class that adds functionality to print the size
 * of each bucket in the hash table. This class inherits from MyHashTable and
 * uses the same underlying data structure.
 *
 * The class is useful for debugging and analyzing the distribution of elements across
 * the buckets, helping to understand how evenly the hash function distributes the keys.
 *
 * @param <K> the type of keys maintained by this hash table
 * @param <V> the type of mapped values
 */
class ExtendedMyHashTable<K, V> extends MyHashTable<K, V> {

    /**
     * Constructs an empty ExtendedMyHashTable with default initial capacity.
     * The default capacity is defined in the superclass MyHashTable.
     */
    public ExtendedMyHashTable() {
        super();
    }

    /**
     * Constructs an empty ExtendedMyHashTable with the specified initial capacity.
     * This capacity is passed to the superclass MyHashTable to initialize the underlying array.
     *
     * @param capacity the initial capacity of the hash table
     */
    public ExtendedMyHashTable(int capacity) {
        super(capacity);
    }

    /**
     * Prints the number of elements (nodes) in each bucket (chain) of the hash table.
     * This method can be used to visually inspect the load factor and distribution of
     * hash codes across the buckets, which is helpful for analyzing the effectiveness
     * of the hash function in distributing keys uniformly.
     *
     * If the chainArray is null, a message is printed indicating that the array
     * has not been initialized, which could point to issues in the hash table construction.
     */
    public void printBucketSize() {
        if (chainArray == null) {
            System.out.println("chainArray is null");
            return;
        }

        for (int i = 0; i < chainArray.length; i++) {
            int count = 0;
            HashNode<K, V> node = chainArray[i];
            while (node != null) {
                count++;
                node = node.next;
            }
            System.out.println("Bucket " + i + ": " + count + " elements");
        }
    }
}
