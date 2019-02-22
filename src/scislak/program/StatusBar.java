package scislak.program;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class StatusBar extends JPanel{
    private JLabel statusLabel;

    private static final StatusBar instance = new StatusBar();
    private StatusBar(){}
    public static StatusBar getInstance(){
        return instance;
    }
    
    public void init(){
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        statusLabel = new JLabel("status");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(statusLabel);
    }
    
    public void updateStatus(int line, int column){
        statusLabel.setText("Line:" + line +", Column:" +column);
    }
}
