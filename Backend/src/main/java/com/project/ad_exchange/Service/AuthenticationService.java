package com.project.ad_exchange.Service;

import com.project.ad_exchange.Model.User;
import com.project.ad_exchange.Service.Authentication.AuthenticationRequest;
import com.project.ad_exchange.Service.Authentication.AuthenticationResponse;
import com.project.ad_exchange.Repository.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class AuthenticationService {

    private final AuthenticationManager authManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final TokenService tokenService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final long accessTokenExpiration;
    private final long refreshTokenExpiration;

    public AuthenticationService(
            AuthenticationManager authManager,
            UserDetailsService userDetailsService,
            UserService userService,
            TokenService tokenService,
            RefreshTokenRepository refreshTokenRepository,
            @Value("${jwt.accessTokenExpiration}") long accessTokenExpiration,
            @Value("${jwt.refreshTokenExpiration}") long refreshTokenExpiration) {
        this.authManager = authManager;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.tokenService = tokenService;
        this.refreshTokenRepository = refreshTokenRepository;
        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshTokenExpiration = refreshTokenExpiration;
    }

    public AuthenticationResponse authentication(AuthenticationRequest authenticationRequest) {
        // Find the user by identifier (email or username)
        User foundUser = findUserByIdentifier(authenticationRequest.getIdentifier());

        // Authenticate user credentials
        authManager.authenticate(new UsernamePasswordAuthenticationToken(
                foundUser.getUsername(),
                authenticationRequest.getPassword()
        ));

        // Load user details and dynamically retrieve role
        UserDetails userDetails = userDetailsService.loadUserByUsername(foundUser.getUsername());
        String role = foundUser.getAccountType().name(); // Extract the role from AccountType

        // Create tokens with the role
        String accessToken = createAccessToken(userDetails, role);
        String refreshToken = createRefreshToken(userDetails, role);

        // Save refresh token
        refreshTokenRepository.save(refreshToken, userDetails);

        return new AuthenticationResponse(accessToken, refreshToken);
    }
    public AuthenticationResponse refreshTokens(String refreshToken) {
        // Extract username from the refresh token
        String username = tokenService.extractUsername(refreshToken);

        // Validate the refresh token
        if (refreshTokenRepository.findUserDetailsByToken(refreshToken) == null) {
            throw new AuthenticationServiceException("Invalid refresh token");
        }

        // Load user details and retrieve the role
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String role = tokenService.extractRole(refreshToken);

        // Generate a new access token
        String newAccessToken = createAccessToken(userDetails, role);

        // Generate a new refresh token
        String newRefreshToken = createRefreshToken(userDetails, role);

        // Save the new refresh token in the repository (and optionally delete the old one)
        refreshTokenRepository.save(newRefreshToken, userDetails);

        // Return both tokens in an AuthenticationResponse
        return new AuthenticationResponse(newAccessToken, newRefreshToken);
    }




    public boolean isTokenValid(String token) {
        try {
            Claims claims = tokenService.extractAllClaims(token);

            // Check expiration
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            // If any exception occurs (e.g., invalid signature, expired token), the token is invalid
            return false;
        }
    }


    public void logout(String refreshToken) {

        refreshTokenRepository.deleteByToken(refreshToken);
    }

    private String createAccessToken(UserDetails user, String role) {

        return tokenService.generateToken(
                user.getUsername(),            // The subject or identifier (can be username/email)
                role,                          // Role
                new Date(System.currentTimeMillis() + accessTokenExpiration)

        );
    }


    private String createRefreshToken(UserDetails user, String role) {
        return tokenService.generateToken(user.getUsername(), role,
                new Date(System.currentTimeMillis() + refreshTokenExpiration));
    }

    public User findUserByIdentifier(String identifier) {
        // Check if the identifier is an email or a username
        if (isEmail(identifier)) {
            return userService.findByEmail(identifier)
                    .orElseThrow(() -> new AuthenticationServiceException("User with email " + identifier + " not found"));
        } else {
            return userService.findByUsername(identifier)
                    .orElseThrow(() -> new AuthenticationServiceException("User with username " + identifier + " not found"));
        }
    }

    private boolean isEmail(String identifier) {
        // Regex to check for a valid email format
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return identifier.matches(emailRegex);
    }

}
