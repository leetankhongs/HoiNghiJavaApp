/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContentUI;

import AdminUI.AdminButtonEditor;
import AdminUI.AdminButtonRenderer;
import AdminUI.RequestButtonEditor;
import AdminUI.RequestButtonRenderer;
import Business.ConferenceBus;
import Business.UserConferenceBus;
import MainScreenUI.MainScreen;
import MainScreenUI.NewConferenceDialog;
import POJO.Conference;
import POJO.UserConference;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.hibernate.jpa.criteria.expression.function.AggregationFunction;

/**
 *
 * @author ADMIN
 */
public class ConferenceUI extends javax.swing.JPanel {

    /**
     * Creates new form Conference
     */
    final static private Color deufault = new Color(224, 224, 250);
    final static private Color colorMoved = new Color(153, 153, 255);

    List<Conference> listConference;
    private int maxPag;
    private int minPag = 1;

    private void filter() {
        statusFilter();
        requestFilter();
        organizedDateFilter();
        ConferenceNameFilter();
        paginationFilter();
    }

    private void paginationFilter() {
        calculatePag();
        List<Conference> temp = new ArrayList<>();
        int start = (Integer.valueOf(jPosition.getText()) - 1) * (Integer.valueOf(jComboBox1.getSelectedItem().toString()));
        int end = Integer.valueOf(jPosition.getText()) * (Integer.valueOf(jComboBox1.getSelectedItem().toString())) - 1;

        if (end > listConference.size() - 1) {
            end = listConference.size() - 1;
        }

        for (int i = start; i <= end; i++) {
            temp.add(listConference.get(i));
        }

        jDescriptionPag.setText("Page " + jPosition.getText() + " for " + (start + 1) + "-" + (end + 1) + "/" + listConference.size());

        listConference.clear();

        for (int i = 0; i < temp.size(); i++) {
            listConference.add(temp.get(i));
        }

    }

    private void statusFilter() {
        int takePlace = jTakePlace.getSelectedIndex();
        Date date = new Date();

        switch (takePlace) {
            case 1:
                for (int i = listConference.size() - 1; i >= 0; i--) {
                    if (listConference.get(i).getStartTime().compareTo(date) >= 0 || listConference.get(i).getIsDelete() == 1) {
                        listConference.remove(i);
                    }
                }
                break;
            case 2:
                for (int i = listConference.size() - 1; i >= 0; i--) {
                    if (listConference.get(i).getStartTime().compareTo(date) < 0 || listConference.get(i).getIsDelete() == 1) {
                        listConference.remove(i);
                    }
                }
                break;
            case 3:
                for (int i = listConference.size() - 1; i >= 0; i--) {
                    if (listConference.get(i).getIsDelete() != 1) {
                        listConference.remove(i);
                    }
                }
                break;
        }
    }

