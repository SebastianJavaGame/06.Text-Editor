package scialsk.program.print;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class OptionPageFrame extends JFrame{
	public OptionPageFrame() {
		super("Option page");
		framePreference();
		addInside();
	}
	
	private void framePreference() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(600 +6, 360 +28);
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}

	private void addInside() {
		JPanel panel = new JPanel();
		add(panel);
		
		JButton okButton = new JButton("OK");
		JButton cancelButton = new JButton("CANCEL");
		
		JPanel leftFirstPanel = new JPanel();
		JPanel leftSecondPanel = new JPanel();
		JPanel leftThirdPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		
		leftFirstPanel.setBounds(0, 0, 400, 100);
		leftSecondPanel.setBounds(0, 100, 400, 100);
		leftThirdPanel.setBounds(0, 200, 400, 100);
		rightPanel.setBounds(400, 0, 200, 300);
		
		okButton.setBounds(400, 300, 60, 30);
		cancelButton.setBounds(460, 300, 90, 30);
		
		addFirstLeftInside(leftFirstPanel);
		addSecondLeftInside(leftSecondPanel);
		addThirdLeftInside(leftThirdPanel);
		addFirstRightInside(rightPanel);
		
		panel.setLayout(null);
		panel.add(leftFirstPanel);
		panel.add(leftSecondPanel);
		panel.add(leftThirdPanel);
		panel.add(rightPanel);
		panel.add(okButton);
		panel.add(cancelButton);
		
		leftFirstPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		leftSecondPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		leftThirdPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		rightPanel.setBorder(BorderFactory.createLineBorder(Color.green));
	}
	
	private void addFirstLeftInside(JPanel panel) {
		JLabel labelSize = new JLabel("Rozmiar:");
		JLabel labelSource = new JLabel("Zrodlo:");
		JComboBox<String> comboBoxSize = new JComboBox<>();
		JComboBox<String> comboBoxSource = new JComboBox<>();
		
		labelSize.setBounds(0, 0, 0, 0);
		labelSource.setBounds(0, 0, 0, 0);
		comboBoxSize.setBounds(0, 0, 0, 0);
		comboBoxSource.setBounds(0, 0, 0, 0);
		
		panel.add(labelSize);
		panel.add(labelSource);
		panel.add(comboBoxSize);
		panel.add(comboBoxSource);
	}
	
	private void addSecondLeftInside(JPanel panel) {
		
	}
	
	private void addThirdLeftInside(JPanel panel) {
		
	}
	
	private void addFirstRightInside(JPanel panel) {
		
	}
}
