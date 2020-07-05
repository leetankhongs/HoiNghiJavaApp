/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminUI;

import UserUI.*;
import POJO.Conference;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author ADMIN
 */
public class RequestButtonRenderer extends JButton implements TableCellRenderer{

    public RequestButtonRenderer(){
        setOpaque(true);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object object, boolean isSelected, boolean hasFocus, int row, int column) {
        Conference conference = (Conference)object;
        setText("New Request");            
        return this;
    }
    
}

