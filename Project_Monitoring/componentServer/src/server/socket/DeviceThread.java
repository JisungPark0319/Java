package server.socket;

import java.io.IOException;
import java.net.Socket;

import javax.swing.JTextArea;

import componentServer.log.TextLog;
import componentServer.log.TextLogComponent;
import componentServer.log.TextLogConsole;
import server.socket.service.ServiceFactory;
import server.stream.InnerStream;
import server.stream.OverlapId;
import server.user.DeviceVO;

public class DeviceThread extends Thread {
	private Socket socket;
	private InnerStream innerStream;
	private OverlapId overlapId;
	private ConnCheckTimer checkTimer;
	private TextLog textLog = new TextLogConsole();

	public void setComponentTextLog(TextLog textLog) {
		this.textLog = textLog;
	}

	public DeviceThread(Socket socket, InnerStream innerStream, OverlapId overlapId) {
		this.socket = socket;
		this.innerStream = innerStream;
		this.overlapId = overlapId;
		this.checkTimer = new ConnCheckTimer();
	}

	@Override
	public void run() {
		String acceptId = "";
		SocketStream socketStream = null;

		/*
		 * 
		 * String server/dataType/data dataType : create, login data : create
		 * -/id/name/password/ login - /id/passowrd/
		 * 
		 * ex) server/login/testid/testpw/
		 */

		ServiceFactory service = new ServiceFactory();

		textLog.TextPrintLog("[DeviceThread] === > address : " + socket.getInetAddress() + ", poart : " + socket.getPort());
		try {
			socketStream = new SocketStream(socket);
			checkTimer.startTimer();
			String protocol = "";

			int threadQuit = 0;
			int concCheckState = 0;
			while (threadQuit == 0) {
				if (socketStream.checkReadLength() > 0) {
					protocol = socketStream.getStringRead();

					String[] array = protocol.split("/");
					if (array[0].equals("server")) {
						switch (array[1]) {
						case "create":
							// System.out.println("[DeviceThread] === > create");
							String createId = array[2];
							String createName = array[3];
							String createPassword = array[4];
							boolean createResult = service.createUser(createId, createName, createPassword);
							String createData;
							if (createResult == true) {
								createData = "client/create/true/";
							} else {
								createData = "client/create/false/";
							}
							socketStream.SocketSend(createData);
							break;
						case "login":
							// System.out.println("[DeviceThread] === > login");
							String loginId = array[2];
							String loginPassword = array[3];
							DeviceVO loginInfo = service.login(loginId, loginPassword);
							String loginData;
							if (loginInfo != null) {
								if (!overlapId.chekcId(loginInfo.getId())) {
									overlapId.pushId(loginInfo.getId());
									loginData = "client/login/true/" + loginInfo.getId() + "/";
									acceptId = "" + loginId;
									service.devConnAccess(acceptId);
									textLog.TextPrintLog("address : " + socket.getInetAddress() + ", Device : " + acceptId + "로그인");
								} else {
									loginData = "client/login/false/overlap/";
								}
							} else {
								loginData = "client/login/false/empty/";
							}
							socketStream.SocketSend(loginData);
							break;
						case "sensor":
							// System.out.println("[DeviceThread] === > sensor");
							String sensorId = array[2];
							int sensorData = Integer.parseInt(array[3]);
							service.storeSensor(sensorId, sensorData);
							break;
						case "button":
							String button = array[2];
							String httpId = acceptId + "_http";
							if (button.equals("on")) {
								innerStream.putStream(httpId, "on");
								// service.saveButtonState(acceptId, "on");
							} else if (button.equals("off")) {
								innerStream.putStream(httpId, "off");
								// service.saveButtonState(acceptId, "off");
							}
							break;
						case "check":
							// System.out.println("[DeviceThread] === > check");
							concCheckState = 0;
							break;
						}
					}
				}
				if (!acceptId.isEmpty()) {
					String checkKeyId = acceptId + "_socket"; // id_socket if
					if (innerStream.checkKey(checkKeyId)) {
						textLog.TextPrintLog("[DeviceThread] === > checkKey :" + checkKeyId);
						String data = innerStream.getStream(checkKeyId);
						String sendData;
						switch (data) {
						case "on":
							textLog.TextPrintLog("[DeviceThread] === > " + acceptId + " : ON");
							sendData = "client/on";
							socketStream.SocketSend(sendData);
							break;
						case "off":
							textLog.TextPrintLog("[DeviceThread] === > " + acceptId + " : OFF");
							sendData = "client/off";
							socketStream.SocketSend(sendData);
							break;
						}
					}
				}
				// 연결된 Socket이 Disconnect되었는지 확인하기 위해 1sec마다 송수신
				if (checkTimer.laterSecTimer(1)) {
					switch (concCheckState) {
					//
					case 0:
						String checkData = "client/check/";
						socketStream.SocketSend(checkData);
						concCheckState = 1;
						break;
					// 응답 없을 시 thread 종료
					case 1:
						threadQuit = 1;
						if(!acceptId.isEmpty() && acceptId != null)
							service.devDisConnAccess(acceptId);
						break;
					default:
						concCheckState = 0;
						break;
					}
					checkTimer.resetTimer();
				}
			}
		} catch (IOException e) {
			//e.printStackTrace();
		} finally {
			overlapId.removeId(acceptId);

			if (socketStream != null) {
				socketStream.close();
			}

			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			textLog.TextPrintLog(
					"[DeviceThread] === > quit - address : " + socket.getInetAddress() + ", poart : " + socket.getPort());
		}
	}
}
