package com.social.media.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.media.config.JwtProvider;
import com.social.media.models.User;
import com.social.media.repository.LoginRequest;
import com.social.media.repository.UserRepository;
import com.social.media.response.AuthResponse;
import com.social.media.service.CustomUserDetailsService;
import com.social.media.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


     @Autowired
    private AuthenticationManager authenticationManager; // ✅ Injected AuthenticationManager

    @Autowired
    private JwtProvider jwtProvider; // ✅ Injected JwtProvider as a Bean
    
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private CustomUserDetailsService customUserDetails;

    // our api is made like localhost:<port>/auth/signup : cause on above we provide getmapping /auth
        @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user)throws Exception{

        //to check user already exist or not with that email or not 
        User isExist = userRepository.findByEmail(user.getEmail());

        if(isExist!=null){
            throw new Exception("email already used with another account");
        }
       


        User newUser=new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        
 
       User savedUser = userRepository.save(newUser);
      
      Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
      
      String token= JwtProvider.generateToken(authentication);
     
     AuthResponse res= new AuthResponse(token, "Register Success");
      return res;

    }

// its actual api is like /auth/signin
      @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
                )
            );

            String token = jwtProvider.generateToken(authentication); // ✅ Corrected token generation

            return ResponseEntity.ok(new AuthResponse(token, "Login Success"));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(new AuthResponse(null, "Invalid Credentials"));
        }
    }


    
        private Authentication authenticate(String email, String password) {
           UserDetails userDetails= customUserDetails.loadUserByUsername(email);

           if(userDetails==null){
            throw new BadCredentialsException("invalid username");
           }
           if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("password not matched");
           }
           return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }
}
