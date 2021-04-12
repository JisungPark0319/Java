package server.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.jdbc.JdbcUtil;
import server.user.DeviceVO;

class DeviceDAO {
	private static final String DEVICE_UPDATE = "INSERT INTO DEVICE VALUES(?, ?, ?, NOW())";
	private static final String DEVICE_ID_SELECT = "SELECT ID FROM DEVICE WHERE ID = ?";
	private static final String DEVICE_SELECT = "SELECT * FROM DEVICE WHERE ID = ?";
	private static final String DEVICE_DELETE = "DELETE FROM DEVICE WHERE ID = ?";
	
	void insertUser(Connection conn, DeviceVO vo) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(DEVICE_UPDATE)) {
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPassword());
			pstmt.executeUpdate();
		}
	}
	
	boolean checkId(Connection conn, DeviceVO vo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(DEVICE_ID_SELECT);
			pstmt.setString(1, vo.getId());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return false;
			}
			return true;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	DeviceVO getUser(Connection conn, DeviceVO vo) throws SQLException {
		DeviceVO userVO = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(DEVICE_SELECT);
			pstmt.setString(1, vo.getId());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				userVO = new DeviceVO();
				userVO.setId(rs.getString("ID"));
				userVO.setName(rs.getString("NAME"));
				userVO.setPassword(rs.getString("PASSWORD"));
			}
			return userVO;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	void deleteUser(Connection conn, DeviceVO vo) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(DEVICE_DELETE)) {
			pstmt.setString(1, vo.getId());
			pstmt.executeUpdate();
		}
	}
}
