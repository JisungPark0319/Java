package server.sensor.impl;

import java.sql.Connection;
import java.sql.SQLException;

import server.sensor.SensorService;
import server.sensor.SensorVO;

public class SensorServiceImpl implements SensorService {
	private SensorDAO sensorDAO = new SensorDAO();
	
	@Override
	public void insertSensor(Connection conn, SensorVO vo) {
		try {
			sensorDAO.insertSensor(conn, vo);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
