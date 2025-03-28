package com.social.media.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.media.config.JwtProvider;
import com.social.media.exception.UserException;
import com.social.media.models.User;
import com.social.media.repository.UserRepository;  // ✅ Correct import





@Service
public class UserServiceImplementation implements UserService {

    @Autowired  // ✅ Properly inject UserRepository
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        User newUser=new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        newUser.setId(user.getId());
 
       User savedUser = userRepository.save(newUser);
       return savedUser;
        
    }

    @Override
    public User findUserById(Integer userId) throws UserException{
         Optional<User> user = userRepository.findById(userId);
       if(user.isPresent()){
        return user.get();
       }

        throw new UserException("user not exist with userId" + userId );
       
    }

    @Override
    public User findUserByEmail(String email) {
        User user=userRepository.findByEmail(email);
        
        return user;
    }

    @Override
    public User followUser(Integer reqUserId, Integer userId2) throws UserException {
       
        User reqUser = findUserById(reqUserId);
        User user2= findUserById(userId2);
        user2.getFollowers().add(reqUser.getId());
        reqUser.getFollowings().add(user2.getId());

        userRepository.save(reqUser);
        userRepository.save(user2);


        return reqUser;
       
    }

    @Override
    public User updateUser(User user , Integer userId) throws UserException {
         
        Optional<User> user1= userRepository.findById(userId);
           
        if (user1.isEmpty()) {
            throw new RuntimeException("User not found with userId " + userId);
        }
    
    
        User oldUser = user1.get();
    
        
    if(user.getFirstName()!=null){
        oldUser.setFirstName(user.getFirstName());
    }
    if(user.getLastName()!=null){
        oldUser.setLastName(user.getLastName());
    }
    if(user.getEmail()!=null){
        oldUser.setEmail(user.getEmail());
    }
    if(user.getPassword()!=null){
        oldUser.setPassword(user.getPassword());
    }
    if(user.getGender()!=null){
        oldUser.setGender((user.getGender()));
    }
    
    User updatedUser = userRepository.save(oldUser);
    
    
            return updatedUser;
        
    }




    @Override
    public List<User> searchUser(String query) {
       return userRepository.searchUser(query);
       
    }

    //method to find user by email inside UserController 

    @Override
    public User findUserByJwt(String jwt) {
       String email= JwtProvider.getEmailFromJwtToken(jwt);

       User user= userRepository.findByEmail(email);
       return user;
    }
    
}
