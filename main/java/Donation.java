package net.organ.springboot;

import jakarta.persistence.*;

@Entity
@Table(name = "donation")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String organName;
    private String donorName;
    private String bloodGroup;
    private int donorAge;

    private Long userId; // 👈 New field added to track which user made the donation

    public Donation() {
    }

    public Donation(String organName, String donorName, String bloodGroup, int donorAge, Long userId) {
        this.organName = organName;
        this.donorName = donorName;
        this.bloodGroup = bloodGroup;
        this.donorAge = donorAge;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getDonorAge() {
        return donorAge;
    }

    public void setDonorAge(int donorAge) {
        this.donorAge = donorAge;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
