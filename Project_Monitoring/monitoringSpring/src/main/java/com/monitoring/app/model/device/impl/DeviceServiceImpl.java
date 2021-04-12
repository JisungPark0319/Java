package com.monitoring.app.model.device.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitoring.app.model.device.DeviceService;
import com.monitoring.app.model.device.DeviceVO;

@Service("UserService")
public class DeviceServiceImpl implements DeviceService {
	@Autowired
	DeviceDAO userDAO;
	
	@Override
	public DeviceVO getDeviceInfo(DeviceVO vo) {
		return userDAO.getDeviceInfo(vo);
	}
}
