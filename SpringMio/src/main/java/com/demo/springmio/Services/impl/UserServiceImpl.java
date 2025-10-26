package com.demo.springmio.Services.impl;

import com.demo.springmio.DTOs.UserResponseDTO;
import com.demo.springmio.Exceptions.NotFoundException;
import com.demo.springmio.Model.User;
import com.demo.springmio.Services.UserService;
import com.demo.springmio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user =  userRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("User with id " + id + " not found"));

        return new UserResponseDTO(user);
    }

    @Override
    public User createUser(User user) {
        // aquí podrías validar duplicados de username/email, hash de password, etc.
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User userData) {
        User existing = userRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("User with id " + id + " not found"));

        // actualizamos solo campos que queremos permitir
        existing.setUsername(userData.getUsername());
        existing.setEmail(userData.getEmail());
        existing.setPassword(userData.getPassword());
        existing.setRole(userData.getRole());

        return userRepository.save(existing);
    }

    @Override
    public void deleteUser(Long id) {
        User existing = userRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("User with id " + id + " not found"));

        userRepository.delete(existing);
    }
}
