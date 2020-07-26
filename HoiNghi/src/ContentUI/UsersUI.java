/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContentUI;

import ButtonEditor.BlockButtonEditor;
import ButtonRenderer.BlockButtonRenderer;
import ButtonEditor.ConferenceUserButtonEditor;
import Business.ConferenceBus;
import Business.UserBus;
import Business.UserConferenceBus;
import ButtonRenderer.ButtonRenderer;
import ButtonEditor.DetailConferenceButtonEditor;
import POJO.Conference;
import POJO.User;
import POJO.UserConference;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class UsersUI extends javax.swing.JPanel {

    /**
     * Creates new form UsersUI
     */
    final static private Color deufault = new Color(224, 224, 250);
    final static private Color colorCliked = new Color(84, 3, 156);
    final static private Color colorMoved = new Color(153, 153, 255);
    final static private Color colorMoved_2 = new Color(220, 220, 255);

    List<User> list;
    private int maxPag;
    private User preUser = null;
    
    public User getPreUser(){
        return preUser;
    }

    public void setPreUser(User preUser){
        this.preUser = preUser;
        
        List<UserConference> conferenceList = UserConferenceBus.getListUserConferenceIsAcceptedByUser(preUser);
        DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
        for (int i = tm.getRowCount() - 1; i >= 0; i--) {
            tm.removeRow(i);
        }

        for (int i = 0; i < conferenceList.size(); i++) {
            tm.addRow(new Object[]{i + 1, conferenceList.get(i).getConference().getName(), conferenceList.get(i).getConference().getStartTime(), conferenceList.get(i).getConference()});
        }

        jTable1.setModel(tm);
        
        jTable1.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer("Detail"));
        jTable1.getColumnModel().getColumn(3).setCellEditor(new DetailConferenceButtonEditor(new JTextField(),4));
    }
    
    public JPanel getJPanel(){
        return jConferenceListPnl;
    }
    
    public JTable getJTable(){
        return jTable;
    }
    
    private void filter() {
        FullNameFilter();
        userNameFilter();
        ConferenceNameFileter();
        paginationFilter();
        ConferenceNameFileter();
        blockedFilter();
    }

    private void userNameFilter() {
        if (jUserNameTF.getText().compareTo("User name") == 0) {
            return;
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).getAccount().getUserName().toLowerCase().indexOf(jUserNameTF.getText().toLowerCase()) == -1) {
                list.remove(i);
            }

        }
    }
    

    private void blockedFilter() {
        int index = jBlockCB.getSelectedIndex();

        if (index == 1) {
            for (int i = list.size() - 1; i >= 0; i--) {
                if (list.get(i).getIsDelete() != 1) {
                    list.remove(i);
                }

            }
        } else if (index == 2) {
            for (int i = list.size() - 1; i >= 0; i--) {
                if (list.get(i).getIsDelete() == 1) {
                    list.remove(i);
                }

            }
        }
    }

    private void ConferenceNameFileter() {
        int index = jConferenceCB.getSelectedIndex();

        if (index == 0 || index == -1) {
            return;
        }

        String conferenceName = jConferenceCB.getItemAt(index);

        for (int i = list.size() - 1; i >= 0; i--) {
            List<UserConference> userConferences = UserConferenceBus.getListUserConferenceIsAcceptedByUser(list.get(i));

            boolean has = false;
            for (int j = 0; j < userConferences.size(); j++) {
                if (conferenceName.compareTo(userConferences.get(j).getConference().getName()) == 0) {
                    has = true;
                    break;
                }
            }

            if (!has) {
                list.remove(i);
            }
        }
    }

    private void paginationFilter() {
        calculatePag();
        List<User> temp = new ArrayList<>();
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

    private void FullNameFilter() {
        if (jFullNameTF.getText().compareTo("Full name") == 0) {
            return;
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).getName().toLowerCase().indexOf(jFullNameTF.getText().toLowerCase()) == -1) {
                list.remove(i);
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

    public UsersUI() {
        initComponents();
        resetData();
    }

    public void resetData() {
        list = UserBus.getAllUser();
        Collections.reverse(list);
        filter();
        System.out.println("Vo");
        jConferenceListPnl.setPreferredSize(new Dimension(0,0));
        DefaultTableModel tm = (DefaultTableModel) jTable.getModel();
        for (int i = tm.getRowCount() - 1; i >= 0; i--) {
            tm.removeRow(i);
        }

        for (int i = 0; i < list.size(); i++) {
            tm.addRow(new Object[]{i + 1, list.get(i).getName(), list.get(i).getAccount().getUserName(), list.get(i).getEmail(), UserConferenceBus.getListUserConferenceIsAcceptedByUser(list.get(i)).size(), list.get(i), list.get(i)});
        }

        jTable.setModel(tm);

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
        jPanel3 = new javax.swing.JPanel();
        jResetbtn = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jConferenceCB = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jBlockCB = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jFullNameTF = new javax.swing.JTextField();
        jUserNameTF = new javax.swing.JTextField();
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
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jConferenceListPnl = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jHeader.setBackground(new java.awt.Color(224, 224, 250));
        jHeader.setPreferredSize(new java.awt.Dimension(100, 170));
        jHeader.setLayout(new java.awt.BorderLayout());

        jOption.setBackground(new java.awt.Color(255, 255, 255));
        jOption.setPreferredSize(new java.awt.Dimension(100, 50));
        jOption.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(224, 224, 250));
        jPanel3.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jResetbtn.setBackground(new java.awt.Color(224, 224, 250));
        jResetbtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jResetbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Available-Updates-icon.png"))); // NOI18N
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
        jPanel3.add(jResetbtn, java.awt.BorderLayout.NORTH);

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));
        jComboBox1.setPreferredSize(new java.awt.Dimension(70, 50));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel3.add(jComboBox1, java.awt.BorderLayout.CENTER);

        jOption.add(jPanel3, java.awt.BorderLayout.WEST);

        jPanel4.setBackground(new java.awt.Color(224, 224, 250));
        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 100, 0, 100));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 5, 0));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Conference");
        jLabel1.setPreferredSize(new java.awt.Dimension(100, 13));
        jPanel1.add(jLabel1, java.awt.BorderLayout.WEST);

        jConferenceCB.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jConferenceCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Conference Name" }));
        jConferenceCB.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 5, 0));
        jConferenceCB.setPreferredSize(new java.awt.Dimension(300, 50));
        jConferenceCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConferenceCBActionPerformed(evt);
            }
        });
        List<Conference> conferenceList = ConferenceBus.getAllConference();
        Collections.sort(conferenceList);
        for (int i = 0; i < conferenceList.size(); i++) {
            jConferenceCB.addItem(conferenceList.get(i).getName());
        }
        jPanel1.add(jConferenceCB, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jBlockCB.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jBlockCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Is Blocked", "Is Not Blocked" }));
        jBlockCB.setBorder(null);
        jBlockCB.setPreferredSize(new java.awt.Dimension(300, 50));
        jBlockCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlockCBActionPerformed(evt);
            }
        });
        jPanel2.add(jBlockCB, java.awt.BorderLayout.CENTER);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Blocked");
        jLabel2.setPreferredSize(new java.awt.Dimension(100, 13));
        jPanel2.add(jLabel2, java.awt.BorderLayout.WEST);

        jPanel4.add(jPanel2, java.awt.BorderLayout.NORTH);

        jOption.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel5.setBackground(new java.awt.Color(224, 224, 250));
        jPanel5.setPreferredSize(new java.awt.Dimension(300, 60));

        jFullNameTF.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jFullNameTF.setText("Full name");
        jFullNameTF.setPreferredSize(new java.awt.Dimension(250, 50));
        jFullNameTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFullNameTFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFullNameTFFocusLost(evt);
            }
        });
        jFullNameTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFullNameTFKeyTyped(evt);
            }
        });
        jPanel5.add(jFullNameTF);

        jUserNameTF.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jUserNameTF.setText("User name");
        jUserNameTF.setPreferredSize(new java.awt.Dimension(250, 50));
        jUserNameTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jUserNameTFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jUserNameTFFocusLost(evt);
            }
        });
        jUserNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUserNameTFActionPerformed(evt);
            }
        });
        jUserNameTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jUserNameTFKeyTyped(evt);
            }
        });
        jPanel5.add(jUserNameTF);

        jOption.add(jPanel5, java.awt.BorderLayout.EAST);

        jHeader.add(jOption, java.awt.BorderLayout.CENTER);

        jBrief.setFont(new java.awt.Font("Times New Roman", 1, 28)); // NOI18N
        jBrief.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jBrief.setText("User manager ");
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

        jPanel6.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setOpaque(false);

        jTable.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        DefaultTableModel tm = new DefaultTableModel(new Object[0][], new String[]{"STT", "Full Name", "User Name", "Email", "Number", "Conferences", "Blocked"}) {
            @Override
            public Class<?> getColumnClass(int col) {
                //here it really returns the right column class (Integer.class)
                Class retVal = Object.class  ;

                if (getRowCount() > 0) {
                    retVal = getValueAt(0, col).getClass();
                }
                return retVal ;
            }
        };
        jTable.getTableHeader().setOpaque(true);
        jTable.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 16));
        jTable.setModel(tm);
        jTable.getColumnModel().getColumn(6).setCellRenderer(new BlockButtonRenderer());
        jTable.getColumnModel().getColumn(6).setCellEditor(new BlockButtonEditor(new JTextField()));
        jTable.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer("Conference List"));
        jTable.getColumnModel().getColumn(5).setCellEditor(new ConferenceUserButtonEditor(new JTextField()));
        jTable.setAutoCreateRowSorter(true);
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

        jPanel6.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jConferenceListPnl.setPreferredSize(new java.awt.Dimension(10, 0));
        jConferenceListPnl.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Conference List");
        jLabel3.setPreferredSize(new java.awt.Dimension(31, 25));
        jConferenceListPnl.add(jLabel3, java.awt.BorderLayout.NORTH);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Conference Name", "Organized date", "Detail"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
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
        jTable1.setRowHeight(30);
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(400);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        jConferenceListPnl.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel6.add(jConferenceListPnl, java.awt.BorderLayout.SOUTH);

        jData.add(jPanel6, java.awt.BorderLayout.CENTER);

        add(jData, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

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
    }//GEN-LAST:event_jResetbtnMouseReleased

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        calculatePag();
        jPosition.setText("1");
        resetData();
    }//GEN-LAST:event_jComboBox1ActionPerformed

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

    private void jConferenceCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConferenceCBActionPerformed
        // TODO add your handling code here:
        jPosition.setText("1");
        resetData();
    }//GEN-LAST:event_jConferenceCBActionPerformed

    private void jFullNameTFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFullNameTFFocusGained
        // TODO add your handling code here:
        if (jFullNameTF.getText().compareTo("Full name") == 0)
            jFullNameTF.setText("");
    }//GEN-LAST:event_jFullNameTFFocusGained

    private void jFullNameTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFullNameTFFocusLost
        // TODO add your handling code here:
        if (jFullNameTF.getText().compareTo("") == 0)
            jFullNameTF.setText("Full name");
    }//GEN-LAST:event_jFullNameTFFocusLost

    private void jFullNameTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFullNameTFKeyTyped
        // TODO add your handling code here:
        jPosition.setText("1");
        resetData();
    }//GEN-LAST:event_jFullNameTFKeyTyped

    private void jBlockCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlockCBActionPerformed
        // TODO add your handling code here:
        jPosition.setText("1");
        resetData();
    }//GEN-LAST:event_jBlockCBActionPerformed

    private void jUserNameTFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jUserNameTFFocusGained
        // TODO add your handling code here:
        if (jUserNameTF.getText().compareTo("User name") == 0)
            jUserNameTF.setText("");
    }//GEN-LAST:event_jUserNameTFFocusGained

    private void jUserNameTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jUserNameTFFocusLost
        // TODO add your handling code here:
        
        if (jUserNameTF.getText().compareTo("") == 0)
            jUserNameTF.setText("User name");
    }//GEN-LAST:event_jUserNameTFFocusLost

    private void jUserNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUserNameTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jUserNameTFActionPerformed

    private void jUserNameTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jUserNameTFKeyTyped
        // TODO add your handling code here:
        jPosition.setText("1");
        resetData();
    }//GEN-LAST:event_jUserNameTFKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jBlockCB;
    private javax.swing.JLabel jBrief;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jConferenceCB;
    private javax.swing.JPanel jConferenceListPnl;
    private javax.swing.JPanel jData;
    private javax.swing.JLabel jDescriptionPag;
    private javax.swing.JLabel jFirstbtn;
    private javax.swing.JTextField jFullNameTF;
    private javax.swing.JPanel jHeader;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLastbtn;
    private javax.swing.JLabel jNextbtn;
    private javax.swing.JPanel jOption;
    private javax.swing.JPanel jPagination;
    private javax.swing.JPanel jPaginationButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField jPosition;
    private javax.swing.JLabel jPrebtn;
    private javax.swing.JLabel jResetbtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jUserNameTF;
    // End of variables declaration//GEN-END:variables
}
