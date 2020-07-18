/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminUI;

import ContentUI.ConferenceUI;
import POJO.Conference;
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
public class RequestButtonEditor extends DefaultCellEditor {

    private JButton button;
    private Boolean clicked;
    private Conference conference;
    private ConferenceUI conferenceUI;

    public RequestButtonEditor(JTextField txt) {
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

    public void setConferenceUI(ConferenceUI conferenceUI) {
        this.conferenceUI = conferenceUI;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object object, boolean isSelected, int row, int column) {
        Conference conference = (Conference) object;
        this.conference = conference;
        button.setBackground(new Color(220, 220, 255));
        button.setText("New Request");

        clicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (clicked) {
            new NewRequestDialog(MainScreen.getInstance(), true, conference).setVisible(true);
            System.out.println(conferenceUI);
        }

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
