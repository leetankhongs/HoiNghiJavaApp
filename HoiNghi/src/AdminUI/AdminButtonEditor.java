/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminUI;

import MainScreenUI.MainScreen;
import POJO.Conference;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author ADMIN
 */
public class AdminButtonEditor extends DefaultCellEditor {

    private JButton button;
    private String lbl;
    private Boolean clicked;
    private Conference conference;

    public AdminButtonEditor(JTextField txt) {
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
        Conference conference = (Conference) object;
        this.conference = conference;
        lbl = conference.getName();
        button.setBackground(new Color(220, 220, 255));
        button.setText("Detail");
        clicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (clicked) {
//            JPanel jPanel = MainScreen.getInstance().getConferenceUI().getJpanel();
//            JTable jTable = MainScreen.getInstance().getConferenceUI().getJTable();
//
//            if (jPanel.getHeight() > 0) {
//
//                jPanel.setPreferredSize(new Dimension(0, 0));
//                MainScreen.getInstance().getConferenceUI().revalidate();
//
//            } else {
//
//                jPanel.setPreferredSize(new Dimension(0, 200));
//                MainScreen.getInstance().getConferenceUI().revalidate();
//
////
//            }
//            
//               Thread thread = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            Thread.sleep(100);
//                        } catch (InterruptedException ex) {
//                            Logger.getLogger(AdminButtonEditor.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                        jTable.scrollRectToVisible(new Rectangle(jTable.getCellRect(jTable.getSelectedRow(), 0, true)));
//                    }});
//                thread.start();
//        }
            new Edit_DetailConferenceDialog(MainScreen.getInstance(), true, conference.getId()).setVisible(true);
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
