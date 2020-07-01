/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author ADMIN
 */
public class ImageTextRenderer extends JLabel implements ListCellRenderer{

    public ImageTextRenderer() {
    }


    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        ImageText imageText = (ImageText)(value);
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(imageText.getIcon()));
        Image image = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(image));
        setText(imageText.getText());
        setBackground(new Color(58,1,108));
        setForeground(Color.WHITE);
        return this;
    }
    
}
