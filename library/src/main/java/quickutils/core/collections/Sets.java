package quickutils.core.collections;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class Sets {
	
	// HashSet
	
	/**
	 * Creates a <i>mutable</i>, empty {@code HashSet} instance.
	 * 
	 * @return a new, empty {@code HashSet}
	 */
	public static <E> HashSet<E> newHashSet() {
		return new HashSet<E>();
	}
	
	/**
	 * Creates a <i>mutable</i> {@code HashSet} instance containing the given elements in unspecified order.
	 * 
	 * @param elements the elements that the set should contain
	 * @return a new {@code HashSet} containing those elements (minus duplicates)
	 */
	@SafeVarargs
	public static <E> HashSet<E> newHashSet(E... elements) {
		HashSet<E> set = new HashSet<E>();
		Collections.addAll(set, elements);
		return set;
	}
	
	/**
	 * Creates a <i>mutable</i> {@code HashSet} instance containing the given elements in unspecified order.
	 * 
	 * @param elements the elements that the set should contain
	 * @return a new {@code HashSet} containing those elements (minus duplicates)
	 */
	public static <E> HashSet<E> newHashSet(Iterable<? extends E> elements) {
		return (elements instanceof Collection) ? new HashSet<E>((Collection<? extends E>)elements)
				: newHashSet(elements.iterator());
	}
	
	/**
	 * Creates a <i>mutable</i> {@code HashSet} instance containing the given elements in unspecified order.
	 * 
	 * @param elements the elements that the set should contain
	 * @return a new {@code HashSet} containing those elements (minus duplicates)
	 */
	public static <E> HashSet<E> newHashSet(Iterator<? extends E> elements) {
		HashSet<E> set = newHashSet();
		while (elements.hasNext()) {
			set.add(elements.next());
		}
		return set;
	}
	
	// LinkedHashSet
	
	/**
	 * Creates a <i>mutable</i>, empty {@code LinkedHashSet} instance.
	 * 
	 * @return a new, empty {@code LinkedHashSet}
	 */
	public static <E> LinkedHashSet<E> newLinkedHashSet() {
		return new LinkedHashSet<E>();
	}
	
	/**
	 * Creates a <i>mutable</i> {@code LinkedHashSet} instance containing the given elements in order.
	 * 
	 * @param elements the elements that the set should contain, in order
	 * @return a new {@code LinkedHashSet} containing those elements (minus duplicates)
	 */
	public static <E> LinkedHashSet<E> newLinkedHashSet(Iterable<? extends E> elements) {
		if (elements instanceof Collection) {
			return new LinkedHashSet<E>((Collection<? extends E>)elements);
		}
		LinkedHashSet<E> set = newLinkedHashSet();
		for (E element : elements) {
			set.add(element);
		}
		return set;
	}
	
	// TreeSet
	
	/**
	 * Creates a <i>mutable</i>, empty {@code TreeSet} instance sorted by the natural sort ordering of its elements.
	 * 
	 * @return a new, empty {@code TreeSet}
	 */
	public static <E extends Comparable<?>> TreeSet<E> newTreeSet() {
		return new TreeSet<E>();
	}
	
	/**
	 * Creates a <i>mutable</i> {@code TreeSet} instance containing the given elements sorted by their natural ordering.
	 * 
	 * @param elements the elements that the set should contain
	 * @return a new {@code TreeSet} containing those elements (minus duplicates)
	 */
	public static <E extends Comparable<?>> TreeSet<E> newTreeSet(Iterable<? extends E> elements) {
		TreeSet<E> set = newTreeSet();
		for (E element : elements) {
			set.add(element);
		}
		return set;
	}
}
