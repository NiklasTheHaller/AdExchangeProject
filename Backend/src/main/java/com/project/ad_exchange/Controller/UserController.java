package com.project.ad_exchange.Controller;

import com.project.ad_exchange.Dto.JwtDto;
import com.project.ad_exchange.Dto.LoginDto;
import com.project.ad_exchange.Dto.UserDto;
import com.project.ad_exchange.Dto.UserUpdateDto;
import com.project.ad_exchange.Model.User;
import com.project.ad_exchange.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        try {
            User user = userService.registerUser(userDto);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
        String token = userService.loginUser(loginDto);
        if (token != null) {
            return ResponseEntity.ok(new JwtDto(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login credentials");
        }
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

