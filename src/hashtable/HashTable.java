package hashtable;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 * Hash table based implementation of the Set interface. This implementation
 * does not permit null values and the null key.  This class makes no
 * guarantees as to the order of the set; in particular, it does not guarantee
 * that the order will remain constant over time.
 *
 * This implementation provides constant-time performance for the basic
 * operations (add, contains, and remove), assuming the hash function
 * disperses the elements properly among the buckets. Iteration over
 * the collection requires time proportional to the "capacity" of the
 * instance (the number of buckets) plus its size (the number of elements).
 * Thus, it's very important not to set the initial capacity too high (or the
 * load factor too low) if iteration performance is important.
 *
 * @author hofmannt
 * @version 0.1
 */

public class HashTable<E> implements Set<E> {

	private int size;

	/**
	 * Array containing the values
	 */
	private LinkedList<E>[] table;

	/**
	 * Constructs a new HashTable object with capacity buckets.
	 * @param capacity The desired number of buckets
	 */
	public HashTable(int capacity) {
		table = (LinkedList<E>[]) new Object[capacity];
		size = 0;
	}

	/**
	 * Adds the specified element to this set if it is not already present.
	 * More formally, adds the specified element
	 * <tt>element</tt> to this set if the set contains no element <tt>e2</tt>
	 * such that <tt>element.equals(e2))</tt>.
	 * If this set already contains the element, the call leaves the set
	 * unchanged and returns <tt>false</tt>.  In combination with the
	 * restriction on constructors, this ensures that sets never contain
	 * duplicate elements.
	 *
	 * @param element element to be added to this set
	 * @return <tt>true</tt> if this set did not already contain the specified
	 *         element
	 * @throws NullPointerException if the specified element is null
	 */
	@Override
	public boolean add(E element) throws NullPointerException {
		if (element == null){
			throw new NullPointerException("Element cannot be null.");
		}
		int hash = Math.abs(element.hashCode())%table.length;
		if(table[hash] == null){
			table[hash] = new LinkedList<E>();
		}
		if (table[hash].contains(element)){
			return false; //element already in HashTable
		}
		table[hash].add(element);
		size++;
		return true;
	}

	/**
	 * Adds all of the elements in the specified collection to this set if
	 * they're not already present.  If the specified
	 * collection is also a set, the <tt>addAll</tt> operation effectively
	 * modifies this set so that its value is the <i>union</i> of the two
	 * sets.  The behavior of this operation is undefined if the specified
	 * collection is modified while the operation is in progress.
	 *
	 * @param  collection collection containing elements to be added to this set
	 * @return <tt>true</tt> if this set changed as a result of the call
	 *
	 * @throws NullPointerException if the specified collection contains one
	 *         or more null elements or if the specified collection is null
	 * @see #add(Object)
	 */
	@Override
	public boolean addAll(Collection<? extends E> collection) {
		boolean changed = false;
		for (E e : collection){
			changed = add(e) || changed;
		}
		return changed;
	}

	/**
	 * Removes all of the elements from this set.
	 * The set will be empty after this call returns.
	 */
	@Override
	public void clear() {
		table = (LinkedList<E>[]) new Object[table.length];
		size = 0;
	}

	 /**
	   * Returns <tt>true</tt> if this set contains the specified element.
	   * More formally, returns <tt>true</tt> if and only if this set
	   * contains an element <tt>e</tt> such that <tt>target.equals(e))</tt>.
	   *
	   * @param target element whose presence in this set is to be tested
	   * @return <tt>true</tt> if this set contains the specified element
	   * @throws ClassCastException if the type of the specified element
	   *         is incompatible with this set
	   * (<a href="Collection.html#optional-restrictions">optional</a>)
	   * @throws NullPointerException if the specified element is null
	   * (<a href="Collection.html#optional-restrictions">optional</a>)
	   */
	@Override
	public boolean contains(Object target) {
		int hash = Math.abs(target.hashCode())%table.length;
		return table[hash] != null && table[hash].contains(target);
	}

