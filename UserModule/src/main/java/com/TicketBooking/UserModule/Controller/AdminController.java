package com.TicketBooking.UserModule.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TicketBooking.UserModule.DTO.AdminRequestDTO;
import com.TicketBooking.UserModule.DTO.AdminResponseDTO;
import com.TicketBooking.UserModule.Service.AdminService;

@RestController
@RequestMapping("/apiv1/admin")
public class AdminController {

    private AdminService adminService;

    AdminController( AdminService adminService){
        this.adminService= adminService;
    }
    
    @PostMapping("/addAdmins")
    public ResponseEntity<?> addUser(@RequestBody AdminRequestDTO userdto){ 

        AdminResponseDTO responsebody= adminService.addAdmin(userdto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responsebody);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<AdminResponseDTO>> getAllUsers(){

        List<AdminResponseDTO> user_Details=adminService.getAllUserDetails();

        return ResponseEntity.status(HttpStatus.OK).body(user_Details);
    }
    
    @DeleteMapping("/deleteUsers/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
         adminService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body("User Deleted Sucessfully");
    }
}
