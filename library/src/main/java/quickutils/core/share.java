package quickutils.core;

import java.util.List;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

public class share {

	/**
	 * Share via Email
	 * 
	 * @param context
	 *            application context
	 * @param email
	 *            destination email (e.g. support@company.com)
	 * @param subject
	 *            email subject
	 * @param emailBody
	 *            email body
	 */
	public static void sendEmail(Context context, String email, String subject, String emailBody) {
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		emailIntent.setType("message/rfc822");
		emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { email });
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
		emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody);

		emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		try {
			context.startActivity(emailIntent);
		} catch (android.content.ActivityNotFoundException ex) {
			QuickUtils.system.toast(context, "There are no email clients installed.");
		}
	}

	/**
	 * Share message via Twitter
	 * 
	 * @param context
	 *            Application Context
	 * @param message
	 *            Message to be delivered
	 */
	public static void sendTweet(Context context, String message) {
		shareMethod(context, message, "com.twitter.android.PostActivity");
	}

	/**
	 * Share message via Facebook (for the time being, facebook api has a bug
	 * that prevents this from working, we hope facebook solves it fast enough!)
	 * 
	 * @param context
	 *            Application Context
	 * @param message
	 *            Message to be delivered
	 */
	@Deprecated
	public static void updateFacebookStatus(Context context, String message) {
		shareMethod(context, message, "facebook");
	}

	/**
	 * Private method that handles facebook and twitter sharing
	 * 
	 * @param context
	 *            Application Context
	 * @param message
	 *            Message to be delivered
	 * @param activityInfoName
	 */
	private static void shareMethod(Context context, String message, String activityInfoName) {
		Intent shareIntent = new Intent(Intent.ACTION_SEND);
		shareIntent.setType("text/plain");
		shareIntent.putExtra(Intent.EXTRA_TEXT, message);
		PackageManager pm = context.getPackageManager();
		List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
		for (final ResolveInfo app : activityList) {
			if ((app.activityInfo.name).contains(activityInfoName)) {
				final ActivityInfo activity = app.activityInfo;
				final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
				shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
				shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
				shareIntent.setComponent(name);
				context.startActivity(shareIntent);
				break;
			}
		}
	}

	/**
	 * Generic method for sharing that Deliver some data to someone else. Who
	 * the data is being delivered to is not specified; it is up to the receiver
	 * of this action to ask the user where the data should be sent.
	 * 
	 * @param context
	 *            Application Context
	 * @param subject
	 *            The title, if applied
	 * @param message
	 *            Message to be delivered
	 */
	public static void genericSharing(Context context, String subject, String message) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, message);
		intent.putExtra(Intent.EXTRA_SUBJECT, subject);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
}
