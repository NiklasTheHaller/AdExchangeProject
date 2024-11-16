package com.project.ad_exchange.Service;

import com.project.ad_exchange.Dto.LoginDto;
import com.project.ad_exchange.Dto.UserDto;
import com.project.ad_exchange.Dto.UserUpdateDto;

import com.project.ad_exchange.Model.User;
import com.project.ad_exchange.Repository.UserRepository;
import com.project.ad_exchange.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;



    public User registerUser(UserDto userDto) {
        // Check if email or username is already taken
        if (userRepository.existsByUsername(userDto.username())) {
            throw new IllegalArgumentException("Username is already taken.");
        }
        if (userRepository.existsByEmail(userDto.email())) {
            throw new IllegalArgumentException("Email is already registered.");
        }

        // Encode the password
        String encodedPassword = passwordEncoder.encode(userDto.password());

        // Create and save the new user
        User user = new User();
        user.setUsername(userDto.username());
        user.setEmail(userDto.email());
        user.setPassword(encodedPassword);
        user.setAccountType(userDto.accountType()); // Assuming you have an accountType field

        return userRepository.save(user);
    }

    public String loginUser(LoginDto loginDto) {
        try {
            String identifier = (loginDto.username() != null && !loginDto.username().isEmpty())
                    ? loginDto.username() : loginDto.email();

            // Authenticate using AuthenticationManager with email or username
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            identifier, // email or username
                            loginDto.password()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Generate JWT token if authentication is successful
            return jwtUtil.generateToken(userDetails.getUsername());
        } catch (AuthenticationException ex) {
            // Handle authentication failure
            throw new BadCredentialsException("Invalid username/email or password", ex);
        }
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
}
