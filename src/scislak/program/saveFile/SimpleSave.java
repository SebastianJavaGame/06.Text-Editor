package scislak.program.saveFile;

import scislak.program.CreateSaveDialog;
import scislak.program.Frame;

public class SimpleSave extends SaveFile{

	public SimpleSave() {
		super();
		overwriteFile();
	}
	
	@Override
	public void showSaveDialogFrame() {
		if(!isEmptyDocument)
			CreateSaveDialog.createDialog(Frame.getInstance().getFrame());
	}

}
