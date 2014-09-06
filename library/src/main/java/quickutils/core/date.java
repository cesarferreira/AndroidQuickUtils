package quickutils.core;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;


public class date {
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
    public static java.util.Date getCurrentDate() {
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
     * @param day    Can be: <li>QuickUtils.date.YESTERDAY</li><li>
     *               QuickUtils.date.TODAY</li><li>QuickUtils.date.TOMORROW</li>
     * @param format desired format (e.g. "yyyy-MM-dd HH:mm:ss")
     * @return returns a day with the given format
     */
    public static String getDayAsString(int day, String format) {
        SimpleDateFormat simpleFormat = new SimpleDateFormat(format);
        return simpleFormat.format(getDayAsDate(day));
    }

    /**
     * Gets a date with a desired format as a String
     *
     * @param date   date to be formated
     * @param format desired format (e.g. "yyyy-MM-dd HH:mm:ss")
     * @return returns a date with the given format
     */
    public static String formatDate(long date, String format) {
        return formatDateBase(date, format, null);
    }

    /**
     * Gets a date with a desired format as a String
     *
     * @param date     date to be formated
     * @param format   desired format (e.g. "yyyy-MM-dd HH:mm:ss")
     * @param timeZone specify the intended timezone (e.g. "GMT", "UTC", etc.)
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
     * @param day Can be: <li>QuickUtils.date.YESTERDAY</li><li>
     *            QuickUtils.date.TODAY</li><li>QuickUtils.date.TOMORROW</li>
     * @return returns a Date for that day
     */
    public static java.util.Date getDayAsDate(int day) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }

    /**
     * Parse a data string into a real Date
     * <p/>
     * Note: (e.g. "yyyy-MM-dd HH:mm:ss")
     *
     * @param dateString date in String format
     * @param dateFormat desired format (e.g. "yyyy-MM-dd HH:mm:ss")
     * @return
     */
    public static java.util.Date parseDate(String dateString, String dateFormat) {
        java.util.Date newDate = null;

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


    /**
     * Get number with a suffix
     *
     * @param number number that will be converted
     * @return (e.g. "1" becomes "1st", "3" becomes "3rd", etc)
     */
    public static String getNumberWithSuffix(int number) {
        int j = number % 10;
        if (j == 1 && number != 11) {
            return number + "st";
        }
        if (j == 2 && number != 12) {
            return number + "nd";
        }
        if (j == 3 && number != 13) {
            return number + "rd";
        }
        return number + "th";
    }


    /**
     * Converts a month by number to full text
     *
     * @param month    number of the month 1..12
     * @param useShort boolean that gives "Jun" instead of "June" if true
     * @return returns "January" if "1" is given
     */
    public static String convertMonth(int month, boolean useShort) {
        String monthStr;
        switch (month) {
            default:
                monthStr = "January";
                break;
            case Calendar.FEBRUARY:
                monthStr = "February";
                break;
            case Calendar.MARCH:
                monthStr = "March";
                break;
            case Calendar.APRIL:
                monthStr = "April";
                break;
            case Calendar.MAY:
                monthStr = "May";
                break;
            case Calendar.JUNE:
                monthStr = "June";
                break;
            case Calendar.JULY:
                monthStr = "July";
                break;
            case Calendar.AUGUST:
                monthStr = "August";
                break;
            case Calendar.SEPTEMBER:
                monthStr = "September";
                break;
            case Calendar.OCTOBER:
                monthStr = "October";
                break;
            case Calendar.NOVEMBER:
                monthStr = "November";
                break;
            case Calendar.DECEMBER:
                monthStr = "December";
                break;
        }
        if (useShort) monthStr = monthStr.substring(0, 3);
        return monthStr;
    }


}


