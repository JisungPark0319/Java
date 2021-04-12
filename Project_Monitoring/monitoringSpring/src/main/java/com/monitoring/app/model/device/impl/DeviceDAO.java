package com.monitoring.app.model.device.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.monitoring.app.model.device.DeviceVO;

@Repository
class DeviceDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	private static final String GET_ID_DEVICE = "SELECT * FROM DEVICE WHERE ID = ?";

	DeviceVO getDeviceInfo(DeviceVO vo) {
		Object[] args = { vo.getId() };
		return jdbcTemplate.queryForObject(GET_ID_DEVICE, args, new DeviceRowMapper());
	}
}

class DeviceRowMapper implements RowMapper<DeviceVO> {
	public DeviceVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		DeviceVO deviceVO = new DeviceVO();
		deviceVO.setId(rs.getString("id"));
		deviceVO.setName(rs.getString("name"));
		deviceVO.setPassword(rs.getString("password"));
		deviceVO.setCreateDate(rs.getTimestamp("createDate"));
		return deviceVO;
	}
}
