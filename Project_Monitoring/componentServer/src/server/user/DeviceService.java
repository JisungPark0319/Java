package server.user;

import java.sql.Connection;

public interface DeviceService {
	public void insertUser(Connection conn, DeviceVO vo);
	public boolean checkId(Connection conn, DeviceVO vo);
	public DeviceVO getUser(Connection conn, DeviceVO vo);
	void deleteUser(Connection conn, DeviceVO vo);
}