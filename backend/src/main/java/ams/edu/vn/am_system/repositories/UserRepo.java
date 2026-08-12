package ams.edu.vn.am_system.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import ams.edu.vn.am_system.entities.User;

public interface UserRepo extends JpaRepository<User, String> {
    Optional<User> findByUserName(String userName);
}