	/**
	 * Returns <tt>true</tt> if this set contains all of the elements of the
	 * specified collection.  If the specified collection is also a set, this
	 * method returns <tt>true</tt> if it is a <i>subset</i> of this set.
	 *
	 * @param  collection collection to be checked for containment in this set
	 * @return <tt>true</tt> if this set contains all of the elements of the
	 *         specified collection
	 * @throws ClassCastException if the types of one or more elements
	 *         in the specified collection are incompatible with this set
	 * (<a href="Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException if the specified collection contains one
	 *         or more null elements or if the collection is null
	 * (<a href="Collection.html#optional-restrictions">optional</a>),
	 *         or if the specified collection is null
	 * @see    #contains(Object)
	 */
	@Override
	public boolean containsAll(Collection<?> collection) {
		for (Object o : collection){
			if (!contains(o)){
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns <tt>true</tt> if this set contains no elements.
	 *
	 * @return <tt>true</tt> if this set contains no elements
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Removes the specified element from this set if it is present.
	 * More formally, removes an element <tt>e</tt> such that
	 * <tt>target.equals(e))</tt>, if this set contains such an element.
	 * Returns <tt>true</tt> if this set
	 * contained the element (or equivalently, if this set changed as a
	 * result of the call).  (This set will not contain the element once the
	 * call returns.)
	 *
	 * @param target object to be removed from this set, if present
	 * @return <tt>true</tt> if this set contained the specified element
	 * @throws ClassCastException if the type of the specified element
	 *         is incompatible with this set
	 * (<a href="Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException if the specified element is null
	 * (<a href="Collection.html#optional-restrictions">optional</a>)
	 */
	@Override
	public boolean remove(Object target) {
		if (!contains(target)){
			return false;
		}
		int hash = Math.abs(target.hashCode())%table.length;
		table[hash].remove(target);
		return true;
	}

	/**
	 * Returns the number of elements in this set (its cardinality).  If this
	 * set contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
	 * <tt>Integer.MAX_VALUE</tt>.
	 *
	 * @return the number of elements in this set (its cardinality)
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Returns the number of buckets in the hash table.
	 *
	 * @return The number of buckets
	 */
	public int getCapacity() {
		// TODO
		return 0;
	}

	/**
	 * Sets the number of buckets in the hash table.
	 *
	 * @param capacity desired number of buckets
	 */
	public void setCapacity(int capacity) {
		// TODO
	}

	/**
	 * Returns the load factor of the hash table.  The load factor
	 * is the ratio of elements in the hash table to the number of
	 * buckets in the hash table.
	 *
	 * @return The load factor
	 */
	public double loadFactor() {
		// TODO
		return 0.0;
	}

	/**
	 * Returns the number of collisions in the hash table.
	 *
	 * @return The number of collisions
	 */
	public int numberOfCollisions() {
		// TODO
		return 0;
	}

	/**
	 * Returns the number of elements in the bucket with
	 * the most elements.
	 *
	 * @return The maximum number of elements in a bucket
	 */
	public int biggestBucket() {
		// TODO
		return 0;
	}

	/**
	 * Returns the number of empty buckets in the hash table.
	 *
	 * @return The number of empty buckets
	 */
	public int numberOfEmptyBuckets() {
		// TODO
		return 0;
	}

	// METHODS BELOW THIS LINE ARE NOT REQUIRED IN ORDER TO COMPLETE THE
	// LAB ASSIGNMENT; HOWEVER, IF THESE METHODS ARE NOT IMPLEMENTED, THE
	// HIGHEST SCORE POSSIBLE ON THIS ASSIGNMENT MAY NOT BE 100%.
	//        << SEE YOU INSTRUCTOR FOR DETAILS >>

	/**
	 * Returns an iterator over the elements in this set.  The elements are
	 * returned in no particular order (unless this set is an instance of some
	 * class that provides a guarantee).
	 *
	 * @return an iterator over the elements in this set
	 */
	@Override
	public Iterator<E> iterator() {
		throw new UnsupportedOperationException("Implementation Not Found");
	}

	/**
	 * Removes from this set all of its elements that are contained in the
	 * specified collection.  If the specified
	 * collection is also a set, this operation effectively modifies this
	 * set so that its value is the <i>asymmetric set difference</i> of
	 * the two sets.
	 *
	 * @param  collection collection containing elements to be removed from this set
	 * @return <tt>true</tt> if this set changed as a result of the call
	 * @throws UnsupportedOperationException if the <tt>removeAll</tt> operation
	 *         is not supported by this set
	 * @throws ClassCastException if the class of an element of this set
	 *         is incompatible with the specified collection
	 * (<a href="Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException if this set contains one
	 *         or more null elements or if the collection is null
	 * @see #remove(Object)
	 * @see #contains(Object)
	 */
	@Override
	public boolean removeAll(Collection<?> collection) {
		throw new UnsupportedOperationException("Implementation Not Found");
	}

	/**
	 * Retains only the elements in this set that are contained in the
	 * specified collection (optional operation).  In other words, removes
	 * from this set all of its elements that are not contained in the
	 * specified collection.  If the specified collection is also a set, this
	 * operation effectively modifies this set so that its value is the
	 * <i>intersection</i> of the two sets.
	 *
	 * @param  collection collection containing elements to be retained in this set
	 * @return <tt>true</tt> if this set changed as a result of the call
	 * @throws UnsupportedOperationException if the <tt>retainAll</tt> operation
	 *         is not supported by this set
	 * @throws ClassCastException if the class of an element of this set
	 *         is incompatible with the specified collection
	 * (<a href="Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException if this set contains one
	 *         or more null elements or if the collection is null
	 * @see #remove(Object)
	 */
	@Override
	public boolean retainAll(Collection<?> collection) {
		throw new UnsupportedOperationException("Implementation Not Found");
	}

	 /**
	  * Returns an array containing all of the elements in this set.
	  * If this set makes any guarantees as to what order its elements
	  * are returned by its iterator, this method must return the
	  * elements in the same order.
	  *
	  * <p>The returned array will be "safe" in that no references to it
	  * are maintained by this set.  (In other words, this method must
	  * allocate a new array even if this set is backed by an array).
	  * The caller is thus free to modify the returned array.
	  *
	  * <p>This method acts as bridge between array-based and collection-based
	  * APIs.
	  *
	  * @return an array containing all the elements in this set
	  */
	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException("Implementation Not Found");
	}

	/**
	  * Returns an array containing all of the elements in this set; the
	  * runtime type of the returned array is that of the specified array.
	  * If the set fits in the specified array, it is returned therein.
	  * Otherwise, a new array is allocated with the runtime type of the
	  * specified array and the size of this set.
	  *
	  * <p>If this set fits in the specified array with room to spare
	  * (i.e., the array has more elements than this set), the element in
	  * the array immediately following the end of the set is set to
	  * <tt>null</tt>.  (This is useful in determining the length of this
	  * set <i>only</i> if the caller knows that this set does not contain
	  * any null elements.)
	  *
	  * <p>If this set makes any guarantees as to what order its elements
	  * are returned by its iterator, this method must return the elements
	  * in the same order.
	  *
	  * <p>Like the {@link #toArray()} method, this method acts as bridge between
	  * array-based and collection-based APIs.  Further, this method allows
	  * precise control over the runtime type of the output array, and may,
	  * under certain circumstances, be used to save allocation costs.
	  *
	  * <p>Suppose <tt>x</tt> is a set known to contain only strings.
	  * The following code can be used to dump the set into a newly allocated
	  * array of <tt>String</tt>:
	  *
	  * <pre>
	  *     String[] y = x.toArray(new String[0]);</pre>
	  *
	  * Note that <tt>toArray(new Object[0])</tt> is identical in function to
	  * <tt>toArray()</tt>.
	  *
	  * @param array the array into which the elements of this set are to be
	  *        stored, if it is big enough; otherwise, a new array of the same
	  *        runtime type is allocated for this purpose.
	  * @return an array containing all the elements in this set
	  * @throws ArrayStoreException if the runtime type of the specified array
	  *         is not a supertype of the runtime type of every element in this
	  *         set
	  * @throws NullPointerException if the specified array is null
	  */
	@Override
	public <T> T[] toArray(T[] array) {
		throw new UnsupportedOperationException("Implementation Not Found");
	}

}
