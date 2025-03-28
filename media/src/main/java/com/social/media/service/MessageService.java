package com.social.media.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.media.models.Chat;
import com.social.media.models.Message;
import com.social.media.models.User;

public interface MessageService {
    

    public Message createMessage(User user , Integer chatId, Message req)throws Exception;

    public List<Message> findChatsMessages(Integer chatId)throws Exception;

}
