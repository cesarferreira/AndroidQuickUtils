package quickutils.core.util.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import quickutils.core.QuickUtils;
import android.util.Base64;

public class security {

	/**
	 * private constructor
	 */
	// private security() {
	// }

	/**
	 * Encode in base64
	 * 
	 * @param toEncode
	 *            String to be encoded
	 * @param flags
	 *            flags to encode the String
	 * @return encoded String in base64
	 */
	public static String encodeBase64(String toEncode, int flags) {
		return privateBase64Encoder(toEncode, flags);

	}

	/**
	 * Encode in base64
	 * 
	 * @param toEncode
	 *            String to be encoded
	 * @return encoded String in base64
	 */
	public static String encodeBase64(String toEncode) {
		return privateBase64Encoder(toEncode, -1);
	}

	/**
	 * private Encoder in base64
	 * 
	 * @param toEncode
	 *            String to be encoded
	 * @param flags
	 *            flags to encode the String
	 * @return encoded String in base64
	 */
	private static String privateBase64Encoder(String toEncode, int flags) {
		byte[] data = null;
		try {
			data = toEncode.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		if (flags == -1) {
			flags = Base64.DEFAULT;
		}

		return Base64.encodeToString(data, flags);
	}

	/**
	 * Decode in base64
	 * 
	 * @param toDecode
	 *            String to be encoded
	 * @return decoded String in base64
	 */
	public static String decodeBase64(String toDecode) {
		return privateBase64Decoder(toDecode, -1);
	}

	/**
	 * Decode in base64
	 * 
	 * @param toDecode
	 *            String to be encoded
	 * @param flags
	 *            flags to decode the String
	 * @return decoded String in base64
	 */
	public static String decodeBase64(String toDecode, int flags) {
		return privateBase64Decoder(toDecode, flags);
	}

	/**
	 * Private decoder in base64
	 * 
	 * @param toDecode
	 *            String to be encoded
	 * @param flags
	 *            flags to decode the String
	 * @return decoded String in base64
	 */
	private static String privateBase64Decoder(String decode, int flags) {
		if (flags == -1) {
			flags = Base64.DEFAULT;
		}

		byte[] data1 = Base64.decode(decode, flags);
		String decodedBase64 = null;
		try {
			decodedBase64 = new String(data1, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return decodedBase64;
	}

	/**
	 * Calculate the MD5 of a given String
	 * 
	 * @param string
	 *            String to be MD5'ed
	 * @return MD5'ed String
	 */
	public static String calculateMD5(String string) {
		byte[] hash;

		try {
			hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}

		StringBuilder hex = new StringBuilder(hash.length * 2);

		for (byte b : hash) {
			int i = (b & 0xFF);
			if (i < 0x10)
				hex.append('0');
			hex.append(Integer.toHexString(i));
		}

		return hex.toString();
	}

	/**
	 * Calculate the SHA-1 of a given String
	 * 
	 * @param string
	 *            String to be SHA1'ed
	 * @return SHA1'ed String
	 */
	public static String calculateSHA1(String string) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			QuickUtils.log.e("NoSuchAlgorithmException", e);
		}
		try {
			md.update(string.getBytes("iso-8859-1"), 0, string.length());
		} catch (UnsupportedEncodingException e) {
			QuickUtils.log.e("UnsupportedEncodingException", e);

		}
		byte[] sha1hash = md.digest();
		return convertToHex(sha1hash);
	}

	private static String convertToHex(byte[] data) {
		StringBuilder buf = new StringBuilder();
		for (byte b : data) {
			int halfbyte = (b >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
				halfbyte = b & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buf.toString();
	}
}
