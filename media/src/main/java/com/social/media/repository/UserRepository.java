package com.social.media.repository;
import com.social.media.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User , Integer> {
    
}
