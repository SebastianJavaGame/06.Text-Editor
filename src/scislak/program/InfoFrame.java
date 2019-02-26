package scislak.program;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoFrame extends JFrame{
    public InfoFrame(){
        super("Info");
        init();
    }
    
    private void init(){
        setSize(350, 350);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setResizable(false);
	setVisible(true);
	setAlwaysOnTop(true);
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
        addInside();
    }
    
    private void addInside(){
        JPanel panel = new JPanel();
        add(panel);
        
        JLabel label = new JLabel("");
        panel.add(label, BorderLayout.CENTER);
        
        JButton button = new JButton("OK");
        panel.add(button, BorderLayout.SOUTH);
        
        label.setText("Sebastian Ściślak - Kopia 'Notatnik' z Windows 10");
    
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
