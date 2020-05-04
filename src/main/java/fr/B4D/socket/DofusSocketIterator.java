package fr.B4D.socket;

import java.util.Arrays;

public class DofusSocketIterator {

	private byte[] socket;
	private Integer cursor = 0;
	
	public DofusSocketIterator(byte[] socket) {
		this.socket = socket;
	}
	
	public Integer skip(Integer length) {
		return cursor+=length;
	}
	
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
		throw new PatternNotFoundException(socket, pattern);
	}
	
	public Integer moveAfterPattern(byte[] pattern) throws PatternNotFoundException {
		moveToPattern(pattern);
		cursor += pattern.length;
		return cursor;
	}
	

	public byte getNextByte() {
		byte result = socket[cursor];
		cursor += 1;
		return result;
	}
	public SocketElement getNextSocketElement(Integer length) {
		SocketElement element = new SocketElement(Arrays.copyOfRange(socket, cursor, cursor+length));
		cursor += length;
		return element;
	}
}
