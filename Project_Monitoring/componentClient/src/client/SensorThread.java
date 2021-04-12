package client;


import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import stream.SocketStream;

public class SensorThread {
	private String id;
	private SocketStream socketStream;

	public SensorThread(SocketStream socketStream, String id) {
		this.id = id;
		this.socketStream = socketStream;
	}

	public void sensorStart() {
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		
		Timer m_timer = new Timer();
		TimerTask m_task = new TimerTask() {
			@Override
			public void run() {
				String sensor = Integer.toString(rand.nextInt(28-22+1)+22);
				String data = id + "/" + sensor;
				ProtocolData loginProtocol = new ProtocolData();
				loginProtocol.setId("server");
				loginProtocol.setType("sensor");
				loginProtocol.setData(data);
				try {
					socketStream.socketSend(loginProtocol.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		m_timer.schedule(m_task, 1000, 3000);
	}
}
