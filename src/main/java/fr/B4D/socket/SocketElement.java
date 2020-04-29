package fr.B4D.socket;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.B4D.bot.B4DException;

public class SocketElement {

	public static final String ENCODING = "UTF-8";
	
	private byte[] payload;
	
	public SocketElement(byte[] payload) {
		if(payload == null)
			throw new IllegalArgumentException("Cannot be null.");
		if(payload.length == 0)
			throw new IllegalArgumentException("Cannot be empty.");
		
		this.payload = payload;
	}
	
	public byte[] getPayload() {
		return payload;
	}
	
	public String asString() throws B4DException {
		try {
			return new String(payload, ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new B4DException(e);
		}
	}
	
	public Integer asSmallEndian() {
		Integer value = 0;
		for(int i=0; i<payload.length;i++) {
			value += (Byte.toUnsignedInt(payload[i])*(int)Math.pow(2, 8*(payload.length-1-i)));
		}
		return value;
	}
	
	public Integer asBigEndian() {
		Integer value = 1;
		for(int i=0; i<payload.length;i++) {
			value += (Byte.toUnsignedInt(payload[i]) - 1)*(int)Math.pow(2, 7*i);
		}
		return value;
	}
	
	public List<Integer> asBigEndians(){
		List<Integer> values = new ArrayList<Integer>();
		
		int begin = 0, end = 0;
		
		for(byte b:payload) {
			end++;
			
			if(Byte.toUnsignedInt(b) < 128) {
				values.add(new SocketElement(Arrays.copyOfRange(payload, begin, end)).asBigEndian());
				begin = end;
			}
		}
		
		return values;
	}
}
