/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminUI;
import ContentUI.ConferenceUI;
import MainScreenUI.MainScreen;
import POJO.Conference;
import POJO.User;
import POJO.UserConference;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
/**
 *
 * @author ADMIN
 */
public class ConferenceUserButtonEditor extends DefaultCellEditor {

    private JButton button;
    private Boolean clicked;
    private User user;

    public ConferenceUserButtonEditor(JTextField txt) {
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
        User user = (User) object;
        this.user = user;
        button.setBackground(new Color(220, 220, 255));
        button.setText("New Request");

        clicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (clicked) {
            new ListConferenceUserDialog(MainScreen.getInstance(), true, user).setVisible(true);
        }

        clicked = false;
        return user;
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
