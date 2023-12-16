package org.learnhub.backend.controller;

import org.learnhub.backend.exceptions.UserAlreadyExistAuthenticationException;
import org.learnhub.backend.misc.payloads.GenericFailureMessageResponse;
import org.learnhub.backend.misc.payloads.ResponsePayload;
import org.learnhub.backend.database.entity.UserAccount;
import org.learnhub.backend.api.payload.response.AuthenticationResponse;
import org.learnhub.backend.database.repostitory.UserCredentialsRepository;
import org.learnhub.backend.service.UserService;
import org.learnhub.backend.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    UserCredentialsRepository credentialsRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTUtils jwtUtils;
    
    UserService userService;

    @GetMapping("/login")
    public ResponseEntity<ResponsePayload> authenticate(@RequestParam String email, @RequestParam String password){

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        }
        catch (BadCredentialsException e){
            GenericFailureMessageResponse authenticationResponse = new GenericFailureMessageResponse("Wrong credentials!");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(authenticationResponse);
        }

        if(!authentication.isAuthenticated()){
           GenericFailureMessageResponse authenticationResponse = new GenericFailureMessageResponse("Something went wrong during login!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(authenticationResponse);
        }

        String role = authentication.getAuthorities().toArray()[0].toString();

        AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwtUtils.generateToken(email, role));
        return ResponseEntity.status(HttpStatus.OK).body(authenticationResponse);
    }

    @GetMapping("/register")
    public ResponseEntity<ResponsePayload> register(@RequestParam String email, @RequestParam String password){

        try {
            UserAccount account = userService.createUser(email, password, "USER");
        }
        catch (UserAlreadyExistAuthenticationException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericFailureMessageResponse("Account with this email already exists!"));
        }

        //Try to log in to check the account
        boolean isAuthenticated;
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            isAuthenticated = authentication.isAuthenticated();
        }
        catch (AuthenticationException e){
            isAuthenticated = false;
        }
        if(!isAuthenticated){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwtUtils.generateToken(email, "USER"));

        return ResponseEntity.status(HttpStatus.OK).body(authenticationResponse);
    }

}
