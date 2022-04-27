package com.example.gestionquestion.Services;

import com.example.gestionquestion.Exceptions.LoginBadCredentials;
import com.example.gestionquestion.SecurityConfig.UserDetailsServiceImpl;
import com.example.gestionquestion.SecurityConfig.jwtUtil;
import com.example.gestionquestion.dto.LoginResponse;
import com.example.gestionquestion.entities.LoginRequestDTO;
import com.example.gestionquestion.entities.User;
import com.example.gestionquestion.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final com.example.gestionquestion.SecurityConfig.jwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserRepository userRepository;

    public ResponseEntity<?> login(LoginRequestDTO loginRequestDTO){
        Authentication authentication = null;
        try{
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
        } catch (BadCredentialsException e){
            return new ResponseEntity<>(new LoginBadCredentials("Username or password incorrect"), HttpStatus.BAD_REQUEST);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok(LoginResponseBuilder(loginRequestDTO.getUsername()));
    }

    private UserDetails getUserDetails(String username){
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return userDetails;
    }

    private LoginResponse LoginResponseBuilder(String username){
        String token = jwtUtil.generateToken(this.getUserDetails(username));
        LoginResponse loginResponse = LoginResponse.builder()
                .accesToken(token)
                .username(username)
                .accesTokenExpirationDate(this.jwtUtil.extractExpiration(token))
                .refreshTokenExpirationDate(new Date(System.currentTimeMillis() +this.jwtUtil.refreshTokenDuration()))
                .refreshToken(UUID.randomUUID().toString())
                .build();
        return loginResponse;
    }



}

