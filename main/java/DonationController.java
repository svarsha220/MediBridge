package net.organ.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donations")
public class DonationController {

    @Autowired
    private DonationService donationService;

    // CREATE
    @PostMapping
    public Donation createDonation(@RequestBody Donation donation) {
        return donationService.saveDonation(donation);
    }

    // READ ALL
    @GetMapping("/getAll")
    public List<Donation> getAllDonations() {
        return donationService.getAllDonations();
    }

    // READ BY USER
    @GetMapping("/getByUser/{userId}")
    public List<Donation> getDonationsByUserId(@PathVariable Long userId) {
        return donationService.getDonationsByUserId(userId);
    }

    // READ ONE
    @GetMapping("/{id}")
    public Donation getDonationById(@PathVariable Long id) {
        return donationService.getDonationById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Donation updateDonation(@PathVariable Long id, @RequestBody Donation donation) {
        return donationService.updateDonation(id, donation);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteDonation(@PathVariable Long id) {
        donationService.deleteDonation(id);
    }
}
