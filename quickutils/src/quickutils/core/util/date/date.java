package quickutils.core.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import android.text.format.Time;

import quickutils.core.QuickUtils;

public  class date {
	/**
	 * private constructor
	 */
//	private date() {
//	}

	public static final int YESTERDAY = -1;
	public static final int TODAY = 0;
	public static final int TOMORROW = 1;

	/**
	 * Gets the current year
	 * 
	 * @return current year
	 */
	public static int getCurrentYear() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR);
	}

	/**
	 * Gets the current month
	 * 
	 * @return current month
	 */
	public static int getCurrentMonth() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MONTH);
	}

	/**
	 * Gets the current day
	 * 
	 * @return current day
	 */
	public static int getCurrentDay() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * Gets the current date
	 * 
	 * @return current date
	 */
	public static Date getCurrentDate() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * Miliseconds since midnight
	 * 
	 * @return the number of miliseconds since midnight
	 */
	public static long getTimeSinceMidnight() {
		Calendar c = Calendar.getInstance();
		long now = c.getTimeInMillis();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return now - c.getTimeInMillis();
	}

	/**
	 * Gets a date with a desired format as a String
	 * 
	 * @param day
	 *            Can be: <li>QuickUtils.date.YESTERDAY</li><li>
	 *            QuickUtils.date.TODAY</li><li>QuickUtils.date.TOMORROW</li>
	 * @param format
	 *            desired format (e.g. "yyyy-MM-dd HH:mm:ss")
	 * @return returns a day with the given format
	 */
	public static String getDayAsString(int day, String format) {
		SimpleDateFormat simpleFormat = new SimpleDateFormat(format);
		return simpleFormat.format(getDayAsDate(day));
	}

	/**
	 * Gets a date with a desired format as a String
	 * 
	 * @param date
	 *            date to be formated
	 * 
	 * @param format
	 *            desired format (e.g. "yyyy-MM-dd HH:mm:ss")
	 * @return returns a date with the given format
	 */
	public static String formatDate(long date, String format) {
		return formatDateBase(date, format, null);
	}

	/**
	 * Gets a date with a desired format as a String
	 * 
	 * @param date
	 *            date to be formated
	 * 
	 * @param format
	 *            desired format (e.g. "yyyy-MM-dd HH:mm:ss")
	 * 
	 * @param timeZone
	 *            specify the intended timezone (e.g. "GMT", "UTC", etc.)
	 * @return returns a date with the given format
	 */
	public static String formatDate(long date, String format,
			String timeZone) {
		return formatDateBase(date, format, timeZone);
	}

	private static String formatDateBase(long date, String format,
			String timeZone) {
		DateFormat simpleFormat = new SimpleDateFormat(format);
		if (timeZone != null) {
			simpleFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
		} else {
			simpleFormat.setTimeZone(TimeZone.getDefault());
		}
		return simpleFormat.format(date);
	}

	/**
	 * Gets the desired day as a Date
	 * 
	 * @param day
	 *            Can be: <li>QuickUtils.date.YESTERDAY</li><li>
	 *            QuickUtils.date.TODAY</li><li>QuickUtils.date.TOMORROW</li>
	 * @return returns a Date for that day
	 * 
	 */
	public static Date getDayAsDate(int day) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, day);
		return cal.getTime();
	}

	/**
	 * Parse a data string into a real Date
	 * 
	 * Note: (e.g. "yyyy-MM-dd HH:mm:ss")
	 * 
	 * @param dateString
	 *            date in String format
	 * @param dateFormat
	 *            desired format (e.g. "yyyy-MM-dd HH:mm:ss")
	 * 
	 * @return
	 */
	public static Date parseDate(String dateString, String dateFormat) {
		Date newDate = null;

		try {
			newDate = new SimpleDateFormat(dateFormat, Locale.ENGLISH)
					.parse(dateString);
		} catch (ParseException e) {
			QuickUtils.log.d("parse error", e);
		}
		return newDate;
	}

	/**
	 * get Current time in milliseconds
	 * 
	 * @return current time in milliseconds
	 */
	public static long getCurrentTimeInMiliseconds() {
		return TimeUnit.MILLISECONDS.toMillis(Calendar.getInstance()
				.getTimeInMillis());
	}

	/**
	 * get Current time in seconds
	 * 
	 * @return current time in seconds
	 */
	public static long getCurrentTimeInSeconds() {
		return TimeUnit.SECONDS.toSeconds(Calendar.getInstance()
				.getTimeInMillis());

	}

}


