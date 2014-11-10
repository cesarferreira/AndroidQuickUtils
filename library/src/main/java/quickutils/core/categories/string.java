package quickutils.core.categories;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import quickutils.core.collections.Lists;
import quickutils.core.collections.Sets;

/**
 * Created by cesarferreira on 01/11/14.
 */
public class string {

    protected string() {
    }


    public final static String EMPTY = "";
    public final static String ELLIPSIS = "...";
    public final static String COMMA = ",";
    public final static String SPACE = " ";
    public final static String DASH = "-";
    public final static String SLASH = "/";
    public final static String DOT = ".";
    public final static String NEW_LINE = "\n";
    public final static String UNDERSCORE = "_";
    public final static String BANG = "!";
    public final static String PIPE = "|";

    private final static String PLACEHOLDER_PATTERN = "\\$\\{(.*?)\\}";
    private final static String ALPHANUMERIC_PATTERN = "([^\\w\\s])*";

    public static String getNotEmptyString(String text) {
        return string.isEmpty(text) ? null : text;
    }

    public static Boolean equal(String text1, String text2) {
        return defaultString(text1).equals(defaultString(text2));
    }

    public static Boolean isEmpty(String text) {
        return text != null ? text.length() == 0 : true;
    }

    public static Boolean isNotEmpty(String text) {
        return !isEmpty(text);
    }

