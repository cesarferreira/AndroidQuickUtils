package quickutils.core.categories;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import quickutils.core.QuickUtils;

public class math {

	/**
	 * private constructor
	 */
	// private math() {
	// }

	private static final float DEG_TO_RAD = 3.1415926f / 180.0f;
	private static final float RAD_TO_DEG = 180.0f / 3.1415926f;

	/**
	 * Rounds a double value to a certain number of digits
	 * 
	 * @param toBeRounded
	 *            number to be rounded
	 * @param digits
	 *            number of digits to be rounded
	 * @return the double rounded
	 */
	public static double round(double toBeRounded, int digits) {
		if (digits < 0) {
			QuickUtils.log.e("must be greater than 0");
			return 0;
		}
		String formater = "";
		for (int i = 0; i < digits; i++) {
			formater += "#";
		}

		DecimalFormat twoDForm = new DecimalFormat("#." + formater, new DecimalFormatSymbols(Locale.US));
		return Double.valueOf(twoDForm.format(toBeRounded));
	}

	/**
	 * Converts pounds to kilograms
	 * 
	 * @param weight
	 *            to be converted
	 * @return value converted
	 */
	public static double poundsToKg(double weight) {
		return weight / 2.2;
	}

	/**
	 * Converts kilograms to pounds
	 * 
	 * @param weight
	 *            to be converted
	 * @return value converted
	 */
	public static double kgToPounds(double weight) {
		return weight * 2.2;
	}

	/**
	 * Converts inches to centimeters
	 * 
	 * @param value
	 *            to be converted
	 * @return value converted
	 */
	public static double inchesToCm(double inches) {
		return inches * 2.54;
	}

	/**
	 * Converts centimeters to inches
	 * 
	 * @param value
	 *            to be converted
	 * @return value converted
	 */
	public static double cmToInches(double cm) {
		return cm / 2.54;
	}

	/**
	 * Returns a random integer between MIN inclusive and MAX inclusive.
	 * 
	 * @param min
	 *            value inclusive
	 * @param max
	 *            value inclusive
	 * @return an int between MIN inclusive and MAX exclusive.
	 */
	public static int getRandomInteger(int min, int max) {
		Random r = new Random();
		return r.nextInt(max - min + 1) + min;
	}

	/**
	 * Returns a random integer between 0 (Zero) inclusive and MAX inclusive. <br/>
	 * Same as {@code getRandomInteger(0, max);} <br/>
	 * See {@see RandomUtil#getRandomInteger(int, int)}
	 * 
	 * @param max
	 *            value exclusive
	 * @return an int between 0 inclusive and MAX inclusive.
	 */
	public static int getRandomInteger(int max) {
		return getRandomInteger(0, max);
	}

	/**
	 * Returns a random double between MIN inclusive and MAX inclusive.
	 * 
	 * @param min
	 *            value inclusive
	 * @param max
	 *            value inclusive
	 * @return an int between 0 inclusive and MAX exclusive.
	 */
	public static double getRandomDouble(double min, double max) {
		Random r = new Random();
		return min + (max - min) * r.nextDouble();
	}

	/**
	 * Returns a random double between 0 (Zero) inclusive and MAX inclusive. <br/>
	 * Same as {@code getRandomDouble(0, max);} <br/>
	 * See {@see RandomUtil#getRandomDouble(double, double)}
	 * 
	 * @param max
	 *            value exclusive
	 * @return an int between 0 inclusive and MAX inclusive.
	 */
	public static double getRandomDouble(double max) {
		return getRandomDouble(0, max);
	}

	/**
	 * Get a random position(object) from an array of generic objects. <br/>
	 * Using generics saves the trouble of casting the return object.
	 * 
	 * @param <T>
	 *            the type of the array to get the object from
	 * @param array
	 *            the array with objects
	 * @return random object from given array or null of array is either null or
	 *         empty
	 */
	public static <T> T getRandomPosition(T[] array) {
		if (array == null || array.length == 0) {
			return null;
		}
		return array[getRandomInteger(array.length - 1)];
	}

	/**
	 * Get a random position(object) from a list of generic objects. <br/>
	 * Using generics saves the trouble of casting the return object.
	 * 
	 * @param <T>
	 *            the type of the list objects to get the object from
	 * @param list
	 *            the list with objects
	 * @return random object from given list or null of list is either null or
	 *         empty
	 */
	public static <T> T getRandomPosition(List<T> list) {
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.get(getRandomInteger(list.size() - 1));
	}

	/**
	 * Degrees to radians
	 * 
	 * @param degrees
	 * @return the converted value
	 */
	public static float degreesToRadians(float degrees) {
		return degrees * DEG_TO_RAD;
	}

	/**
	 * Radians to degrees
	 * 
	 * @param degrees
	 * @return the converted value
	 */
	public static float radiansToDegrees(float radians) {
		return radians * RAD_TO_DEG;
	}

