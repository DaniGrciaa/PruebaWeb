package com.demo.springmio.Controllers;


import com.demo.springmio.DTOs.UserResponseDTO;
import com.demo.springmio.Model.User;
import com.demo.springmio.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // ======================================================
    //  GET /api/users
    //  Devuelve todos los usuarios
    // ======================================================
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // ======================================================
    //  GET /api/users/{id}
    //  Devuelve un usuario por su ID
    // ======================================================
    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // ======================================================
    //  POST /api/users
    //  Crea un nuevo usuario
    // ======================================================
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // ======================================================
    //  PUT /api/users/{id}
    //  Actualiza los datos de un usuario existente
    // ======================================================
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userData) {
        return userService.updateUser(id, userData);
    }

    // ======================================================
    //  DELETE /api/users/{id}
    //  Elimina un usuario
    // ======================================================
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
