package com.TicketBooking.UserModule.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.TicketBooking.UserModule.DTO.SignupRequestDTO;
import com.TicketBooking.UserModule.DTO.SignupResponseDTO;
import com.TicketBooking.UserModule.Entity.User;
import com.TicketBooking.UserModule.Enum.Role;
import com.TicketBooking.UserModule.ExceptionHandler.BadCredentialsException;
import com.TicketBooking.UserModule.ExceptionHandler.UserAlreadyExisitException;
import com.TicketBooking.UserModule.ExceptionHandler.UserNotFoundException;
import com.TicketBooking.UserModule.Repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepo;

    private final PasswordEncoder  passwordEncoder;

    UserService(UserRepository userRepository, PasswordEncoder  passwordEncoder){
        this.userRepo=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public SignupResponseDTO signUp(SignupRequestDTO signUpRequest){

        String email=signUpRequest.getEmail();

        Optional<User> exisitingUser= userRepo.findByEmail(email);

        if(exisitingUser.isPresent()){
            throw new UserAlreadyExisitException("User Already Signedup.");
        }

        User user=new User();
        String hashed_password=passwordEncoder.encode(signUpRequest.getPassword());
        user.setName(signUpRequest.getName());
        user.setContact(signUpRequest.getContact());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(hashed_password);
        user.setRole(Role.USER);
        user.setCreatedAt(LocalDateTime.now());
        user.setModifiedAt(LocalDateTime.now());

        User savedUser= userRepo.save(user);

        return signupResponse(savedUser);
    }

    private SignupResponseDTO signupResponse(User savedUser) {
        SignupResponseDTO signupResponse= new SignupResponseDTO();
        signupResponse.setEmail(savedUser.getEmail());
        signupResponse.setName(savedUser.getName());

        return signupResponse;
    }

    public String updatePassword(String email, String oldPasword, String updatedPassword) {

         User user= userRepo.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not Found"));

         String currentPassword=user.getPassword();

        if(passwordEncoder.matches(oldPasword,currentPassword)){
            user.setPassword(passwordEncoder.encode(updatedPassword));
            user.setModifiedAt(LocalDateTime.now());
            userRepo.save(user);
        }
        else{
            throw new BadCredentialsException("Old password is incorrect");
        }
        return "Password updated successfully";
    }

}
