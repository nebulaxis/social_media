package com.social.media.controller;

import com.social.media.models.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.social.media.models.Reels;
import com.social.media.service.ReelsService;
import com.social.media.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class ReelsController {

    @Autowired
    private ReelsService reelsService;


    @Autowired
    private UserService userService;


   
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    

    @PostMapping("/api/reels")
    public Reels createReels(@RequestBody Reels reel, @RequestHeader("Authorization") String jwt) {
      
      User reqUser = userService.findUserByJwt(jwt);


        Reels createdReels = reelsService.createReel(reel, reqUser);
   

        return createdReels;
   
   
   
    }




    
    @GetMapping("/api/reels")

    public List<Reels> findAllReels(){
  
       List <Reels> reels = reelsService.findAllReels();
   

        return reels;
   
    }




    @GetMapping("/api/reels/user/{userId}")

    public List<Reels> findUsersReels(@PathVariable Integer userId ) throws Exception{
  
        
       List <Reels> reels = reelsService.findUsersReel( userId);
   
        return reels;
   
    }


    
}
