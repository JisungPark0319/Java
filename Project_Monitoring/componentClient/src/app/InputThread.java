package app;


import java.util.Scanner;

import stream.MapBuffer;

public class InputThread extends Thread {
	private Scanner sc;
	private MapBuffer buffer;
	public InputThread(MapBuffer buffer) {
		sc = new Scanner(System.in);
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		while(true) {
			String data = sc.nextLine();
			buffer.pushData("scanner", data);
		}
	}
}
