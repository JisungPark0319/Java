package server.socket.service;

import java.sql.Connection;
import java.sql.SQLException;

import server.jdbc.connection.ConnectionProvider;
import server.user.DeviceVO;
import server.user.impl.DeviceServiceImpl;

public class Login {
	private DeviceVO deviceVO;

	void setUser(String id, String password) {
		deviceVO = new DeviceVO();
		deviceVO.setId(id);
		deviceVO.setPassword(password);
	}

	DeviceVO login() {
		if (deviceVO != null) {
			try (Connection conn = ConnectionProvider.getConnection()) {
				DeviceServiceImpl userService = new DeviceServiceImpl();
				DeviceVO tempDeviceVO = userService.getUser(conn, deviceVO);
				if (tempDeviceVO != null && tempDeviceVO.getPassword().equals(deviceVO.getPassword())) {
					return tempDeviceVO;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		return null;
	}
}
