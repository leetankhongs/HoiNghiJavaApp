/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ButtonEditor;

import MainScreenUI.MainScreen;
import POJO.User;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
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
        button.setText("Conference List");

        clicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (clicked) {
            JPanel jPanel = MainScreen.getInstance().getUsersUI().getJPanel();
            JTable jTable = MainScreen.getInstance().getUsersUI().getJTable();
            User preUser = MainScreen.getInstance().getUsersUI().getPreUser();
            MainScreen.getInstance().getUsersUI().setPreUser(user);

            if (preUser == null || preUser.getId() != user.getId() || jPanel.getHeight() == 0) {
                jPanel.setPreferredSize(new Dimension(0, 175));
                MainScreen.getInstance().getUsersUI().revalidate();

            } else {
                jPanel.setPreferredSize(new Dimension(0, 0));
                MainScreen.getInstance().getUsersUI().revalidate();
            }

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ConferenceUserButtonEditor.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    jTable.scrollRectToVisible(new Rectangle(jTable.getCellRect(jTable.getSelectedRow(), 0, true)));
                }
            });
            thread.start();

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
