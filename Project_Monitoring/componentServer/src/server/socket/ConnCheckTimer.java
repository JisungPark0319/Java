package server.socket;

public class ConnCheckTimer {
	private long startTime;
	private long laterTime;
	
	public ConnCheckTimer() {
		startTime = System.currentTimeMillis();
		laterTime = System.currentTimeMillis() - startTime;
	}
	
	public void startTimer() {
		startTime = System.currentTimeMillis();
	}
	
	public boolean laterSecTimer(int sec) {
		laterTime = System.currentTimeMillis() - startTime;
		if(sec*1000 <= laterTime) {
			return true;
		}
		return false;
	}
	
	public void resetTimer() {
		startTime = System.currentTimeMillis();
	}
}
