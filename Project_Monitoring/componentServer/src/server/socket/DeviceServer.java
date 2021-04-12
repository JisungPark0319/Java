package server.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;

import componentServer.log.TextLog;
import componentServer.log.TextLogComponent;
import componentServer.log.TextLogConsole;
import server.stream.InnerStream;
import server.stream.OverlapId;

public class DeviceServer extends Thread {
	private ServerConfig serverConfig;
	private InnerStream innerStream;
	private OverlapId overlapId;
	private TextLog textLog = new TextLogConsole();
	
	public void setComponentTextLog(JTextArea jtextArea) {
		this.textLog = new TextLogComponent(jtextArea);
	}
	
	public DeviceServer() {
		this.serverConfig = new ServerConfig();
		textLog.TextPrintLog("[DeviceServer] ===> serverConfig : " + serverConfig);
	}
	
	public DeviceServer(ServerConfig serverConfig) {
		this.serverConfig = serverConfig;
	}
	
	public void setServerConfig(ServerConfig serverConfig) {
		textLog.TextPrintLog("[DeviceServer] ===> setServerConfig : " + serverConfig);
		this.serverConfig = serverConfig;
	}
	
	public void setInnerStream(InnerStream innerStream) {
		this.innerStream = innerStream;
	}
	
	public void setOverlapId(OverlapId overlapId) {
		this.overlapId = overlapId;
	}
	
	@Override
	public void run() {
		textLog.TextPrintLog("[DeviceServer] ===> start");
		try (ServerSocket serverSocket = new ServerSocket(this.serverConfig.getPort())) {
				while(true) {
					textLog.TextPrintLog("[DeviceServer] ===> accept wait");
					Socket clientSocket = serverSocket.accept();
					textLog.TextPrintLog("[DeviceServer] ===> accept");
					DeviceThread deviceThread = new DeviceThread(clientSocket, innerStream, overlapId);
					deviceThread.setComponentTextLog(textLog);
					deviceThread.start();
					textLog.TextPrintLog("[DeviceServer] ===> thread");
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
