package com.monitoring.app.model.device.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.monitoring.app.model.device.DeviceAccessVO;

@Repository
class DeviceAccessDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final String GET_DEVICE_ACCESS_LIST = 
			"SELECT * FROM DEVICE_CON_LOG WHERE (ID, ACCESS_DATE) IN ( "
			+ "SELECT ID, MAX(ACCESS_DATE) FROM DEVICE_CON_LOG GROUP BY ID"
			 + ") ORDER BY ACCESS_DATE DESC";
	private static final String GET_DEVICE_LIST_COUNT =
			"SELECT COUNT(*) FROM DEVICE_CON_LOG WHERE (ID, ACCESS_DATE) IN ( "
					+ "SELECT ID, MAX(ACCESS_DATE) FROM DEVICE_CON_LOG GROUP BY ID)";
	private static final String GET_DEVICE_ACCESS = 
			"SELECT * FROM DEVICE_CON_LOG WHERE (ID, ACCESS_DATE) IN ( "
			+ "SELECT ID, MAX(ACCESS_DATE) FROM DEVICE_CON_LOG GROUP BY ID"
			 + ") ORDER BY ACCESS_DATE DESC LIMIT ?,?";
	private static final String GET_ID_DEVICE_ACCESS = "SELECT * FROM DEVICE_CON_LOG WHERE ID = ? "
			+ "ORDER BY ACCESS_DATE DESC LIMIT 0,1";
	
	List<DeviceAccessVO> getDevStateList() {
		return jdbcTemplate.query(GET_DEVICE_ACCESS_LIST, new DeviceAccessRowMapper());
	}
	
	int deviceListCount() {
		return jdbcTemplate.queryForObject(GET_DEVICE_LIST_COUNT, Integer.class);
	}
	
	List<DeviceAccessVO> getDevStateList(int startRow, int endRow) {
		Object[] args = { startRow, endRow };
		return jdbcTemplate.query(GET_DEVICE_ACCESS, args, new DeviceAccessRowMapper());
	}
	
	DeviceAccessVO getDevIdState(String id) {
		Object[] args = { id };
		return jdbcTemplate.queryForObject(GET_ID_DEVICE_ACCESS, args, new DeviceAccessRowMapper());
	}
}

class DeviceAccessRowMapper implements RowMapper<DeviceAccessVO> {
	@Override
	public DeviceAccessVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		DeviceAccessVO vo = new DeviceAccessVO();
		vo.setId(rs.getString("id"));
		vo.setState(rs.getString("state"));
		vo.setAccessDate(rs.getTimestamp("access_date"));
		return vo;
	}
}
