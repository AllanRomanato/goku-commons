package br.com.gokustore.security.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HMAC {
	
	private static final int OPAD = 0x5c;
	private static final int IPAD = 0x36;

	public static byte[] hmac(byte[] key, byte[] message) throws NoSuchAlgorithmException {
		byte[] newKey = new byte[64];
		byte[] oKeyPad = new byte[64];
		byte[] iKeyPad = new byte[64];
		byte[] appendedIKey;
		byte[] appendedOKey;
		byte[] sha1IKey;
		int i;
		int x = 0;

		if (key.length > 64)
			key = sha(key);

		for (i = 0; i < 64; i++) {
			if (key.length > i)
				newKey[i] = key[i];
			else
				newKey[i] = 0x00;
		}

		for (i = 0; i < 64; i++) {
			iKeyPad[i] = (byte) (newKey[i] ^ IPAD);
			oKeyPad[i] = (byte) (newKey[i] ^ OPAD);
		}

		appendedIKey = new byte[iKeyPad.length + message.length];

		for (i = 0; i < appendedIKey.length; i++) {
			if (iKeyPad.length > i) {
				appendedIKey[i] = iKeyPad[i];
			} else {
				appendedIKey[i] = message[x];
				x++;
			}
		}

		x = 0;
		sha1IKey = sha(appendedIKey);

		appendedOKey = new byte[oKeyPad.length + sha1IKey.length];

		for (i = 0; i < appendedOKey.length; i++) {
			if (iKeyPad.length > i) {
				appendedOKey[i] = oKeyPad[i];
			} else {
				appendedOKey[i] = sha1IKey[x];
				x++;
			}
		}
		return sha(appendedOKey);
	}

	public static String getHmac(String key, String msg) {
		StringBuffer sb = new StringBuffer();
		try {
			byte[] result = hmac(key.getBytes(), msg.getBytes());
			for (int i = 0; i < result.length; i++) {
				sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	private static byte[] sha(byte[] input) throws NoSuchAlgorithmException {
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		byte[] result = mDigest.digest(input);
		return result;
	}
}
