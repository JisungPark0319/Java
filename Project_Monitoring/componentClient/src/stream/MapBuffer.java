package stream;


import java.util.HashMap;
import java.util.Map;

public class MapBuffer {
	private Map<String, String> buffer;
	
	public MapBuffer() {
		buffer = new HashMap<String, String>();
	}
	
	public void pushData(String id, String data) {
		buffer.put(id, data);
	}
	
	public String getData(String id) {
		String data = buffer.get(id);
		buffer.remove(id);
		return data;
	}
	
	public boolean idCheck(String id) {
		return buffer.containsKey(id);
	}
}
