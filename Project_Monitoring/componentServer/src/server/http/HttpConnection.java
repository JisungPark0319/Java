package server.http;

import java.io.IOException;
import java.net.InetSocketAddress;

import javax.swing.JTextArea;

import com.sun.net.httpserver.HttpServer;

import componentServer.log.TextLog;
import componentServer.log.TextLogComponent;
import componentServer.log.TextLogConsole;
import server.stream.InnerStream;
import server.stream.OverlapId;

public class HttpConnection extends Thread {
	private int port = 9000;
	private InnerStream innerStream;;
	private HttpServer server = null;
	private OverlapId overlapId;
	private TextLog textLog = new TextLogConsole();

	public HttpConnection(InnerStream innerStream, OverlapId overlapId) {
		this.innerStream = innerStream;
		this.overlapId = overlapId;
	}
	
	public void setComponentTextLog(JTextArea jtextArea) {
		this.textLog = new TextLogComponent(jtextArea);
	}
	
	@Override
	public void run() {
		try {
			server = HttpServer.create(new InetSocketAddress(port), 0);
			server.createContext("/button", new HttpGetHandler(innerStream, textLog));
			server.createContext("/check", new HttpDevConnCheckHandler(overlapId, textLog));
			server.setExecutor(null);
			server.start();

			textLog.TextPrintLog("[HttpConnection]===> Http Server Start Success");
		} catch (IOException e) {
			e.printStackTrace();
			textLog.TextPrintLog("[HttpConnection]===> Http Server Start Faile");
		}
	}
}
