package scislak.program;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import scialsk.program.print.OptionPageFrame;
import scialsk.program.print.PrintFile;
import scislak.program.saveFile.SaveAndOpenDocument;
import scislak.program.saveFile.SaveFileClosing;
import scislak.program.saveFile.SaveFileNewDocumet;
import scislak.program.saveFile.SimpleSave;

public class Frame{
	private static final Frame instance = new Frame();  

	private String actualDocumentName;
	private String oldDocument;
    private JFrame frame;
	private JMenuBar bar;
	private JTextArea textArea;	
	
	public static Frame getInstance(){
        return instance;
    }
	
	private Frame() {}
	
	public void init() {
		actualDocumentName = "Bez nazwy.txt";
		frame = new JFrame(actualDocumentName +" - Notepade");
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
	}
	
	private void addTextArea() {
		textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		frame.add(scrollPane);
		frame.validate();
		textArea.requestFocus();
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
		
		newFileListener(newFile);
		openFileListener(openFile);
		saveFileListener(saveFile);
		saveAsFileListener(saveAsFile);
		optionFileListener(optionFile);
		printFileListener(printFile);
		exitFileListener(exit);
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
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setResizable(true);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize((int)(dim.width*0.7), (int)(dim.height*0.7));
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		closeApplication();
	}
	
	private void closeApplication() {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				new SaveFileClosing(); 
			}
		});
	}
	
	private void newFileListener(JMenuItem newFile) {

		newFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SaveFileNewDocumet();
			}
		});
	}
	
	private void openFileListener(JMenuItem openFile) {

		openFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SaveAndOpenDocument();
			}
		});
	}
	
	private void saveFileListener(JMenuItem saveFile) {
		saveFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SimpleSave(false);
			}
		});
	}
	
	private void saveAsFileListener(JMenuItem saveAsFile) {
		saveAsFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SimpleSave(true);
			}
		});
	}
	
	private void optionFileListener(JMenuItem optionFile) {
		optionFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new OptionPageFrame();
			}
		});
	}
	
	private void printFileListener(JMenuItem printFile) {
		printFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new PrintFile();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void exitFileListener(JMenuItem exitFile) {
		exitFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SaveFileClosing();
			}
		});
	}
	
	public void clearDocument() {
		textArea.setText("");
		actualDocumentName = "Bez nazwy.txt";
	}
	
	public void setTextDocument(String text) {
		textArea.setText(text);
	}
	
	public String getDocumentText() {
		return textArea.getText();
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public String getActualDocumentName() {
		return actualDocumentName;
	}
	
	public void setActualDocumentName(String actualDocumentName) {
		this.actualDocumentName = actualDocumentName;
		frame.setTitle(actualDocumentName +" - Notepad");
	}

	public String getOldDocument() {
		return oldDocument;
	}

	public void setOldDocument(String oldDocument) {
		this.oldDocument = oldDocument;
	}
}
