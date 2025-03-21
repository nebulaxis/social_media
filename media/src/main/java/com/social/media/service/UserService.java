package com.social.media.service;  
import com.social.media.models.User;

public interface UserService {
  
    public User registerUser(User user);
    public User findUserById( Integer UserId);

    public User findUserByEmail(String email);
    public User followUser(Integer userId1, Integer userId2);
    public User updateUser(User user);
    public List<User> searchUser(String query);
    

}
