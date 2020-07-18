package POJO;
// Generated Jul 9, 2020, 5:02:16 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Conference generated by hbm2java
 */
@Entity
@Table(name="conference"
    ,catalog="conferencemanagement"
)
public class Conference  implements java.io.Serializable, Comparable<Conference> {


     private String id;
     private Place place;
     private String name;
     private String briefDescription;
     private String detailDescription;
     private String image;
     private Date startTime;
     private Date endTime;
     private Integer participants;
     private Byte isDelete;
     private Set<UserConference> userConferences = new HashSet<UserConference>(0);

    public Conference() {
    }

	
    public Conference(String id) {
        this.id = id;
    }
    public Conference(Place place, String name, String briefDescription, String detailDescription, String image, Date startTime, Date endTime, Integer participants) {
       this.place = place;
       this.name = name;
       this.briefDescription = briefDescription;
       this.detailDescription = detailDescription;
       this.image = image;
       this.startTime = startTime;
       this.endTime = endTime;
       this.participants = participants;
       this.isDelete = 0;
    }
   
     @Id 

    
    @Column(name="ID", unique=true, nullable=false, length=4)
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDPlace")
    public Place getPlace() {
        return this.place;
    }
    
    public void setPlace(Place place) {
        this.place = place;
    }

    
    @Column(name="Name", length=64)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="BriefDescription", length=256)
    public String getBriefDescription() {
        return this.briefDescription;
    }
    
    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    
    @Column(name="DetailDescription", length=8192)
    public String getDetailDescription() {
        return this.detailDescription;
    }
    
    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    
    @Column(name="Image", length=256)
    public String getImage() {
        return this.image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="StartTime", length=19)
    public Date getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="EndTime", length=19)
    public Date getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    
    @Column(name="Participants")
    public Integer getParticipants() {
        return this.participants;
    }
    
    public void setParticipants(Integer participants) {
        this.participants = participants;
    }

    
    @Column(name="IsDelete")
    public Byte getIsDelete() {
        return this.isDelete;
    }
    
    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="conference")
    public Set<UserConference> getUserConferences() {
        return this.userConferences;
    }
    
    public void setUserConferences(Set<UserConference> userConferences) {
        this.userConferences = userConferences;
    }

    @Override
    public int compareTo(Conference o) {
        return getName().compareTo(o.getName());
    }




}


