import app.InputThread;
import component.TextAreaComponent;
import stream.MapBuffer;

public class Application {
	public static void main(String[] args) {
		MapBuffer mapBuffer = new MapBuffer();
		InputThread inputThread = new InputThread(mapBuffer);

		new TextAreaComponent(mapBuffer, inputThread);
		
	}	
}
