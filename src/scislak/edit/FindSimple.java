package scislak.edit;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import scislak.program.Frame;

public class FindSimple extends Find{

    private static int iterator;
    private static String finding;
    
    private JTextField fieldFinding;
    
    public FindSimple(String name) {
        super(name);
    }

    public FindSimple() {}

    public void init() {
        setVisible(true);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setResizable(false);
		
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	setSize(220, 150);
	setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
        addInside();
    }

    private void addInside() {
        JPanel panel = new JPanel();
        JButton buttonFind = new JButton("Find");
        fieldFinding = new JTextField();
        
        buttonFind.setBounds(10, 20, 75, 25);
        fieldFinding.setBounds(90, 20, 110, 25);
        buttonCancel.setBounds(10, 50, 75, 25);
        
        fieldFinding.setText(finding);
        
        add(panel);
        panel.setLayout(null);
        panel.add(fieldFinding);
        panel.add(buttonFind);
        panel.add(buttonCancel);
        
        buttonFindListener(buttonFind);
    }
    
    private void buttonFindListener(JButton findButton){
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String findString = fieldFinding.getText();
                finding = findString;
                
                if(!finding.isEmpty())
                    iterator = findFrom(findString, Frame.getInstance().getDocumentText(), Frame.getInstance().getTextArea(), iterator);
            }
        });
    }
    
    public static void buttonFindNextListener(JMenuItem findNextButton){
        findNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                System.out.println(iterator);
                if(!finding.isEmpty())
                    iterator = findFrom(finding, Frame.getInstance().getDocumentText(), Frame.getInstance().getTextArea(), iterator);
            }
        });
    }
}
