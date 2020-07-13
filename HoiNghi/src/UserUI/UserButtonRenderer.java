/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserUI;

import POJO.Conference;
import POJO.UserConference;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author ADMIN
 */
public class UserButtonRenderer extends JButton implements TableCellRenderer{

    public UserButtonRenderer(){
        setOpaque(true);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object object, boolean isSelected, boolean hasFocus, int row, int column) {
        UserConference userConference = (UserConference)object;
        setText("Detail");            
        return this;
    }
    
}

