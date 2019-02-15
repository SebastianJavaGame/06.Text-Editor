package scislak.program.dialogFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public abstract class DialogFrame extends JFrame{
	private JLabel label;
	
	public DialogFrame() {
		super("Save");	
		setSize(380, 140);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setAlwaysOnTop(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
		addInside();
	}
	
	protected abstract void addListenerSaveButton(JButton button, JFrame frame);
	protected abstract void addListenerNoSaveButton(JButton button, JFrame frame);
	protected abstract void addListenerCancelButton(JButton button, JFrame frame);
	
	private void addInside() {
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel comunicate = new JPanel();
		label = new JLabel("", SwingConstants.CENTER);
		JButton save = new JButton("Save");
		JButton noSave = new JButton("No save");
		JButton cancel = new JButton("Cancel");
		
		label.setFont(new Font("Serif", Font.PLAIN, 20));
		
		save.setSize(50, 50);
		noSave.setSize(50,50);
		cancel.setSize(50,50);
		
		add(panel1, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
		
		panel1.setLayout(new BorderLayout());
		panel.setLayout(new BorderLayout());
		comunicate.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		panel.add(new JSeparator(), BorderLayout.NORTH);
		panel.add(comunicate, BorderLayout.CENTER);
		
		panel1.add(label);
		comunicate.add(save);
		comunicate.add(noSave);
		comunicate.add(cancel);
		
		addListenerSaveButton(save, this);
		addListenerNoSaveButton(noSave, this);
		addListenerCancelButton(cancel, this);
	}
	
	protected JFrame getFrame() {
		return this;
	}
	
	protected void setCommunicate(String communicate) {
		this.label.setText(communicate);
	}
}
