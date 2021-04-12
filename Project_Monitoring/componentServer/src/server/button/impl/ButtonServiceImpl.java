package server.button.impl;

import java.sql.Connection;
import java.sql.SQLException;

import server.button.ButtonService;
import server.button.ButtonVO;

public class ButtonServiceImpl implements ButtonService {
	ButtonDAO buttonDAO = new ButtonDAO();

	@Override
	public void insertButton(Connection conn, ButtonVO vo) {
		try {
			buttonDAO.insertButton(conn, vo);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
