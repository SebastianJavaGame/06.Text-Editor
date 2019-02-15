package scislak.program.saveFile;

import scislak.program.dialogFrame.DialogFrameClosing;

public class SaveFileClosing extends SaveFile{

	public SaveFileClosing() {
		super();
		showSaveDialogFrame();
	}

	@Override
	public void showSaveDialogFrame() {
		if(isEmptyDocument || !checkIsEditabled())
			System.exit(0);
		else
			new DialogFrameClosing();
	}

}
