package com.TicketBooking.UserModule.Service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.TicketBooking.UserModule.DTO.AdminRequestDTO;
import com.TicketBooking.UserModule.DTO.AdminResponseDTO;
import com.TicketBooking.UserModule.Entity.User;
import com.TicketBooking.UserModule.Enum.Role;
import com.TicketBooking.UserModule.Repository.UserRepository;

@Service
public class AdminService {

    private final UserRepository userRepo;

    private final PasswordEncoder paswordEncoder;

    AdminService(UserRepository userRepo, PasswordEncoder paswordEncoder){
        this.userRepo=userRepo; //Injecting UserRepository object thorugh constructor injection
        this.paswordEncoder=paswordEncoder;
    }

    //method to addUser and return UserResponseDTO object
    public AdminResponseDTO  addAdmin(AdminRequestDTO adminRequest ) {

        User user=new User();
        String hashed_password= paswordEncoder.encode(adminRequest.getPassword());
        user.setName(adminRequest.getName());
        user.setContact(adminRequest.getContact());
        user.setEmail(adminRequest.getEmail());
        user.setPassword(hashed_password);
        user.setRole(Role.ADMIN);
        user.setCreatedAt(LocalDateTime.now());
        user.setModifiedAt(LocalDateTime.now());

        User savedUser= userRepo.save(user);

        return responseDTO_Convert(savedUser);

    }

    //method to convert User Object to Response DTO
    private AdminResponseDTO responseDTO_Convert(User savedUser) {
        AdminResponseDTO response= new AdminResponseDTO();
        response.setId(savedUser.getId());
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());
        response.setRole(savedUser.getRole());
        response.setCreatedAt(savedUser.getCreatedAt());
        response.setModifiedAt(savedUser.getModifiedAt());

        return response;

    }

    //method to get List of Users
    public List<AdminResponseDTO> getAllUserDetails() {
        List<User> userDetails= userRepo.findAll();
        List<AdminResponseDTO> result= userDetails.stream()
                    .map(user -> responseDTO_Convert(user))
                    .toList();
        return result;
    }

    //method to delete user by Id
    public void deleteUserById(Long id) {
        if(!userRepo.existsById(id)){
            throw new UsernameNotFoundException("User Not Found" + "id");
        }
        userRepo.deleteById(id);
    }

}
