package scislak.program.saveFile;

import scislak.program.Frame;
import scislak.program.dialogFrame.DialogFrameOpenDocument;
import scislak.program.dialogFrame.OpenFileToDocument;

public class SaveAndOpenDocument extends SaveFile{

	public SaveAndOpenDocument() {
		super();
		loadOldDocument(true);
	}

	@Override
	public void showSaveDialogFrame() {
		if(isEmptyDocument || !checkIsEditabled())
			OpenFileToDocument.loadFileOfFile(Frame.getInstance().getFrame());
		else
			new DialogFrameOpenDocument();			
	}
}
