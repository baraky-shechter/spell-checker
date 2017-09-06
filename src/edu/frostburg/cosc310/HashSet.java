package edu.frostburg.cosc310;

import java.util.Set;
import java.util.*;

/**
 * Class for the HashSet ADT
 * 
 * @author Barak Shechter
 * @version 2015.11.9
 *
 * @param <T> Type of HashSet
 */
public class HashSet<T> extends AbstractSet<T> implements Set<T> {

	private HashMap<T, Object> map;
	
	private static final Object DUMMY = new Object();
	
	/**
	 * Creates a new hash set based on a hashmap
	 */
	public HashSet() {
		map = new HashMap<T,Object>();
	}
	
	/**
	 * Adds the element of type T to the HashSet
	 * 
	 * @param element of type T to be added
	 * @return true if the element was added successfully
	 */
	public boolean add(T e) {
		return map.put (e, DUMMY )==null;
	}
	
	/**
	 * Clears the elements of the HashSet
	 */
	public void clear() {
		map.clear();
	}
	
	/**
	 * Looks for an object in the HashSet and returns true if found
	 * 
	 * @param o Object to be found
	 * @return true if the object was found
	 */
	public boolean contains(Object o) {
		return map.containsKey(o);
	}
	
	/**
	 * Removes Object o from the HashSet
	 * 
	 * @param o Object to be removed
	 * @return true if the object was removed successfully
	 */
	public boolean remove(Object o) {
		return map.remove(o) == DUMMY; 
	}
	
	/**
	 * Updates S so that it only keeps those elements that are
	 * also elements of set c, effectively replacing S by S∩C.
	 * 
	 * @param Collection to be intersected
	 * @return true if the original collection has been modified
	 */
	public boolean retainAll(Collection<?> c) {
		boolean modified = false;
		Iterator<T> e = iterator();
		while (e.hasNext()) {
			if(!c.contains(e.next())) {
				e.remove();
				modified = true;
			}
		}
		return modified;
	}
	
	/**
	 * @return Iterator of the HashSet
	 */
	public Iterator<T> iterator() {
		return map.keySet().iterator();
	}
	
	/**
	 * @return int size of the HashSet
	 */
	public int size() {
		return map.size();
	}
	
	/**
	 * Checks whether the HashSet is empty
	 * 
	 * @return true if the HashSet is empty
	 */
	public boolean isEmpty() {
		return map.size() == 0;
	}
	
	
}
