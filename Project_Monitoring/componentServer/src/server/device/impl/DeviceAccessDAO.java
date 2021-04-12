package server.device.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.device.DeviceAccessVO;
import server.jdbc.JdbcUtil;

class DeviceAccessDAO {
	private static final String INSER_DEVICE_LOG = "INSERT INTO DEVICE_CON_LOG VALUES(?, ?, NOW())";
	private static final String GET_DEVICE_ACCESS_LIST = 
			"select * from DEVICE_CON_LOG where (id, ACCESS_DATE) in ( "
			+ "select id, max(ACCESS_DATE) from DEVICE_CON_LOG group by id"
			 + ") order by ACCESS_DATE desc";
	
	void insertUser(Connection conn, DeviceAccessVO vo) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(INSER_DEVICE_LOG)) {
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getState());
			pstmt.executeUpdate();
		}
	}
	DeviceAccessVO getUser(Connection conn, DeviceAccessVO vo) throws SQLException {
		DeviceAccessVO userVO = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(GET_DEVICE_ACCESS_LIST);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				userVO = new DeviceAccessVO();
				userVO.setId(rs.getString("ID"));
				userVO.setState(rs.getString("STATE"));
				userVO.setAccessDate(rs.getDate("ACCESS_DATE"));
			}
			return userVO;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}
