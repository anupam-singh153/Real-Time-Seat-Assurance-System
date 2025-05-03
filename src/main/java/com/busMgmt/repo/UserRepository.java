package com.busMgmt.repo;

import com.busMgmt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.bus.id = :busId")
    boolean alreadyBusAssigned(Long busId);

    @Query("SELECT u FROM User u WHERE u.role = :role AND u.bus.id IS NULL AND u.role != 'ADMIN'")
    List<User> findAvailableDriver(String role);
}
