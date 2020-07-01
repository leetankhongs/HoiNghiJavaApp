/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import MainScreenUI.Login;
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
public class ButtonEditor extends DefaultCellEditor{
    private JButton button;
    private String lbl;
    private Boolean clicked;
    private Conference con;
    
    public ButtonEditor(JTextField txt){
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
        con = conference;
        lbl = conference.getNameConference();
        button.setText(lbl);
        clicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if(clicked)
        {
            JOptionPane.showMessageDialog(button, con.getNameConference());
        }
        
        clicked = false;
        return con;
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
