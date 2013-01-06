package quickutils.core.util.misc;

import java.util.ArrayList;
import java.util.List;

import quickutils.core.QuickUtils;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.RecognizerIntent;
import android.widget.Toast;

public class misc {

	/**
	 * private constructor
	 */
	protected misc() {
	}

	/**
	 * Navigate to an activity programmatically by providing the package +
	 * activity name
	 * 
	 * @param context
	 *            Context where I am coming from
	 * @param className
	 *            Full path to the desired Activity (e.g.
	 *            "com.sample.MainActivity")
	 */
	public static void navigateToActivityByClassName(Context context, String className) throws ClassNotFoundException {
		Class<?> c = null;
		if (className != null) {
			try {
				c = Class.forName(className);
			} catch (ClassNotFoundException e) {
				QuickUtils.log.d("ClassNotFound", e);
			}
		}

		navigateToActivity(context, c);
	}

	/**
	 * Navigate to an activity programmatically by providing the package +
	 * activity name
	 * 
	 * @param context
	 *            Activity where I am coming from
	 * @param className
	 *            Full path to the desired Activity (e.g.
	 *            "com.sample.MainActivity")
	 * @param flags
	 *            flags to be set upon the launch of the B Activity
	 */
	public static void navigateToActivityByClassName(Context context, String className, int flags) throws ClassNotFoundException {
		Class<?> c = null;
		if (className != null) {
			try {
				c = Class.forName(className);
			} catch (ClassNotFoundException e) {
				QuickUtils.log.d("ClassNotFound", e);
			}
		}

		navigateToActivity(context, c, flags);
	}

	/**
	 * Navigate to an activity.<br>
	 * e.g. navigateToActivity(context, SecondActivity.class)
	 * 
	 * @param A
	 *            From Activity
	 * @param B
	 *            Destination Activity
	 * 
	 */
	public static void navigateToActivity(Context A, Class<?> B) {
		Intent myIntent = new Intent(A, B);
		A.startActivity(myIntent);
	}

	/**
	 * Navigate to an activity.<br>
	 * e.g. navigateToActivity(this,SecondActivity.class,
	 * Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK)
	 * 
	 * @param A
	 *            From Activity
	 * @param B
	 *            Destination Activity
	 * @param flags
	 *            flags to be set upon the launch of the B Activity
	 * 
	 */
	public static void navigateToActivity(Context A, Class<?> B, int flags) {
		Intent myIntent = new Intent(A, B);
		myIntent.setFlags(flags);
		A.startActivity(myIntent);
	}

	/**
	 * Navigate to an activity.<br>
	 * e.g. navigateToActivity(this,SecondActivity.class, extras)
	 * 
	 * @param A
	 *            From Activity
	 * @param B
	 *            Destination Activity
	 * @param extras
	 *            Extras to be included in the new Activity
	 */
	public static void navigateToActivity(Context A, Class<?> B, Bundle extras) {
		Intent myIntent = new Intent(A, B);
		myIntent.putExtras(extras);
		A.startActivity(myIntent);
	}

	/**
	 * Navigate to an activity.<br>
	 * e.g. navigateToActivity(this,SecondActivity.class, extras,
	 * Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK))
	 * 
	 * @param A
	 *            From Activity
	 * @param B
	 *            Destination Activity
	 * @param extras
	 *            Extras to be included in the new Activity
	 * @param flags
	 *            flags to be set upon the launch of the B Activity
	 */
	public static void navigateToActivity(Context A, Class<?> B, Bundle extras, int flags) {
		Intent myIntent = new Intent(A, B);
		myIntent.putExtras(extras);
		myIntent.setFlags(flags);
		A.startActivity(myIntent);
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
		activity.startActivityForResult(intent, QuickUtils.REQUEST_CODE);
	}

	/**
	 * Get all results from the Google Speech Recognition activity (to be called
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
		if (requestCode == 0 && resultCode == QuickUtils.RESULT_OK) {
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
	 * Recognition activity (to be called onActivityResult()) and the Dictionary
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
		if (requestCode == 0 && resultCode == QuickUtils.RESULT_OK) {
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
	 * Get first result from the Google Speech Recognition activity (to be
	 * called onActivityResult())
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
		if (requestCode == 0 && resultCode == QuickUtils.RESULT_OK) {
			List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			if (results != null && results.size() > 0) {
				return results.get(0);
			}
		}
		return null;
	}

	/**
	 * Make the smartphone vibrate for a giving time.you need to put the
	 * vibration permission in the manifest as follows: <uses-permission
	 * android:name="android.permission.VIBRATE"/>
	 * 
	 * 
	 * @param context
	 *            context in which the smartphone will vibrate
	 * @param duration
	 *            duration of the vibration in miliseconds
	 * 
	 */
	public static void vibrate(Context context, int duration) {
		Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		v.vibrate(duration);
	}

	/**
	 * Quick toast method with short duration
	 * 
	 * @param context
	 *            context in which will be displayed
	 * @param message
	 *            toast content
	 */
	public static void toast(Context context, String message) {

		Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		toast.show();
	}

	/**
	 * Quick toast method
	 * 
	 * @param context
	 *            context in which will be displayed
	 * @param message
	 *            The text to show. Can be formatted text.
	 * @param duration
	 *            How long to display the message. Either Toast.LENGTH_SHORT or
	 *            Toast.LENGTH_LONG
	 */
	public static void toast(Context context, String message, int duration) {

		Toast toast = Toast.makeText(context, message, duration);
		toast.show();
	}

	/**
	 * Sleep
	 * 
	 * @param milliseconds
	 *            seconds that the app will sleep
	 */
	public static void sleep(int milliseconds) {

		try {
			QuickUtils.log.i("delaying for " + milliseconds / 1000 + " seconds");
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			QuickUtils.log.e("Interrupted exception", e);
		}
	}

}
