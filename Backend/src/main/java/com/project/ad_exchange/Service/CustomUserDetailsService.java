package com.project.ad_exchange.Service;

import com.project.ad_exchange.Model.User;
import com.project.ad_exchange.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        // Try finding user by username, then by email if username lookup fails
        User user = userRepository.findByUsername(identifier)
                .orElseGet(() -> userRepository.findByEmail(identifier)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found with identifier: " + identifier)));

        GrantedAuthority authority = new SimpleGrantedAuthority(user.getAccountType().name());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),  // Use username in the UserDetails principal
                user.getPassword(),
                List.of(authority)
        );
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // Fetch the user from the repository
//        User user = userRepository.findByUsername(username).orElseThrow(() ->
//                new UsernameNotFoundException("User not found with username: " + username));
//
//        // Create a SimpleGrantedAuthority based on the user's account type
//        GrantedAuthority authority = new SimpleGrantedAuthority(user.getAccountType().name());
//
//        // Return a new UserDetails object, using a list to contain the single authority
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                List.of(authority)
//        );
//    }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found with email: " + email));
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getAccountType().name());
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(authority)
        );
    }

}

