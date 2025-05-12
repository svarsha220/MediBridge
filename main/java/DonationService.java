package net.organ.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    // Save donation (Create)
    public Donation saveDonation(Donation donation) {
        return donationRepository.save(donation);
    }

    // Get all donations (Read)
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    // Get donations by user ID (NEW)
    public List<Donation> getDonationsByUserId(Long userId) {
        return donationRepository.findByUserId(userId);
    }

    // Get donation by ID (Read)
    public Donation getDonationById(Long id) {
        return donationRepository.findById(id).orElse(null);
    }

    // Update donation
    public Donation updateDonation(Long id, Donation updatedDonation) {
        Donation existingDonation = donationRepository.findById(id).orElse(null);

        if (existingDonation != null) {
            existingDonation.setDonorName(updatedDonation.getDonorName());
            existingDonation.setDonorAge(updatedDonation.getDonorAge());
            existingDonation.setOrganName(updatedDonation.getOrganName());
            existingDonation.setBloodGroup(updatedDonation.getBloodGroup());
            return donationRepository.save(existingDonation);
        }

        return null;
    }

    // Delete donation
    public void deleteDonation(Long id) {
        donationRepository.deleteById(id);
    }
}
