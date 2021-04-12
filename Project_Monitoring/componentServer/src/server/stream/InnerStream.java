package server.stream;

import java.util.HashMap;
import java.util.Map;

public class InnerStream {
	private Map<String, String> innerMap;
	
	public InnerStream() {
		innerMap = new HashMap<String, String>();
	}
	
	public void putStream(String id, String data) {
		innerMap.put(id, data);
	}
	
	public boolean checkKey(String id) {
		return innerMap.containsKey(id);
	}
	
	public String getStream(String id) {
		String data = innerMap.get(id);
		innerMap.remove(id);
		return data;
	}
	
	public void removeKey(String id) {
		innerMap.remove(id);
	}
}
