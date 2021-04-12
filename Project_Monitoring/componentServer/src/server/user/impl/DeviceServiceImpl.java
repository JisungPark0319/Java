package server.user.impl;

import java.sql.Connection;
import java.sql.SQLException;

import server.user.DeviceService;
import server.user.DeviceVO;

public class DeviceServiceImpl implements DeviceService {
	private DeviceDAO deviceDAO = new DeviceDAO();

	@Override
	public void insertUser(Connection conn, DeviceVO vo) {
		try {
			deviceDAO.insertUser(conn, vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean checkId(Connection conn, DeviceVO vo) {
		try {
			return deviceDAO.checkId(conn, vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public DeviceVO getUser(Connection conn, DeviceVO vo) {
		try {
			return deviceDAO.getUser(conn, vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteUser(Connection conn, DeviceVO vo) {
		try {
			deviceDAO.deleteUser(conn, vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
