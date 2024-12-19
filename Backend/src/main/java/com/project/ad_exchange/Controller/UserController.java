package com.project.ad_exchange.Controller;


import com.project.ad_exchange.Dto.UserDto;
import com.project.ad_exchange.Dto.UserUpdateDto;
import com.project.ad_exchange.Model.User;
import com.project.ad_exchange.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto) {
        User registeredUser = userService.registerUser(userDto);

        return ResponseEntity.ok(registeredUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        boolean isDeleted = userService.deleteUserById(id);
        if (isDeleted) {
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    //Updating User settings
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserUpdateDto userUpdateDto) {
        boolean isUpdated = userService.updateUserSettings(id, userUpdateDto);

        if (isUpdated) {
            return ResponseEntity.ok("User settings updated successfully");
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }


}

