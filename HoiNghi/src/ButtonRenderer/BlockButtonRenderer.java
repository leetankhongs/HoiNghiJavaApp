/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ButtonRenderer;

import POJO.User;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author ADMIN
 */
public class BlockButtonRenderer extends JButton implements TableCellRenderer{
    public BlockButtonRenderer(){
        setOpaque(true);
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object object, boolean isSelected, boolean hasFocus, int row, int column) {
        User user = (User)object;
        
        if(user.getIsDelete() == 1)
            setText("UNLOCK");
        else
            setText("BLOCK");
        
        return this;
    }
}
