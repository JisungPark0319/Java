package server.socket.service;

import java.sql.Connection;
import java.sql.SQLException;

import server.jdbc.connection.ConnectionProvider;
import server.user.DeviceVO;
import server.user.impl.DeviceServiceImpl;

public class CreateUser {
	private DeviceVO deviceVO;

	void setUser(String id, String name, String password) {
		deviceVO = new DeviceVO();
		deviceVO.setId(id);
		deviceVO.setName(name);
		deviceVO.setPassword(password);
	}

	boolean create() {
		if (deviceVO != null) {
			try (Connection conn = ConnectionProvider.getConnection()) {
				System.out.println("[CreateDevice]===> create : " + deviceVO);
				DeviceServiceImpl userService = new DeviceServiceImpl();
				if (!userService.checkId(conn, deviceVO)) {
					return false;
				}
				userService.insertUser(conn, deviceVO);
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
