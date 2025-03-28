package com.social.media.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.social.media.models.Chat;
import com.social.media.models.User;

public interface ChatRepository extends JpaRepository <Chat, Integer> {

    public List <Chat> findByUsersId(Integer userId);


    //we have to give Chat as capital cause we create class same as capital i.e., Chat.java
    @Query("select c from Chat c Where :user Member of c.users And :reqUser Member of c.users")
    public Chat findChatByUsersId(@Param("user") User user, @Param("reqUser")User reqUser);



    
    
}
