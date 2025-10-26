package com.demo.springmio.Services;



import com.demo.springmio.DTOs.UserResponseDTO;
import com.demo.springmio.Model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    UserResponseDTO getUserById(Long id);

    User createUser(User user);

    User updateUser(Long id, User userData);

    void deleteUser(Long id);
}
