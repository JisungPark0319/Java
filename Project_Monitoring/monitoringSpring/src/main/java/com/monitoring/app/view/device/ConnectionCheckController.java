package com.monitoring.app.view.device;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.monitoring.app.model.device.DeviceVO;

@Controller
public class ConnectionCheckController {
	
	@RequestMapping(value="/check.do")
	public String deviceConnectionCheck(DeviceVO vo, HttpSession session) {
		URI uri;

		try {
			uri = new URI("http://localhost:9000/check");
			uri = new URIBuilder(uri).addParameter("id", vo.getId()).build();
			System.out.println(vo.getId());
			System.out.println(uri);
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpResponse response = httpClient.execute(new HttpGet(uri));
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity);
			if(content.equals("ok")) {
				session.setAttribute("device", Boolean.TRUE);
			} else {
				session.setAttribute("device", Boolean.FALSE);
			}
			System.out.println(content);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "monitoring.jsp";
	}
}