    /**
     * <p>
     * Checks if a String is whitespace, empty ("") or null.
     * </p>
     * <p/>
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is null, empty or whitespace
     */
    public static boolean isBlank(String str) {
        int strLen;
        if ((str == null) || ((strLen = str.length()) == 0)) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * Checks if a String is not empty (""), not null and not whitespace only.
     * </p>
     * <p/>
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is not empty and not null and not whitespace
     */
    public static boolean isNotBlank(String str) {
        return !string.isBlank(str);
    }

    /**
     * <p>
     * Returns either the passed in String, or if the String is <code>null</code>, an empty String ("").
     * </p>
     * <p/>
     * <pre>
     * StringUtils.defaultString(null)  = ""
     * StringUtils.defaultString("")    = ""
     * StringUtils.defaultString("bat") = "bat"
     * </pre>
     *
     * @param str the String to check, may be null
     * @return the passed in String, or the empty String if it was <code>null</code>
     */
    public static String defaultString(String str) {
        return defaultString(str, EMPTY);
    }

    public static String defaultString(String str, String defaultString) {
        return str == null ? defaultString : str;
    }

    public static String capitalize(String text) {
        if (isEmpty(text) || (text.length() == 1)) {
            return text.toUpperCase();
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

    /**
     * <p>
     * Capitalizes all the whitespace separated words in a String. Only the first letter of each word is changed. To
     * convert the rest of each word to lowercase at the same time, use {@link #capitalize(String)}.
     * </p>
     * <p/>
     * <p>
     * Whitespace is defined by {@link Character#isWhitespace(char)}. A <code>null</code> input String returns
     * <code>null</code>. Capitalization uses the unicode title case, normally equivalent to upper case.
     * </p>
     * <p/>
     * <pre>
     * WordUtils.capitalize(null)        = null
     * WordUtils.capitalize("")          = ""
     * WordUtils.capitalize("i am FINE") = "I Am FINE"
     * </pre>
     *
     * @param str the String to capitalize, may be null
     * @return capitalized String, <code>null</code> if null String input
     */
    public static String capitalizeWords(String str) {
        return capitalizeWords(str, null);
    }

    /**
     * <p>
     * Capitalizes all the delimiter separated words in a String. Only the first letter of each word is changed.
     * </p>
     * <p/>
     * <p>
     * The delimiters represent a set of characters understood to separate words. The first string character and the
     * first non-delimiter character after a delimiter will be capitalized.
     * </p>
     * <p/>
     * <p>
     * A <code>null</code> input String returns <code>null</code>. Capitalization uses the unicode title case, normally
     * equivalent to upper case.
     * </p>
     * <p/>
     * <pre>
     * WordUtils.capitalize(null, *)            = null
     * WordUtils.capitalize("", *)              = ""
     * WordUtils.capitalize(*, new char[0])     = *
     * WordUtils.capitalize("i am fine", null)  = "I Am Fine"
     * WordUtils.capitalize("i aM.fine", {'.'}) = "I aM.Fine"
     * </pre>
     *
     * @param str        the String to capitalize, may be null
     * @param delimiters set of characters to determine capitalization, null means whitespace
     * @return capitalized String, <code>null</code> if null String input
     */
    public static String capitalizeWords(String str, char[] delimiters) {
        int delimLen = (delimiters == null ? -1 : delimiters.length);
        if ((str == null) || (str.length() == 0) || (delimLen == 0)) {
            return str;
        }
        int strLen = str.length();
        StringBuffer buffer = new StringBuffer(strLen);
        boolean capitalizeNext = true;
        for (int i = 0; i < strLen; i++) {
            char ch = str.charAt(i);

            if (isDelimiter(ch, delimiters)) {
                buffer.append(ch);
                capitalizeNext = true;
            } else if (capitalizeNext) {
                buffer.append(Character.toTitleCase(ch));
                capitalizeNext = false;
            } else {
                buffer.append(ch);
            }
        }
        return buffer.toString();
    }

    /**
     * Is the character a delimiter.
     *
     * @param ch         the character to check
     * @param delimiters the delimiters
     * @return true if it is a delimiter
     */
    private static boolean isDelimiter(char ch, char[] delimiters) {
        if (delimiters == null) {
            return Character.isWhitespace(ch);
        }
        for (int i = 0, isize = delimiters.length; i < isize; i++) {
            if (ch == delimiters[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Joins all the strings in the list in a single one separated by the separator sequence.
     *
     * @param objectsToJoin The objects to join.
     * @param separator     The separator sequence.
     * @return The joined strings.
     */
    public static String join(Collection<?> objectsToJoin, String separator) {
        if ((objectsToJoin != null) && !objectsToJoin.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (Object object : objectsToJoin) {
                builder.append(object);
                builder.append(separator);
            }
            // Remove the last separator
            return builder.substring(0, builder.length() - separator.length());
        } else {
            return EMPTY;
        }
    }

    /**
     * Joins all the strings in the list in a single one separated by ','.
     *
     * @param objectsToJoin The objects to join.
     * @return The joined strings.
     */
    public static String join(Collection<?> objectsToJoin) {
        return join(objectsToJoin, COMMA);
    }

    /**
     * Truncate the text adding "..." if the truncateWords parameter is true. The ellipsis will be taken into account
     * when counting the amount of characters.
     *
     * @param text          The text to truncate
     * @param maxCharacters The maximum amount of characters allowed for the returned text
     * @param truncateWords True if the words should be truncated
     * @return The truncated text
     */
    public static String truncate(String text, Integer maxCharacters, Boolean truncateWords) {

        if (isNotBlank(text)) {
            StringBuilder truncatedTextBuilder = new StringBuilder();
            if (text.length() > maxCharacters) {
                if (truncateWords) {

                    // The words are truncated and the ellipsis is added when is possible
                    if (maxCharacters <= ELLIPSIS.length()) {
                        truncatedTextBuilder.append(text.substring(0, maxCharacters));
                    } else {
                        truncatedTextBuilder.append(text.substring(0, maxCharacters - ELLIPSIS.length()));
                        truncatedTextBuilder.append(ELLIPSIS);
                    }
                } else {

                    // The words are not truncated and the ellipsis is not added
                    List<String> words = Lists.newArrayList(text.split(SPACE));
                    Iterator<String> it = words.iterator();
                    int usedChars = 0;
                    Boolean exit = false;
                    while (it.hasNext() && !exit) {
                        String word = it.next();
                        int increment = usedChars == 0 ? word.length() : word.length() + 1;
                        if ((usedChars + increment) <= maxCharacters) {
                            truncatedTextBuilder.append(usedChars == 0 ? word : SPACE + word);
                            usedChars += increment;
                        } else {
                            exit = true;
                        }
                    }
                }
            } else {
                truncatedTextBuilder.append(text);
            }
            return truncatedTextBuilder.toString();
        }
        return text;
    }

    /**
     * Truncate the text adding "...". The ellipsis will be taken into account when counting the amount of characters.
     *
     * @param text          The text to truncate
     * @param maxCharacters The maximum amount of characters allowed for the returned text
     * @return The truncated text
     */
    public static String truncate(String text, Integer maxCharacters) {
        return truncate(text, maxCharacters, true);
    }

    /**
     * Extract all the placeholder's names of the string
     *
     * @param string The whole string with placeholders
     * @return A set with all the placeholder's names
     */
    public static Set<String> extractPlaceHolders(String string) {
        Matcher matcher = Pattern.compile(PLACEHOLDER_PATTERN).matcher(string);
        Set<String> placeHolders = Sets.newHashSet();
        while (matcher.find()) {
            placeHolders.add(matcher.group(1));
        }
        return placeHolders;
    }

    /**
     * Transform the received value removing all the not alphanumeric or spaces characters
     *
     * @param value The string to transform
     * @return The transformed string
     */
    public static String toAlphanumeric(String value) {
        return Pattern.compile(ALPHANUMERIC_PATTERN).matcher(value).replaceAll("");
    }

    public static Collection<String> splitToCollection(String text) {
        return splitToCollection(text, COMMA);
    }

    public static Collection<String> splitToCollection(String text, String separator) {
        Collection<String> values = Lists.newArrayList();
        if (isNotEmpty(text)) {
            values = Lists.newArrayList(text.split(separator));
        }
        return values;
    }

    /**
     * Returns the first token of the string if that string can be split by the token, else return the unmodified input
     * string
     *
     * @param string The string to split
     * @param token  The token to use as splitter
     * @return The resultant string
     */
    public static String getFirstToken(String string, String token) {
        if ((string != null) && string.contains(token)) {
            return string.split(token)[0];
        }
        return string;
    }

    /**
     * Returns the first token of the string if that string can be split by a ",", else return the unmodified input
     * string
     *
     * @param string The string to split
     * @return The resultant string
     */
    public static String getFirstToken(String string) {
        return getFirstToken(string, COMMA);
    }

    /**
     * This method word wrap a text to two lines if the text has multiple words and its length is greater than the
     * specified minLength. To do the word wrap, the white space closest to the middle of the string is replaced by a
     * "\n" character
     *
     * @param text      A Sting, the text to word wrap to two lines.
     * @param minLength The min text length to appy word wrap.
     * @return The input text word wrapped to two lines or the original text.
     */
    public static String wordWrapToTwoLines(String text, int minLength) {
        String wordWrapText = text != null ? text.trim() : null;
        if ((wordWrapText != null) && (wordWrapText.length() > minLength)) {
            int middle = wordWrapText.length() / 2;
            int leftSpaceIndex = wordWrapText.substring(0, middle).lastIndexOf(SPACE);
            int rightSpaceIndex = wordWrapText.indexOf(SPACE, middle);
            int wordWrapIndex = rightSpaceIndex;
            if ((leftSpaceIndex >= 0)
                    && ((rightSpaceIndex < 0) || ((middle - leftSpaceIndex) < (rightSpaceIndex - middle)))) {
                wordWrapIndex = leftSpaceIndex;
            }
            if (wordWrapIndex >= 0) {
                wordWrapText = wordWrapText.substring(0, wordWrapIndex) + "\n"
                        + wordWrapText.substring(wordWrapIndex + 1);
            }
        }

        return wordWrapText;
    }

    public static String replaceValues(final String template, final Map<String, String> values) {

        final StringBuffer sb = new StringBuffer();
        final Pattern pattern = Pattern.compile("\\$\\{(.*?)\\}", Pattern.DOTALL);
        final Matcher matcher = pattern.matcher(template);
        while (matcher.find()) {
            final String key = matcher.group(1);
            final String replacement = values.get(key);
            if (replacement == null) {
                throw new IllegalArgumentException("Template contains unmapped key: " + key);
            }
            matcher.appendReplacement(sb, replacement);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static boolean hasOnlyCharacters(String name) {
        if (isEmpty(name)) {
            return false;
        }
        return name.matches("[A-Za-z\\s]*");
    }

    /**
     * Turns a camel case string into an underscored one, e.g. "HelloWorld"
     * becomes "hello_world".
     *
     * @param camelCaseString
     *        the string to underscore
     * @return the underscored string
     */
    public static String underscore(String camelCaseString) {
        String[] words = splitByCharacterTypeCamelCase(camelCaseString);
        return TextUtils.join("_", words).toLowerCase();
    }


    /**
     * <p>
     * Splits a String by Character type as returned by
     * <code>java.lang.Character.getType(char)</code>. Groups of contiguous
     * characters of the same type are returned as complete tokens, with the
     * following exception: the character of type
     * <code>Character.UPPERCASE_LETTER</code>, if any, immediately preceding a
     * token of type <code>Character.LOWERCASE_LETTER</code> will belong to the
     * following token rather than to the preceding, if any,
     * <code>Character.UPPERCASE_LETTER</code> token.
     *
     * <pre>
     * StringUtils.splitByCharacterTypeCamelCase(null)         = null
     * StringUtils.splitByCharacterTypeCamelCase("")           = []
     * StringUtils.splitByCharacterTypeCamelCase("ab de fg")   = ["ab", " ", "de", " ", "fg"]
     * StringUtils.splitByCharacterTypeCamelCase("ab   de fg") = ["ab", "   ", "de", " ", "fg"]
     * StringUtils.splitByCharacterTypeCamelCase("ab:cd:ef")   = ["ab", ":", "cd", ":", "ef"]
     * StringUtils.splitByCharacterTypeCamelCase("number5")    = ["number", "5"]
     * StringUtils.splitByCharacterTypeCamelCase("fooBar")     = ["foo", "Bar"]
     * StringUtils.splitByCharacterTypeCamelCase("foo200Bar")  = ["foo", "200", "Bar"]
     * StringUtils.splitByCharacterTypeCamelCase("ASFRules")   = ["ASF", "Rules"]
     * </pre>
     *
     * @param str
     *        the String to split, may be <code>null</code>
     * @return an array of parsed Strings, <code>null</code> if null String
     *         input
     * @since 2.4
     */
    public static String[] splitByCharacterTypeCamelCase(String str) {
        return splitByCharacterType(str, true);
    }

    /**
     * <p>
     * Splits a String by Character type as returned by
     * <code>java.lang.Character.getType(char)</code>. Groups of contiguous
     * characters of the same type are returned as complete tokens, with the
     * following exception: if <code>camelCase</code> is <code>true</code>, the
     * character of type <code>Character.UPPERCASE_LETTER</code>, if any,
     * immediately preceding a token of type
     * <code>Character.LOWERCASE_LETTER</code> will belong to the following
     * token rather than to the preceding, if any,
     * <code>Character.UPPERCASE_LETTER</code> token.
     *
     * @param str
     *        the String to split, may be <code>null</code>
     * @param camelCase
     *        whether to use so-called "camel-case" for letter types
     * @return an array of parsed Strings, <code>null</code> if null String
     *         input
     * @since 2.4
     */
    private static String[] splitByCharacterType(String str, boolean camelCase) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return new String[0];
        }
        char[] c = str.toCharArray();
        ArrayList<String> list = new ArrayList<String>();
        int tokenStart = 0;
        int currentType = Character.getType(c[tokenStart]);
        for (int pos = tokenStart + 1; pos < c.length; pos++) {
            int type = Character.getType(c[pos]);
            if (type == currentType) {
                continue;
            }
            if (camelCase && type == Character.LOWERCASE_LETTER
                    && currentType == Character.UPPERCASE_LETTER) {
                int newTokenStart = pos - 1;
                if (newTokenStart != tokenStart) {
                    list.add(new String(c, tokenStart, newTokenStart - tokenStart));
                    tokenStart = newTokenStart;
                }
            } else {
                list.add(new String(c, tokenStart, pos - tokenStart));
                tokenStart = pos;
            }
            currentType = type;
        }
        list.add(new String(c, tokenStart, c.length - tokenStart));
        return (String[]) list.toArray(new String[list.size()]);
    }

}
