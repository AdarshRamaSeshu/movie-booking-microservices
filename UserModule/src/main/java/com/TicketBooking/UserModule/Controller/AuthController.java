package com.TicketBooking.UserModule.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TicketBooking.UserModule.Configurations.JwtUtil;
import com.TicketBooking.UserModule.DTO.LoginRequestDTO;
import com.TicketBooking.UserModule.DTO.LoginResponseDTO;
import com.TicketBooking.UserModule.DTO.SignupRequestDTO;
import com.TicketBooking.UserModule.DTO.SignupResponseDTO;
import com.TicketBooking.UserModule.Service.CustomUserDetailsService;
import com.TicketBooking.UserModule.Service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private  AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignupRequestDTO signupRequest){

        SignupResponseDTO signup_response=userService.signUp(signupRequest);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(signup_response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest){

      @SuppressWarnings("unused")
      Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));

      UserDetails userDetails= customUserDetailsService.loadUserByUsername(loginRequest.getEmail());
      
      String token= jwtUtil.generateToken(userDetails.getUsername(), userDetails.getAuthorities());
      
      return ResponseEntity.ok(new LoginResponseDTO(token));
        
    } 

    

}
