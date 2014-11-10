package quickutils.core.collections;

import java.util.Collection;

public class Iterables {
	
	/**
	 * Returns the number of elements in {@code iterable}.
	 * 
	 * @param iterable The {@link Iterable}
	 * @return The {@link Iterable} size
	 */
	public static int size(Iterable<?> iterable) {
		return (iterable instanceof Collection) ? ((Collection<?>)iterable).size()
				: Iterators.size(iterable.iterator());
	}
	
	/**
	 * Copies an iterable's elements into an array.
	 * 
	 * @param iterable the iterable to copy
	 * @param type the type of the elements
	 * @return a newly-allocated array into which all the elements of the iterable have been copied
	 */
	public static <T> T[] toArray(Iterable<? extends T> iterable, Class<T> type) {
		Collection<? extends T> collection = toCollection(iterable);
		T[] array = ObjectArrays.newArray(type, collection.size());
		return collection.toArray(array);
	}
	
	/**
	 * Converts an iterable into a collection. If the iterable is already a collection, it is returned. Otherwise, an
	 * {@link java.util.ArrayList} is created with the contents of the iterable in the same iteration order.
	 */
	private static <E> Collection<E> toCollection(Iterable<E> iterable) {
		return (iterable instanceof Collection) ? (Collection<E>)iterable : Lists.newArrayList(iterable.iterator());
	}
	
}
