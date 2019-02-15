package scislak.program.saveFile;

import java.io.File;

import scislak.program.Frame;

public abstract class SaveFile {
	private String documnet;
	
	protected boolean isEmptyDocument;
	
	public SaveFile() {
		this.documnet = Frame.getInstance().getDocumentText();
		isEmptyDocument = checkIsEmpty();
	}
	
	public abstract void showSaveDialogFrame();
	
	protected boolean checkIsEmpty() {
		if(documnet.isEmpty())
			return true;
		else
			return false;
	}
	
	protected boolean checkIsEditabled() {
		System.out.println(Frame.getInstance().getOldDocument());
		Frame frame = Frame.getInstance();
		if((frame.getOldDocument() == null && !frame.getDocumentText().isEmpty())
		  ||frame.getOldDocument().equals(frame.getDocumentText()))
			return false;
		else
			return true;
	}
	
	protected void overwriteFile() {
		String nameFile = Frame.getInstance().getActualDocumentName();
		File file = new File("C:\\Users\\Sebastian\\Documents\\" +nameFile);
		
		if(file.exists()) {
			System.out.println("exist");
			SaveDocumentToFile saveDocumentToFile = new SaveDocumentToFile(file.getParent(), nameFile);
			saveDocumentToFile.saveFile(false);
			loadOldDocument(false);
		}else
			loadOldDocument(true);
	}
	
	protected void loadOldDocument(boolean withShowDialogSave) {
		if(withShowDialogSave)
			showSaveDialogFrame();
		
		Frame.getInstance().setOldDocument(Frame.getInstance().getDocumentText());
	}
}
