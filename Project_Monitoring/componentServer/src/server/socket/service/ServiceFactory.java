package server.socket.service;

import server.user.DeviceVO;

public class ServiceFactory {
	public boolean createUser(String id, String name, String password) {
		CreateUser createUser = new CreateUser();
		createUser.setUser(id, name, password);
		return createUser.create();
	}

	public DeviceVO login(String id, String password) {
		Login login = new Login();
		login.setUser(id, password);
		return login.login();
	}
	
	public void storeSensor(String id, int data) {
		SensorData sensor = new SensorData();
		sensor.setSensorData(id, data);
		sensor.storeSensor();
	}
	
	public void saveButtonState(String id, String state) {
		ButtonStateSave button = new ButtonStateSave();
		button.setButton(id, state);
		button.saveState();
	}
	
	public void devConnAccess(String id) {
		DeviceAccessLog device = new DeviceAccessLog();
		device.setConDeviceLog(id);
		device.devAccessLog(id);
	}
	
	public void devDisConnAccess(String id) {
		DeviceAccessLog device = new DeviceAccessLog();
		device.setDisConDeviceLog(id);
		device.devAccessLog(id);
	}
}
