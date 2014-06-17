package quickutils.core;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import quickutils.core.QuickUtils;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.Settings;
import android.speech.RecognizerIntent;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

public class system {

	/**
	 * private constructor
	 */
	protected system() {
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
	 * Your app key hash is required for example, for Facebook Login in order to
	 * perform security check before authorizing your app.
	 * 
	 * @param context
	 *            Application context
	 * @param packageName
	 *            name of the package (e.g. "com.example.app")
	 * @return the application hash key
	 */
	public static String getApplicationHashKey(Context context, String packageName) {

		String hash = "";
		try {
			PackageInfo info = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				hash = Base64.encodeToString(md.digest(), Base64.DEFAULT);
			}
		} catch (NameNotFoundException e) {
			QuickUtils.log.e("NameNotFoundException");
		} catch (NoSuchAlgorithmException e) {
			QuickUtils.log.e("NoSuchAlgorithmException");

		}
		return hash;
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


    /**
     * Get device ID
     * @param context application context
     * @return
     */
    public static String getDeviceID(Context context) {
        return   Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }




}
