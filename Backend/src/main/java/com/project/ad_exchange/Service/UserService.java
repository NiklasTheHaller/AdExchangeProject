package com.project.ad_exchange.Service;
import com.project.ad_exchange.Dto.LoginDto;
import com.project.ad_exchange.Dto.UserDto;
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
        String encodedPassword = passwordEncoder.encode(userDto.password());
        User user = new User(userDto.username(), userDto.email(), encodedPassword, userDto.accountType());
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
//    public String loginUser(LoginDto loginDto) {
//        try {
//            UserDetails userDetails;
//            String identifier;
//
//            // Check if the input is a username or email
//            if (loginDto.username() != null && !loginDto.username().isEmpty()) {
//                userDetails = userDetailsService.loadUserByUsername(loginDto.username());
//                identifier = loginDto.username();
//            } else if (loginDto.email() != null && !loginDto.email().isEmpty()) {
//                userDetails = userDetailsService.loadUserByEmail(loginDto.email());
//                identifier = loginDto.email();
//            } else {
//                throw new BadCredentialsException("Username or email must be provided");
//            }
//
//            // Authenticate the user
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            identifier, // either username or email
//                            loginDto.password()
//                    )
//            );
//
//            // Generate JWT token if authentication is successful
//            return jwtUtil.generateToken(userDetails.getUsername());
//        } catch (AuthenticationException ex) {
//            // Handle authentication failure
//            throw new BadCredentialsException("Invalid username/email or password", ex);
//        }
//    }

    public boolean deleteUserById(long id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public  List<User> getAllUsers(){
        return userRepository.findAll();

    }
}
