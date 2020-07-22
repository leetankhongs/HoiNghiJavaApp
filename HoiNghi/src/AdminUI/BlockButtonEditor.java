/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminUI;

import Business.UserBus;
import Business.UserConferenceBus;
import ContentUI.ConferenceUI;
import POJO.Conference;
import POJO.User;
import POJO.UserConference;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author ADMIN
 */
public class BlockButtonEditor extends DefaultCellEditor {

    private JButton button;
    private Boolean clicked;
    private User user;
    private ConferenceUI conferenceUI;

    public BlockButtonEditor(JTextField txt) {
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
        User user = (User) object;
        this.user = user;
        button.setBackground(new Color(220, 220, 255));

        if (user.getIsDelete() == 1) {
            button.setText("Unlock");
        } else {
            button.setText("Block");
        }

        clicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {

        if (clicked) {
            int reply = JOptionPane.showConfirmDialog(null, "Bạn có muốn thực hiện thao tác không?", "", JOptionPane.YES_NO_OPTION);
            boolean result = false;
            if (reply == JOptionPane.YES_OPTION) {
                if (user.getIsDelete() == 1) {
                    result = UserBus.unlockUser(user);
                    if (result == true) {
                        JOptionPane.showMessageDialog(null, "Thành công");
                    } else {
                        JOptionPane.showMessageDialog(null, "Thất bại");
                    }
                } else {
                    result = UserBus.blockUser(user);
                    if (result == true) {
                        JOptionPane.showMessageDialog(null, "Thành công");
                        List<UserConference> listUserConference = UserConferenceBus.getListUserConferenceByUser(user);

                        for (int i = 0; i < listUserConference.size(); i++) {
                            if (listUserConference.get(i).getConference().getStartTime().compareTo(new Date()) > 0) {
                                UserConferenceBus.deleteRegistration(listUserConference.get(i));
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Thất bại");
                    }
                }

            }
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
