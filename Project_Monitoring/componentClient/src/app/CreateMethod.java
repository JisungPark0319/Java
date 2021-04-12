package app;
import component.log.TextLog;
import stream.MapBuffer;
import user.UserVO;

public class CreateMethod {
	private int createState;
	private MapBuffer mapBuffer;
	private UserVO userInfo;
	private boolean result;
	private TextLog textLog;

	public CreateMethod(MapBuffer mapBuffer, UserVO userInfo, TextLog textLog) {
		createState = 0;
		this.mapBuffer = mapBuffer;
		this.userInfo = userInfo;
		this.textLog = textLog;
	}
	
	public boolean result() {
		return result;
	}

	public void create() {
		switch (createState) {
		case 0:
			textLog.TextPrintLog("아이디 : ");
			createState++;
			break;
		case 1:
			if (mapBuffer.idCheck("scanner")) {
				userInfo.setId(mapBuffer.getData("scanner"));
				createState++;
			}
			break;
		case 2:
			textLog.TextPrintLog("이름 : ");
			createState++;
			break;
		case 3:
			if (mapBuffer.idCheck("scanner")) {
				userInfo.setName(mapBuffer.getData("scanner"));
				createState++;
			}
			break;
		case 4:
			textLog.TextPrintLog("비밀번호 : ");
			createState++;
			break;
		case 5:
			if (mapBuffer.idCheck("scanner")) {
				userInfo.setPassword(mapBuffer.getData("scanner"));
				result = true;
			}
			break;
		}
	}
}
