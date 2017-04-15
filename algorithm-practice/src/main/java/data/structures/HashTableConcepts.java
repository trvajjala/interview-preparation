package data.structures;

import java.util.Hashtable;

//@formatter:off
/**
 *  InitialCapacity:  Initial capacity is number of buckets created at the time of Hashtable instantiation
 *  default value 11 ,loadFactor =.75
 *
 *  Rehashing happens after threshold exceeds =  11*.75
 *
 * Collision: when two keys results in same hashCode and directs to the same bucket (assume bucket 1) you
 * want to store the second element also in the same bucket.
 * You add this second element to the already created linked list as the adjacent element.
 *
 * @author ThirupathiReddy V
 *
 */
//@formatter:on
public class HashTableConcepts {

    static int initialCapacity = 10;// if you increase this initialCapacity
    // there is less chance of collision
    // performance is better

    public static void main(String[] args) {

	final long start = System.nanoTime();
	// Default initialCapacity is 11 and load factor is .75

	final Hashtable<HashTableConcepts, Integer> table = new Hashtable<>(initialCapacity);

	table.put(new HashTableConcepts(), 22);
	table.put(new HashTableConcepts(), 2234);

	table.put(new HashTableConcepts(), 22);
	table.put(new HashTableConcepts(), 2234);
	table.put(new HashTableConcepts(), 22);
	table.put(new HashTableConcepts(), 2234);

	System.out.println("Size of the final table " + table.size());

	System.out.println(System.nanoTime() - start);

    }

    @Override
    public int hashCode() {
	final int hashCode =10;// super.hashCode();
	System.err.println("calculating hashCode " + hashCode);
	System.err.println(" Placing element into bucket location " + (hashCode & initialCapacity - 1));
	return hashCode;
    }


    @Override
    public boolean equals(Object obj) {
	System.out.println("equals method invoked as part of contract ");
	return true;
    }
}
