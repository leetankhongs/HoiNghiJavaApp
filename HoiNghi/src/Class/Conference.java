/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Conference {
    private String nameConference;
    private String briefDescription;

    public String getNameConference() {
        return nameConference;
    }

    public void setNameConference(String nameConference) {
        this.nameConference = nameConference;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public Conference(String nameConference, String briefDescription) {
        this.nameConference = nameConference;
        this.briefDescription = briefDescription;
    }
   
}
