package scislak.program;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import scislak.program.saveFile.SaveDocumentToFile;

public class CreateSaveDialog {
	public static void createDialog(JFrame frame) {
		if(frame != Frame.getInstance().getFrame())
				frame.dispose();
		
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", "txt");
		String nameFile = Frame.getInstance().getActualDocumentName();
		File file = new File("C:\\Users\\Sebastian\\Documents\\" +nameFile);
		
		chooser.setFileFilter(filter);
		chooser.setSelectedFile(file);
		
		int rVal = chooser.showSaveDialog(frame);
		if(rVal == JFileChooser.APPROVE_OPTION) {		
			SaveDocumentToFile saveFile = new SaveDocumentToFile(chooser.getCurrentDirectory().toPath().toString(), chooser.getSelectedFile().getName());
			saveFile.saveFile(true);
		}
	}
}
