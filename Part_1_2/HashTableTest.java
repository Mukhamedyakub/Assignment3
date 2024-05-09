package Part_1_2;
import Part_1_1.MyHashTable;

import java.util.Random;

/**
 * Class to test the distribution of hash codes in MyHashTable.
 */
public class HashTableTest {
    /**
     * Main method to run the hash table test.
     *
     * @param args command line arguments
     */

    public static void main(String[] args) {
//        ExtendedMyHashTable<String, Integer> myTable = new ExtendedMyHashTable<>();

        ExtendedMyHashTable<MyTestingClass, Student> table = new ExtendedMyHashTable<>();
        Random random = new Random();

        // Adding random 10,000 elements
        for (int i = 0; i < 10000; i++) {
            int id = random.nextInt(10000);
            String name = "Name" + id;
            MyTestingClass key = new MyTestingClass(id, name);
            Student value = new Student("Student" + i);
            table.put(key, value);
        }

        // Print the number of elements in each bucket
        System.out.println("Bucket distribution:");
        table.printBucketSize();
    }
}
