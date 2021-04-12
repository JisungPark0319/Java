package server.sensor;

import java.sql.Connection;

public interface SensorService {
	void insertSensor(Connection conn, SensorVO vo);
}