    private void requestFilter() {
        int requestIndex = jNewRequest.getSelectedIndex();

        switch (requestIndex) {
            case 1:
                for (int i = listConference.size() - 1; i >= 0; i--) {
                    List<UserConference> userConferences = UserConferenceBus.getNewRequests(listConference.get(i));
                    if (userConferences.size() != 0) {
                        listConference.remove(i);
                    }
                }
                break;
            case 2:
                for (int i = listConference.size() - 1; i >= 0; i--) {
                    List<UserConference> userConferences = UserConferenceBus.getNewRequests(listConference.get(i));
                    if (userConferences.size() == 0) {
                        listConference.remove(i);
                    }
                }
                break;

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

        for (int i = listConference.size() - 1; i >= 0; i--) {
            try {
                if (formatter.parse(formatter.format(listConference.get(i).getStartTime())).compareTo(getDate) != 0) {
                    listConference.remove(i);
                }
            } catch (ParseException ex) {
                Logger.getLogger(ConferenceUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void ConferenceNameFilter() {
        if (jSearchText.getText().compareTo("Search conference name") == 0) {
            return;
        }

        for (int i = listConference.size() - 1; i >= 0; i--) {
            if (listConference.get(i).getName().toLowerCase().indexOf(jSearchText.getText().toLowerCase()) == -1) {
                listConference.remove(i);
            }

        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
    }

    public ConferenceUI() {

        initComponents();

        RequestButtonEditor requestButtonEditor = new RequestButtonEditor(new JTextField());
        requestButtonEditor.setConferenceUI(this);
        jTable.getColumnModel().getColumn(5).setCellEditor(requestButtonEditor);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(500);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        resetData();
        jTable.getColumnModel().getColumn(4).setCellRenderer(new AdminButtonRenderer());
        jTable.getColumnModel().getColumn(4).setCellEditor(new AdminButtonEditor(new JTextField()));
        jTable.getColumnModel().getColumn(5).setCellRenderer(new RequestButtonRenderer());
        jTable.getColumnModel().getColumn(5).setCellEditor(new RequestButtonEditor(new JTextField()));
    }

    private void calculatePag() {
        int countRow = listConference.size();

        if (countRow % (Integer.valueOf(jComboBox1.getSelectedItem().toString())) == 0) {
            maxPag = countRow / Integer.valueOf(jComboBox1.getSelectedItem().toString());
        } else {
            maxPag = (int) (countRow / Integer.valueOf(jComboBox1.getSelectedItem().toString())) + 1;
        }

        if (maxPag == 0) {
            maxPag = 1;
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
        jPanel1 = new javax.swing.JPanel();
        jResetbtn = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jRequest = new javax.swing.JPanel();
        jOrganizedDateText1 = new javax.swing.JLabel();
        jNewRequest = new javax.swing.JComboBox<>();
        jStatus = new javax.swing.JPanel();
        jRegisteredDateText1 = new javax.swing.JLabel();
        jTakePlace = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jSearchPnl = new javax.swing.JPanel();
        jSearchbtn = new javax.swing.JLabel();
        jSearchText = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jAddNewConference = new javax.swing.JButton();
        jOrganizedDate = new javax.swing.JPanel();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        jOrganizedDateText = new javax.swing.JLabel();
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

        jPanel1.setBackground(new java.awt.Color(224, 224, 250));
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanel1.setLayout(new java.awt.BorderLayout());

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
        jPanel1.add(jResetbtn, java.awt.BorderLayout.NORTH);

        jComboBox1.setBackground(new java.awt.Color(224, 224, 250));
        jComboBox1.setEditable(true);
        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 0, 5));
        jComboBox1.setPreferredSize(new java.awt.Dimension(70, 50));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox1, java.awt.BorderLayout.CENTER);

        jOption.add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel2.setBackground(new java.awt.Color(224, 224, 250));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 50, 0, 50));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jRequest.setBackground(new java.awt.Color(224, 224, 250));
        jRequest.setEnabled(false);
        jRequest.setPreferredSize(new java.awt.Dimension(275, 50));
        jRequest.setLayout(new java.awt.BorderLayout());

        jOrganizedDateText1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jOrganizedDateText1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jOrganizedDateText1.setText("Request");
        jOrganizedDateText1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 10));
        jOrganizedDateText1.setPreferredSize(new java.awt.Dimension(100, 50));
        jRequest.add(jOrganizedDateText1, java.awt.BorderLayout.WEST);

        jNewRequest.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jNewRequest.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Non Request", "New Request" }));
        jNewRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNewRequestActionPerformed(evt);
            }
        });
        jRequest.add(jNewRequest, java.awt.BorderLayout.CENTER);

        jPanel2.add(jRequest, java.awt.BorderLayout.NORTH);

        jStatus.setBackground(new java.awt.Color(224, 224, 250));
        jStatus.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 5, 0));
        jStatus.setMinimumSize(new java.awt.Dimension(125, 27));
        jStatus.setPreferredSize(new java.awt.Dimension(325, 50));
        jStatus.setLayout(new java.awt.BorderLayout());

        jRegisteredDateText1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jRegisteredDateText1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jRegisteredDateText1.setText("Status");
        jRegisteredDateText1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 10));
        jRegisteredDateText1.setPreferredSize(new java.awt.Dimension(100, 50));
        jStatus.add(jRegisteredDateText1, java.awt.BorderLayout.WEST);

        jTakePlace.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTakePlace.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Took place", "Not take place", "IsDeleted" }));
        jTakePlace.setPreferredSize(new java.awt.Dimension(100, 23));
        jTakePlace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTakePlaceActionPerformed(evt);
            }
        });
        jStatus.add(jTakePlace, java.awt.BorderLayout.CENTER);

        jPanel2.add(jStatus, java.awt.BorderLayout.CENTER);

        jOption.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(224, 224, 250));
        jPanel3.setPreferredSize(new java.awt.Dimension(700, 60));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jSearchPnl.setBackground(new java.awt.Color(224, 224, 250));
        jSearchPnl.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 20, 5, 5));
        jSearchPnl.setPreferredSize(new java.awt.Dimension(250, 50));
        jSearchPnl.setLayout(new java.awt.BorderLayout());

        jSearchbtn.setBackground(new java.awt.Color(224, 224, 250));
        jSearchbtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jSearchbtn.setText("Name");
        jSearchbtn.setOpaque(true);
        jSearchbtn.setPreferredSize(new java.awt.Dimension(100, 13));
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
            public void focusGained(java.awt.event.FocusEvent evt) {
                jSearchTextFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jSearchTextFocusLost(evt);
            }
        });
        jSearchText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchTextActionPerformed(evt);
            }
        });
        jSearchText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jSearchTextKeyTyped(evt);
            }
        });
        jSearchPnl.add(jSearchText, java.awt.BorderLayout.CENTER);

        jPanel3.add(jSearchPnl, java.awt.BorderLayout.CENTER);

        jPanel4.setBackground(new java.awt.Color(224, 224, 250));
        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 20, 0, 5));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jAddNewConference.setBackground(new java.awt.Color(255, 255, 255));
        jAddNewConference.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jAddNewConference.setText("Add New Conference");
        jAddNewConference.setBorderPainted(false);
        jAddNewConference.setPreferredSize(new java.awt.Dimension(230, 40));
        jAddNewConference.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jAddNewConferenceMousePressed(evt);
            }
        });
        jAddNewConference.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddNewConferenceActionPerformed(evt);
            }
        });
        jPanel4.add(jAddNewConference, java.awt.BorderLayout.EAST);

        jOrganizedDate.setBackground(new java.awt.Color(224, 224, 250));
        jOrganizedDate.setPreferredSize(new java.awt.Dimension(350, 50));
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
        jOrganizedDateText.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jOrganizedDateText.setText("Organized Date");
        jOrganizedDateText.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 10));
        jOrganizedDateText.setPreferredSize(new java.awt.Dimension(150, 50));
        jOrganizedDate.add(jOrganizedDateText, java.awt.BorderLayout.WEST);

        jPanel4.add(jOrganizedDate, java.awt.BorderLayout.WEST);

        jPanel3.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jOption.add(jPanel3, java.awt.BorderLayout.EAST);

        jHeader.add(jOption, java.awt.BorderLayout.CENTER);

        jBrief.setFont(new java.awt.Font("Times New Roman", 1, 28)); // NOI18N
        jBrief.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jBrief.setText("Conference manager ");
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
        jPosition.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
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

        jScrollPane1.setBackground(new java.awt.Color(224, 224, 250));
        jScrollPane1.setOpaque(false);

        jTable.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        DefaultTableModel tm = new DefaultTableModel(new Object[0][], new String[]{"STT", "Conference Name", "Organized Date", "Status", "Detail", "New Request"}) {
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
        jTable.getTableHeader().setBackground(Color.red);
        jTable.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 16));

        jTable.setAutoCreateRowSorter(true);
        jTable.setModel(tm);
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

    public void resetData() {
        listConference = ConferenceBus.getAllConference();
        Collections.reverse(listConference);
        filter();
        DefaultTableModel tm = (DefaultTableModel) jTable.getModel();

        for (int i = tm.getRowCount() - 1; i >= 0; i--) {
            tm.removeRow(i);
        }

        RequestButtonEditor requestButtonEditor = new RequestButtonEditor(new JTextField());
        requestButtonEditor.setConferenceUI(this);
        jTable.getColumnModel().getColumn(5).setCellEditor(requestButtonEditor);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(500);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(100);

        for (int i = 0; i < listConference.size(); i++) {
            String status = "";
            Date nowDate = new Date();

            if (listConference.get(i).getStartTime().compareTo(nowDate) <= 0) {
                status = "Took place";
            } else {
                status = "Not take place";
            }

            if (listConference.get(i).getIsDelete() == 1) {
                status = "is Deleted";
            }
            tm.addRow(new Object[]{i + 1, listConference.get(i).getName(), listConference.get(i).getStartTime(), status, listConference.get(i), listConference.get(i)});
        }

    }
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

        jTakePlace.setSelectedIndex(0);
        jNewRequest.setSelectedIndex(0);
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

    private void jAddNewConferenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddNewConferenceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jAddNewConferenceActionPerformed

    private void jNewRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNewRequestActionPerformed
        // TODO add your handling code here:
        jPosition.setText("1");
        resetData();
    }//GEN-LAST:event_jNewRequestActionPerformed

    private void jAddNewConferenceMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jAddNewConferenceMousePressed
        // TODO add your handling code here:

        new NewConferenceDialog(MainScreen.getInstance(), true).setVisible(true);
    }//GEN-LAST:event_jAddNewConferenceMousePressed

    private void jTakePlaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTakePlaceActionPerformed
        // TODO add your handling code here:
        jPosition.setText("1");
        resetData();
    }//GEN-LAST:event_jTakePlaceActionPerformed

    private void jDateChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserPropertyChange
        // TODO add your handling code here:
        if ("date".equals(evt.getPropertyName())) {
            jPosition.setText("1");
            resetData();
        }
    }//GEN-LAST:event_jDateChooserPropertyChange

    private void jSearchTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchTextActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jSearchTextActionPerformed

    private void jSearchTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSearchTextKeyTyped
        // TODO add your handling code here:
        jPosition.setText("1");
        resetData();
    }//GEN-LAST:event_jSearchTextKeyTyped

    private void jSearchTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSearchTextFocusGained
        // TODO add your handling code here:
        if (jSearchText.getText().compareTo("Search conference name") == 0)
            jSearchText.setText("");
    }//GEN-LAST:event_jSearchTextFocusGained

    private void jSearchTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSearchTextFocusLost
        // TODO add your handling code here:
        if (jSearchText.getText().compareTo("") == 0)
            jSearchText.setText("Search conference name");
    }//GEN-LAST:event_jSearchTextFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAddNewConference;
    private javax.swing.JLabel jBrief;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jData;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel jDescriptionPag;
    private javax.swing.JLabel jFirstbtn;
    private javax.swing.JPanel jHeader;
    private javax.swing.JLabel jLastbtn;
    private javax.swing.JComboBox<String> jNewRequest;
    private javax.swing.JLabel jNextbtn;
    private javax.swing.JPanel jOption;
    private javax.swing.JPanel jOrganizedDate;
    private javax.swing.JLabel jOrganizedDateText;
    private javax.swing.JLabel jOrganizedDateText1;
    private javax.swing.JPanel jPagination;
    private javax.swing.JPanel jPaginationButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jPosition;
    private javax.swing.JLabel jPrebtn;
    private javax.swing.JLabel jRegisteredDateText1;
    private javax.swing.JPanel jRequest;
    private javax.swing.JLabel jResetbtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jSearchPnl;
    private javax.swing.JTextField jSearchText;
    private javax.swing.JLabel jSearchbtn;
    private javax.swing.JPanel jStatus;
    private javax.swing.JTable jTable;
    private javax.swing.JComboBox<String> jTakePlace;
    // End of variables declaration//GEN-END:variables

    public void setFilter(int indexPlace, int indexRequest, int indexLine, int positionPag, Date organizeDate, String searchString) {
        jTakePlace.setSelectedIndex(indexPlace);
        jNewRequest.setSelectedIndex(indexRequest);
        jComboBox1.setSelectedIndex(indexLine);
        jPosition.setText(String.valueOf(positionPag));
        jDateChooser.setDate(organizeDate);
        jSearchText.setText(searchString);
    }

    public ConferenceUI cloneS() {
        ConferenceUI conferenceUI = new ConferenceUI();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Date getDate = null;

        if (jDateChooser.getDate() != null) {
            try {
                getDate = formatter.parse(formatter.format(jDateChooser.getDate()));
            } catch (ParseException ex) {
            }
        }

        conferenceUI.setFilter(jTakePlace.getSelectedIndex(), jNewRequest.getSelectedIndex(), jComboBox1.getSelectedIndex(), Integer.valueOf(jPosition.getText()), getDate, jSearchText.getText());
        return conferenceUI;
    }
}
