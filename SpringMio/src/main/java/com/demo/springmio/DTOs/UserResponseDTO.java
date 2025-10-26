package com.demo.springmio.DTOs;


import com.demo.springmio.Model.User;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserResponseDTO {

    public UserResponseDTO(User user){
        username = user.getUsername();
        email = user.getEmail();
        role = user.getRole();
    }
    private String username;    
    private String email;
    private String role;
}