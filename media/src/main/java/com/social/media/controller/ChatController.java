package com.social.media.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.social.media.models.Chat;
import com.social.media.models.User;
import com.social.media.request.CreateChatRequest;
import com.social.media.service.ChatService;
import com.social.media.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;


    @Autowired
    private UserService userService;

    @PostMapping("/api/chats")
    public Chat createChat(@RequestHeader("Authorization")String jwt , @RequestBody CreateChatRequest req) throws Exception{

        User reqUser = userService.findUserByJwt(jwt);
        User user2=userService.findUserById(req.getUserId());
        Chat chat= chatService.createChat(reqUser, user2);
        
        
        return chat;
    }




    @GetMapping("/api/chats")
    public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJwt(jwt);
        List<Chat> chats= chatService.findUsersChat(user.getId());
        
        
        return chats;
    }
    
}
