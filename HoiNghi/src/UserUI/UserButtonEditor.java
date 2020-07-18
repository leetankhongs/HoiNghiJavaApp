/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserUI;

import POJO.UserConference;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import MainScreenUI.MainScreen;

/**
 *
 * @author ADMIN
 */
public class UserButtonEditor extends DefaultCellEditor {

    private JButton button;
    private Boolean clicked;
    private UserConference userConference;

    public UserButtonEditor(JTextField txt) {
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
        UserConference userConference = (UserConference) object;
        this.userConference = userConference;
        button.setBackground(new Color(220, 220, 255));
        button.setText("Detail");
        clicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (clicked) {
            new DetailConferenceUserDialog(MainScreen.getInstance(), true, userConference.getConference()).setVisible(true);
        }

        clicked = false;
        return userConference;
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
