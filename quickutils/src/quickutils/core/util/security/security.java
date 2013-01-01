package quickutils.core.util.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import quickutils.core.QuickUtils;

public  class security {

	/**
	 * private constructor
	 */
//	private security() {
//	}

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
			hash = MessageDigest.getInstance("MD5").digest(
					string.getBytes("UTF-8"));
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
				buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte)
						: (char) ('a' + (halfbyte - 10)));
				halfbyte = b & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buf.toString();
	}
}
