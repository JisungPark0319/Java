package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

import app.CreateMethod;
import app.InputThread;
import app.LoginMethod;
import app.ProcessType;
import component.log.TextLog;
import component.log.TextLogComponent;
import component.log.TextLogConsole;
import stream.MapBuffer;
import stream.SocketStream;
import timer.CheckTimer;
import user.UserVO;

public class Client extends Thread {
	private String address = "127.0.0.1";
	private int port = 6000;

	private MapBuffer mapBuffer;
	private InputThread inputThread;

	private TextLog textLog = new TextLogConsole();

	public void setComponentTextLog(JTextArea jtextArea) {
		this.textLog = new TextLogComponent(jtextArea);
	}

	public Client(MapBuffer mapBuffer, InputThread inputThread) {
		this.inputThread = inputThread;
		this.mapBuffer = mapBuffer;
	}

	@Override
	public void run() {
		CheckTimer connectTimer = new CheckTimer();
		textLog.TextPrintLog("서버 연결 시도");
		while (true) {
			if (connectTimer.laterSecTimer(3)) {
				try (Socket socket = new Socket(address, port)) {
					textLog.TextPrintLog("서버 연결");
					if (textLog instanceof TextLogConsole) {
						inputThread.start();
					}

					SocketStream socketStream = new SocketStream(socket);
					CheckTimer checkTimer = new CheckTimer();

					ProcessType deviceState = ProcessType.START_PRINT;
					UserVO userInfo = new UserVO();

					CreateMethod createMethod = null;
					LoginMethod loginMethod = null;

					while (true) {
						String readData = socketReadProccess(socketStream);
						if(readData.equals("connect")) {
							connectTimer.resetTimer();
						} else {
							if(connectTimer.laterSecTimer(5)) {
								textLog.TextPrintLog("서버 연결 끊김");
								socketStream.close();
								break;
							}
						}
						switch (deviceState) {
						case START_PRINT:
							textLog.TextPrintLog("1. 회원가입/ 2. 로그인");
							deviceState = ProcessType.START_INPUT;
							break;
						case START_INPUT:
							if (mapBuffer.idCheck("scanner")) {
								String state = mapBuffer.getData("scanner");
								if (state.equals("1")) {
									deviceState = ProcessType.CREATE;
									createMethod = new CreateMethod(mapBuffer, userInfo, textLog);
								} else if (state.equals("2")) {
									deviceState = ProcessType.LOGIN;
									loginMethod = new LoginMethod(mapBuffer, userInfo, textLog);
								}
							}
							break;
						case CREATE:
							createMethod.create();
							if (createMethod.result()) {
								String createData = userInfo.getId() + "/" + userInfo.getName() + "/"
										+ userInfo.getPassword();
								ProtocolData createProtocol = new ProtocolData();
								createProtocol.setId("server");
								createProtocol.setType("create");
								createProtocol.setData(createData);
								socketStream.socketSend(createProtocol.toString());
								checkTimer.startTimer();
								deviceState = ProcessType.CREATE_WAIT;
							}
							break;
						case CREATE_WAIT:
							if (readData.equals("create/true/")) {
								deviceState = ProcessType.START_PRINT;
								textLog.TextPrintLog("회원가입 성공");
							} else if (readData.equals("create/false/")) {
								deviceState = ProcessType.START_PRINT;
								textLog.TextPrintLog("회원가입 실패");
							}
							if (checkTimer.laterSecTimer(5)) {
								deviceState = ProcessType.START_PRINT;
								textLog.TextPrintLog("회원가입 응답 시간 초과");
							}
							break;
						case LOGIN:
							loginMethod.login();
							if (loginMethod.result()) {
								String loginData = userInfo.getId() + "/" + userInfo.getPassword();
								ProtocolData loginProtocol = new ProtocolData();
								loginProtocol.setId("server");
								loginProtocol.setType("login");
								loginProtocol.setData(loginData);
								socketStream.socketSend(loginProtocol.toString());
								checkTimer.startTimer();
								deviceState = ProcessType.LOGIN_WAIT;
							}
							break;
						case LOGIN_WAIT:
							if (readData.contains("login/true/")) {
								String[] parse = readData.split("/");
								userInfo.setName(parse[2]);
								SensorThread sensorThread = new SensorThread(socketStream, userInfo.getName());
								sensorThread.sensorStart();
								deviceState = ProcessType.OPERATION;
								textLog.TextPrintLog(userInfo.getName() + "님 로그인 되었습니다.");
							} else if (readData.equals("login/false/empty/")) {
								deviceState = ProcessType.START_PRINT;
								textLog.TextPrintLog("로그인 실패");
							} else if (readData.equals("login/false/overlap/")) {
								deviceState = ProcessType.START_PRINT;
								textLog.TextPrintLog("접속 중인 아이디입니다.");
							}
							if (checkTimer.laterSecTimer(5)) {
								deviceState = ProcessType.START_PRINT;
								textLog.TextPrintLog("로그인 응답 시간 초과");
							}
							break;
						case OPERATION:
							switch (readData) {
							case "button/on/":
								textLog.TextPrintLog("button ON");
								ProtocolData onProtocol = new ProtocolData();
								onProtocol.setId("server");
								onProtocol.setType("button");
								onProtocol.setData("on");
								socketStream.socketSend(onProtocol.toString());
								break;
							case "button/off/":
								textLog.TextPrintLog("button OFF");
								ProtocolData offProtocol = new ProtocolData();
								offProtocol.setId("server");
								offProtocol.setType("button");
								offProtocol.setData("off");
								socketStream.socketSend(offProtocol.toString());
								break;
							default:
								break;
							}
							break;
						}
					}

				} catch (UnknownHostException e) {
					textLog.TextPrintLog("서버 접속이 불가능합니다");
					// e.printStackTrace();
				} catch (IOException e) {
					textLog.TextPrintLog("서버 접속이 불가능합니다");
					// TODO Auto-generated catch block
					// e.printStackTrace();
				} finally {
					textLog.TextPrintLog("서버 재연결");
					connectTimer.resetTimer();
				}
			}
		}
	}

	private static String socketReadProccess(SocketStream socketStream) {
		try {
			if (socketStream.checkReadLength() > 0) {
				String createRead = "";
				createRead = socketStream.getStringRead();
				String[] parse = createRead.split("/");
				if (parse[0].equals("client")) {
					switch (parse[1]) {
					case "create":
						if (parse[2].equals("true")) {
							return "create/true/";
						} else {
							return "create/false/";
						}
					case "login":
						if (parse[2].equals("true")) {
							return "login/true/" + parse[3] + "/";
						} else if (parse[2].equals("false")) {
							if (parse[3].equals("empty")) {
								return "login/false/empty/";
							} else if (parse[3].equals("overlap")) {
								return "login/false/overlap/";
							}
						}
					case "check":
						String checkData = "server/check/";
						socketStream.socketSend(checkData);
						return "connect";
					case "on":
						return "button/on/";
					case "off":
						return "button/off/";
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "false";
	}
}
