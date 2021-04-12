package server.device;

import java.sql.Connection;

public interface DeviceAccessService {
	void insertUser(Connection conn, DeviceAccessVO vo);
}
