package server.socket.service;

import java.sql.Connection;
import java.sql.SQLException;

import server.button.ButtonService;
import server.button.ButtonVO;
import server.button.impl.ButtonServiceImpl;
import server.jdbc.connection.ConnectionProvider;

public class ButtonStateSave {
	private ButtonVO buttonVO;
	
	void setButton(String id, String state) {
		buttonVO = new ButtonVO();
		buttonVO.setId(id);
		buttonVO.setState(state);
	}
	
	void saveState() {
		if (buttonVO != null) {
			try (Connection conn = ConnectionProvider.getConnection()) {
				ButtonService buttonService = new ButtonServiceImpl();
				buttonService.insertButton(conn, buttonVO);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
}
