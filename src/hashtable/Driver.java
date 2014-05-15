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

	}

	private static void test(String filename, int capacity){

	}

	private HashTable loadHashTable(String filename, int capacity){
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

}
