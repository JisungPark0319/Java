package com.monitoring.app.model.sensor;

import java.sql.Timestamp;

public class SensorVO {
	private String id;
	private int data;
	private Timestamp createDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "[{id:" + id + ", data:" + data + ", createDate:" + createDate + "}]";
	}

}
