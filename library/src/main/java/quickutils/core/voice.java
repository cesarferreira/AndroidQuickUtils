package quickutils.core;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;

import java.util.ArrayList;
import java.util.List;

public class voice {
	protected voice() {
	}
	
	/**
	 * Start google activity of speechRecognition (needed on
	 * onActivityResult(int requestCode, int resultCode, Intent data) to call
	 * getSpeechRecognitionResults() to get the results)
	 * 
	 * @param activity
	 *            - activity
	 * @param maxResults
	 *            - Max number of results that you want to get
	 * @param text
	 *            - what will ask to user when activity start
	 */
	public static void speechRecognition(final Activity activity, int maxResults, String text) {
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT, text);
		intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, maxResults);
		activity.startActivityForResult(intent, 0);
	}

	/**
	 * Get all results from the Google Speech Recognition activity (DO NOT FORGET call this function on
	 * onActivityResult())
	 * 
	 * @param requestCode
	 *            - onActivityResult request code
	 * @param resultCode
	 *            - onActivityResult result code
	 * @param data
	 *            - onActivityResult Intent data
	 * @return ArrayList<String> with all results or null if was not possible to
	 *         get any results
	 */
	public static ArrayList<String> getSpeechRecognitionResults(int requestCode, int resultCode, Intent data) {
		ArrayList<String> matches = null;
		if (requestCode == 0 && resultCode == -1) {
			matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			StringBuilder sb = new StringBuilder();
			for (String match : matches) {
				sb.append(match + ", ");
			}
		}
		return matches;
	}

	/**
	 * Get the first result that matches the Result List from Google Speech
	 * Recognition activity (DO NOT FORGET call this function on onActivityResult()) and the Dictionary
	 * given
	 * 
	 * @param requestCode
	 *            - onActivityResult request code
	 * @param resultCode
	 *            - onActivityResult result code
	 * @param data
	 *            - onActivityResult Intent data
	 * @param array
	 *            - Dictionary with all keywords
	 * 
	 * @return String with the first result matched or null if was not possible
	 *         to get any result
	 */
	public static String getSpeechRecognitionResultFromDicionary(int requestCode, int resultCode, Intent data, ArrayList<String> array) {
		ArrayList<String> matches = null;
		if (requestCode == 0 && resultCode == -1) {
			matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			StringBuilder sb = new StringBuilder();
			for (String match : matches) {
				for (String arrayString : array) {
					if (arrayString.equals(match)) {
						return match;
					}
				}

			}
		}
		return null;
	}

	/**
	 * Get first result from the Google Speech Recognition activity (DO NOT FORGET call this function on
	 * onActivityResult())
	 * 
	 * @param requestCode
	 *            - onActivityResult request code
	 * @param resultCode
	 *            - onActivityResult result code
	 * @param data
	 *            - onActivityResult Intent data
	 * @return string containing the first result of what was recognized
	 */
	public static String getSpeechRecognitionFirstResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 0 && resultCode == -1) {
			List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			if (results != null && results.size() > 0) {
				return results.get(0);
			}
		}
		return null;
	}
}
