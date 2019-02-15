package scislak.program.dialogFrame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import scislak.program.Frame;

public class OpenFileToDocument {
	private static String name;
	
	public static void loadFileOfFile(JFrame frame) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", "txt");
		chooser.setFileFilter(filter);
		
		int rVal = chooser.showOpenDialog(frame);
		if(rVal == JFileChooser.APPROVE_OPTION) {
			try {
				name = chooser.getSelectedFile().getName();
				FileReader reader = new FileReader(chooser.getSelectedFile());
				BufferedReader buff = new BufferedReader(reader);
				String line = null;
				Frame.getInstance().clearDocument();
				
				while((line = buff.readLine()) != null) {
					Frame.getInstance().setTextDocument(Frame.getInstance().getDocumentText() +line +"\n");
				}
				buff.close();
				reader.close();
				Frame.getInstance().setActualDocumentName(name);
				Frame.getInstance().setOldDocument(Frame.getInstance().getDocumentText());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
