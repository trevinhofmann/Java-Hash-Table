package hashtable;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: hofmannt
 * Date: 5/14/14
 * Time: 7:36 PM
 * This is the Driver class for testing the HashTable.
 */

public class Driver {

	public static void main(String[] args){
		test("story.txt", 1000);
	}

	private static void test(String filename, int capacity){
		HashTable<String> hashTable = loadHashTable(filename, capacity);
		int size = hashTable.size();
		double loadFactor = hashTable.loadFactor();
		int numberOfCollisions = hashTable.numberOfCollisions();
		int biggestBucket = hashTable.biggestBucket();
		int numberOfEmptyBuckets = hashTable.numberOfEmptyBuckets();
		printResults(filename, capacity, size, loadFactor, numberOfCollisions, biggestBucket, numberOfEmptyBuckets);
	}

	private static HashTable loadHashTable(String filename, int capacity){
		HashTable<String> hashTable = new HashTable<String>(capacity);
		Scanner scanner = null;
		try{
			scanner = new Scanner(filename);
			String line;
			while (scanner.hasNextLine()){
				line = scanner.nextLine();
				for (String word : line.replaceAll("[^a-zA-Z' -]", "").toLowerCase().split(" ")){
					hashTable.add(word);
				}
			}
		}
		finally {
			if (scanner != null) {
				scanner.close();
			}
		}
		return hashTable;
	}

	/**
	 * Prints the heading labels for result output to the console
	 */
	private static void printLabels(){
		System.out.printf("| %15s | %15s | %15s | %25s | %25s | %25s | %25s |%n",
				"File",
				"Capacity",
				"Size",
				"Load Factor",
				"Number of Collisions",
				"Size of Biggest Bucket",
				"Number of Empty Buckets");
	}

	/**
	 * Prints the heading labels for result output to the console
	 */
	private static void printResults(String filename, int capacity, int size, double loadFactor,
									 int numberOfCollisions, int biggestBucket, int numberOfEmptyBuckets){
		System.out.printf("| %15s | %15s | %15s | %25s | %25s | %25s | %25s |%n",
				filename,
				capacity,
				size,
				loadFactor,
				numberOfCollisions,
				biggestBucket,
				numberOfEmptyBuckets);
	}

}
