package server.socket.service;

import java.sql.Connection;
import java.sql.SQLException;

import server.device.DeviceAccessVO;
import server.device.impl.DeviceAccessServiceImpl;
import server.jdbc.connection.ConnectionProvider;

public class DeviceAccessLog {
	private DeviceAccessVO accessVO;
	private final String connectState = "CONNECT";
	private final String disConnectState = "DISCONNECT";
	
	void setConDeviceLog(String id) {
		accessVO = new DeviceAccessVO();
		accessVO.setId(id);
		accessVO.setState(connectState);
	}
	void setDisConDeviceLog(String id) {
		accessVO = new DeviceAccessVO();
		accessVO.setId(id);
		accessVO.setState(disConnectState);
	}
	
	boolean devAccessLog(String id) {
		if (accessVO != null) {
			try (Connection conn = ConnectionProvider.getConnection()) {
				DeviceAccessServiceImpl deviceService = new DeviceAccessServiceImpl();
				deviceService.insertUser(conn, accessVO);
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				accessVO = null;
			}
		}
		return false;
	}
}
