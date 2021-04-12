package server.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class HttpPostHandler implements HttpHandler {
	@Override

	public void handle(HttpExchange arg) throws IOException {

		System.out.println("request method Post");

		arg.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

		Map<String, Object> parameters = new HashMap<String, Object>();

		InputStreamReader isr = new InputStreamReader(arg.getRequestBody(), "utf-8");

		BufferedReader br = new BufferedReader(isr);

		String query = br.readLine();

		UriParser uriParse = new UriParser();

		uriParse.parseQuery(query, parameters);

		String response = "";

		for (String key : parameters.keySet()) {

			response += key + "=" + parameters.get(key) + ",   ";

		}

		System.out.println("response : " + response);

		arg.sendResponseHeaders(200, response.length());

		OutputStream os = arg.getResponseBody();

		os.write(response.toString().getBytes());

		os.close();

	}

}
