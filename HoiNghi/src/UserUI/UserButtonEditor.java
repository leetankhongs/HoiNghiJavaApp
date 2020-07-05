/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserUI;

import UserUI.Register_DetailConference;
import MainScreenUI.Login;
import POJO.Conference;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author ADMIN
 */
public class UserButtonEditor extends DefaultCellEditor{
    private JButton button;
    private String lbl;
    private Boolean clicked;
    private Conference conference;
    
    public UserButtonEditor(JTextField txt){
        super(txt);
        setClickCountToStart(1);
        button = new JButton();     
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
        
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object object, boolean isSelected, int row, int column) {
        Conference conference = (Conference)object;
        this.conference = conference;
        lbl = conference.getName();
        button.setBackground(new Color(220,220,255));
        button.setText("Detail");
        clicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if(clicked)
            new Register_DetailConference(conference).setVisible(true);
        
        clicked = false;
        return conference;
    }

    @Override
    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
