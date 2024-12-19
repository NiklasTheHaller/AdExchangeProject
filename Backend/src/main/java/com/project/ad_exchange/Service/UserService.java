package com.project.ad_exchange.Service;


import com.project.ad_exchange.Dto.UserDto;
import com.project.ad_exchange.Dto.UserUpdateDto;

import com.project.ad_exchange.Model.User;
import com.project.ad_exchange.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;





    public User registerUser(UserDto userDto) {
        String encodedPassword = passwordEncoder.encode(userDto.password());
        User user = new User(userDto.username(), userDto.email(), encodedPassword, userDto.accountType());
        return userRepository.save(user);
    }



    public boolean deleteUserById(long id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //Update User settings if they changed
    public boolean updateUserSettings(Long id, UserUpdateDto userUpdateDto) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Mail
            if (userUpdateDto.email() != null && !userUpdateDto.email().equals(user.getEmail())) {
                user.setEmail(userUpdateDto.email());
            }

            // Username
            if (userUpdateDto.username() != null && !userUpdateDto.username().equals(user.getUsername())) {
                user.setUsername(userUpdateDto.username());
            }

            // Password
            if (userUpdateDto.password() != null && !passwordEncoder.matches(userUpdateDto.password(), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(userUpdateDto.password()));
            }

            userRepository.save(user); // Save only if changes were made
            return true;
        } else {
            return false; // User not found
        }
    }

    public  List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
