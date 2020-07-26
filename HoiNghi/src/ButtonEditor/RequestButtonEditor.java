/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ButtonEditor;

import Dialog.NewRequestDialog;
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
import java.util.Date;
import javassist.compiler.TokenId;
import javax.swing.JOptionPane;

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
            if (conference.getStartTime().compareTo(new Date()) <= 0) {
                JOptionPane.showMessageDialog(MainScreen.getInstance(), "This conference has already been organized");
            } else {
                new NewRequestDialog(MainScreen.getInstance(), true, conference).setVisible(true);
            }
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
