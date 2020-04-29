package fr.B4D.socket;

public class SocketUtils {

	public static Integer findArray(byte[] subArray, byte[] array) {
		if(subArray == null)
			throw new IllegalArgumentException("Cannot be null.");
		if(array == null)
			throw new IllegalArgumentException("Cannot be null.");
		if(subArray.length == 0)
			throw new IllegalArgumentException("Cannot be empty.");
		if(array.length == 0)
			throw new IllegalArgumentException("Cannot be empty.");
		
		for(int i=0; i<=array.length-subArray.length; i++) {
			int correct = 0;
			
			for(int j=0; j<subArray.length; j++) {				
				if(array[i+j] == subArray[j]) {
					correct++;

					if(correct == subArray.length)
						return i;
				}
				else
					break;
			}
			
		}
		return null;
	}
}
