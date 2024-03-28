package com.AvA.controller;

import com.AvA.configuration.JwtProvider;
import com.AvA.model.User;
import com.AvA.repository.UserRepository;
import com.AvA.request.LoginRequest;
import com.AvA.response.AuthorizationResponse;
import com.AvA.service.CustomerUserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthorizationController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerUserServiceImplementation customUserDetails;



    @PostMapping("/signup")
    public ResponseEntity<AuthorizationResponse> createUserHandler(
            @RequestBody User user)throws Exception{

        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();
        String role = user.getRole();

        User isEmailExist = userRepository.findByEmail(email);

        if(isEmailExist!=null){
            throw new Exception("Email Is Already Used With Another Account");
        }

        // Creating a new User
        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setFullName(fullName);
        createdUser.setRole(role);
        createdUser.setPassword(passwordEncoder.encode(password));

        User savedUser = userRepository.save(createdUser);


        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProvider.generateToken(authentication);

        AuthorizationResponse authorizationResponse = new AuthorizationResponse();
        authorizationResponse.setJwt(token);
        authorizationResponse.setMessage("Register Successfully");
        authorizationResponse.setStatus(true);

        return new ResponseEntity<>(authorizationResponse, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthorizationResponse> signin(@RequestBody LoginRequest loginRequest){

        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        System.out.println(username + "------" + password);

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProvider.generateToken(authentication);
        AuthorizationResponse authorizationResponse = new AuthorizationResponse();

        authorizationResponse.setMessage("Login Success");
        authorizationResponse.setJwt(token);
        authorizationResponse.setStatus(true);

        return new ResponseEntity<>(authorizationResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password){
        UserDetails userDetails = customUserDetails.loadUserByUsername(username);

        System.out.println("Sign in User Details - " + userDetails);

        if(userDetails == null){
            System.out.println("sign in userDetails - unll " + userDetails);
            throw new BadCredentialsException("Invalid username or password");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            System.out.println("sign in user details - password not match-" + userDetails);
            throw new BadCredentialsException("Invalid user name or password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
