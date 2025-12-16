package com.TicketBooking.UserModule.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TicketBooking.UserModule.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

 
    boolean existsById(Long id);
    
    void deleteById(Long id);

    Optional<User> findByEmail(String email);

}
