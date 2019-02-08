package scislak.program.saveFile;

import scislak.program.CreateSaveDialog;
import scislak.program.Frame;

public class SimpleSave extends SaveFile{

	public SimpleSave(boolean alwaysShowDialog) {
		super();
		if(alwaysShowDialog)
			showSaveDialogFrame();
		else
			loadOldDocument(true);
	}
	
	@Override
	public void showSaveDialogFrame() {
		if(!isEmptyDocument)
			CreateSaveDialog.createDialog(Frame.getInstance().getFrame());
	}

}
