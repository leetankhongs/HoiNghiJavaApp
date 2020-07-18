/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import DAO.PlaceDao;
import POJO.Place;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class PlaceBus {

    public static List<Place> getAllPlace() {
        return PlaceDao.getAllPlace();
    }

    public static Place getPlaceInformation(String idPlace) {
        return PlaceDao.getPlaceInformation(idPlace);
    }

    public static String insertNewPlace(Place place) {
        List<Place> list = PlaceDao.getAllPlace();
        String format = "%1$04d";
        
        for(int i = 1 ; i < list.size() + 1; i++)
            if(list.get(i - 1).getId().compareTo(String.format(format, i)) != 0)
            {
                place.setId(String.format(format, i));
                break;
            }
        
        return PlaceDao.insertNewPlace(place);
    }
    
    public static boolean updatePlaceInformation(Place place){
        return PlaceDao.updatePlaceInformation(place);
    }
}
