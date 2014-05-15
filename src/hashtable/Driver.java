package hashtable;

import java.io.File;
import java.io.FileNotFoundException;
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
		printLabels();
		testFile("story.txt");
		testFile("kjv10.txt");
		testFile("words.txt");
	}

	private static void testFile(String filename){
		test(filename, 10);
		test(filename, 100);
		test(filename, 1000);
		test(filename, 10000);
		test(filename, 100000);
		test(filename, 1000000);
	}

	private static void test(String filename, int capacity){
		long start = System.nanoTime();
		HashTable<String> hashTable = loadHashTable(filename, capacity);
		int size = hashTable.size();
		double loadFactor = hashTable.loadFactor();
		int numberOfCollisions = hashTable.numberOfCollisions();
		int biggestBucket = hashTable.biggestBucket();
		int numberOfEmptyBuckets = hashTable.numberOfEmptyBuckets();
		double loadTime = (System.nanoTime() - start) / 1000000000.0;
		printResults(filename, capacity, size, loadFactor, numberOfCollisions, biggestBucket,
				numberOfEmptyBuckets, loadTime);
	}

	private static HashTable loadHashTable(String filename, int capacity){
		HashTable<String> hashTable = new HashTable<String>(capacity);
		Scanner scanner = null;
		try{
			scanner = new Scanner(new File(filename));
			String line;
			while (scanner.hasNextLine()){
				line = scanner.nextLine();
				for (String word : line.replaceAll("[^a-zA-Z' -]", "").toLowerCase().split(" ")){
					hashTable.add(word);
				}
			}
		}
		catch(FileNotFoundException e){
			System.out.println("Test failed: "+e.getMessage());
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
		System.out.printf("| %9s | %8s | %6s | %11s | %10s | %14s | %13s | %9s |%n",
				"File",
				"Capacity",
				"Size",
				"Load Factor",
				"Collisions",
				"Biggest Bucket",
				"Empty Buckets",
				"Load Time");
	}

	/**
	 * Prints the heading labels for result output to the console
	 */
	private static void printResults(String filename, int capacity, int size, double loadFactor,
									 int numberOfCollisions, int biggestBucket, int numberOfEmptyBuckets,
									 double loadTime){
		System.out.printf("| %9s | %8s | %6s | %11.5f | %10s | %14s | %13s | %8.5fs |%n",
				filename,
				capacity,
				size,
				loadFactor,
				numberOfCollisions,
				biggestBucket,
				numberOfEmptyBuckets,
				loadTime);
	}

}
