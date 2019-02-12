package scialsk.program.print;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

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
		setSize(600, 375);
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
		JPanel leftSecondLeftPanel = new JPanel();
		JPanel leftSecondRightPanel = new JPanel();
		JPanel leftThirdPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		
		leftFirstPanel.setBounds(1, 0, 400, 100);
		leftSecondLeftPanel.setBounds(1, 100, 150, 100);
		leftSecondRightPanel.setBounds(150, 100, 250, 100);
		leftThirdPanel.setBounds(1, 200, 400, 100);
		rightPanel.setBounds(400, 0, 192, 300);
		
		okButton.setBounds(420, 305, 60, 30);
		cancelButton.setBounds(490, 305, 90, 30);
		
		addFirstLeftInside(leftFirstPanel);
		addSecondLeftFirstLeftInside(leftSecondLeftPanel);
		addSecondLeftFirstRightInside(leftSecondRightPanel);
		addThirdLeftInside(leftThirdPanel);
		addFirstRightInside(rightPanel);
		addActionListenerToButtons(okButton, cancelButton);
		
		panel.setLayout(null);
		panel.add(leftFirstPanel);
		panel.add(leftSecondLeftPanel);
		panel.add(leftSecondRightPanel);
		panel.add(leftThirdPanel);
		panel.add(rightPanel);
		panel.add(okButton);
		panel.add(cancelButton);
	}
	
	private void addFirstLeftInside(JPanel panel) {
		JLabel labelSize = new JLabel("Rozmiar:");
		JLabel labelSource = new JLabel("Zrodlo:");
		JComboBox<String> comboBoxSize = new JComboBox<>();
		JComboBox<String> comboBoxSource = new JComboBox<>();
		
		panel.setLayout(null);
		
		labelSize.setBounds(60, 20, 120, 25);
		labelSource.setBounds(60, 55, 120, 25);
		comboBoxSize.setBounds(140, 20, 200, 25);
		comboBoxSource.setBounds(140, 55, 200, 25);
		
		panel.add(labelSize);
		panel.add(labelSource);
		panel.add(comboBoxSize);
		panel.add(comboBoxSource);
		
		Border etched = BorderFactory.createEtchedBorder();
		Border titled = BorderFactory.createTitledBorder(etched, "Papier");
		panel.setBorder(titled);
	}
	
	private void addSecondLeftFirstLeftInside(JPanel panel) {
		ButtonGroup group = new ButtonGroup();
		JRadioButton vertical = new JRadioButton("Pozioma", true);
		group.add(vertical);
		
		JRadioButton horizontal = new JRadioButton("Pionowa", false);
		group.add(horizontal);
		
		vertical.setBounds(25, 20, 100, 25);
		horizontal.setBounds(25, 55, 100, 25);
		
		panel.setLayout(null);
		panel.add(vertical);
		panel.add(horizontal);
		
		Border etched = BorderFactory.createEtchedBorder();
		Border titled = BorderFactory.createTitledBorder(etched, "Orientacja");
		panel.setBorder(titled);
	}
	
	private void addSecondLeftFirstRightInside(JPanel panel) {
		JLabel leftLabel = new JLabel("Lewy:");
		JLabel rightLabel = new JLabel("Prawy:");
		JLabel upLabel = new JLabel("Górny:");
		JLabel downLabel = new JLabel("Dolny:");
		
		JTextField leftField = new JTextField();
		JTextField rightField = new JTextField();
		JTextField upField = new JTextField();
		JTextField downField = new JTextField();
		
		leftLabel.setBounds(25, 20, 40, 25);
		rightLabel.setBounds(135, 20, 40, 25);
		upLabel.setBounds(25, 55, 40, 25);
		downLabel.setBounds(135, 55, 40, 25);
		leftField.setBounds(60, 20, 50, 25);
		rightField.setBounds(175, 20, 50, 25);
		upField.setBounds(60, 55, 50, 25);
		downField.setBounds(175, 55, 50, 25);
		
		panel.setLayout(null);
		panel.add(leftLabel);
		panel.add(rightLabel);
		panel.add(upLabel);
		panel.add(downLabel);
		panel.add(leftField);
		panel.add(rightField);
		panel.add(upField);
		panel.add(downField);
		
		Border etched = BorderFactory.createEtchedBorder();
		Border titled = BorderFactory.createTitledBorder(etched, "Marginesy (milimetry)");
		panel.setBorder(titled);
	}
	
	private void addThirdLeftInside(JPanel panel) {
		JLabel header = new JLabel("Nag³ówek");
		JLabel footer = new JLabel("Stopka");
		
		JTextField headerField = new JTextField();
		JTextField footerField = new JTextField();
		
		header.setBounds(60, 20, 70, 25);
		footer.setBounds(60, 55, 70, 25);
		headerField.setBounds(140, 20, 200, 25);
		footerField.setBounds(140, 55, 200, 25);
		
		panel.setLayout(null);
		panel.add(header);
		panel.add(footer);
		panel.add(headerField);
		panel.add(footerField);
		
		Border etched = BorderFactory.createEtchedBorder();
		Border titled = BorderFactory.createTitledBorder(etched, "Opis");
		panel.setBorder(titled);
	}
	
	private void addFirstRightInside(JPanel panel) {
		Border etched = BorderFactory.createEtchedBorder();
		Border titled = BorderFactory.createTitledBorder(etched, "Podgl¹d");
		panel.setBorder(titled);
	}
	
	private void addActionListenerToButtons(JButton ok, JButton cancel){
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}
}
