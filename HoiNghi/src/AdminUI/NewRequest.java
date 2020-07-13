/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminUI;

import Business.UserConferenceBus;
import ContentUI.ConferenceUI;
import POJO.Conference;
import POJO.UserConference;
import UserUI.UserButtonEditor;
import UserUI.UserButtonRenderer;
import com.mchange.v1.db.sql.DriverManagerDataSource;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class NewRequest extends javax.swing.JFrame {

    /**
     * Creates new form NewRequest
     */
    Conference conference;
    List<UserConference> list;
    ConferenceUI conferenceUI;

    public NewRequest(Conference conference, ConferenceUI conferenceUI) {
        initComponents();
        this.conference = conference;
        this.conferenceUI = conferenceUI;

        jNameTF.setText(conference.getName());
        jCapacityTF.setText(conference.getPlace().getCapacity().toString());

        list = UserConferenceBus.getNewRequests(conference);

        DefaultTableModel tm = (DefaultTableModel) jTable.getModel();

        for (int i = 0; i < list.size(); i++) {
            tm.addRow(new Object[]{i + 1, list.get(i).getUser().getName(), list.get(i).getUser().getEmail(), false});
        }

        jTable.setModel(tm);
        jTable.setAutoCreateRowSorter(true);
        System.out.println(conferenceUI);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jNameTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jCapacityTF = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jAcceptbtn = new javax.swing.JButton();
        jDecline = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("ConferenceName");
        jLabel1.setPreferredSize(new java.awt.Dimension(120, 50));
        jPanel1.add(jLabel1, new java.awt.GridBagConstraints());

        jNameTF.setEditable(false);
        jNameTF.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jNameTF.setText("jTextField1");
        jNameTF.setPreferredSize(new java.awt.Dimension(450, 40));
        jPanel1.add(jNameTF, new java.awt.GridBagConstraints());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Capacity");
        jLabel2.setPreferredSize(new java.awt.Dimension(120, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        jPanel1.add(jLabel2, gridBagConstraints);

        jCapacityTF.setEditable(false);
        jCapacityTF.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jCapacityTF.setText("jTextField2");
        jCapacityTF.setPreferredSize(new java.awt.Dimension(450, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        jPanel1.add(jCapacityTF, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel2.setPreferredSize(new java.awt.Dimension(600, 50));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, java.awt.BorderLayout.EAST);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setPreferredSize(new java.awt.Dimension(600, 50));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 5));

        jAcceptbtn.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jAcceptbtn.setText("Accept");
        jAcceptbtn.setPreferredSize(new java.awt.Dimension(73, 40));
        jAcceptbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jAcceptbtnMouseReleased(evt);
            }
        });
        jPanel4.add(jAcceptbtn);

        jDecline.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jDecline.setText("Decline");
        jDecline.setPreferredSize(new java.awt.Dimension(75, 40));
        jDecline.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jDeclineMousePressed(evt);
            }
        });
        jPanel4.add(jDecline);

        jPanel3.add(jPanel4, java.awt.BorderLayout.NORTH);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        jTable.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Name", "Email", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable.setRowHeight(20);
        jScrollPane1.setViewportView(jTable);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jAcceptbtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jAcceptbtnMouseReleased
        // TODO add your handling code here:
        DefaultTableModel tm = (DefaultTableModel) jTable.getModel();
        List<Integer> checkedPositions = new ArrayList<>();

        for (int i = 0; i < tm.getRowCount(); i++) {
            boolean checked = (boolean) tm.getValueAt(i, 3);

            if (checked == true) {
                checkedPositions.add((Integer) tm.getValueAt(i, 0));
            }
        }

        if (UserConferenceBus.getTheNumberOfUserIsAccepted(conference) + checkedPositions.size() > conference.getParticipants())
            JOptionPane.showMessageDialog(this, "The number of people has exceeded the limit");
        else {
            for (int i = 0; i < checkedPositions.size(); i++) {
                UserConferenceBus.acceptRequest(list.get(checkedPositions.get(i) - 1).getId());
            }

            JOptionPane.showMessageDialog(this, "Success");
            resetNewRequests();
            conferenceUI.resetData();
        }
    }//GEN-LAST:event_jAcceptbtnMouseReleased

    private void jDeclineMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDeclineMousePressed
        // TODO add your handling code here:
        DefaultTableModel tm = (DefaultTableModel) jTable.getModel();
        List<Integer> checkedPositions = new ArrayList<>();

        for (int i = 0; i < tm.getRowCount(); i++) {
            boolean checked = (boolean) tm.getValueAt(i, 3);

            if (checked == true) {
                checkedPositions.add((Integer) tm.getValueAt(i, 0));
            }
        }

        for (int i = 0; i < checkedPositions.size(); i++) {
            UserConferenceBus.declineRequest(list.get(checkedPositions.get(i) - 1).getId());
        }

        JOptionPane.showMessageDialog(this, "Success");
        resetNewRequests();
        conferenceUI.resetData();

    }//GEN-LAST:event_jDeclineMousePressed

    private void resetNewRequests() {
        list = UserConferenceBus.getNewRequests(conference);
        DefaultTableModel tm = (DefaultTableModel) jTable.getModel();

        for (int i = tm.getRowCount() - 1; i >= 0; i--) {
            tm.removeRow(i);
        }

        for (int i = 0; i < list.size(); i++) {
            tm.addRow(new Object[]{i + 1, list.get(i).getUser().getName(), list.get(i).getUser().getEmail(), false});
        }

        jTable.setModel(tm);
        jTable.setAutoCreateRowSorter(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAcceptbtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JTextField jCapacityTF;
    private javax.swing.JButton jDecline;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jNameTF;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    // End of variables declaration//GEN-END:variables
}
