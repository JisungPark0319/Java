package server.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import componentServer.log.TextLog;
import server.stream.InnerStream;

public class HttpGetHandler implements HttpHandler {
	private InnerStream innerStream;
	private TextLog textLog;
	
	public HttpGetHandler(InnerStream innerStream, TextLog textLog) {
		this.innerStream = innerStream;
		this.textLog = textLog;
	}
	
	@Override
	public void handle(HttpExchange arg) throws IOException {

		//textLog.TextPrintLog("request method Get");

		arg.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

		Map<String, Object> parameters = new HashMap<String, Object>();

		URI requestedUri = arg.getRequestURI();

		String query = requestedUri.getRawQuery();

		UriParser uriParser = new UriParser();

		uriParser.parseQuery(query, parameters);

		String id = null;
		String data = null;
		
		for (String key : parameters.keySet()) {
			textLog.TextPrintLog("[HttpGetHandler] ===> request data : " + key + " = " + parameters.get(key) + "\n");
			if(key.equals("id")) {
				id = (String) parameters.get(key);
			} else if(key.equals("data")) {
				data = (String) parameters.get(key);
			}
		}
		
		String response = "false";
		
		if(!id.isEmpty() && !data.isEmpty()) {
			textLog.TextPrintLog("[HttpGetHandler] ===> id, data Not Empty");
			String checkId = id + "_http"; // id_http
			String socketId = id + "_socket";
			innerStream.removeKey(socketId);
			innerStream.removeKey(checkId);

			innerStream.putStream(socketId, data);
			int timeStart = (int)System.currentTimeMillis();
			int timeCur = (int)System.currentTimeMillis();
			int timeLater = timeCur - timeStart;			
			while(timeLater < 1000) {
				if(innerStream.checkKey(checkId)) {
					response = innerStream.getStream(checkId);
					break;
				}
				timeCur = (int)System.currentTimeMillis();
				timeLater = timeCur - timeStart;			
			}
		}

		arg.sendResponseHeaders(200, response.length());

		OutputStream os = arg.getResponseBody();

		os.write(response.toString().getBytes());
		
		textLog.TextPrintLog("[HttpGetHandler] ===> response data :" + response.toString());
		
		os.close();

	}
}
