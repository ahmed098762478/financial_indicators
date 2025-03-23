package com.gov.cmr.transparisation_module.controller;

import com.gov.cmr.transparisation_module.model.DTO.JwtResponse;
import com.gov.cmr.transparisation_module.model.DTO.LoginRequest;
import com.gov.cmr.transparisation_module.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Create an auth token based on email & password
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword()
                );

        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(authToken);

        // Generate JWT
        String jwt = jwtTokenProvider.generateToken((UserDetails) authentication.getPrincipal());

        // Return in JSON
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
}
