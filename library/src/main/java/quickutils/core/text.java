package quickutils.core;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class text {
    /**
     * private constructor
     */
    protected text() {
    }

    /**
     * Common Lorem Ipsum sentence
     */
    public static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, "
            + "consectetur adipisicing elit, sed do eiusmod tempor incididunt "
            + "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis"
            + " nostrud exercitation ullamco laboris nisi ut aliquip ex ea "
            + "commodo consequat. Duis aute irure dolor in reprehenderit in "
            + "voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
            + "Excepteur sint occaecat cupidatat non proident, sunt in culpa "
            + "qui officia deserunt mollit anim id est laborum.";

    /**
     * Words used in Lorem Ipsum
     */
    private static final String[] DICTIONARY = new String[]{
            "exercitationem", "perferendis", "perspiciatis", "laborum",
            "eveniet", "sunt", "iure", "nam", "nobis", "eum", "cum",
            "officiis", "excepturi", "odio", "consectetur", "quasi", "aut",
            "quisquam", "vel", "eligendi", "itaque", "non", "odit",
            "tempore", "quaerat", "dignissimos", "facilis", "neque",
            "nihil", "expedita", "vitae", "vero", "ipsum", "nisi", "animi",
            "cumque", "pariatur", "velit", "modi", "natus", "iusto",
            "eaque", "sequi", "illo", "sed", "ex", "et", "voluptatibus",
            "tempora", "veritatis", "ratione", "assumenda", "incidunt",
            "nostrum", "placeat", "aliquid", "fuga", "provident",
            "praesentium", "rem", "necessitatibus", "suscipit", "adipisci",
            "quidem", "possimus", "voluptas", "debitis", "sint",
            "accusantium", "unde", "sapiente", "voluptate", "qui",
            "aspernatur", "laudantium", "soluta", "amet", "quo", "aliquam",
            "saepe", "culpa", "libero", "ipsa", "dicta", "reiciendis",
            "nesciunt", "doloribus", "autem", "impedit", "minima",
            "maiores", "repudiandae", "ipsam", "obcaecati", "ullam",
            "enim", "totam", "delectus", "ducimus", "quis", "voluptates",
            "dolores", "molestiae", "harum", "dolorem", "quia",
            "voluptatem", "molestias", "magni", "distinctio", "omnis",
            "illum", "dolorum", "voluptatum", "ea", "quas", "quam",
            "corporis", "quae", "blanditiis", "atque", "deserunt",
            "laboriosam", "earum", "consequuntur", "hic", "cupiditate",
            "quibusdam", "accusamus", "ut", "rerum", "error", "minus",
            "eius", "ab", "ad", "nemo", "fugit", "officia", "at", "in",
            "id", "quos", "reprehenderit", "numquam", "iste", "fugiat",
            "sit", "inventore", "beatae", "repellendus", "magnam",
            "recusandae", "quod", "explicabo", "doloremque", "aperiam",
            "consequatur", "asperiores", "commodi", "optio", "dolor",
            "labore", "temporibus", "repellat", "veniam", "architecto",
            "est", "esse", "mollitia", "nulla", "a", "similique", "eos",
            "alias", "dolore", "tenetur", "deleniti", "porro", "facere",
            "maxime", "corrupti"};
    /**
     * Common words used in Lorem Ipsum
     */
    private static final String[] COMMON_WORDS = new String[]{"lorem",
            "ipsum", "dolor", "sit", "amet", "consectetur", "adipisicing",
            "elit", "sed", "do", "eiusmod", "tempor", "incididunt", "ut",
            "labore", "et", "dolore", "magna", "aliqua"};

    private final static String EMPTY_STRING = "";

    /**
     * Joins a Collection of typed objects using their toString method, separated by delimiter
     *
     * @param collection Collection of objects
     * @param delimiter  (optional) String to separate the items of the collection in the joined string - <code>null</code> is interpreted as empty string
     * @return the joined string
     */
    public static String join(Collection<?> collection, String delimiter) {

        if (collection == null || collection.isEmpty()) {
            return EMPTY_STRING;
        } else if (delimiter == null) {
            delimiter = EMPTY_STRING;
        }

        StringBuilder builder = new StringBuilder();
        Iterator<?> it = collection.iterator();

        while (it.hasNext()) {
            builder.append(it.next()).append(delimiter);
        }

        int length = builder.length();
        builder.delete(length - delimiter.length(), length);
        return builder.toString();
    }


    /**
     * Returns a list of words.
     *
     * @param count      the number of words to get
     * @param startLorem whether to start with standard Lorem Ipsum text
     * @return a list of lorem ipsum words
     */
    public static List<String> getWords(int count, boolean startLorem) {
        LinkedList<String> wordList = new LinkedList<String>();

        if (startLorem) {
            if (count > COMMON_WORDS.length) {
                wordList.addAll(Arrays.asList(COMMON_WORDS));

            } else {
                // add first "count" words
                for (int i = 0; i < count; i++) {
                    wordList.add(COMMON_WORDS[i]);
                }
            }
        }

        // add remaining words (random)
        for (int i = wordList.size(); i < count; i++) {
            wordList.add(QuickUtils.math.getRandomPosition(DICTIONARY));
        }

        return wordList;
    }

    /**
     * Returns a randomly generated sentence of lorem ipsum text. <br/>
     * The first word is capitalized, and the sentence ends in either a
     * period, exclamation mark or question mark. Commas are added at
     * random.<br/>
     * Same as {@code getSentence(13, startLorem); }<br/>
     *
     * @param startLorem whether to start with standard lorem ipsum text or not
     * @return generated sentence, with punctuation
     * @see LoremIpsum#getSentence(int, boolean) getSentence(int, boolean)
     */
    public static String getSentence(boolean startLorem) {
        return getSentence(13, startLorem);
    }

    /**
     * Returns a randomly generated sentence of lorem ipsum text. <br/>
     * The first word is capitalized, and the sentence ends in either a
     * period, exclamation mark or question mark. Commas are added at
     * random.
     *
     * @param maxNumWords the length of the word
     * @param startLorem  whether to start with standard lorem ipsum text or not
     * @return generated sentence, with punctuation
     */
    public static String getSentence(int maxNumWords, boolean startLorem) {
        // number of words varies between 3 and 15
        int wordCount = QuickUtils.math.getRandomInteger(3, maxNumWords);
        List<String> words = getWords(wordCount, startLorem);

        // run trough string, add commas every 3-10 words
        int i = 0;
        while (i < wordCount) {
            // decide a jump 3-end words
            i += QuickUtils.math.getRandomInteger(3, wordCount);
            if (i < wordCount - 1) {
                words.set(i, words.get(i) + ",");
            }
        }

        // add end puntuation - rand between '.', '!' and '?'.
        // '.' has most possibility of coming out
        char end = '.';
        switch (QuickUtils.math.getRandomInteger(10)) {
            case 9:
                end = '?';
                break;
            case 8:
                end = '!';
                break;
        }
        words.set(wordCount - 1, words.get(wordCount - 1) + end);
        String raw = implode(words.toArray(new String[words.size()]), " ");
        return capitalize(raw);
    }

    /**
     * Get a paragraph of lorem ipsum text. A paragraph is made of <i>n</i>
     * number of sentences. <i>n</i> is random between 1 and
     * {@code maxSenteces } sentences. <br/>
     * This is the same as {@code getParagraph(4, startLorem); }
     *
     * @param startLorem whether to start first paragraph with standard lorem ipsum
     *                   text or not.
     * @return generated paragraph
     * @see LoremIpsum#getParagraph(int, boolean) getParagraph(int, boolean)
     */
    public static String getParagraph(boolean startLorem) {
        return getParagraph(4, startLorem);
    }

    /**
     * Get a paragraph of lorem ipsum text. A paragraph is made of <i>n</i>
     * number of sentences. <i>n</i> is random between 1 and
     * {@code maxSenteces } sentences.
     *
     * @param maxSentences maximum number of sentences to include in paragraph
     * @param startLorem   whether to start first paragraph with standard lorem ipsum
     *                     text or not.
     * @return generated paragraph
     */
    public static String getParagraph(int maxSentences, boolean startLorem) {
        String paragraph = "";
        int sentences = QuickUtils.math.getRandomInteger(1, maxSentences);
        for (int i = 0; i < sentences; i++) {
            paragraph += getSentence(startLorem);
            startLorem = false; // run this only once
        }
        return paragraph;
    }

    /**
     * Implodes an array into a string Similar to PHP implode function
     *
     * @param inputArr  the array to implode
     * @param delimiter the delimiter that will be used to merge items in array
     * @return the imploded string
     */
    public static String implode(String[] inputArr, String delimiter) {
        String implodedStr = "";

        implodedStr += inputArr[0];
        for (int i = 1; i < inputArr.length; i++) {
            implodedStr += delimiter;
            implodedStr += inputArr[i];
        }
        return implodedStr;
    }

    /**
     * Capitalize first word in a string
     *
     * @param input string to capitalize
     * @return capitalized string(first letter only)
     */
    public static String capitalize(String input) {
        char[] stringArray = input.toCharArray();
        stringArray[0] = Character.toUpperCase(stringArray[0]);
        return new String(stringArray);
    }


    /**
     * The following snippets remove from a String accented letters and replace them by their regular ASCII equivalent.
     *
     * @param input String that will be transformed
     * @return If we pass "à", the method returns "a"
     */
    public static String unAccent(String input) {
        String temp = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }


}
