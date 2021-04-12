package com.monitoring.app.model.device.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitoring.app.model.device.DeviceAccessService;
import com.monitoring.app.model.device.DeviceAccessVO;

@Service("DeviceService")
public class DeviceAccessServiceImpl implements DeviceAccessService {
	@Autowired
	DeviceAccessDAO deviceDAO;
	
	@Override
	public List<DeviceAccessVO> getDevStateList() {
		return deviceDAO.getDevStateList();
	}
	@Override
	public List<DeviceAccessVO> getDevStateList(int startRow, int endRow) {
		return deviceDAO.getDevStateList(startRow, endRow);
	}
	@Override
	public int deviceListCount() {
		return deviceDAO.deviceListCount();
	}
	@Override
	public DeviceAccessVO getDevIdState(String id) {
		return deviceDAO.getDevIdState(id);
	}
}
