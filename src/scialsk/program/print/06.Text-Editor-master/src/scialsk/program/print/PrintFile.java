package scialsk.program.print;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.standard.PrinterMoreInfo;
import javax.print.attribute.standard.PrinterState;
import javax.print.attribute.standard.PrinterStateReasons;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;

public class PrintFile extends JFrame{
        private JLabel state;
        
	public PrintFile() {
            super("Print");
            initFrame();
            addInside();
	}
        
        private void initFrame(){
            setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(400, 440);
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
        }
        
        private void addInside(){
            setLayout(null);
            JPanel panelPrinting = new JPanel();
            JPanel panelRangePages = new JPanel();
            JPanel panelCopies = new JPanel();
            JPanel panelBottom = new JPanel();
            
            panelPrinting(panelPrinting);
            panelRangePages(panelRangePages);
            panelCopies(panelCopies);
            panelBottom(panelBottom);
            
            add(panelPrinting);
            add(panelRangePages);
            add(panelCopies);
            add(panelBottom);
        }

    private void panelPrinting(JPanel panel) {
       EtchedBorder etched = new EtchedBorder();
       TitledBorder titled = BorderFactory.createTitledBorder(etched, "Wybieranie drukarki");
       
       panel.setBounds(10, 0, 380, 210);
       panel.setBorder(titled);
       
       JList list = new JList();
       JScrollPane scrollableList = new JScrollPane(list);
       
       addItemToList(list);
    
       state = new JLabel("Stan: ");
       JLabel localisation = new JLabel("Lokalizacja: ");
       JLabel comments = new JLabel("Komentarz: ");
       JCheckBox checkPrintToFile = new JCheckBox("Drukuj do pliku");
       JButton buttonPreference = new JButton("Preferencje");
       JButton buttonFindPrinter = new JButton("Znajdz drukarke");
       
       scrollableList.setBounds(10, 20, 360, 100);
       state.setBounds(25, 120, 100, 25);
       localisation.setBounds(25, 145, 100, 25);
       comments.setBounds(25, 170, 100, 25);
       checkPrintToFile.setBounds(235, 120, 120, 25);
       buttonPreference.setBounds(235, 145, 120, 25);
       buttonFindPrinter.setBounds(230, 175, 130, 25);
       
       panel.setLayout(null);
       panel.add(scrollableList);
       panel.add(state);
       panel.add(localisation);
       panel.add(comments);
       panel.add(checkPrintToFile);
       panel.add(buttonPreference);
       panel.add(buttonFindPrinter);
    }
    
    private void addItemToList(JList list){
        PrintService[] prints = PrintServiceLookup.lookupPrintServices(null, null);
        list.setListData(prints);
        
        for(PrintService p: prints)
            System.out.println(p); 
        
        list.addListSelectionListener((ListSelectionEvent e) -> {
            int selectedindex = list.getSelectedIndex();
            boolean statusPrinter = PrintServiceLookup.registerService(prints[selectedindex]);
            setPrinterStatus(statusPrinter);
        });
    }

    private void panelRangePages(JPanel panel) {
       EtchedBorder etched = new EtchedBorder();
       TitledBorder titled = BorderFactory.createTitledBorder(etched, "Zakres stron");
       
       panel.setBounds(10, 220, 190, 150);
       panel.setBorder(titled);
       
       ButtonGroup group = new ButtonGroup();
       JRadioButton option1 = new JRadioButton("Wszystko");
       JRadioButton option2 = new JRadioButton("Zaznaczenie");
       JRadioButton option3 = new JRadioButton("Strony (przedzial):");
       
       option1.setBounds(10, 10, 150, 25);
       option2.setBounds(10, 10, 150, 25);
       option3.setBounds(10, 10, 150, 25);
       
       group.add(option1);
       group.add(option2);
       group.add(option3);
       
       option1.setSelected(true);
       option2.setEnabled(false);
       option3.setEnabled(false);
       
       panel.add(option1);
       panel.add(option2);
       panel.add(option3);
    }

    private void panelCopies(JPanel panel) {
       EtchedBorder etched = new EtchedBorder();
       TitledBorder titled = BorderFactory.createTitledBorder(etched, "Licba kopi");
       
       panel.setBounds(200, 220, 190, 150);
       panel.setBorder(titled);
       
       JLabel copies = new JLabel("Kopie:");
       JSpinner spinner = new JSpinner(new SpinnerNumberModel());
       JCheckBox sorted = new JCheckBox("Sortuj: 1-1  2-2  3-3"); 
       
       copies.setBounds(35, 30, 50, 25);
       spinner.setBounds(100, 30, 50, 25);
       sorted.setBounds(30, 80, 150, 25);
       
       panel.setLayout(null);
       panel.add(copies);
       panel.add(spinner);
       panel.add(sorted);
       
       spinner.setValue(1);
       sorted.setEnabled(false);
       spinner.addChangeListener((ChangeEvent e) -> {
           int value = Integer.valueOf(spinner.getValue().toString());
           
           if(value < 1){
               spinner.setValue(1);
               value = 1;
           }
           if(value == 1){
               sorted.setEnabled(false);
               sorted.setSelected(false);
           }else
               sorted.setEnabled(true);
       });
    }

    private void panelBottom(JPanel panel) {
      panel.setBounds(10, 370, 380, 50);
         
      JButton print = new JButton("Drukuj");
      JButton cancel = new JButton("Anuluj");
      JButton confirm = new JButton("Zastosuj");
      
      confirm.setEnabled(false);
      print.setBounds(110, 10, 85, 25);
      cancel.setBounds(200, 10, 85, 25);
      confirm.setBounds(290, 10, 85, 25);
      
      panel.setLayout(null);
      panel.add(print);
      panel.add(cancel);
      panel.add(confirm);
      
      printListener(print);
      cancelListener(cancel);
      confirmListener(confirm);
    }
    
    private void printListener(JButton button){
        button.addActionListener((ActionEvent e) -> {
        });
    }
    
    private void cancelListener(JButton button){
        button.addActionListener((ActionEvent e) -> {
            dispose();
        });
    }
    
    private void confirmListener(JButton button){
        button.addActionListener((ActionEvent e) -> {
        });
    }
    
    private void setPrinterStatus(boolean status){
        if(status)
            state.setText("Stan: Gotowe");
        else
            state.setText("Stan: Offline");
    }
}
