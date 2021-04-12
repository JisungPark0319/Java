package server.stream;

import java.util.ArrayList;
import java.util.List;

public class OverlapId {
	private List<String> checkIdList;
	
	public OverlapId() {
		checkIdList = new ArrayList<String>();
	}
	
	public void pushId(String id) {
		checkIdList.add(id);
	}
	
	public boolean chekcId(String id) {
		return checkIdList.contains(id);
	}
	
	public void removeId(String id) {
		checkIdList.remove(id);
	}
	
	public int size() {
		return checkIdList.size();
	}
	
	public String getIndex(int index) {
		return checkIdList.get(index);
	}
}
