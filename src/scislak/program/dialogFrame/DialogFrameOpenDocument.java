package scislak.program.dialogFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import scislak.program.CreateSaveDialog;
import scislak.program.Frame;

@SuppressWarnings("serial")
public class DialogFrameOpenDocument extends DialogFrame{

	public DialogFrameOpenDocument() {
		super();
		setCommunicate("Do you want saveing prevoius file?");
	}
	
	@Override
	protected void addListenerSaveButton(JButton button, JFrame frame) {
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateSaveDialog.createDialog(getFrame());
				Frame.getInstance().clearDocument();
				OpenFileToDocument.loadFileOfFile(frame);
			}
		});
	}

	@Override
	protected void addListenerNoSaveButton(JButton button, JFrame frame) {
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Frame.getInstance().clearDocument();
				OpenFileToDocument.loadFileOfFile(frame);
			}
		});
	}

	@Override
	protected void addListenerCancelButton(JButton button, JFrame frame) {
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}

}
