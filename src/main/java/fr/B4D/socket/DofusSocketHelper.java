package fr.B4D.socket;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import fr.B4D.utils.HexHelper;

/**
 * The {@code DofusSocketHelper} class is used to find values within a socket.
 * 
 * @author Lucas
 *
 */
public class DofusSocketHelper {

	/**
	 * Minimum number of bytes on which the values can be stored.
	 */
	public Integer MIN_NUMBER_BYTES = 1;
	
	/**
	 * Maximum number of bytes on which the values can be stored.
	 */
	public Integer MAX_NUMBER_BYTES = 5;
	
	/**
	 * Socket in which look for a value.
	 */
	private byte[] socket;
	
	/**
	 * Result of the research.
	 */
	private Map<Integer,String> results;
	
	/**
	 * Constructs a {@code DofusSocketHelper} with a socket.
	 * @param dofusSocket - Socket in which look for a value.
	 */
	public DofusSocketHelper(DofusSocket dofusSocket) {
		this.socket = dofusSocket.getPayload();
		this.results = new HashMap<Integer,String>();
	}
	
	/**
	 * Returns the result of a research.
	 * @return Result of the research.
	 */
	public Map<Integer,String> getResults(){
		return results;
	}
	
	/**
	 * Find for a given value if the socket.
	 * @param value - Value to find in the socket.
	 */
	public void find(Integer value) {
		for(int length=MIN_NUMBER_BYTES; length <= MAX_NUMBER_BYTES; length++) {	//For each length
			
			for(int index=0; index <= socket.length-length; index++) {
				
				byte[] subArray = Arrays.copyOfRange(socket, index, index+length);
				DofusSocketElement element = new DofusSocketElement(subArray);
				
				Integer asBigEndian = element.asBigEndian();
				Integer asSmallEndian = element.asSmallEndian();
								
				if(value.equals(asBigEndian) || value.equals(asSmallEndian)) {	//If value found
					/*byte[] before = Arrays.copyOfRange(socket, 0, index);
					byte[] after = Arrays.copyOfRange(socket, index+length, socket.length);*/
					
					StringBuilder builder = new StringBuilder();
					builder.append(value + " found at index " + index);

					if(value.equals(asBigEndian)) {
						builder.append("(BigEndian)");
					}
					if(value.equals(asSmallEndian)) {
						builder.append("(SmallEndian)");
					}
					
					builder.append(" : [" + HexHelper.toString(subArray) + "]");
					results.put(index, builder.toString());
				}
			}
		}
	}
	
	/**
	 * Prints the results of a research sorted by position.
	 */
	public void printResults() {
		if(results.isEmpty()) {
			System.out.println("No result found.");
		}
		else {
			results
			.entrySet()
			.stream()
			.sorted(Map.Entry.<Integer, String>comparingByKey())
			.forEach(r -> System.out.println(r.getValue()));
		}
	}
}
