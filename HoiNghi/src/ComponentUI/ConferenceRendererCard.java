package ComponentUI;


import UserUI.Register_DetailConference;
import POJO.Conference;
import POJO.User;
import java.awt.Color;
import java.awt.Image;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class ConferenceRendererCard extends javax.swing.JPanel {

    /**
     * Creates new form ConferenceRenderCard
     */
    private final Color moveColor = new Color(220,220,255);
    private final Color defaultColor = new Color(238,238,255);
    
    private Conference conference;
    private User user;
    
    public ConferenceRendererCard(Conference conference, User user) {
        initComponents();
        this.conference = conference;
        this.user = user;
        initData();
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
        jDate = new javax.swing.JLabel();
        jScrollDescription = new javax.swing.JScrollPane();
        jDescription = new javax.swing.JTextArea();
        jName = new javax.swing.JLabel();

        setBackground(new java.awt.Color(238, 238, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setPreferredSize(new java.awt.Dimension(300, 300));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        jIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jIcon.setPreferredSize(new java.awt.Dimension(125, 200));
        add(jIcon, java.awt.BorderLayout.WEST);

        jDate.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jDate.setForeground(new java.awt.Color(255, 0, 51));
        jDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jDate.setPreferredSize(new java.awt.Dimension(0, 30));
        add(jDate, java.awt.BorderLayout.SOUTH);

        jScrollDescription.setBackground(new java.awt.Color(238, 238, 255));
        jScrollDescription.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jScrollDescription.setEnabled(false);
        jScrollDescription.setFocusable(false);
        jScrollDescription.setOpaque(false);
        jScrollDescription.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jScrollDescriptionMouseMoved(evt);
            }
        });
        jScrollDescription.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jScrollDescriptionMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jScrollDescriptionMouseReleased(evt);
            }
        });

        jDescription.setEditable(false);
        jDescription.setBackground(new java.awt.Color(238, 238, 255));
        jDescription.setColumns(20);
        jDescription.setLineWrap(true);
        jDescription.setRows(5);
        jDescription.setWrapStyleWord(true);
        jDescription.setBorder(null);
        jDescription.setCaretColor(new java.awt.Color(255, 255, 255));
        jDescription.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jDescription.setFocusable(false);
        jDescription.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jDescription.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jDescriptionMouseMoved(evt);
            }
        });
        jDescription.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jDescriptionMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jDescriptionMouseReleased(evt);
            }
        });
        jScrollDescription.setViewportView(jDescription);

        add(jScrollDescription, java.awt.BorderLayout.CENTER);

        jName.setBackground(new java.awt.Color(255, 255, 255));
        jName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jName.setPreferredSize(new java.awt.Dimension(0, 40));
        add(jName, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        // TODO add your handling code here:
        jDescription.setBackground(defaultColor);
        setBackground(defaultColor);
    }//GEN-LAST:event_formMouseExited

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        // TODO add your handling code here:
        setBackground(moveColor);
        jDescription.setBackground(moveColor);
    }//GEN-LAST:event_formMouseMoved

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
        new Register_DetailConference(conference, user, false).setVisible(true);
    }//GEN-LAST:event_formMouseReleased

    private void jScrollDescriptionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollDescriptionMouseExited
        // TODO add your handling code here:
        jScrollDescription.setBackground(defaultColor);
    }//GEN-LAST:event_jScrollDescriptionMouseExited

    private void jScrollDescriptionMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollDescriptionMouseMoved
        // TODO add your handling code here:
        jScrollDescription.setBackground(moveColor);
    }//GEN-LAST:event_jScrollDescriptionMouseMoved

    private void jScrollDescriptionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollDescriptionMouseReleased
        // TODO add your handling code here:
        new Register_DetailConference(conference, user, false).setVisible(true);
    }//GEN-LAST:event_jScrollDescriptionMouseReleased

    private void jDescriptionMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDescriptionMouseMoved
        // TODO add your handling code here:
        jDescription.setBackground(moveColor);
        setBackground(moveColor);
    }//GEN-LAST:event_jDescriptionMouseMoved

    private void jDescriptionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDescriptionMouseExited
        // TODO add your handling code here:
        jDescription.setBackground(defaultColor);
        setBackground(defaultColor);
    }//GEN-LAST:event_jDescriptionMouseExited

    private void jDescriptionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDescriptionMouseReleased
        // TODO add your handling code here:
        new Register_DetailConference(conference, user, false).setVisible(true);
    }//GEN-LAST:event_jDescriptionMouseReleased

    void initData(){
        ImageIcon imageIcon = new ImageIcon(conference.getImage());
        Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        jIcon.setIcon(new ImageIcon(image));
        jName.setText(conference.getName());
        jDescription.setText(conference.getBriefDescription());
        jDate.setText(conference.getStartTime().toString());
        SimpleDateFormat sdf=new SimpleDateFormat("hh:mm:ss dd/MM/YYYY ");
        jDate.setText(sdf.format(conference.getStartTime())+ " - " + sdf.format(conference.getEndTime()));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jDate;
    private javax.swing.JTextArea jDescription;
    private javax.swing.JLabel jIcon;
    private javax.swing.JLabel jName;
    private javax.swing.JScrollPane jScrollDescription;
    // End of variables declaration//GEN-END:variables

    public void setUser(User user) {
        this.user = user;
    }
}
