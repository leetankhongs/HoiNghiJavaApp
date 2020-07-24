/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContentUI;

import Business.UserConferenceBus;
import ButtonRenderer.ButtonRenderer;
import ButtonEditor.DetailConferenceButtonEditor;
import MainScreenUI.MainScreen;
import POJO.UserConference;
import ButtonEditor.Cancel_RegisterUserButtonEditor;
import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class Statistic extends javax.swing.JPanel {

    /**
     * Creates new form Statictic
     */
    final static private Color deufault = new Color(224, 224, 250);
    final static private Color colorCliked = new Color(84, 3, 156);
    final static private Color colorMoved = new Color(153, 153, 255);
    final static private Color colorMoved_2 = new Color(220, 220, 255);

    List<UserConference> list = new ArrayList<>();
    private int maxPag;
    private int minPag = 1;

    private void filter() {
        organizedDateFilter();
        registeredDateFilter();
        ConferenceNameFilter();
        paginationFilter();
    }

    private void paginationFilter() {
        calculatePag();
        List<UserConference> temp = new ArrayList<>();
        int start = (Integer.valueOf(jPosition.getText()) - 1) * (Integer.valueOf(jComboBox1.getSelectedItem().toString()));
        int end = Integer.valueOf(jPosition.getText()) * (Integer.valueOf(jComboBox1.getSelectedItem().toString())) - 1;

        if (end > list.size() - 1) {
            end = list.size() - 1;
        }

        for (int i = start; i <= end; i++) {
            temp.add(list.get(i));
        }

        jDescriptionPag.setText("Page " + jPosition.getText() + " for " + (start + 1) + "-" + (end + 1) + "/" + list.size());

        list.clear();

        for (int i = 0; i < temp.size(); i++) {
            list.add(temp.get(i));
        }

    }

    private void ConferenceNameFilter() {
        if (jSearchText.getText().compareTo("Search conference name") == 0) {
            return;
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).getConference().getName().toLowerCase().indexOf(jSearchText.getText().toLowerCase()) == -1) {
                list.remove(i);
            }

        }
    }

    private void organizedDateFilter() {
        if (jDateChooser.getDate() == null) {
            return;
        }

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Date getDate = null;

        try {
            getDate = formatter.parse(formatter.format(jDateChooser.getDate()));
        } catch (ParseException ex) {
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            try {
                if (formatter.parse(formatter.format(list.get(i).getConference().getStartTime())).compareTo(getDate) != 0) {
                    list.remove(i);
                }
            } catch (ParseException ex) {
                Logger.getLogger(ConferenceUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void registeredDateFilter() {
        if (jDateChooser1.getDate() == null) {
            return;
        }

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Date getDate = null;

        try {
            getDate = formatter.parse(formatter.format(jDateChooser1.getDate()));
        } catch (ParseException ex) {
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            try {
                if (formatter.parse(formatter.format(list.get(i).getRegistationTime())).compareTo(getDate) != 0) {
                    list.remove(i);
                }
            } catch (ParseException ex) {
                Logger.getLogger(ConferenceUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void calculatePag() {
        int countRow = list.size();

        if (countRow % (Integer.valueOf(jComboBox1.getSelectedItem().toString())) == 0) {
            maxPag = countRow / Integer.valueOf(jComboBox1.getSelectedItem().toString());
        } else {
            maxPag = (int) (countRow / Integer.valueOf(jComboBox1.getSelectedItem().toString())) + 1;
        }

        if (maxPag == 0) {
            maxPag = 1;
        }

    }

    public Statistic() {

        initComponents();
    }

    public void resetData() {
        list = UserConferenceBus.getListUserConferenceByUser(MainScreen.getInstance().getUser());
        Collections.reverse(list);
        filter();
        DefaultTableModel tm = (DefaultTableModel) jTable.getModel();
        for (int i = tm.getRowCount() - 1; i >= 0; i--) {
            tm.removeRow(i);
        }

        for (int i = 0; i < list.size(); i++) {

            String status = "";

            switch (list.get(i).getIsAccepted()) {
                case 0:
                    status = "No Accepted yet";
                    break;
                case 1:
                    status = "Accepted";
                    break;
                case 2:
                    status = "Denied";
                    break;

            }

            if (list.get(i).getConference().getIsDelete() == 1) {
                status = "is Deleted";
            }

            tm.addRow(new Object[]{i + 1, list.get(i).getConference().getName(), list.get(i).getRegistationTime(), list.get(i).getConference().getStartTime(), status, list.get(i).getConference(), list.get(i)});

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jHeader = new javax.swing.JPanel();
        jOption = new javax.swing.JPanel();
        jDate = new javax.swing.JPanel();
        jOrganizedDate = new javax.swing.JPanel();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        jOrganizedDateText = new javax.swing.JLabel();
        jRegisterdDate = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jRegisteredDateText1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jResetbtn = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jSearchPnl = new javax.swing.JPanel();
        jSearchbtn = new javax.swing.JLabel();
        jSearchText = new javax.swing.JTextField();
        jBrief = new javax.swing.JLabel();
        jData = new javax.swing.JPanel();
        jPagination = new javax.swing.JPanel();
        jDescriptionPag = new javax.swing.JLabel();
        jPaginationButton = new javax.swing.JPanel();
        jFirstbtn = new javax.swing.JLabel();
        jPrebtn = new javax.swing.JLabel();
        jPosition = new javax.swing.JTextField();
        jNextbtn = new javax.swing.JLabel();
        jLastbtn = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jHeader.setBackground(new java.awt.Color(224, 224, 250));
        jHeader.setPreferredSize(new java.awt.Dimension(100, 170));
        jHeader.setLayout(new java.awt.BorderLayout());

        jOption.setBackground(new java.awt.Color(255, 255, 255));
        jOption.setPreferredSize(new java.awt.Dimension(100, 50));
        jOption.setLayout(new java.awt.BorderLayout());

        jDate.setBackground(new java.awt.Color(224, 224, 250));
        jDate.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 50, 0, 50));
        jDate.setPreferredSize(new java.awt.Dimension(650, 100));
        jDate.setLayout(new java.awt.BorderLayout());

        jOrganizedDate.setBackground(new java.awt.Color(224, 224, 250));
        jOrganizedDate.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 5, 0));
        jOrganizedDate.setPreferredSize(new java.awt.Dimension(275, 50));
        jOrganizedDate.setLayout(new java.awt.BorderLayout());

        jDateChooser.setDateFormatString("dd/MM/yyyy");
        jDateChooser.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jDateChooser.setOpaque(false);
        jDateChooser.setPreferredSize(new java.awt.Dimension(75, 50));
        jDateChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserPropertyChange(evt);
            }
        });
        jOrganizedDate.add(jDateChooser, java.awt.BorderLayout.CENTER);

        jOrganizedDateText.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jOrganizedDateText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jOrganizedDateText.setText("Organized Date");
        jOrganizedDateText.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 10));
        jOrganizedDateText.setPreferredSize(new java.awt.Dimension(150, 50));
        jOrganizedDate.add(jOrganizedDateText, java.awt.BorderLayout.WEST);

        jDate.add(jOrganizedDate, java.awt.BorderLayout.CENTER);

        jRegisterdDate.setBackground(new java.awt.Color(224, 224, 250));
        jRegisterdDate.setMinimumSize(new java.awt.Dimension(125, 27));
        jRegisterdDate.setPreferredSize(new java.awt.Dimension(325, 50));
        jRegisterdDate.setLayout(new java.awt.BorderLayout());

        jDateChooser1.setDateFormatString("dd/MM/yyyy");
        jDateChooser1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jDateChooser1.setOpaque(false);
        jDateChooser1.setPreferredSize(new java.awt.Dimension(75, 50));
        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });
        jRegisterdDate.add(jDateChooser1, java.awt.BorderLayout.CENTER);

        jRegisteredDateText1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jRegisteredDateText1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jRegisteredDateText1.setText("Registered Date");
        jRegisteredDateText1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 10));
        jRegisteredDateText1.setPreferredSize(new java.awt.Dimension(150, 50));
        jRegisterdDate.add(jRegisteredDateText1, java.awt.BorderLayout.WEST);

        jDate.add(jRegisterdDate, java.awt.BorderLayout.NORTH);

        jOption.add(jDate, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jComboBox1.setEditable(true);
        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));
        jComboBox1.setPreferredSize(new java.awt.Dimension(70, 50));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox1, java.awt.BorderLayout.EAST);

        jResetbtn.setBackground(new java.awt.Color(224, 224, 250));
        jResetbtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jResetbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Available-Updates-icon.png"))); // NOI18N
        jResetbtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 5, 0));
        jResetbtn.setOpaque(true);
        jResetbtn.setPreferredSize(new java.awt.Dimension(70, 50));
        jResetbtn.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jResetbtnMouseMoved(evt);
            }
        });
        jResetbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jResetbtnMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jResetbtnMouseReleased(evt);
            }
        });
        jPanel1.add(jResetbtn, java.awt.BorderLayout.NORTH);

        jOption.add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel3.setBackground(new java.awt.Color(224, 224, 250));
        jPanel3.setPreferredSize(new java.awt.Dimension(500, 60));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jSearchPnl.setBackground(new java.awt.Color(224, 224, 250));
        jSearchPnl.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0));
        jSearchPnl.setPreferredSize(new java.awt.Dimension(250, 50));
        jSearchPnl.setLayout(new java.awt.BorderLayout());

        jSearchbtn.setBackground(new java.awt.Color(224, 224, 250));
        jSearchbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Search-icon.png"))); // NOI18N
        jSearchbtn.setOpaque(true);
        jSearchbtn.setPreferredSize(new java.awt.Dimension(50, 13));
        jSearchbtn.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jSearchbtnMouseMoved(evt);
            }
        });
        jSearchbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jSearchbtnMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jSearchbtnMouseReleased(evt);
            }
        });
        jSearchPnl.add(jSearchbtn, java.awt.BorderLayout.WEST);

        jSearchText.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jSearchText.setText("Search conference name");
        jSearchText.setToolTipText("");
        jSearchText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jSearchText.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jSearchText.setPreferredSize(new java.awt.Dimension(200, 27));
        jSearchText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jSearchTextFocusLost(evt);
            }
        });
        jSearchText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jSearchTextKeyTyped(evt);
            }
        });
        jSearchPnl.add(jSearchText, java.awt.BorderLayout.CENTER);

        jPanel3.add(jSearchPnl, java.awt.BorderLayout.NORTH);

        jOption.add(jPanel3, java.awt.BorderLayout.EAST);

        jHeader.add(jOption, java.awt.BorderLayout.CENTER);

        jBrief.setFont(new java.awt.Font("Times New Roman", 1, 28)); // NOI18N
        jBrief.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jBrief.setText("List of registered conferences");
        jBrief.setPreferredSize(new java.awt.Dimension(78, 60));
        jHeader.add(jBrief, java.awt.BorderLayout.NORTH);

        add(jHeader, java.awt.BorderLayout.NORTH);

        jData.setBackground(new java.awt.Color(255, 102, 51));
        jData.setOpaque(false);
        jData.setLayout(new java.awt.BorderLayout());

        jPagination.setBackground(new java.awt.Color(224, 224, 250));
        jPagination.setPreferredSize(new java.awt.Dimension(1000, 50));
        jPagination.setLayout(new java.awt.BorderLayout());

        jDescriptionPag.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jDescriptionPag.setText("Page 1 for 1-6/100");
        jDescriptionPag.setPreferredSize(new java.awt.Dimension(300, 13));
        jPagination.add(jDescriptionPag, java.awt.BorderLayout.WEST);

        jPaginationButton.setOpaque(false);
        jPaginationButton.setPreferredSize(new java.awt.Dimension(290, 50));
        jPaginationButton.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        jFirstbtn.setBackground(new java.awt.Color(224, 224, 250));
        jFirstbtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jFirstbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Rewind-icon.png"))); // NOI18N
        jFirstbtn.setOpaque(true);
        jFirstbtn.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jFirstbtnMouseMoved(evt);
            }
        });
        jFirstbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jFirstbtnMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFirstbtnMouseReleased(evt);
            }
        });
        jPaginationButton.add(jFirstbtn);

        jPrebtn.setBackground(new java.awt.Color(224, 224, 250));
        jPrebtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPrebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Chevron-Left-icon.png"))); // NOI18N
        jPrebtn.setOpaque(true);
        jPrebtn.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPrebtnMouseMoved(evt);
            }
        });
        jPrebtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPrebtnMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPrebtnMouseReleased(evt);
            }
        });
        jPaginationButton.add(jPrebtn);

        jPosition.setEditable(false);
        jPosition.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jPosition.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPosition.setText("1");
        jPaginationButton.add(jPosition);

        jNextbtn.setBackground(new java.awt.Color(224, 224, 250));
        jNextbtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jNextbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Chevron-Right-icon.png"))); // NOI18N
        jNextbtn.setOpaque(true);
        jNextbtn.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jNextbtnMouseMoved(evt);
            }
        });
        jNextbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jNextbtnMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jNextbtnMouseReleased(evt);
            }
        });
        jPaginationButton.add(jNextbtn);

        jLastbtn.setBackground(new java.awt.Color(224, 224, 250));
        jLastbtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLastbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Fast-Forward-icon.png"))); // NOI18N
        jLastbtn.setOpaque(true);
        jLastbtn.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLastbtnMouseMoved(evt);
            }
        });
        jLastbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLastbtnMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLastbtnMouseReleased(evt);
            }
        });
        jPaginationButton.add(jLastbtn);

        jPagination.add(jPaginationButton, java.awt.BorderLayout.EAST);

        jData.add(jPagination, java.awt.BorderLayout.SOUTH);

        jScrollPane1.setOpaque(false);

        jTable.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        DefaultTableModel tm = new DefaultTableModel(new Object[0][], new String[]{"STT", "Conference Name", "Registered Date", "Organized Date", "Status", "Detail", "Cancel"}) {
            @Override
            public Class

            <?> getColumnClass(int col) {
                //here it really returns the right column class (Integer.class)
                Class retVal = Object.class

                ;

                if (getRowCount()
                    > 0) {
                    retVal = getValueAt(0, col).getClass();
                }

                return retVal ;
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return column == 5 || column == 6;
            }

        };
        jTable.setModel(tm);
        jTable.getTableHeader().setOpaque(true);
        jTable.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 16));

        jTable.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer("Detail"));
        jTable.getColumnModel().getColumn(5).setCellEditor(new DetailConferenceButtonEditor(new JTextField(), 2));
        jTable.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer("Cancel"));
        jTable.getColumnModel().getColumn(6).setCellEditor(new Cancel_RegisterUserButtonEditor(new JTextField()));
        jTable.setAutoCreateRowSorter(true);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(6).setPreferredWidth(100);
        jTable.setFocusable(false);
        jTable.setGridColor(new java.awt.Color(153, 153, 255));
        jTable.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable.setRowHeight(40);
        jTable.setSelectionBackground(new java.awt.Color(204, 204, 255));
        jTable.setShowGrid(true);
        jTable.setShowVerticalLines(false);
        jTable.getTableHeader().setResizingAllowed(false);
        jTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable);

        jData.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(jData, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        calculatePag();
        jPosition.setText("1");
        resetData();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jResetbtnMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jResetbtnMouseMoved
        // TODO add your handling code here:
        jResetbtn.setBackground(colorMoved);
    }//GEN-LAST:event_jResetbtnMouseMoved

    private void jResetbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jResetbtnMouseExited
        // TODO add your handling code here:
        jResetbtn.setBackground(deufault);
    }//GEN-LAST:event_jResetbtnMouseExited

    private void jResetbtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jResetbtnMouseReleased
        // TODO add your handling code here:
        jDateChooser1.setDate(null);
        jDateChooser.setDate(null);
        jSearchText.setText("Search conference name");
        jPosition.setText("1");
        resetData();
    }//GEN-LAST:event_jResetbtnMouseReleased

    private void jSearchbtnMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchbtnMouseMoved
        // TODO add your handling code here:
        jSearchbtn.setBackground(colorMoved);
    }//GEN-LAST:event_jSearchbtnMouseMoved

    private void jSearchbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchbtnMouseExited
        // TODO add your handling code here:
        jSearchbtn.setBackground(deufault);
    }//GEN-LAST:event_jSearchbtnMouseExited

    private void jSearchbtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchbtnMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jSearchbtnMouseReleased

    private void jSearchTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSearchTextFocusLost
        // TODO add your handling code here:
        if (jSearchText.getText().compareTo("") == 0)
            jSearchText.setText("Search conference name ");
    }//GEN-LAST:event_jSearchTextFocusLost

    private void jFirstbtnMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFirstbtnMouseMoved
        // TODO add your handling code here:
        jFirstbtn.setBackground(colorMoved);
    }//GEN-LAST:event_jFirstbtnMouseMoved

    private void jFirstbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFirstbtnMouseExited
        // TODO add your handling code here:
        jFirstbtn.setBackground(deufault);
    }//GEN-LAST:event_jFirstbtnMouseExited

    private void jFirstbtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFirstbtnMouseReleased
        // TODO add your handling code here:
        jPosition.setText("1");
        resetData();
    }//GEN-LAST:event_jFirstbtnMouseReleased

    private void jPrebtnMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPrebtnMouseMoved
        // TODO add your handling code here:
        jPrebtn.setBackground(colorMoved);
    }//GEN-LAST:event_jPrebtnMouseMoved

    private void jPrebtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPrebtnMouseExited
        // TODO add your handling code here:
        jPrebtn.setBackground(deufault);
    }//GEN-LAST:event_jPrebtnMouseExited

    private void jPrebtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPrebtnMouseReleased
        // TODO add your handling code here:
        int currentPosition = Integer.valueOf(jPosition.getText());
        currentPosition = currentPosition == 1 ? 1 : --currentPosition;
        jPosition.setText(String.valueOf(currentPosition));
        resetData();
    }//GEN-LAST:event_jPrebtnMouseReleased

    private void jNextbtnMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jNextbtnMouseMoved
        // TODO add your handling code here:
        jNextbtn.setBackground(colorMoved);
    }//GEN-LAST:event_jNextbtnMouseMoved

    private void jNextbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jNextbtnMouseExited
        // TODO add your handling code here:
        jNextbtn.setBackground(deufault);
    }//GEN-LAST:event_jNextbtnMouseExited

    private void jNextbtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jNextbtnMouseReleased
        // TODO add your handling code here:
        int currentPosition = Integer.valueOf(jPosition.getText());
        currentPosition = currentPosition == maxPag ? maxPag : ++currentPosition;
        jPosition.setText(String.valueOf(currentPosition));
        resetData();
    }//GEN-LAST:event_jNextbtnMouseReleased

    private void jLastbtnMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLastbtnMouseMoved
        // TODO add your handling code here:
        jLastbtn.setBackground(colorMoved);
    }//GEN-LAST:event_jLastbtnMouseMoved

    private void jLastbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLastbtnMouseExited
        // TODO add your handling code here:
        jLastbtn.setBackground(deufault);
    }//GEN-LAST:event_jLastbtnMouseExited

    private void jLastbtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLastbtnMouseReleased
        // TODO add your handling code here:
        jPosition.setText(String.valueOf(maxPag));
        resetData();
    }//GEN-LAST:event_jLastbtnMouseReleased

    private void jDateChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserPropertyChange
        // TODO add your handling code here:
        if ("date".equals(evt.getPropertyName())) {
            jPosition.setText("1");
            resetData();
        }
    }//GEN-LAST:event_jDateChooserPropertyChange

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        // TODO add your handling code here:
        if ("date".equals(evt.getPropertyName())) {
            jPosition.setText("1");
            resetData();
        }
    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void jSearchTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSearchTextKeyTyped
        // TODO add your handling code here:
        jPosition.setText("1");
        resetData();
    }//GEN-LAST:event_jSearchTextKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jBrief;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jData;
    private javax.swing.JPanel jDate;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jDescriptionPag;
    private javax.swing.JLabel jFirstbtn;
    private javax.swing.JPanel jHeader;
    private javax.swing.JLabel jLastbtn;
    private javax.swing.JLabel jNextbtn;
    private javax.swing.JPanel jOption;
    private javax.swing.JPanel jOrganizedDate;
    private javax.swing.JLabel jOrganizedDateText;
    private javax.swing.JPanel jPagination;
    private javax.swing.JPanel jPaginationButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jPosition;
    private javax.swing.JLabel jPrebtn;
    private javax.swing.JPanel jRegisterdDate;
    private javax.swing.JLabel jRegisteredDateText1;
    private javax.swing.JLabel jResetbtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jSearchPnl;
    private javax.swing.JTextField jSearchText;
    private javax.swing.JLabel jSearchbtn;
    private javax.swing.JTable jTable;
    // End of variables declaration//GEN-END:variables

    public void setFilter(Date registerDate, Date organizeDate, String searchString, int indexLine, int positionPag) {
        jDateChooser1.setDate(registerDate);
        jDateChooser.setDate(organizeDate);
        jSearchText.setText(searchString);
        jComboBox1.setSelectedIndex(indexLine);
        jPosition.setText(String.valueOf(positionPag));
    }

    public Statistic cloneS() {
        Statistic statistic = new Statistic();
        ConferenceUI conferenceUI = new ConferenceUI();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Date getRegisterDate = jDateChooser1.getDate();
        Date getOrganizeDate = jDateChooser.getDate();

        try {
            getRegisterDate = getRegisterDate == null ? null : formatter.parse(formatter.format(getRegisterDate));
            getOrganizeDate = getOrganizeDate == null ? null : formatter.parse(formatter.format(getOrganizeDate));
        } catch (ParseException ex) {
        }

        statistic.setFilter(getRegisterDate, getOrganizeDate, jSearchText.getText(), jComboBox1.getSelectedIndex(), Integer.valueOf(jPosition.getText()));
        return statistic;
    }

}
