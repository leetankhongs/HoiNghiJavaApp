/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminUI;

import Business.ConferenceBus;
import MainScreenUI.MainScreen;
import MainScreenUI.NewConferenceDialog;
import POJO.Conference;
import POJO.Place;
import java.awt.Frame;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class Edit_DetailConferenceDialog extends java.awt.Dialog {

    /**
     * Creates new form Edit_DetailConferenceDialog
     */
    Conference conference;

    public Edit_DetailConferenceDialog(java.awt.Frame parent, boolean modal, String conferenceID) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo((Frame) parent);
        this.conference = ConferenceBus.getConferenceInformation(conferenceID);
        resetData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPlaceName = new javax.swing.JLabel();
        jAddress = new javax.swing.JLabel();
        jCapacity = new javax.swing.JLabel();
        jDate = new javax.swing.JLabel();
        jPlaceNameTF = new javax.swing.JTextField();
        jAddressTF = new javax.swing.JTextField();
        jCapacityTF = new javax.swing.JTextField();
        jDateTF = new javax.swing.JTextField();
        jStart = new javax.swing.JLabel();
        jEnd = new javax.swing.JLabel();
        jEndTF = new javax.swing.JTextField();
        jStartTF = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jDetailDescription = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jImage = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jNameConference = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jBriefDescription = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jEditbtn = new javax.swing.JButton();
        jDeletebtn = new javax.swing.JButton();
        jCancelbtn = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel4.setPreferredSize(new java.awt.Dimension(100, 250));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPlaceName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPlaceName.setText("Place Name");
        jPlaceName.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel4.add(jPlaceName, new java.awt.GridBagConstraints());

        jAddress.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jAddress.setText("Adress");
        jAddress.setPreferredSize(new java.awt.Dimension(100, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        jPanel4.add(jAddress, gridBagConstraints);

        jCapacity.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jCapacity.setText("Capacity");
        jCapacity.setPreferredSize(new java.awt.Dimension(100, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        jPanel4.add(jCapacity, gridBagConstraints);

        jDate.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jDate.setText("Date");
        jDate.setPreferredSize(new java.awt.Dimension(100, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        jPanel4.add(jDate, gridBagConstraints);

        jPlaceNameTF.setEditable(false);
        jPlaceNameTF.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPlaceNameTF.setText("jTextField1");
        jPlaceNameTF.setPreferredSize(new java.awt.Dimension(450, 40));
        jPanel4.add(jPlaceNameTF, new java.awt.GridBagConstraints());

        jAddressTF.setEditable(false);
        jAddressTF.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jAddressTF.setText("jTextField2");
        jAddressTF.setPreferredSize(new java.awt.Dimension(450, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        jPanel4.add(jAddressTF, gridBagConstraints);

        jCapacityTF.setEditable(false);
        jCapacityTF.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jCapacityTF.setText("jTextField3");
        jCapacityTF.setPreferredSize(new java.awt.Dimension(450, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        jPanel4.add(jCapacityTF, gridBagConstraints);

        jDateTF.setEditable(false);
        jDateTF.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jDateTF.setText("jTextField4");
        jDateTF.setPreferredSize(new java.awt.Dimension(450, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        jPanel4.add(jDateTF, gridBagConstraints);

        jStart.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jStart.setText("Start");
        jStart.setPreferredSize(new java.awt.Dimension(100, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        jPanel4.add(jStart, gridBagConstraints);

        jEnd.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jEnd.setText("End");
        jEnd.setPreferredSize(new java.awt.Dimension(100, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        jPanel4.add(jEnd, gridBagConstraints);

        jEndTF.setEditable(false);
        jEndTF.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jEndTF.setText("jTextField6");
        jEndTF.setPreferredSize(new java.awt.Dimension(170, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel4.add(jEndTF, gridBagConstraints);

        jStartTF.setEditable(false);
        jStartTF.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jStartTF.setText("jTextField5");
        jStartTF.setPreferredSize(new java.awt.Dimension(170, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel4.add(jStartTF, gridBagConstraints);

        jPanel1.add(jPanel4, java.awt.BorderLayout.SOUTH);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jDetailDescription.setEditable(false);
        jDetailDescription.setColumns(20);
        jDetailDescription.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jDetailDescription.setLineWrap(true);
        jDetailDescription.setRows(5);
        jDetailDescription.setText("Detail Description");
        jDetailDescription.setWrapStyleWord(true);
        jScrollPane2.setViewportView(jDetailDescription);

        jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(153, 255, 153));
        jPanel2.setPreferredSize(new java.awt.Dimension(200, 150));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jImage.setText("Image");
        jImage.setOpaque(true);
        jImage.setPreferredSize(new java.awt.Dimension(200, 13));
        jPanel2.add(jImage, java.awt.BorderLayout.WEST);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jNameConference.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jNameConference.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jNameConference.setText("Name Conference");
        jNameConference.setOpaque(true);
        jNameConference.setPreferredSize(new java.awt.Dimension(50, 70));
        jPanel3.add(jNameConference, java.awt.BorderLayout.NORTH);

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jBriefDescription.setEditable(false);
        jBriefDescription.setColumns(20);
        jBriefDescription.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jBriefDescription.setLineWrap(true);
        jBriefDescription.setRows(5);
        jBriefDescription.setText("Brief Description");
        jBriefDescription.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jBriefDescription);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel5.setPreferredSize(new java.awt.Dimension(600, 50));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 100, 15);
        flowLayout1.setAlignOnBaseline(true);
        jPanel5.setLayout(flowLayout1);

        jEditbtn.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jEditbtn.setText("Edit");
        jEditbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jEditbtnMouseReleased(evt);
            }
        });
        jEditbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEditbtnActionPerformed(evt);
            }
        });
        jPanel5.add(jEditbtn);

        jDeletebtn.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jDeletebtn.setText("Delete");
        jDeletebtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jDeletebtnMousePressed(evt);
            }
        });
        jPanel5.add(jDeletebtn);

        jCancelbtn.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jCancelbtn.setText("Cancel");
        jPanel5.add(jCancelbtn);

        add(jPanel5, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog

        MainScreen.getInstance().resetConferenceUI();

        dispose();
    }//GEN-LAST:event_closeDialog

    private void jEditbtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jEditbtnMouseReleased
        // TODO add your handling code here:
        new NewConferenceDialog(MainScreen.getInstance(), true, conference, this).setVisible(true);
    }//GEN-LAST:event_jEditbtnMouseReleased

    private void jEditbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditbtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jEditbtnActionPerformed

    private void jDeletebtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDeletebtnMousePressed
        // TODO add your handling code here:
        Date nowDate = new Date();
        int reply = JOptionPane.showConfirmDialog(null, "Bạn có muốn thực hiện thao tác không?", "", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {

            if (conference.getStartTime().compareTo(nowDate) <= 0) {
                JOptionPane.showMessageDialog(this, "Hội nghị này đã diễn ra rồi");
            } else {
                boolean result = ConferenceBus.deleteConference(conference);

                if (result == true) {
                    JOptionPane.showMessageDialog(null, "Thành công");
                } else {
                    JOptionPane.showMessageDialog(null, "Thất bại");
                }

            }

        }

        MainScreen.getInstance().resetConferenceUI();
    }//GEN-LAST:event_jDeletebtnMousePressed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jAddress;
    private javax.swing.JTextField jAddressTF;
    private javax.swing.JTextArea jBriefDescription;
    private javax.swing.JButton jCancelbtn;
    private javax.swing.JLabel jCapacity;
    private javax.swing.JTextField jCapacityTF;
    private javax.swing.JLabel jDate;
    private javax.swing.JTextField jDateTF;
    private javax.swing.JButton jDeletebtn;
    private javax.swing.JTextArea jDetailDescription;
    private javax.swing.JButton jEditbtn;
    private javax.swing.JLabel jEnd;
    private javax.swing.JTextField jEndTF;
    private javax.swing.JLabel jImage;
    private javax.swing.JLabel jNameConference;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel jPlaceName;
    private javax.swing.JTextField jPlaceNameTF;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jStart;
    private javax.swing.JTextField jStartTF;
    // End of variables declaration//GEN-END:variables

    public void resetData() {
        this.conference = ConferenceBus.getConferenceInformation(conference.getId());

        ImageIcon imageIcon = new ImageIcon(conference.getImage());
        Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        jImage.setIcon(new ImageIcon(image));
        jNameConference.setText(conference.getName());
        jBriefDescription.setText(conference.getBriefDescription());
        jDetailDescription.setText(conference.getDetailDescription());

        Place place = conference.getPlace();
        jPlaceNameTF.setText(place.getName());
        jAddressTF.setText(place.getAddress());
        jCapacityTF.setText(String.valueOf(conference.getParticipants()));

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        jDateTF.setText(dateFormat.format(conference.getStartTime()));
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        jStartTF.setText(timeFormat.format(conference.getStartTime()));
        jEndTF.setText(timeFormat.format(conference.getEndTime()));
    }
}
