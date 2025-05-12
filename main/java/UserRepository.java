package net.organ.springboot;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface UserRepository extends JpaRepository<User, Long> {

        Optional<User> findByUsername(String username);
        //Optional<User> findByUsernameAndPassword(String username, String password);

}
