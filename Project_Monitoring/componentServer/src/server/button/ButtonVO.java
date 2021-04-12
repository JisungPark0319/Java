package server.button;

import java.util.Date;

public class ButtonVO {
	private String id;
	private String state;
	private Date date;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "ButtonVO [id=" + id + ", state=" + state + ", date=" + date + "]";
	}
	
	
}
