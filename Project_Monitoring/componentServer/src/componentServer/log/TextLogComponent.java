package componentServer.log;

import javax.swing.JTextArea;

public class TextLogComponent implements TextLog {
	private JTextArea textArea;
	
	public TextLogComponent(JTextArea textArea) {
		this.textArea = textArea;
	}
	
	@Override
	public void TextPrintLog(String log) {
		textArea.append(log+"\n");
	}
}
