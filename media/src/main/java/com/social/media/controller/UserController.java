package com.social.media.controller;

import org.springframework.web.bind.annotation.RestController;

import com.social.media.exception.UserException;
import com.social.media.models.User;
import com.social.media.repository.UserRepository;
import com.social.media.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PutMapping;




@RestController

public class UserController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;






    

@GetMapping("/api/users")
    public List<User> getUsers(){

        List<User> users = userRepository.findAll();


        return users;
    }

    
    @GetMapping("/api/users/{userId}")
    public User getUsersById(@PathVariable("userId")Integer id)throws UserException{

      User user=userService.findUserById(id);
        return user;
    }



   

   

@PutMapping("/api/users")

    public User updateUser(@RequestHeader("Authorization")String jwt, @RequestBody User user )throws UserException{
        

        User reqUser = userService.findUserByJwt(jwt);
      User updatedUser = userService.updateUser(user, reqUser.getId());
      return updatedUser;
    }



    @PutMapping("/users/follow/{userId2}")

 
    public User followUserHandler(@RequestHeader("Authorization")String jwt, @PathVariable Integer userId2) throws UserException{
       
        User reqUser= userService.findUserByJwt(jwt);
        User user = userService.followUser(reqUser.getId(), userId2);
       

      
        return user;
    }

    @GetMapping("/api/users/search")
    public List<User> searchUser(@RequestParam("query") String query) {
        return userService.searchUser(query);

       
    }

    @GetMapping("/api/users/profile")

    public User getUserFromToken(@RequestHeader("Authorization")String jwt){
      User user=userService.findUserByJwt(jwt);

      user.setPassword(null);
        return user; 
    }
    
}
