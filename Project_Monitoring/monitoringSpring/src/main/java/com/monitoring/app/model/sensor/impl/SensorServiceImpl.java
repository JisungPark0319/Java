package com.monitoring.app.model.sensor.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitoring.app.model.sensor.SensorService;
import com.monitoring.app.model.sensor.SensorVO;


@Service("SensorService")
public class SensorServiceImpl implements SensorService {
	@Autowired
	SensorDAO sensorDAO;
	
	@Override
	public List<SensorVO> getSensorData(SensorVO vo) {
		return sensorDAO.getSensorData(vo);
	}
}
