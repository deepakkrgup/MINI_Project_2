package com.example.socialMediaPlatform.service;

import com.example.socialMediaPlatform.dto.*;
import org.springframework.data.domain.Page;

public interface PostService {

    String createPost(String email, CreatePostDto dto);

    Page<?> getAllPosts(int page, int size);

    String addComment(String email, Long postId, CommentDto dto);

    String likePost(String email, Long postId);

    String deletePost(String email, Long postId);
}