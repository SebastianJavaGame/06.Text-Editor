package scislak.program.saveFile;

import scislak.program.dialogFrame.DialogFrameNewDocument;

public class SaveFileNewDocumet extends SaveFile{

	public SaveFileNewDocumet() {
		super();
		loadOldDocument(true);
	}

	@Override
	public void showSaveDialogFrame() {
		if(!isEmptyDocument)
			new DialogFrameNewDocument();
	}

}
