package server.button;

import java.sql.Connection;

public interface ButtonService {
	void insertButton(Connection conn, ButtonVO vo);
}
