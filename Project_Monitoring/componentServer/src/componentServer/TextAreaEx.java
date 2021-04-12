package componentServer;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import server.http.HttpConnection;
import server.jdbc.DBCPInitListener;
import server.socket.DeviceServer;
import server.stream.InnerStream;
import server.stream.OverlapId;

public class TextAreaEx extends JFrame {
	JTextArea ta = new JTextArea(20, 50);

	public TextAreaEx() {
		setTitle("Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btn = new JButton("Start");
		btn.addActionListener(new ActionListener() { // 익명클래스로 리스너 작성
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				if (btn.getText().equals("Start")) {
					// DB Connection
					DBCPInitListener dbcpInitListener = new DBCPInitListener();
					dbcpInitListener.DBCPStart();

					// Device Socket Thread <-> Http Server 통신을 위한 내부 스트림 (Map)
					InnerStream innerStream = new InnerStream();
					// Socket 연결된 Device 아이디 저장
					OverlapId overlapId = new OverlapId();

					// Port 9000 Http Server
					HttpConnection httpCon = new HttpConnection(innerStream, overlapId);
					httpCon.setComponentTextLog(ta);
					httpCon.start();
					
					// Socket Server
					DeviceServer server = new DeviceServer();
					server.setComponentTextLog(ta);
					server.setInnerStream(innerStream);
					server.setOverlapId(overlapId);
					server.start();
					btn.setText("Exit");
				} else {
					System.exit(JFrame.EXIT_ON_CLOSE); // 프레임을 종료한다
				}
			}
		});

		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		c.add(new JScrollPane(ta));
		c.add(btn);

		// ta.append(t.getText() + "\n");
		setSize(600, 500);
		setVisible(true);
	}
}
