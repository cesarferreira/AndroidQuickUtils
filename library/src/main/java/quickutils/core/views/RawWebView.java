package quickutils.core.views;

/**
 * Created by cesarferreira on 9/6/14.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * A WebView that allows you to load a html file from your /res/raw directory
 */
public class RawWebView extends WebView {

    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final String TEXT_HTML = "text/html";
    private static final String ANDROID_RAW_BASE_URL = "file:///android_res/raw";
    private static final String FAIL_URL = null;
    private final StreamTils streamTils;

    public RawWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        streamTils = new StreamTils();
    }

    public RawWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        streamTils = new StreamTils();
    }

    public void loadRawData(String html) {
        loadDataWithBaseURL(ANDROID_RAW_BASE_URL, html, TEXT_HTML, DEFAULT_ENCODING, FAIL_URL);
    }

    /**
     * Using this method with Proguard may result it missing file behaviour.
     * Recommended {@link #loadRawResource(int)} instead.
     *
     * @param rawFileName a filename in your /raw/ directory i.e. "novoda.html"
     */
    public void loadRawUrl(String rawFileName) {
        loadUrl(ANDROID_RAW_BASE_URL + "/" + rawFileName);
    }

    /**
     * @param rawResourceId the corresponding id of a filename in your /raw/ directory i.e. "R.raw.novoda"
     */
    public void loadRawResource(int rawResourceId) {
        InputStream input = null;
        try {
            input = getResources().openRawResource(rawResourceId);
            String html = streamTils.loadFrom(input);
            loadRawData(html);
        } finally {
            streamTils.tryClose(input);
        }
    }

    private static class StreamTils {

        private static final String END_OF_STREAM = "\\A";

        private String loadFrom(InputStream input) {
            Scanner scanner = new Scanner(input, DEFAULT_ENCODING).useDelimiter(END_OF_STREAM);
            return scanner.hasNext() ? scanner.next() : "";
        }

        private void tryClose(Closeable input) {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
