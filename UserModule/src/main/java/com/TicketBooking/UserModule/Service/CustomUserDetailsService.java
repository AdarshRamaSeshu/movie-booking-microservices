package com.TicketBooking.UserModule.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.TicketBooking.UserModule.Entity.User;
import com.TicketBooking.UserModule.ExceptionHandler.UserNotFoundException;
import com.TicketBooking.UserModule.Model.CustomUserDetails;
import com.TicketBooking.UserModule.Repository.UserRepository;



@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) {
       User user= userRepo.findByEmail(email).orElseThrow(() -> new UserNotFoundException ("User not Found"));
                

       return  new CustomUserDetails(user); 
    
}

}
