package server.socket.service;

import java.sql.Connection;
import java.sql.SQLException;

import server.jdbc.connection.ConnectionProvider;
import server.sensor.SensorVO;
import server.sensor.impl.SensorServiceImpl;

public class SensorData {
	private SensorVO sensorVO;
	
	void setSensorData(String id, int data) {
		sensorVO = new SensorVO();
		sensorVO.setId(id);
		sensorVO.setData(data);
	}
	
	void storeSensor() {
		if (sensorVO != null) {
			try (Connection conn = ConnectionProvider.getConnection()) {
				SensorServiceImpl sensorService = new SensorServiceImpl();
				sensorService.insertSensor(conn, sensorVO);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
