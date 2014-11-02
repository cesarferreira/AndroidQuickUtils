package quickutils.core.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Lists {
	
	// ArrayList
	
	/**
	 * Creates a <i>mutable</i>, empty {@code ArrayList} instance.
	 * 
	 * @return a new, empty {@code ArrayList}
	 */
	public static <E> ArrayList<E> newArrayList() {
		return new ArrayList<E>();
	}
	
	/**
	 * Creates a <i>mutable</i> {@code ArrayList} instance containing the given elements.
	 * 
	 * @param elements the elements that the list should contain, in order
	 * @return a new {@code ArrayList} containing those elements
	 */
	@SafeVarargs
	public static <E> ArrayList<E> newArrayList(E... elements) {
		ArrayList<E> list = new ArrayList<E>();
		Collections.addAll(list, elements);
		return list;
	}
	
	public static <E> List<E> safeArrayList(List<E> list) {
		return list != null ? list : Lists.<E>newArrayList();
	}
	
	/**
	 * Creates a <i>mutable</i> {@code ArrayList} instance containing the given elements.
	 * 
	 * @param elements the elements that the list should contain, in order
	 * @return a new {@code ArrayList} containing those elements
	 */
	public static <E> ArrayList<E> newArrayList(Iterable<? extends E> elements) {
		return (elements instanceof Collection) ? new ArrayList<E>((Collection<? extends E>)elements)
				: newArrayList(elements.iterator());
	}
	
	/**
	 * Creates a <i>mutable</i> {@code ArrayList} instance containing the given elements.
	 * 
	 * @param elements the elements that the list should contain, in order
	 * @return a new {@code ArrayList} containing those elements
	 */
	public static <E> ArrayList<E> newArrayList(Iterator<? extends E> elements) {
		ArrayList<E> list = newArrayList();
		while (elements.hasNext()) {
			list.add(elements.next());
		}
		return list;
	}
	
	// LinkedList
	
	/**
	 * Creates an empty {@code LinkedList} instance.
	 * 
	 * @return a new, empty {@code LinkedList}
	 */
	public static <E> LinkedList<E> newLinkedList() {
		return new LinkedList<E>();
	}
	
	/**
	 * Creates a {@code LinkedList} instance containing the given elements.
	 * 
	 * @param elements the elements that the list should contain, in order
	 * @return a new {@code LinkedList} containing those elements
	 */
	public static <E> LinkedList<E> newLinkedList(Iterable<? extends E> elements) {
		LinkedList<E> list = newLinkedList();
		for (E element : elements) {
			list.add(element);
		}
		return list;
	}
	
	/**
	 * @param list
	 * @param maxCount
	 * @return same list if size doesn't exceeds maxCount, new list with trimmed element otherwise
	 */
	public static <T> List<T> trim(List<T> list, int maxCount) {
		if (list.size() > maxCount) {
			return Lists.newArrayList(list.subList(0, maxCount));
		}
		return list;
	}
	
	public static Boolean isNullOrEmpty(List<?> list) {
		return (list == null) || list.isEmpty();
	}
	
	public static <T> List<T> filter(List<T> unfilteredList, Predicate<T> predicate) {
		List<T> filteredList = Lists.newArrayList();
		for (T each : unfilteredList) {
			if (predicate.apply(each)) {
				filteredList.add(each);
			}
		}
		return filteredList;
	}
	
}
