package com.monitoring.app.model.device;

import java.sql.Timestamp;
import java.util.Date;

public class DeviceAccessVO {
	private String id;
	private String state;
	private Timestamp accessDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getAccessDate() {
		return accessDate;
	}
	public void setAccessDate(Timestamp accessDate) {
		this.accessDate = accessDate;
	}
	
	
}
