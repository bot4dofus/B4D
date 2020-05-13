package fr.B4D.socket;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import fr.B4D.utils.HexHelper;

public class DofusSocketHelper {

	public Integer MIN_NUMBER_BYTES = 1;
	public Integer MAX_NUMBER_BYTES = 5;
	
	private byte[] socket;
	private Map<Integer,String> results;
	
	public DofusSocketHelper(DofusSocket dofusSocket) {
		this.socket = dofusSocket.getPayload();
		this.results = new HashMap<Integer,String>();
	}
	
	public Map<Integer,String> getResults(){
		return results;
	}
	
	public void find(Integer value) {
		for(int length=MIN_NUMBER_BYTES; length <= MAX_NUMBER_BYTES; length++) {	//For each length
			
			for(int index=0; index <= socket.length-length; index++) {
				
				byte[] subArray = Arrays.copyOfRange(socket, index, index+length);
				SocketElement element = new SocketElement(subArray);
				
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
	
	public void printResults() {
		results
		.entrySet()
		.stream()
		.sorted(Map.Entry.<Integer, String>comparingByKey())
		.forEach(r -> System.out.println(r.getValue()));
	}
}
