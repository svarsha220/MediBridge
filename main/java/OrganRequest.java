package net.organ.springboot;

import jakarta.persistence.*;

@Entity
public class OrganRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String organName;
    private String requesterName;
    private String bloodGroup;
    private int requesterAge;
    private Long userId; // Link to User

    public OrganRequest() {
        // default constructor
    }

    public OrganRequest(String organName, String requesterName, String bloodGroup, int requesterAge, Long userId) {
        this.organName = organName;
        this.requesterName = requesterName;
        this.bloodGroup = bloodGroup;
        this.requesterAge = requesterAge;
        this.userId = userId;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getRequesterAge() {
        return requesterAge;
    }

    public void setRequesterAge(int requesterAge) {
        this.requesterAge = requesterAge;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
