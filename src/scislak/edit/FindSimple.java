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

    protected static int iterator;
    protected static String finding;
    
    protected JTextField fieldFinding;
    
    private JButton buttonFind;
    
    public FindSimple(String name) {
        super(name);
        iterator = 0;
    }

    public FindSimple() {}

    public void init(int x, int y) {
        setVisible(true);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setResizable(false);
		
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	setSize(x, y);
	setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
        addInside();
    }

    protected void addInside() {
        JPanel panel = new JPanel();
        buttonFind = new JButton("Find");
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
    
    protected void buttonFindListener(JButton findButton){
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findMethod(fieldFinding);
            }
        });
    }
    
    public static void buttonFindNextListener(JMenuItem findNextButton){
        findNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                if(!finding.isEmpty())
                    iterator = findFrom(finding, Frame.getInstance().getDocumentText(), Frame.getInstance().getTextArea(), iterator);
            }
        });
    }
    
    protected void findMethod(JTextField fieldFinding){
        String findString = fieldFinding.getText();
        saveFieldFinding(findString);
                
        if(!finding.isEmpty())
            iterator = findFrom(findString, Frame.getInstance().getDocumentText(), Frame.getInstance().getTextArea(), iterator);
    }
    
    @Override
    protected void doElse(){
        saveFieldFinding(fieldFinding.getText());
    }
    
    protected void setIteratorToZero(){
        iterator = 0;
    }
    
    protected static void saveFieldFinding(String text){
        finding = text;
    }
}
