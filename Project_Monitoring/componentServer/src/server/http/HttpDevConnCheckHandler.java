package server.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import componentServer.log.TextLog;
import server.stream.InnerStream;
import server.stream.OverlapId;

public class HttpDevConnCheckHandler implements HttpHandler {
	private OverlapId overlapId;
	private TextLog textLog;
	
	public HttpDevConnCheckHandler(OverlapId overlapId, TextLog textLog) {
		this.overlapId = overlapId;
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

		String response = "false";
		
		if(parameters.isEmpty()) {
			int ovelapIdSize = overlapId.size();
			response = "[";
			for(int index = 0; index < ovelapIdSize; index++) {
				response = response + "{\"id\":\"" + overlapId.getIndex(index) + "\"}";
				if(index != ovelapIdSize-1) {
					response += ", ";
				}
			}
			response += "]";
			textLog.TextPrintLog(response);
			
		}
		
		for (String key : parameters.keySet()) {
			textLog.TextPrintLog("[HttpDevConnCheckHandler] ===> request data : " + key + " = " + parameters.get(key) + "\n");
			if (key.equals("id")) {
				id = (String) parameters.get(key);
				if(overlapId.chekcId(id)) {
					response = "ok";
				}
			}
		}

		arg.sendResponseHeaders(200, response.length());

		OutputStream os = arg.getResponseBody();

		os.write(response.toString().getBytes());

		textLog.TextPrintLog("[HttpDevConnCheckHandler] ===> response data :" + response.toString());

		os.close();

	}
}
