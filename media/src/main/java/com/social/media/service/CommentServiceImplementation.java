package com.social.media.service;

import com.social.media.models.User;
import com.social.media.models.Comment;
import com.social.media.models.Post;
import com.social.media.repository.CommentRepository;
import com.social.media.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class CommentServiceImplementation implements CommentService {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentRepository commentRepository;


    @Autowired
    private PostRepository postRepository;
    @Override 
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);

        comment.setUser(user);
       
        comment.setContent(comment.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        Comment savedComment=commentRepository.save(comment);
  
  
        post.getComments().add(savedComment);
      
       
        postRepository.save(post);
        return savedComment;
    }

    @Override
    public Comment findCommentById(Integer commentId) throws Exception {
       Optional<Comment> opt= commentRepository.findById(commentId);

       if(opt.isEmpty()){
         throw new Exception("comment not exist");

       }

       
        return opt.get(); // ✅ Return null instead of throwing an exception
    }

    @Override
    public Comment likeComment(Integer commentId, Integer userId) throws Exception {
        Comment comment=  findCommentById(commentId);
       
       User user= userService.findUserById(userId);
       
       if(!comment.getLiked().contains(user)){

        comment.getLiked().add(user);
       }else comment.getLiked().remove(user);
       
       
       return commentRepository.save(comment); // ✅ Return null instead of throwing an exception
    }
}
