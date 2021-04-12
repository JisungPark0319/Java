package server.sensor.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import server.sensor.SensorVO;

class SensorDAO {
	private static final String SENSOR_INSERT = "INSERT INTO SENSOR VALUES(?, ?, NOW())";

	void insertSensor(Connection conn, SensorVO vo) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(SENSOR_INSERT)) {
			pstmt.setString(1, vo.getId());
			pstmt.setInt(2, vo.getData());
			pstmt.executeUpdate();
		}
	}
}
