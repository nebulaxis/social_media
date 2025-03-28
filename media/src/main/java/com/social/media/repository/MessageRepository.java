package com.social.media.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.media.models.Message;

public interface MessageRepository extends JpaRepository <Message, Integer>{
    

    public List<Message> findByChatId(Integer chatId);

    
}
