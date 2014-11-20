package quickutils.core.categories;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

/**
 * Created by cesarferreira on 02/11/14.
 */
public class validation {

    protected validation() {

    }

    public static final Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9\\+-]+(\\.[_A-Za-z0-9\\+-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z]{2,}){1}$)");

    /**
     * Validate the email address
     *
     * @param value The email address to validate
     * @return True if the value is a valid email address
     */
    public static boolean isValidEmail(String value) {
        return validation.match(value, validation.EMAIL_PATTERN);
    }

    private static boolean match(String value, Pattern pattern) {
        return string.isNotEmpty(value) && pattern.matcher(value).matches();
    }

    /**
     * Validate the URL
     *
     * @param value The URL to validate
     * @return True if the value is a valid URL
     */
    public static boolean isValidURL(String value) {
        try {
            new URL(value);
            return true;
        } catch (MalformedURLException ex) {
            return false;
        }
    }

    /**
     * Validate the URL without protocol
     *
     * @param value The URL to validate
     * @return True if the value is a valid URL
     */
    public static boolean isValidURLWithoutProtocol(String value) {
        return isValidURL("http://" + value);
    }

    public static boolean isValidDouble(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
