/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentUI;

import MainScreenUI.*;
import Class.Conference11;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;

/**
 *
 * @author ADMIN
 */
public class ConferenceRendererList extends javax.swing.JPanel {

    /**
     * Creates new form ConferenceRender
     */
//    implements ListCellRenderer<Conference>
    private final Color moveColor = new Color(220,220,255);
    private final Color defaultColor = new Color(238,238,255);
    
    Conference11 conference;

    public ConferenceRendererList() {
        initComponents();
        
    }
    public ConferenceRendererList(Conference11 con){
        initComponents();
        conference = con;
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Picture/hoi-nghi-truyen-hinh.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        jIcon.setIcon(new ImageIcon(image));
        jName.setText(conference.getNameConference());
        jDescription.setText(conference.getBriefDescription());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jIcon = new javax.swing.JLabel();
        jContent = new javax.swing.JPanel();
        jName = new javax.swing.JLabel();
        jDescription = new javax.swing.JLabel();
        jDate = new javax.swing.JLabel();

        setBackground(new java.awt.Color(238, 238, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setPreferredSize(new java.awt.Dimension(571, 100));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        jIcon.setBackground(new java.awt.Color(255, 255, 255));
        jIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jIcon.setPreferredSize(new java.awt.Dimension(150, 13));
        add(jIcon, java.awt.BorderLayout.WEST);

        jContent.setBackground(new java.awt.Color(255, 255, 255));
        jContent.setOpaque(false);
        jContent.setLayout(new java.awt.BorderLayout());

        jName.setBackground(new java.awt.Color(255, 255, 255));
        jName.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jName.setPreferredSize(new java.awt.Dimension(31, 40));
        jContent.add(jName, java.awt.BorderLayout.NORTH);

        jDescription.setBackground(new java.awt.Color(255, 255, 255));
        jDescription.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jDescription.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jContent.add(jDescription, java.awt.BorderLayout.LINE_START);

        jDate.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jDate.setForeground(new java.awt.Color(255, 0, 51));
        jDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jDate.setText("17/07/1999");
        jDate.setPreferredSize(new java.awt.Dimension(45, 20));
        jContent.add(jDate, java.awt.BorderLayout.PAGE_END);

        add(jContent, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
        int respone = JOptionPane.showConfirmDialog(this, conference.getNameConference(), conference.getBriefDescription(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (respone == JOptionPane.YES_OPTION)
            System.exit(0);

    }//GEN-LAST:event_formMousePressed

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        // TODO add your handling code here:
        setBackground(moveColor);
        

    }//GEN-LAST:event_formMouseMoved

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        // TODO add your handling code here:
        setBackground(defaultColor);

    }//GEN-LAST:event_formMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jContent;
    private javax.swing.JLabel jDate;
    private javax.swing.JLabel jDescription;
    private javax.swing.JLabel jIcon;
    private javax.swing.JLabel jName;
    // End of variables declaration//GEN-END:variables


}
