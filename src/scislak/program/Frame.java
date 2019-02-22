package scislak.program;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import scialsk.program.print.OptionPageFrame;
import scialsk.program.print.PrintFile;
import scislak.edit.ChangeSimple;
import scislak.edit.FindSimple;
import scislak.edit.GoToFrame;
import scislak.edit.JFontChooser;
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

        private String actualDocument;
        private String storageDocument;
        private boolean isDocumentFromStorage;
	
	public static Frame getInstance(){
        return instance;
    }
	
	private Frame() {}
	
	public void init() {
		actualDocumentName = "Bez nazwy.txt";
                storageDocument = "";
                actualDocument = "";
                isDocumentFromStorage = false;
		frame = new JFrame(actualDocumentName +" - Notepade");
                setFrame();
		addTopBar();
		addTextArea();
                textArea.setLineWrap(true);
                updateStatusBar();
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
                
                StatusBar bar = StatusBar.getInstance();
                bar.init();
                JPanel status = bar;
                frame.add(status, BorderLayout.SOUTH);
	}
	
	private void addTextArea() {
		textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		frame.add(scrollPane);
		frame.validate();
		textArea.requestFocus();
                
                textArea.addCaretListener(new CaretListener() {
                    @Override
                    public void caretUpdate(CaretEvent e) {
                        updateStatusBar();
                    }
                });
	}
        
        private void updateStatusBar(){
            GoToFrame.updateCursorPositions();
            int x = GoToFrame.getCursorPosition();
            int y = GoToFrame.getCursorLine();
            StatusBar.getInstance().updateStatus(x, y);
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
                
                undoListener(undo);
                cutListener(cut);
                copyListener(copy);
                pasteListener(paste);
                deleteListener(delete);
                findListener(find);
                findNextListener(findNext);
                changeListener(change);
                goToListener(goTo);
                selectAllListener(selectAll);
                dateHourListener(dateHour);
	}
	
	private void addFormat(JMenu format) {
		JCheckBoxMenuItem wrappingLines = new JCheckBoxMenuItem("Wrapping lines");
		JMenuItem font = new JMenuItem("Font..");
		
		format.add(wrappingLines);
		format.add(font);
                
                wrappingLines.setSelected(true);
                
                wrappingLines.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if(e.getStateChange() == ItemEvent.SELECTED)
                            textArea.setLineWrap(true);
                        else{
                            textArea.setLineWrap(false);
                        }
                    }
                });
                
                font.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFontChooser fontChooser = new JFontChooser();                                    
                        int result = fontChooser.showDialog(textArea);
                        if (result == JFontChooser.OK_OPTION)
                        {
                           Font font = fontChooser.getSelectedFont(); 
                           textArea.setFont(font);
                        }
                    }
                });
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
                    } catch (PrinterException ex) {
                        Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
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
        
        private void undoListener(JMenuItem item){
            item.addActionListener((ActionEvent e) -> {
                if(isDocumentFromStorage){
                    storageDocument = getDocumentText();
                    setTextDocument(actualDocument);
                    isDocumentFromStorage = false;
                }else{
                    actualDocument = getDocumentText();
                    setTextDocument(storageDocument);
                    isDocumentFromStorage = true;
                }
            });
        }
        
        private void cutListener(JMenuItem item){
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    copyText();
                    deleteText();
                }
            });
        }
        
        private void copyListener(JMenuItem item){
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    copyText();
                }
            });
        }
        
        private void pasteListener(JMenuItem item){
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
                    Transferable t = c.getContents(this);
                    if (t == null)
                        return;
                    try {
                        int count = textArea.getCaretPosition();
                        textArea.setText(textArea.getText().substring(0, count) +(String) t.getTransferData(DataFlavor.stringFlavor) +textArea.getText().substring(count));
                    } catch (UnsupportedFlavorException ex) {
                        Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
        
        private void deleteListener(JMenuItem item){
            item.addActionListener((ActionEvent e) -> {
                deleteText();
            });
        }
        
        private void findListener(JMenuItem item){
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new FindSimple("Find").init(220, 150);
                }
            });
        }
        
        private void findNextListener(JMenuItem item){
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FindSimple findSimple = new FindSimple();
                    findSimple.buttonFindNextListener(item);
                }
            });
        }
        
        private void changeListener(JMenuItem item){
            item.addActionListener((ActionEvent e) -> {
                new ChangeSimple("Change").init(220,240);
            });
        }
        
        private void goToListener(JMenuItem item){
            item.addActionListener((ActionEvent e) -> {
                new GoToFrame();
            });
        }
        
        private void selectAllListener(JMenuItem item){
            item.addActionListener((ActionEvent e) -> {
                textArea.selectAll();
            });
        }
        
        private void dateHourListener(JMenuItem item){
            item.addActionListener((ActionEvent e) -> {
                saveStorage();
                DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
                Date date = new Date();
                setTextDocument(dateFormat.format(date));
            });
        }
        
        private void copyText(){
            StringSelection selection = new StringSelection(getTextArea().getSelectedText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        }
        
        private void deleteText(){
            if(textArea.getSelectedText() != null){
                saveStorage();
                textArea.setText(textArea.getText().replace(textArea.getSelectedText(),""));
            }
        }
        
        public void saveStorage(){
            if(isDocumentFromStorage){
                actualDocument = getDocumentText();
            }else{
                storageDocument = getDocumentText();
            }
        }
        
        public void setCursorToPosition(int position){
            textArea.setCaretPosition(position);
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
        
        public JTextArea getTextArea(){
            return textArea;
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
