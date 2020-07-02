package POJO;
// Generated Jul 2, 2020, 12:08:59 AM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * UserConference generated by hbm2java
 */
public class UserConference  implements java.io.Serializable {


     private UserConferenceId id;
     private Conference conference;
     private User user;
     private Date registationTime;

    public UserConference() {
    }

	
    public UserConference(UserConferenceId id, Conference conference, User user) {
        this.id = id;
        this.conference = conference;
        this.user = user;
    }
    public UserConference(UserConferenceId id, Conference conference, User user, Date registationTime) {
       this.id = id;
       this.conference = conference;
       this.user = user;
       this.registationTime = registationTime;
    }
   
    public UserConferenceId getId() {
        return this.id;
    }
    
    public void setId(UserConferenceId id) {
        this.id = id;
    }
    public Conference getConference() {
        return this.conference;
    }
    
    public void setConference(Conference conference) {
        this.conference = conference;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public Date getRegistationTime() {
        return this.registationTime;
    }
    
    public void setRegistationTime(Date registationTime) {
        this.registationTime = registationTime;
    }




}


