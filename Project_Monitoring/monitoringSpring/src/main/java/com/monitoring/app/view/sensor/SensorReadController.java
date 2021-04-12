package com.monitoring.app.view.sensor;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monitoring.app.model.sensor.SensorService;
import com.monitoring.app.model.sensor.SensorVO;

@Controller
public class SensorReadController {
	@Autowired
	SensorService sensorService;

	@ResponseBody
	@RequestMapping(value = "/sensor/getdata.do")
	public String getSensorData(SensorVO vo) {
		List<SensorVO> sensor = sensorService.getSensorData(vo);
		JSONArray personArray = new JSONArray();
		JSONObject jsonObject;

		for (int i = 0; i < sensor.size(); i++) {
			jsonObject = new JSONObject();
			jsonObject.put("data", sensor.get(i).getData());
			personArray.add(jsonObject);
		}
		return personArray.toString();
	}
}
