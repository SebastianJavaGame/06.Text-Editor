package scislak.program.dialogFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import scislak.program.CreateSaveDialog;

@SuppressWarnings("serial")
public  class DialogFrameClosing extends DialogFrame{
	
	public DialogFrameClosing() {
		setCommunicate("Are you want exit without saving?");
	}
	
	@Override
	protected void addListenerSaveButton(JButton button, JFrame frame) {
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateSaveDialog.createDialog(getFrame());
				System.exit(0);
			}
		});
	}

	@Override
	protected void addListenerNoSaveButton(JButton button, JFrame frame) {
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
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
