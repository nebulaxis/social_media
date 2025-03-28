package com.social.media.service;

import java.util.List;

import com.social.media.models.Reels;
import com.social.media.models.User;

public interface ReelsService {

    public Reels createReel(Reels reel ,User user);

    public List<Reels> findAllReels();

    public List<Reels> findUsersReel(Integer userId)throws Exception;
    
}
