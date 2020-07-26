/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContentUI;

import Business.ConferenceBus;
import ComponentUI.ConferenceRendererCard;
import ComponentUI.ConferenceRendererList;
import POJO.Conference;
import POJO.User;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author ADMIN
 */
public class ListConference extends javax.swing.JPanel {

    /**
     * Creates new form ListConference
     */
    final static private Color deufault = new Color(224, 224, 250);
    final static private Color colorCliked = new Color(84, 3, 156);
    final static private Color colorMoved = new Color(153, 153, 255);
    final static private Color colorMoved_2 = new Color(220, 220, 255);

    private User user;
    private List<ConferenceRendererList> conferenceRendererLists;
    private List<ConferenceRendererCard> conferenceRendererCards;
    List<Conference> listConference;

    private int maxPag;
    private int minPag = 0;

    private void filter() {
        isDeletedFilter();
        organizedDateFilter();
        ConferenceNameFilter();

        paginationFilter();
    }
    
    private void isDeletedFilter(){
        for (int i = listConference.size() - 1; i >= 0; i--) {
            if (listConference.get(i).getIsDelete() == 1) {
                listConference.remove(i);
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

    private void paginationFilter() {
        calculatePag();
        List<Conference> temp = new ArrayList<>();
        int start = (Integer.valueOf(jPosition.getText()) - 1) * 6;
        int end = Integer.valueOf(jPosition.getText()) * 6 - 1;

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

    private void calculatePag() {
        int countRow = listConference.size();

        if (countRow % 6 == 0) {
            maxPag = countRow / Integer.valueOf(6);
        } else {
            maxPag = (int) (countRow / 6) + 1;
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

    public ListConference() {
        initComponents();
        resetData();
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
        jChoiceView = new javax.swing.JPanel();
        jListViewbtn = new javax.swing.JLabel();
        jCardViewbtn = new javax.swing.JLabel();
        jAdditionOption = new javax.swing.JPanel();
        jResetAndDate = new javax.swing.JPanel();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        jSpace = new javax.swing.JPanel();
        jReset = new javax.swing.JPanel();
        jResetbtn = new javax.swing.JLabel();
        jSearchPnl = new javax.swing.JPanel();
        jSearchbtn = new javax.swing.JLabel();
        jSearchText = new javax.swing.JTextField();
        jConferenceView = new javax.swing.JPanel();
        jListView = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListConferenceView = new javax.swing.JPanel();
        jCardView = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jCardConferenceView = new javax.swing.JPanel();
        jPaginationConference = new javax.swing.JPanel();
        jDescriptionPag = new javax.swing.JLabel();
        jPaginationButton = new javax.swing.JPanel();
        jFirstbtn = new javax.swing.JLabel();
        jPrebtn = new javax.swing.JLabel();
        jPosition = new javax.swing.JTextField();
        jNextbtn = new javax.swing.JLabel();
        jLastbtn = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jHeader.setBackground(new java.awt.Color(224, 224, 250));
        jHeader.setPreferredSize(new java.awt.Dimension(0, 50));
        jHeader.setLayout(new java.awt.BorderLayout());

        jChoiceView.setBackground(new java.awt.Color(224, 224, 250));
        jChoiceView.setPreferredSize(new java.awt.Dimension(300, 50));
        jChoiceView.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        jListViewbtn.setBackground(new java.awt.Color(84, 3, 156));
        jListViewbtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jListViewbtn.setForeground(new java.awt.Color(255, 255, 255));
        jListViewbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/List_White.png"))); // NOI18N
        jListViewbtn.setText("List view");
        jListViewbtn.setOpaque(true);
        jListViewbtn.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jListViewbtnMouseMoved(evt);
            }
        });
        jListViewbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jListViewbtnMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jListViewbtnMouseReleased(evt);
            }
        });
        jChoiceView.add(jListViewbtn);

        jCardViewbtn.setBackground(new java.awt.Color(255, 255, 255));
        jCardViewbtn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jCardViewbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Grid-View-black.png"))); // NOI18N
        jCardViewbtn.setText("Grid view");
        jCardViewbtn.setOpaque(true);
        jCardViewbtn.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jCardViewbtnMouseMoved(evt);
            }
        });
        jCardViewbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jCardViewbtnMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jCardViewbtnMouseReleased(evt);
            }
        });
        jChoiceView.add(jCardViewbtn);

        jHeader.add(jChoiceView, java.awt.BorderLayout.WEST);

        jAdditionOption.setBackground(new java.awt.Color(58, 1, 108));
        jAdditionOption.setLayout(new java.awt.BorderLayout());

        jResetAndDate.setBackground(new java.awt.Color(224, 224, 250));
        jResetAndDate.setPreferredSize(new java.awt.Dimension(400, 50));
        jResetAndDate.setLayout(new java.awt.BorderLayout());

        jDateChooser.setDateFormatString("dd/MM/yyyy");
        jDateChooser.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jDateChooser.setOpaque(false);
        jDateChooser.setPreferredSize(new java.awt.Dimension(150, 20));
        jDateChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserPropertyChange(evt);
            }
        });
        jResetAndDate.add(jDateChooser, java.awt.BorderLayout.CENTER);

        jSpace.setBackground(new java.awt.Color(224, 224, 250));
        jSpace.setOpaque(false);
        jSpace.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout jSpaceLayout = new javax.swing.GroupLayout(jSpace);
        jSpace.setLayout(jSpaceLayout);
        jSpaceLayout.setHorizontalGroup(
            jSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jSpaceLayout.setVerticalGroup(
            jSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jResetAndDate.add(jSpace, java.awt.BorderLayout.EAST);

        jReset.setOpaque(false);
        jReset.setPreferredSize(new java.awt.Dimension(200, 50));

        jResetbtn.setBackground(new java.awt.Color(224, 224, 250));
        jResetbtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jResetbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Available-Updates-icon.png"))); // NOI18N
        jResetbtn.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jResetbtn.setOpaque(true);
        jResetbtn.setPreferredSize(new java.awt.Dimension(50, 50));
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
        jReset.add(jResetbtn);

        jResetAndDate.add(jReset, java.awt.BorderLayout.WEST);

        jAdditionOption.add(jResetAndDate, java.awt.BorderLayout.WEST);

        jSearchPnl.setBackground(new java.awt.Color(224, 224, 250));
        jSearchPnl.setPreferredSize(new java.awt.Dimension(300, 50));
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
        jSearchText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jSearchTextFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jSearchTextFocusLost(evt);
            }
        });
        jSearchPnl.add(jSearchText, java.awt.BorderLayout.CENTER);

        jAdditionOption.add(jSearchPnl, java.awt.BorderLayout.CENTER);

        jHeader.add(jAdditionOption, java.awt.BorderLayout.CENTER);

        add(jHeader, java.awt.BorderLayout.NORTH);

        jConferenceView.setLayout(new java.awt.CardLayout());

        jListView.setBackground(new java.awt.Color(255, 255, 255));
        jListView.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jListView.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jListView.setInheritsPopupMenu(true);
        jListView.setLayout(new java.awt.BorderLayout(0, 10));

        jListConferenceView.setBackground(new java.awt.Color(255, 255, 255));
        jListConferenceView.setLayout(new java.awt.GridLayout(6, 1, 0, 10));
        jScrollPane3.setViewportView(jListConferenceView);

        jListView.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jConferenceView.add(jListView, "card3");

        jCardView.setBackground(new java.awt.Color(255, 255, 255));
        jCardView.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jCardView.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jCardView.setInheritsPopupMenu(true);
        jCardView.setLayout(new java.awt.BorderLayout());

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setBorder(null);
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jCardConferenceView.setBackground(new java.awt.Color(255, 255, 255));
        jCardConferenceView.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        jCardConferenceView.setMaximumSize(new java.awt.Dimension(1000, 32767));
        jCardConferenceView.setLayout(new java.awt.GridBagLayout());
        jScrollPane4.setViewportView(jCardConferenceView);

        jCardView.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        jConferenceView.add(jCardView, "card2");

        add(jConferenceView, java.awt.BorderLayout.CENTER);

        jPaginationConference.setBackground(new java.awt.Color(224, 224, 250));
        jPaginationConference.setPreferredSize(new java.awt.Dimension(1198, 50));
        jPaginationConference.setLayout(new java.awt.BorderLayout());

        jDescriptionPag.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jDescriptionPag.setText("Page 1 for 1-6/100");
        jDescriptionPag.setPreferredSize(new java.awt.Dimension(300, 13));
        jPaginationConference.add(jDescriptionPag, java.awt.BorderLayout.WEST);

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

        jPaginationConference.add(jPaginationButton, java.awt.BorderLayout.EAST);

        add(jPaginationConference, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void jListViewbtnMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListViewbtnMouseMoved
        // TODO add your handling code here:
        if (jListViewbtn.getBackground().equals(colorCliked) == false)
            jListViewbtn.setBackground(colorMoved_2);
    }//GEN-LAST:event_jListViewbtnMouseMoved

    private void jListViewbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListViewbtnMouseExited
        // TODO add your handling code here:
        if (jListViewbtn.getBackground().equals(colorCliked) == false)
            jListViewbtn.setBackground(Color.WHITE);
    }//GEN-LAST:event_jListViewbtnMouseExited

    private void jListViewbtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListViewbtnMouseReleased
        // TODO add your handling code here:
        jListViewbtn.setBackground(colorCliked);
        jListViewbtn.setForeground(Color.WHITE);
        jListViewbtn.setIcon(new ImageIcon(getClass().getResource("/Picture/List_White.png")));

        jCardViewbtn.setBackground(Color.WHITE);
        jCardViewbtn.setForeground(Color.BLACK);
        jCardViewbtn.setIcon(new ImageIcon(getClass().getResource("/Picture/Grid-View-black.png")));

        jConferenceView.removeAll();
        jConferenceView.add(jListView);
        jConferenceView.repaint();
        jConferenceView.revalidate();
    }//GEN-LAST:event_jListViewbtnMouseReleased

    private void jCardViewbtnMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCardViewbtnMouseMoved
        // TODO add your handling code here:
        if (jCardViewbtn.getBackground().equals(colorCliked) == false)
            jCardViewbtn.setBackground(colorMoved_2);
    }//GEN-LAST:event_jCardViewbtnMouseMoved

    private void jCardViewbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCardViewbtnMouseExited
        // TODO add your handling code here:
        if (jCardViewbtn.getBackground().equals(colorCliked) == false) {
            jCardViewbtn.setBackground(Color.WHITE);
        }

    }//GEN-LAST:event_jCardViewbtnMouseExited

    private void jCardViewbtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCardViewbtnMouseReleased
        // TODO add your handling code here:
        jCardViewbtn.setBackground(colorCliked);
        jCardViewbtn.setForeground(Color.WHITE);
        jCardViewbtn.setIcon(new ImageIcon(getClass().getResource("/Picture/Grid-View-white.png")));

        jListViewbtn.setBackground(Color.WHITE);
        jListViewbtn.setForeground(Color.BLACK);
        jListViewbtn.setIcon(new ImageIcon(getClass().getResource("/Picture/ListView.png")));

        jConferenceView.removeAll();
        jConferenceView.add(jCardView);
        jConferenceView.repaint();
        jConferenceView.revalidate();
    }//GEN-LAST:event_jCardViewbtnMouseReleased

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
        if (jSearchText.getText().compareTo("") == 0) {
            jSearchText.setText("Search conference name");
        }
        jPosition.setText("1");
        resetData();
    }//GEN-LAST:event_jSearchbtnMouseReleased

    private void jSearchTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSearchTextFocusLost
        // TODO add your handling code here:
        if (jSearchText.getText().compareTo("") == 0)
            jSearchText.setText("Search conference name");
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
        int currentPosition = Integer.valueOf(jPosition.getText());
        int oldValue = currentPosition;
        currentPosition = 1;

        if (oldValue != currentPosition) {
            jPosition.setText(String.valueOf(currentPosition));
            resetData();
        }
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
        int oldValue = currentPosition;
        currentPosition = currentPosition == 1 ? 1 : --currentPosition;

        if (oldValue != currentPosition) {
            jPosition.setText(String.valueOf(currentPosition));
            resetData();
        }

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
        int oldValue = currentPosition;
        currentPosition = currentPosition == maxPag ? maxPag : ++currentPosition;
        if (oldValue != currentPosition) {
            jPosition.setText(String.valueOf(currentPosition));
            resetData();
        }
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
        int currentPosition = Integer.valueOf(jPosition.getText());
        int oldValue = currentPosition;
        currentPosition = maxPag;

        if (oldValue != currentPosition) {
            jPosition.setText(String.valueOf(currentPosition));
            resetData();
        }
    }//GEN-LAST:event_jLastbtnMouseReleased

    private void jDateChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserPropertyChange
        // TODO add your handling code here:
        if ("date".equals(evt.getPropertyName())) {
            jPosition.setText("1");
            resetData();
        }
    }//GEN-LAST:event_jDateChooserPropertyChange

    private void jSearchTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSearchTextFocusGained
        // TODO add your handling code here:
        if (jSearchText.getText().compareTo("Search conference name") == 0)
            jSearchText.setText("");
    }//GEN-LAST:event_jSearchTextFocusGained

    public void resetData() {
        listConference = ConferenceBus.getAllConference();
        Collections.reverse(listConference);
        filter();
        conferenceRendererCards = new ArrayList<>();

        for (int i = 0; i < listConference.size(); i++) {
            conferenceRendererCards.add(new ConferenceRendererCard(listConference.get(i)));
        }

        jCardConferenceView.removeAll();
        jCardConferenceView.repaint();
        jCardConferenceView.revalidate();
        GridBagConstraints c = new GridBagConstraints();

        for (int i = 0; i < conferenceRendererCards.size(); i++) {
            c.fill = GridBagConstraints.NONE;
            c.gridx = i % 3;
            c.gridy = i / 3;
            c.insets = new Insets(10, 15, 10, 15);
            jCardConferenceView.add(conferenceRendererCards.get(i), c);
        }

        conferenceRendererLists = new ArrayList<>();

        for (int i = 0; i < listConference.size(); i++) {
            conferenceRendererLists.add(new ConferenceRendererList(listConference.get(i)));
        }

        jListConferenceView.removeAll();
        jListConferenceView.repaint();
        jListConferenceView.revalidate();
        for (int i = 0; i < conferenceRendererLists.size(); i++) {
            jListConferenceView.add(conferenceRendererLists.get(i));
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jAdditionOption;
    private javax.swing.JPanel jCardConferenceView;
    private javax.swing.JPanel jCardView;
    private javax.swing.JLabel jCardViewbtn;
    private javax.swing.JPanel jChoiceView;
    private javax.swing.JPanel jConferenceView;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel jDescriptionPag;
    private javax.swing.JLabel jFirstbtn;
    private javax.swing.JPanel jHeader;
    private javax.swing.JLabel jLastbtn;
    private javax.swing.JPanel jListConferenceView;
    private javax.swing.JPanel jListView;
    private javax.swing.JLabel jListViewbtn;
    private javax.swing.JLabel jNextbtn;
    private javax.swing.JPanel jPaginationButton;
    private javax.swing.JPanel jPaginationConference;
    private javax.swing.JTextField jPosition;
    private javax.swing.JLabel jPrebtn;
    private javax.swing.JPanel jReset;
    private javax.swing.JPanel jResetAndDate;
    private javax.swing.JLabel jResetbtn;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel jSearchPnl;
    private javax.swing.JTextField jSearchText;
    private javax.swing.JLabel jSearchbtn;
    private javax.swing.JPanel jSpace;
    // End of variables declaration//GEN-END:variables

    public void setUser(User user) {
        this.user = user;

        for (int i = 0; i < conferenceRendererLists.size(); i++) {
            conferenceRendererLists.get(i).setUser(user);
            conferenceRendererCards.get(i).setUser(user);
        }

    }
}
