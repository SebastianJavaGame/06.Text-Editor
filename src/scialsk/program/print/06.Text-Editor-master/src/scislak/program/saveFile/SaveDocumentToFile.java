package scislak.program.saveFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import scislak.program.Frame;

public class SaveDocumentToFile {
	public static final String AVAIABLE_EXTENSION = ".txt";
	private String path;
	private String nameFile;
	private String document;
	private File file;
	
	public SaveDocumentToFile(String path, String nameFile) {
		this.path = path;
		this.nameFile = "\\" + nameFile;
	}

	public void saveFile(boolean showConfirm) {
		this.document = Frame.getInstance().getDocumentText();
		
		if(showConfirm && checkFileExist()) {
			int option = JOptionPane.showConfirmDialog(null, "Nadpisaæ plik?", "Save", JOptionPane.YES_NO_OPTION);

			if (option == 0)
			   file.delete();
			else
			   System.out.print("no");
		}
		byte[] data = document.getBytes();
		Path file = Paths.get(path + addExtension(nameFile));
		try {
			Files.write(file, data);
			Frame.getInstance().setActualDocumentName(nameFile.substring(1));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean checkFileExist() {
		file = new File(path + addExtension(nameFile));
		return file.exists();
	}
	
	public static String addExtension(String nameFile) {
			int index = nameFile.length() -AVAIABLE_EXTENSION.length();
			if(index < 0)	index = 0;
			char[] extension = nameFile.substring(index, nameFile.length()).toCharArray();
			
			if(!String.valueOf(extension).equals(AVAIABLE_EXTENSION)) {
				if(nameFile.substring(nameFile.length()-1).equals("."))
					return nameFile + AVAIABLE_EXTENSION.substring(1);
				else
					return nameFile + AVAIABLE_EXTENSION;
			}
		return nameFile;
	}
}
