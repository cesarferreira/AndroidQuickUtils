package quickutils.core.collections;

import java.lang.reflect.Array;

public class ObjectArrays {
	
	/**
	 * Returns a new array of the given length with the specified component type.
	 * 
	 * @param type the component type
	 * @param length the length of the new array
	 * @return an Array
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] newArray(Class<T> type, int length) {
		return (T[])Array.newInstance(type, length);
	}
}
