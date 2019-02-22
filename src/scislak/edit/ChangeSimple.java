package scislak.edit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import scislak.program.Frame;

public class ChangeSimple extends FindSimple{
    protected static String changing;
    
    protected JTextField fieldChanging;
    
    private JTextArea area;
    
    public ChangeSimple(String name){
        super(name);
        area = Frame.getInstance().getTextArea();
        iterator = 0;
    }
    
    @Override
    protected void addInside() {
        JPanel panel = new JPanel();
        JButton buttonFind = new JButton("Find");
        JButton buttonChange = new JButton("Change");
        JButton buttonChangeAll = new JButton("Change all");
        fieldFinding = new JTextField();
        fieldChanging = new JTextField();
        
        buttonFind.setBounds(5, 20, 95, 25);
        buttonChange.setBounds(5, 50, 95, 25);
        buttonChangeAll.setBounds(5, 80, 95, 25);
        fieldFinding.setBounds(105, 20, 100, 25);
        fieldChanging.setBounds(105, 50, 100, 25);
        buttonCancel.setBounds(5, 110, 95, 25);
        
        fieldFinding.setText(finding);
        fieldChanging.setText(changing);
        
        add(panel);
        panel.setLayout(null);
        panel.add(fieldFinding);
        panel.add(fieldChanging);
        panel.add(buttonFind);
        panel.add(buttonChange);
        panel.add(buttonChangeAll);
        panel.add(buttonCancel);
        
        buttonFindListener(buttonFind);
        buttonChangeListener(buttonChange);
        buttonChangeAllListener(buttonChangeAll);
    }
    
    private void buttonChangeListener(JButton button){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!fieldChanging.getText().isEmpty() && area.getSelectedText() != null){
                    Frame.getInstance().saveStorage();
                    area.setText(area.getText().substring(0, iterator-1) +area.getText().substring(iterator-1).replaceFirst(area.getSelectedText(), fieldChanging.getText()));
                    saveFields(fieldFinding.getText(), fieldChanging.getText());
                }
            }
        });
    }
    
    private void buttonChangeAllListener(JButton button){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!fieldChanging.getText().isEmpty() && !fieldFinding.getText().isEmpty()){
                    findMethod(fieldFinding);
                    Frame.getInstance().saveStorage();
                    area.setText(area.getText().replace(area.getSelectedText(), fieldChanging.getText()));
                    saveFields(fieldFinding.getText(), fieldChanging.getText());
                }
            }
        });
    }
    
    @Override
    protected void doElse(){
        saveFields(fieldFinding.getText(), fieldChanging.getText());
    }
    
    protected void saveFields(String textFinding, String textChanging){
        finding = textFinding;
        changing = textChanging;
    }
}
