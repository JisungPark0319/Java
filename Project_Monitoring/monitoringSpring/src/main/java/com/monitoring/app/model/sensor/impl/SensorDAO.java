package com.monitoring.app.model.sensor.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.monitoring.app.model.sensor.SensorVO;

@Repository
class SensorDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final String GET_SENSOR_DATA = "SELECT * FROM SENSOR WHERE ID = ? "
			+ "ORDER BY CREATEDATE DESC LIMIT 0 , 10";
	
	List<SensorVO> getSensorData(SensorVO vo) {
		Object[] args = { vo.getId() };
		return jdbcTemplate.query(GET_SENSOR_DATA, args, new SensorRowMapper());
	}
}

class SensorRowMapper implements RowMapper<SensorVO> {
	@Override
	public SensorVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		SensorVO vo = new SensorVO();
		vo.setId(rs.getString("id"));
		vo.setData(rs.getInt("data"));
		vo.setCreateDate(rs.getTimestamp("createDate"));
		return vo;
	}
}