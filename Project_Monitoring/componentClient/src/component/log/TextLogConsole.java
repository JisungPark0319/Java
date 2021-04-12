package component.log;

public class TextLogConsole implements TextLog {
	@Override
	public void TextPrintLog(String log) {
		System.out.println(log);
	}
}
