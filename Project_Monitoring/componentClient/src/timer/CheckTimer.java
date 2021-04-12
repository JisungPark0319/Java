package timer;


public class CheckTimer {
	private long startTime;
	private long laterTime;
	
	public CheckTimer() {
		startTime = System.currentTimeMillis();
		laterTime = 0;
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
	
	public boolean laterMillisecTimer(int millis) {
		laterTime = System.currentTimeMillis() - startTime;
		if(millis <= laterTime) {
			return true;
		}
		return false;
	}
	
	public void resetTimer() {
		startTime = System.currentTimeMillis();
	}
}
