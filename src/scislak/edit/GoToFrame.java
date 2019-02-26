package scislak.edit;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import scislak.program.Frame;

public class GoToFrame extends JFrame{  
    private static int cursorLine;
    private static int cursorPosition;
    
    public GoToFrame(){
        super("GoTo");
        init();
        addInside();
    }
    
    private void init(){
        setVisible(true);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setResizable(true);
		
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	setSize(250, 120);
        setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
    }
    
    private void addInside(){
        JPanel panel = new JPanel();
        JTextField field = new JTextField();
        JLabel label = new JLabel("Go to:");
        JButton buttonConfirm = new JButton("Ok");
        JButton buttonCancel = new JButton("Cancel");
        
        add(panel);
        
        label.setBounds(50, 10, 50, 25);
        field.setBounds(100, 10, 80, 25);
        buttonConfirm.setBounds(30, 50, 80, 25);
        buttonCancel.setBounds(120, 50, 80, 25);
        
        panel.setLayout(null);
        panel.add(field);
        panel.add(label);
        panel.add(buttonConfirm);
        panel.add(buttonCancel);
        
        buttonConfirm.addActionListener((ActionEvent e) -> {
            
            if(isNumeric(field.getText())){
               String[] lines = Frame.getInstance().getDocumentText().split("\n"); 
            
               int count = Integer.valueOf(field.getText());
               if(count > lines.length)
                   count = lines.length-1;
               else if(count <= 0)
                   count = 1;
               
               int sum =0;
               
               for(int i =0; i <count-1; i++){
                   sum += (lines[i].length()+1);
               }
                
                Frame.getInstance().setCursorToPosition(sum);
            }   
             dispose();
        });
        
        buttonCancel.addActionListener((ActionEvent e) -> {
            dispose();
        });
    } 
    
  private static boolean isNumeric(String str)  
    {  
     try  
     {  
       double d = Double.parseDouble(str);  
     }  
     catch(NumberFormatException nfe)  
     {  
       return false;  
     }  
     return true;  
    }
  
  public static void updateCursorPositions(){
        try { 
            JTextArea area = Frame.getInstance().getTextArea();
            int cursorPos = area.getCaretPosition();
            cursorLine = area.getLineOfOffset(cursorPos);
            cursorPosition = cursorPos - area.getLineStartOffset(cursorLine);
        } catch (BadLocationException ex) {
            Logger.getLogger(GoToFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
  public static int getCursorLine(){
      return cursorLine +1;
  }
  
  public static int getCursorPosition(){
      return cursorPosition +1;
  }
}
