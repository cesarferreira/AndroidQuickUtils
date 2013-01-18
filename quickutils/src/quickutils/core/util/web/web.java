package quickutils.core.util.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONException;
import org.json.JSONObject;

import quickutils.core.QuickUtils;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.util.Log;

public class web {

	/**
	 * protected constructor
	 */
	protected web() {
	}

	/**
	 * Queries the given URL with a list of params
	 * 
	 * @param url
	 *            the url to query
	 * @param params
	 *            list of pair-values
	 * @return the result JSON
	 */
	public static JSONObject getJSONFromUrlViaPOST(String url, List<NameValuePair> params) {

		InputStream is = null;
		JSONObject jObj = null;
		String json = "";

		// Making HTTP request
		try {
			// defaultHttpClient
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(params));

			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}

		// try parse the string to a JSON object
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}

		// return JSON String
		return jObj;

	}

	public static JSONObject getJSONFromUrlViaGET(String url) {
		
		JSONObject jObj = null;

		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
				
				jObj = new JSONObject(builder.toString());
			} else {
				// Log.e(ParseJSON.class.toString(), "Failed to download file");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jObj;
	}

	/**
	 * Checks if the app has connectivity to the Internet
	 * 
	 * @param context
	 *            application context
	 * @return true if has connection to the Internet and false if it doesn't
	 */
	public static boolean hasInternetConnection(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm == null)
			return false;
		NetworkInfo info = cm.getActiveNetworkInfo();

		// 3G
		State mobile = cm.getNetworkInfo(0).getState();

		// wifi
		State wifi = cm.getNetworkInfo(1).getState();

		if (info == null)
			return false;

		if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING) {

			return info.isConnectedOrConnecting();
		} else if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING) {

			return info.isConnectedOrConnecting();
		}
		return info.isConnectedOrConnecting();
	}

	/**
	 * Does a GET request to a given url
	 * 
	 * Note: Please use this method on an AsyncTask in order not to freeze the
	 * application unnecessarely
	 * (http://developer.android.com/guide/practices/responsiveness.html)
	 * 
	 * @param url
	 *            given url
	 * @return the string output of the GET request or null if something went
	 *         wrong
	 */
	public static String HTTPGetRequest(String url) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		StringBuffer stringBuffer = new StringBuffer();

		HttpGet httpGet = new HttpGet(url);

		try {
			HttpResponse response = httpClient.execute(httpGet, localContext);
			InputStream instream = response.getEntity().getContent();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(instream));

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
	 * Set wireless connectivity On, also this method will need the permissions
	 * "android.permission.CHANGE_WIFI_STATE" and
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
			WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
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
	private static class RetreiveCheckServerConnection extends AsyncTask<URL, Void, Boolean> {

		private Exception exception;

		protected Boolean doInBackground(URL... url) {
			try {
				HttpURLConnection huc = (HttpURLConnection) url[0].openConnection();
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
			value = new RetreiveCheckServerConnectionString().execute(serverURL).get();
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
	private static class RetreiveCheckServerConnectionString extends AsyncTask<String, Void, Boolean> {

		private Exception exception;

		protected Boolean doInBackground(String... serverURL) {
			try {
				URL u = new URL(serverURL[0]);
				HttpURLConnection huc = (HttpURLConnection) u.openConnection();
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
