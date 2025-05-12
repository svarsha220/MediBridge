package net.organ.springboot;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrganRequestRepository extends JpaRepository<OrganRequest, Long> {
    List<OrganRequest> findByUserId(Long userId);
}
