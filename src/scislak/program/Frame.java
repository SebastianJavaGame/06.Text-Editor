package scislak.program;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class Frame extends JFrame{
	private JFrame frame;
	private JMenuBar bar;
	
	public Frame() {
		setFrame();
		addTopBar();
		addTextArea();
	}

	private void addTopBar() {
		bar = new JMenuBar();
		frame.setJMenuBar(bar);
		
		JMenu file = new JMenu(" File ");
		JMenu edit = new JMenu(" Edit ");
		JMenu format = new JMenu(" Format ");
		JMenu view = new JMenu(" View ");
		JMenu help = new JMenu(" Help ");
	
		bar.add(file);
		bar.add(edit);
		bar.add(format);
		bar.add(view);
		bar.add(help);
		
		addFile(file);
		addEdit(edit);
		addFormat(format);
		addView(view);
		addHelp(help);
		pack();
	}
	
	private void addTextArea() {
		
	}
	
	private void addFile(JMenu file) {
		JMenuItem newFile = new JMenuItem("New");
		JMenuItem openFile = new JMenuItem("Open");
		JMenuItem saveFile = new JMenuItem("Save");
		JMenuItem saveAsFile = new JMenuItem("Save as");
		JMenuItem optionFile = new JMenuItem("Page options");
		JMenuItem printFile = new JMenuItem("Print");
		JMenuItem exit = new JMenuItem("Exit");
		
		file.add(newFile);
		file.add(openFile);
		file.add(saveFile);
		file.add(saveAsFile);
		file.addSeparator();
		file.add(optionFile);
		file.add(printFile);
		file.addSeparator();
		file.add(exit);
	}
	
	private void addEdit(JMenu edit) {
		JMenuItem undo = new JMenuItem("Undo");
		JMenuItem cut = new JMenuItem("Cut");
		JMenuItem copy = new JMenuItem("Copy");
		JMenuItem paste = new JMenuItem("Paste");
		JMenuItem delete = new JMenuItem("Delete");
		JMenuItem find = new JMenuItem("Find");
		JMenuItem findNext = new JMenuItem("Find next");
		JMenuItem change = new JMenuItem("Change..");
		JMenuItem goTo = new JMenuItem("GO to");
		JMenuItem selectAll = new JMenuItem("Select all");
		JMenuItem dateHour = new JMenuItem("Date/Hour");
		
		edit.add(undo);
		edit.addSeparator();
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.add(delete);
		edit.addSeparator();
		edit.add(find);
		edit.add(findNext);
		edit.add(change);
		edit.add(goTo);
		edit.addSeparator();
		edit.add(selectAll);
		edit.add(dateHour);
	}
	
	private void addFormat(JMenu format) {
		JMenuItem wrappingLines = new JMenuItem("Wrapping lines");
		JMenuItem font = new JMenuItem("Font..");
		
		format.add(wrappingLines);
		format.add(font);
	}
	
	private void addView(JMenu view) {
		JMenuItem statusBar = new JMenuItem("Statusbar");
		
		view.add(statusBar);
	}
	
	private void addHelp(JMenu help) {
		JMenuItem showHelp = new JMenuItem("Show help");
		JMenuItem infoNotepade = new JMenuItem("Info Notepade");
		
		help.add(showHelp);
		help.add(infoNotepade);
	}

	private void setFrame() {
		frame = new JFrame("Text Editor");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setResizable(true);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize((int)(dim.width*0.7), (int)(dim.height*0.7));
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
	}
}