	/**
	 * Arc cosine
	 * 
	 * @param value
	 * @return Returns the closest double approximation of the arc cosine of the
	 *         argument within the range [0..pi]. The returned result is within
	 *         1 ulp (unit in the last place) of the real result.
	 */
	public static float acos(float value) {
		return (float) java.lang.Math.acos(value);
	}

	/**
	 * Arc sine
	 * 
	 * @param value
	 * @return Returns the closest double approximation of the arc sine of the
	 *         argument within the range [-pi/2..pi/2]. The returned result is
	 *         within 1 ulp (unit in the last place) of the real result.
	 */
	public static float asin(float value) {
		return (float) java.lang.Math.asin(value);
	}

	/**
	 * Arc tangent
	 * 
	 * @param value
	 * @return Returns the closest double approximation of the arc tangent of
	 *         the argument within the range [-pi/2..pi/2]. The returned result
	 *         is within 1 ulp (unit in the last place) of the real result.
	 */
	public static float atan(float value) {
		return (float) java.lang.Math.atan(value);
	}

	/**
	 * Arc tangent of y/x within the range [-pi..pi]
	 * 
	 * @param a
	 * @param b
	 * @return Returns the closest double approximation of the arc tangent of
	 *         y/x within the range [-pi..pi]. This is the angle of the polar
	 *         representation of the rectangular coordinates (x,y). The returned
	 *         result is within 2 ulps (units in the last place) of the real
	 *         result.
	 */
	public static float atan2(float a, float b) {
		return (float) java.lang.Math.atan2(a, b);
	}

	/**
	 * Tangent of an angle
	 * 
	 * @param angle
	 *            angle
	 * @return the tangent
	 */
	public static float tan(float angle) {
		return (float) java.lang.Math.tan(angle);
	}

	/**
	 * Absolute value
	 * 
	 * @param v
	 *            value
	 * @return returns the absolute value
	 */
	public static float abs(float v) {
		return v > 0 ? v : -v;
	}

	/**
	 * Number's logarithm <br>
	 * Special cases:
	 * 
	 * <li>log(+0.0) = -infinity</li> <li>log(-0.0) = -infinity</li><li>
	 * log((anything < 0) = NaN</li> <li>log(+infinity) = +infinity</li><li>
	 * log(-infinity) = NaN</li><li>log(NaN) = NaN</li>
	 * 
	 * 
	 * @param number
	 * @return Returns the closest double approximation of the natural logarithm
	 *         of the argument. The returned result is within 1 ulp (unit in the
	 *         last place) of the real result.
	 */
	public static float logarithm(float number) {
		return (float) java.lang.Math.log(number);
	}

	/**
	 * Number's Exponencial
	 * 
	 * @param number
	 *            float number
	 * @return Returns the closest double approximation of the natural logarithm
	 *         of the argument. The returned result is within 1 ulp (unit in the
	 *         last place) of the real result.
	 */
	public static float exponencial(float number) {
		return (float) java.lang.Math.exp(number);
	}

	/**
	 * Gets the higher number
	 * 
	 * @param a
	 *            float number
	 * @param b
	 *            float number
	 * @return the higher number between a and b
	 */
	public static float max(float a, float b) {
		return a > b ? a : b;
	}

	/**
	 * Gets the higher number
	 * 
	 * @param a
	 *            int number
	 * @param b
	 *            int number
	 * @return the higher number between a and b
	 */
	public static int max(int a, int b) {
		return a > b ? a : b;
	}

	/**
	 * Gets the lower number
	 * 
	 * @param a
	 *            float number
	 * @param b
	 *            float number
	 * @return the lower number between a and b
	 */
	public static float min(float a, float b) {
		return a < b ? a : b;
	}

	/**
	 * Gets the lower number
	 * 
	 * @param a
	 *            float number
	 * @param b
	 *            float number
	 * @return the lower number between a and b
	 */
	public static int min(int a, int b) {
		return a < b ? a : b;
	}

	/**
	 * Check if a number is Odd
	 * 
	 * @param num
	 *            int number
	 * @return true if the num is odd and false if it's even
	 */
	public static boolean isOdd(int num) {
		return !isEven(num);
	}

	/**
	 * Check if a number is Even
	 * 
	 * @param num
	 *            int number
	 * @return true if the num is even and false if it's odd
	 */
	public static boolean isEven(int num) {
		return (num % 2 == 0);
	}

	/**
	 * Returns a random number between MIN inclusive and MAX exclusive.
	 * 
	 * @param min
	 *            value inclusive
	 * @param max
	 *            value exclusive
	 * @return an int between MIN inclusive and MAX exclusive.
	 */
	public static int getRandomNumber(int min, int max) {
		Random r = new Random();
		return r.nextInt(max - min + 1) + min;
	}
	
	/**
	 * Truncates a value
	 * @param value - value to be truncated
	 * @param places - decimal places
	 * @return
	 */
	public static double truncate(double value, int places) {
	    if (places < 0) {
	        throw new IllegalArgumentException();
	    }

	    long factor = (long) java.lang.Math.pow(10, places);
	    value = value * factor;
	    long tmp = (long) value;
	    return (double) tmp / factor;
	}

}
