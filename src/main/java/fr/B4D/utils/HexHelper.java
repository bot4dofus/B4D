package fr.B4D.utils;

import java.io.StringWriter;

/**
 * The {@code HexHelper} class is used for formatting and printing binary data in hexadecimal.
 * 
 * @author Lucas
 *
 */
public class HexHelper
{
	/**
	 * Returns a char representation of a byte into
	 * @param x - Byte to convert.
	 * @return Char containing the equivalent.
	 */
	public static char nibbleToDigit(byte x) {
		char c = (char)(x & 0xf); // mask low nibble
		return(c > 9 ? (char)(c - 10 + 'a') : (char)(c + '0')); // int to hex char
	}

	/**
	 * Returns a text representation of a byte.
	 * @param b - Byte to convert.
	 * @return String containing the hex equivalent.
	 */
	public static String toString(byte b) {
		StringBuffer sb = new StringBuffer();
		sb.append(nibbleToDigit((byte)(b >> 4)));
		sb.append(nibbleToDigit(b));
		return sb.toString();
	}

	/**
	 * Returns a text representation of a byte array.
	 * @param bytes - Array of bytes to convert.
	 * @return String containing the hex equivalent.
	 */
	public static String toString(byte [] bytes) {
		StringWriter sw = new StringWriter();

		int length = bytes.length;
		if(length > 0) {
			for(int i = 0; i < length; i++) {
				sw.write(toString(bytes[i]));
				if(i != length - 1)
					sw.write(" ");
			}
		}
		return(sw.toString());
	}
}
