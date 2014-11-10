package quickutils.core.collections;

/**
 * Determines a true or false value for a given input.
 * 
 * @param <T>
 */
public interface Predicate<T> {
	
	/**
	 * Returns the result of applying this predicate to {@code input}. This method is <i>generally expected</i>, but not
	 * absolutely required, to have the following properties:
	 * 
	 * <ul>
	 * <li>Its execution does not cause any observable side effects.
	 * </ul>
	 * 
	 * @param input The input to evaluate
	 * @return True or false
	 */
	public Boolean apply(T input);
	
}
