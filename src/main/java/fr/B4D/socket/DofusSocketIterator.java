package fr.B4D.socket;

import java.util.Arrays;

import fr.B4D.utils.HexHelper;

/**
 * The {@code DofusSocketIterator} class is used to iterate through a socket to extract values. This class is used by parsers.
 * 
 * @author Lucas
 *
 */
public class DofusSocketIterator {

	private byte[] socket;
	private Integer cursor = 0;
	
	/**
	 * Constructs a {@code DofusSocketIterator} with a socket.
	 * @param dofusSocket - Socket on which iterate.
	 */
	public DofusSocketIterator(DofusSocket dofusSocket) {
		this.socket = dofusSocket.getPayload();
	}
	
	/**
	 * Skip a given number of bytes.
	 * @param number - Number of bytes to skip.
	 * @return Current index of the cursor.
	 */
	public Integer skip(Integer number) {
		return cursor+=number;
	}
	
	/**
	 * Move at the beginning of a pattern located after the cursor.
	 * @param pattern - Pattern to find.
	 * @return Current index of the cursor.
	 * @throws PatternNotFoundException If pattern not found.
	 */
	public Integer moveToPattern(byte[] pattern) throws PatternNotFoundException {
		if(pattern == null)
			throw new IllegalArgumentException("Cannot be null.");
		if(pattern.length == 0)
			throw new IllegalArgumentException("Cannot be empty.");
		
		for(int i=cursor; i<=socket.length-pattern.length; i++) {
			int correct = 0;
			
			for(int j=0; j<pattern.length; j++) {				
				if(socket[i+j] == pattern[j]) {
					correct++;

					if(correct == pattern.length) {
						cursor = i;
						return i;
					}
				}
				else
					break;
			}
			
		}
		throw new PatternNotFoundException("Could find the pattern [" + HexHelper.toString(pattern) + "] in  [" + HexHelper.toString(socket) + "]");
	}

	/**
	 * Move after a pattern located after the cursor.
	 * @param pattern - Pattern to find.
	 * @return Current index of the cursor.
	 * @throws PatternNotFoundException If pattern not found.
	 */
	public Integer moveAfterPattern(byte[] pattern) throws PatternNotFoundException {
		moveToPattern(pattern);
		cursor += pattern.length;
		return cursor;
	}
	

	/**
	 * Returns the next byte and move the cursor of 1.
	 * @return Next byte.
	 */
	public byte getNextByte() {
		byte result = socket[cursor];
		cursor += 1;
		return result;
	}
	
	/** Returns the next socket element of a given length.
	 * @param length - Length of the next socket element.
	 * @return Next socket element.
	 */
	public SocketElement getNextSocketElement(Integer length) {
		SocketElement element = new SocketElement(Arrays.copyOfRange(socket, cursor, cursor+length));
		cursor += length;
		return element;
	}
}
