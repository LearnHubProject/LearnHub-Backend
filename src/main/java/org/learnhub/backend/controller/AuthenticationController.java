package org.learnhub.backend.controller;

import jakarta.transaction.Transactional;
import org.learnhub.backend.api.payload.request.LoginRequest;
import org.learnhub.backend.api.payload.request.RegisterRequest;
import org.learnhub.backend.api.payload.response.AuthenticationResponse;
import org.learnhub.backend.database.entity.UserAccount;
import org.learnhub.backend.database.repostitory.UserCredentialsRepository;
import org.learnhub.backend.exceptions.UserAlreadyExistAuthenticationException;
import org.learnhub.backend.misc.payloads.GenericFailureMessageResponse;
import org.learnhub.backend.misc.payloads.ResponsePayload;
import org.learnhub.backend.service.SchoolService;
import org.learnhub.backend.service.UserService;
import org.learnhub.backend.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    UserCredentialsRepository credentialsRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTUtils jwtUtils;

    @Autowired
    UserService userService;

    @Autowired
    SchoolService schoolService;

    @Value("${application.multi-school-enabled}")
    boolean multiSchoolEnabled;

    @PostMapping("/api/login")
    public ResponseEntity<ResponsePayload> authenticate(@RequestBody LoginRequest loginRequest){

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
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

        AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwtUtils.generateToken(loginRequest.getEmail(), role));
        return ResponseEntity.status(HttpStatus.OK).body(authenticationResponse);
    }

    @GetMapping("/api/register")
    @Transactional
    public ResponseEntity<ResponsePayload> register(@RequestBody RegisterRequest registerRequest){


        UserAccount account;

        try {
            account = userService.createUser(registerRequest.getEmail(), registerRequest.getPassword(), registerRequest.getPersonalCode(), "USER");
        }
        catch (UserAlreadyExistAuthenticationException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericFailureMessageResponse("Account with this email already exists!"));
        }

        //Try to log in to check the account
        boolean isAuthenticated;
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(registerRequest.getEmail(), registerRequest.getPassword()));
            isAuthenticated = authentication.isAuthenticated();
        }
        catch (AuthenticationException e){
            isAuthenticated = false;
        }
        if(!isAuthenticated){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        if(!multiSchoolEnabled){
            schoolService.createJoinRequest(null,account.getId());
        }

        AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwtUtils.generateToken(registerRequest.getEmail(), "USER"));

        return ResponseEntity.status(HttpStatus.OK).body(authenticationResponse);
    }

}
