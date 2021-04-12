package com.monitoring.app.model.device;

import java.util.List;

public interface DeviceAccessService {
	List<DeviceAccessVO> getDevStateList();
	int deviceListCount();
	List<DeviceAccessVO> getDevStateList(int startRow, int endRow);
	DeviceAccessVO getDevIdState(String id);
}
