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
        
        boolean isExist = false;
        for(int i = 1 ;; i++)
        {
            isExist = false;
            for(int j = 0; j < list.size(); j++){
                if (list.get(j).getId().compareTo(String.format(format, i)) == 0) {
                    isExist = true;
                    break;
                }
            }
            
            if(isExist == false){
                place.setId(String.format(format, i));
                break;
            }
        }

        return PlaceDao.insertNewPlace(place);
    }
    
    public static boolean updatePlaceInformation(Place place){
        return PlaceDao.updatePlaceInformation(place);
    }
}
