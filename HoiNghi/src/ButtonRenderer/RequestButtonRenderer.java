/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ButtonRenderer;

import Business.UserConferenceBus;
import POJO.Conference;
import POJO.UserConference;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
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
        List<UserConference> list = UserConferenceBus.getNewRequests(conference);
        
        String bonus ="";
        if(list.size() != 0)
        {
            bonus = "(" +list.size() + ")";
        }
        
        setText("New Request" + bonus);            
        return this;
    }
    
}

