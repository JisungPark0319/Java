package server.device.impl;

import java.sql.Connection;
import java.sql.SQLException;

import server.device.DeviceAccessService;
import server.device.DeviceAccessVO;

public class DeviceAccessServiceImpl implements DeviceAccessService {
	DeviceAccessDAO accessDAO = new DeviceAccessDAO();
	
	@Override
	public void insertUser(Connection conn, DeviceAccessVO vo) {
		try {
			accessDAO.insertUser(conn, vo);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
