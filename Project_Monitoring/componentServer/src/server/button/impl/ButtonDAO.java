package server.button.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import server.button.ButtonVO;

class ButtonDAO {
	private static final String BUTTON_INSERT = "INSERT INTO BUTTON VALUES(?, ?, NOW())";

	void insertButton(Connection conn, ButtonVO vo) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(BUTTON_INSERT)) {
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getState());
			pstmt.executeUpdate();
		}
	}
}
