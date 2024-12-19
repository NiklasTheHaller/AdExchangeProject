package com.project.ad_exchange.Controller;

import com.project.ad_exchange.Service.Authentication.AuthenticationRequest;
import com.project.ad_exchange.Service.Authentication.AuthenticationResponse;
import com.project.ad_exchange.Service.Authentication.RefreshTokenRequest;
import com.project.ad_exchange.Service.AuthenticationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authRequest) {
        return authenticationService.authentication(authRequest);
    }

    @PostMapping("/refresh")
    public AuthenticationResponse refreshTokens(@RequestBody RefreshTokenRequest refreshToken) {
        return authenticationService.refreshTokens(refreshToken.getToken());
    }

    @GetMapping("/check")
    public Boolean checkTokenValidity(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "").trim();
        return authenticationService.isTokenValid(token);
    }


    @PostMapping("/logout")
    public void logout(@RequestBody LogoutRequest request) {
        authenticationService.logout(request.getToken());
    }

    public static class LogoutRequest {
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}

