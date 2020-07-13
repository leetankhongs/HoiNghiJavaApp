/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminUI;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import POJO.Conference;
import POJO.Place;
import java.awt.Color;

/**
 *
 * @author ADMIN
 */
public class ImageTextRenderer extends JLabel implements ListCellRenderer {

    public ImageTextRenderer() {
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value != null) {
            Place place = (Place) (value);
            setText(place.getName() + " | " + place.getAddress());
            return this;
        }
        else
            return null;
    }

}