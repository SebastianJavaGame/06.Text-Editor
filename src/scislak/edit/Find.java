package scislak.edit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public abstract class Find extends JFrame{
    protected JButton buttonCancel;
    
    public Find(){
        buttonCancel = new JButton("Cancel");
        cancelListener(buttonCancel);
    }
    
    public Find(String name){
        super(name);
        buttonCancel = new JButton("Cancel");
        cancelListener(buttonCancel);
        closeListener();
    }
    
    private void closeListener(){
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                doElse();
                dispose();
            }
        });
    }
    
    private void cancelListener(JButton cancel){
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doElse();
                dispose(); 
            }
        });
    }
    
    protected void doElse(){}
    
    protected static int findFrom(String finding, String document, JTextArea textArea, int iterator){
        int lenghtOfFinfing = finding.length();
        
        for(int i = iterator; i <document.length(); i++){
            if(document.substring(i, i +lenghtOfFinfing).equals(finding)){
                textArea.select(i, i +lenghtOfFinfing);
                iterator = i;
                break;
            }
            //TODO When wrap to start and finding from 0
        }
        return iterator +1;
    }
}
