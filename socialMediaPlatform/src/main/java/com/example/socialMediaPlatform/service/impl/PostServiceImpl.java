package com.example.socialMediaPlatform.service.impl;

import com.example.socialMediaPlatform.dto.*;
import com.example.socialMediaPlatform.entity.*;
import com.example.socialMediaPlatform.repository.*;
import com.example.socialMediaPlatform.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepo;
    private final UserRepository userRepo;
    private final CommentRepository commentRepo;
    private final LikeRepository likeRepo;

    @Override
    public String createPost(String email, CreatePostDto dto) {

        User user = userRepo.findByEmail(email).orElseThrow();

        Post post = Post.builder()
                .content(dto.getContent())
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();

        postRepo.save(post);
        return "Post created";
    }

    @Override
    public Page<Post> getAllPosts(int page, int size) {
        return postRepo.findAll(PageRequest.of(page, size));
    }

    @Override
    public String addComment(String email, Long postId, CommentDto dto) {

        User user = userRepo.findByEmail(email).orElseThrow();
        Post post = postRepo.findById(postId).orElseThrow();

        Comment comment = Comment.builder()
                .text(dto.getText())
                .createdAt(LocalDateTime.now())
                .post(post)
                .user(user)
                .build();

        commentRepo.save(comment);
        return "Comment added";
    }

    @Override
    public String likePost(String email, Long postId) {

        User user = userRepo.findByEmail(email).orElseThrow();
        Post post = postRepo.findById(postId).orElseThrow();

        if (likeRepo.findByUserAndPost(user, post).isPresent()) {
            return "Already liked";
        }

        Like like = Like.builder()
                .user(user)
                .post(post)
                .build();

        likeRepo.save(like);
        return "Post liked";
    }

    @Override
    public String deletePost(String email, Long postId) {

        User user = userRepo.findByEmail(email).orElseThrow();
        Post post = postRepo.findById(postId).orElseThrow();

        if (!post.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Not authorized");
        }

        postRepo.delete(post);
        return "Post deleted";
    }
}
