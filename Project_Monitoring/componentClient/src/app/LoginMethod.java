package app;
import component.log.TextLog;
import stream.MapBuffer;
import user.UserVO;

public class LoginMethod {
	private int loginState;
	private MapBuffer mapBuffer;
	private UserVO userInfo;
	private boolean result;
	private TextLog textLog;

	public LoginMethod(MapBuffer mapBuffer, UserVO userInfo, TextLog textLog) {
		loginState = 0;
		this.mapBuffer = mapBuffer;
		this.userInfo = userInfo;
		this.textLog = textLog;
	}

	public boolean result() {
		return result;
	}

	public void login() {
		switch (loginState) {
		case 0:
			textLog.TextPrintLog("아이디 : ");
			loginState++;
			break;
		case 1:
			if (mapBuffer.idCheck("scanner")) {
				userInfo.setId(mapBuffer.getData("scanner"));
				loginState++;
			}
			break;
		case 2:
			textLog.TextPrintLog("비밀번호 : ");
			loginState++;
			break;
		case 3:
			if (mapBuffer.idCheck("scanner")) {
				userInfo.setPassword(mapBuffer.getData("scanner"));
				result = true;
			}
			break;
		}
	}
}
