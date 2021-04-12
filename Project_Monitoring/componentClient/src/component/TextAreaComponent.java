package component;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import app.InputThread;
import client.Client;
import stream.MapBuffer;

public class TextAreaComponent extends JFrame {
	private JTextField tf = new JTextField(20);
	private JTextArea ta = new JTextArea(20, 50);
	private JButton btn = new JButton("Start");
	private String clientStart = "wait";

	public TextAreaComponent(MapBuffer mapBuffer, InputThread inputThread) {
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		tf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField t = (JTextField) e.getSource();
				String text = t.getText();
				if (clientStart.equals("start")) {
					ta.append(text + "\n");
					mapBuffer.pushData("scanner", text);
					t.setText("");
				}
			}
		});

		btn.addActionListener(new ActionListener() { // 익명클래스로 리스너 작성
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				if (btn.getText().equals("Start")) {
					Client client = new Client(mapBuffer, inputThread);
					client.setComponentTextLog(ta);
					client.start();
					clientStart = "start";
					btn.setText("Exit");
				} else {
					System.exit(JFrame.EXIT_ON_CLOSE); // 프레임을 종료한다
				}
			}
		});

		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		c.add(new JLabel("입력 후 <Enter> 키를 입력하세요"));
		c.add(tf);
		c.add(new JScrollPane(ta));
		c.add(btn);

		setSize(600, 500);
		setVisible(true);
	}
}
