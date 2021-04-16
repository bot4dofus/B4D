package fr.B4D.socket;

/**
 * The {@code SocketUtils} class is used to define useful methods relative to sockets.
 * 
 * @author Lucas
 *
 */
public class SocketUtils {

	/**
	 * Finds a subarray in an array.
	 * @param subArray Subarray to look for.
	 * @param array Array in which search for the subarray.
	 * @return THe index of subarray beginning, {@code null} if not found.
	 */
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
