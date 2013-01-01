package quickutils.core.util.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import quickutils.core.QuickUtils;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;

public  class web {
	/**
	 * private constructor
	 */
//	private web() {
//	}

	/**
	 * Checks if the app has connectivity to the Internet
	 * 
	 * @param context
	 *            application context
	 * @return true if has connection to the Internet and false if it
	 *         doesn't
	 */
	public static boolean hasInternetConnection(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm == null)
			return false;
		NetworkInfo info = cm.getActiveNetworkInfo();

		// 3G
		State mobile = cm.getNetworkInfo(0).getState();

		// wifi
		State wifi = cm.getNetworkInfo(1).getState();

		if (info == null)
			return false;

		if (mobile == NetworkInfo.State.CONNECTED
				|| mobile == NetworkInfo.State.CONNECTING) {

			return info.isConnectedOrConnecting();
		} else if (wifi == NetworkInfo.State.CONNECTED
				|| wifi == NetworkInfo.State.CONNECTING) {

			return info.isConnectedOrConnecting();
		}
		return info.isConnectedOrConnecting();
	}

	/**
	 * Does a GET request to a given url
	 * 
	 * Note: Please use this method on an AsyncTask in order not to freeze
	 * the application unnecessarely
	 * (http://developer.android.com/guide/practices/responsiveness.html)
	 * 
	 * @param url
	 *            given url
	 * @return the string output of the GET request or null if something
	 *         went wrong
	 */
	public static String HTTPGetRequest(String url) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		StringBuffer stringBuffer = new StringBuffer();

		HttpGet httpGet = new HttpGet(url);

		try {
			HttpResponse response = httpClient.execute(httpGet,
					localContext);
			InputStream instream = response.getEntity().getContent();
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(instream));

			String buffer;
			while ((buffer = bufferedReader.readLine()) != null) {
				stringBuffer.append(buffer);
			}

		} catch (ClientProtocolException e) {
			QuickUtils.log.e("ClientProtocolException", e);
		} catch (IOException e) {
			QuickUtils.log.e("IOException", e);
		} catch (IllegalArgumentException e) {
			QuickUtils.log.e("IllegalArgumentException", e);
		}

		return stringBuffer == null ? null : stringBuffer.toString();
	}

	/**
	 * Set wireless connectivity On, also this method will need the
	 * permissions "android.permission.CHANGE_WIFI_STATE" and
	 * "android.permission.ACCESS_WIFI_STATE"
	 * 
	 * @param context
	 *            - application context
	 * @param state
	 *            - set enable or disable wireless connection
	 * @return true if was set successfully and false if it wasn't
	 */
	public static boolean changeWirelessState(Context context, boolean state) {
		try {
			WifiManager wifi = (WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);
			wifi.setWifiEnabled(state);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Check if can connect to the server, also this method will need the
	 * permissions "android.permission.INTERNET"
	 * 
	 * @param u
	 *            - server url
	 * @return true if the connection returned a successful code
	 */
	public static boolean checkServerConnection(URL u) {
		boolean value = false;
		try {
			value = new RetreiveCheckServerConnection().execute(u).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;

	}

	/**
	 * AsyncTask that will run the code responsible to try to connect to the
	 * server url
	 * 
	 * @author Pereira
	 * 
	 */
	private static class RetreiveCheckServerConnection extends
			AsyncTask<URL, Void, Boolean> {

		private Exception exception;

		protected Boolean doInBackground(URL... url) {
			try {
				HttpURLConnection huc = (HttpURLConnection) url[0]
						.openConnection();
				huc.setRequestMethod("GET");
				huc.connect();
				int code = huc.getResponseCode();
				if (code == 200) {
					return true;
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}

	}

	/**
	 * Check if can connect to the server, also this method will need the
	 * permissions "android.permission.INTERNET"
	 * 
	 * @param serverURL
	 *            - server url
	 * @return true if the connection returned a successful code
	 */
	public static boolean checkServerConnection(String serverURL) {
		boolean value = false;
		try {
			value = new RetreiveCheckServerConnectionString().execute(
					serverURL).get();
		} catch (InterruptedException e) {
			QuickUtils.log.e("InterruptedException", e);
		} catch (ExecutionException e) {
			QuickUtils.log.e("ExecutionException", e);
		}
		return value;
	}

	/**
	 * AsyncTask that will run the code responsible to try to connect to the
	 * server url
	 * 
	 * @author Pereira
	 * 
	 */
	private static class RetreiveCheckServerConnectionString extends
			AsyncTask<String, Void, Boolean> {

		private Exception exception;

		protected Boolean doInBackground(String... serverURL) {
			try {
				URL u = new URL(serverURL[0]);
				HttpURLConnection huc = (HttpURLConnection) u
						.openConnection();
				huc.setRequestMethod("GET"); // OR huc.setRequestMethod
												// ("HEAD");
				huc.connect();
				int code = huc.getResponseCode();
				if (code == 200) {
					return true;
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}

	}
}

