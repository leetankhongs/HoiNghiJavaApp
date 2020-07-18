package ComponentUI;

import Business.UserConferenceBus;
import MainScreenUI.MainScreen;
import UserUI.Register_DetailConference;
import POJO.Conference;
import POJO.User;
import java.awt.Color;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
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
    private final Color moveColor = new Color(220, 220, 255);
    private final Color defaultColor = new Color(238, 238, 255);

    private Conference conference;
    private User user;

    public ConferenceRendererCard(Conference conference) {
        initComponents();
        this.conference = conference;
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

        jImage = new javax.swing.JPanel();
        jIcon = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jName = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jDatePnl = new javax.swing.JPanel();
        jDate = new javax.swing.JLabel();
        jMonth = new javax.swing.JLabel();
        jYear = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jCapacity = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPlace = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(238, 238, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setPreferredSize(new java.awt.Dimension(350, 300));
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

        jImage.setBackground(new java.awt.Color(238, 238, 255));
        jImage.setPreferredSize(new java.awt.Dimension(0, 150));
        jImage.setLayout(new java.awt.BorderLayout());

        jIcon.setBackground(new java.awt.Color(255, 255, 255));
        jIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jIcon.setPreferredSize(new java.awt.Dimension(200, 200));
        jImage.add(jIcon, java.awt.BorderLayout.CENTER);

        add(jImage, java.awt.BorderLayout.NORTH);

        jPanel2.setBackground(new java.awt.Color(238, 238, 255));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel2MouseMoved(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel2MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel2MouseReleased(evt);
            }
        });
        jPanel2.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jScrollPane1.setPreferredSize(new java.awt.Dimension(304, 50));
        jScrollPane1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseMoved(evt);
            }
        });
        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseExited(evt);
            }
        });

        jName.setEditable(false);
        jName.setBackground(new java.awt.Color(238, 238, 255));
        jName.setColumns(20);
        jName.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jName.setLineWrap(true);
        jName.setRows(5);
        jName.setWrapStyleWord(true);
        jName.setOpaque(false);
        jName.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jNameMouseMoved(evt);
            }
        });
        jName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jNameMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jNameMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jName);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.NORTH);

        jPanel1.setBackground(new java.awt.Color(238, 238, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jDatePnl.setBackground(new java.awt.Color(238, 238, 255));
        jDatePnl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jDatePnl.setPreferredSize(new java.awt.Dimension(100, 40));
        jDatePnl.setLayout(new java.awt.BorderLayout());

        jDate.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jDate.setText("23");
        jDate.setPreferredSize(new java.awt.Dimension(0, 30));
        jDatePnl.add(jDate, java.awt.BorderLayout.CENTER);

        jMonth.setBackground(new java.awt.Color(255, 51, 51));
        jMonth.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jMonth.setForeground(new java.awt.Color(255, 255, 255));
        jMonth.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMonth.setText("jLabel1");
        jMonth.setOpaque(true);
        jMonth.setPreferredSize(new java.awt.Dimension(31, 25));
        jDatePnl.add(jMonth, java.awt.BorderLayout.NORTH);

        jYear.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jYear.setForeground(new java.awt.Color(51, 51, 255));
        jYear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jYear.setText("jLabel2");
        jYear.setPreferredSize(new java.awt.Dimension(31, 25));
        jDatePnl.add(jYear, java.awt.BorderLayout.SOUTH);

        jPanel1.add(jDatePnl, java.awt.BorderLayout.EAST);

        jPanel3.setBackground(new java.awt.Color(238, 238, 255));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jCapacity.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jCapacity.setText("Capacity: 3 / 5");
        jCapacity.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        jCapacity.setPreferredSize(new java.awt.Dimension(82, 30));
        jPanel3.add(jCapacity, java.awt.BorderLayout.NORTH);

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setFocusable(false);
        jScrollPane2.setOpaque(false);

        jPlace.setEditable(false);
        jPlace.setBackground(new java.awt.Color(238, 238, 255));
        jPlace.setColumns(20);
        jPlace.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jPlace.setLineWrap(true);
        jPlace.setRows(5);
        jPlace.setWrapStyleWord(true);
        jPlace.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));
        jPlace.setFocusable(false);
        jPlace.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPlaceMouseMoved(evt);
            }
        });
        jPlace.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPlaceMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPlaceMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jPlace);

        jPanel3.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel1, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        // TODO add your handling code here:
        setBackground(defaultColor);
    }//GEN-LAST:event_formMouseExited

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        // TODO add your handling code here:
        setBackground(moveColor);
    }//GEN-LAST:event_formMouseMoved

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
        MainScreen.getInstance().changeDetailConference(conference);
    }//GEN-LAST:event_formMouseReleased

    private void jPanel2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseReleased
        // TODO add your handling code here:
        MainScreen.getInstance().changeDetailConference(conference);
    }//GEN-LAST:event_jPanel2MouseReleased

    private void jNameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jNameMousePressed
        // TODO add your handling code here:
        MainScreen.getInstance().changeDetailConference(conference);
    }//GEN-LAST:event_jNameMousePressed

    private void jPanel2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseMoved
        // TODO add your handling code here:
        setBackground(moveColor);
    }//GEN-LAST:event_jPanel2MouseMoved

    private void jPanel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseExited
        // TODO add your handling code here:
        setBackground(defaultColor);
    }//GEN-LAST:event_jPanel2MouseExited

    private void jScrollPane1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseMoved
        // TODO add your handling code here:
        setBackground(moveColor);
    }//GEN-LAST:event_jScrollPane1MouseMoved

    private void jScrollPane1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseExited
        // TODO add your handling code here:
        setBackground(defaultColor);
    }//GEN-LAST:event_jScrollPane1MouseExited

    private void jPlaceMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPlaceMouseMoved
        // TODO add your handling code here:
        setBackground(moveColor);
    }//GEN-LAST:event_jPlaceMouseMoved

    private void jPlaceMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPlaceMouseExited
        // TODO add your handling code here:
        setBackground(defaultColor);
    }//GEN-LAST:event_jPlaceMouseExited

    private void jNameMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jNameMouseMoved
        // TODO add your handling code here:
        setBackground(moveColor);
    }//GEN-LAST:event_jNameMouseMoved

    private void jNameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jNameMouseExited
        // TODO add your handling code here:
        setBackground(defaultColor);
    }//GEN-LAST:event_jNameMouseExited

    private void jPlaceMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPlaceMousePressed
        // TODO add your handling code here:
        MainScreen.getInstance().changeDetailConference(conference);
    }//GEN-LAST:event_jPlaceMousePressed

    void initData() {
        ImageIcon imageIcon = new ImageIcon(conference.getImage());
        Image image = imageIcon.getImage().getScaledInstance(350, 150, Image.SCALE_SMOOTH);
        jIcon.setIcon(new ImageIcon(image));
        jName.setText(conference.getName());
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(conference.getStartTime());
        String month = "";
        switch (cal.get(Calendar.MONTH) + 1) {
            case 1:
                month = "January";
                break;
            case 2:
                month = "February";
                break;
            case 3:
                month = "March";
                break;
            case 4:
                month = "April";
                break;
            case 5:
                month = "May";
                break;
            case 6:
                month = "June";
                break;
            case 7:
                month = "July";
                break;
            case 8:
                month = "August";
                break;
            case 9:
                month = "September";
                break;
            case 10:
                month = "October";
                break;
            case 11:
                month = "November";
                break;
            case 12:
                month = "December";
                break;
        }
        jDate.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
        jMonth.setText(month);
        jYear.setText(String.valueOf(cal.get(Calendar.YEAR)));

        jCapacity.setText("Capacity: " + String.valueOf(UserConferenceBus.getTheNumberOfUserIsAccepted(conference)) + "/" + String.valueOf(conference.getParticipants()));
        jPlace.setText(conference.getPlace().getAddress());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jCapacity;
    private javax.swing.JLabel jDate;
    private javax.swing.JPanel jDatePnl;
    private javax.swing.JLabel jIcon;
    private javax.swing.JPanel jImage;
    private javax.swing.JLabel jMonth;
    private javax.swing.JTextArea jName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextArea jPlace;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jYear;
    // End of variables declaration//GEN-END:variables

    public void setUser(User user) {
        this.user = user;
    }
}
