/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author ADMIN
 */
public class ButtonRenderer extends JButton implements TableCellRenderer{

    public ButtonRenderer(){
        setOpaque(true);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object object, boolean isSelected, boolean hasFocus, int row, int column) {
        Conference11 conference = (Conference11)object;
        setText(conference.getNameConference());            
        return this;
    }
    
}

