package net.organ.springboot;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByUserId(Long userId);
}

