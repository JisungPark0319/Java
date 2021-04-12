package com.monitoring.app.model.sensor;

import java.util.List;

public interface SensorService {
	public List<SensorVO> getSensorData(SensorVO vo);
}
