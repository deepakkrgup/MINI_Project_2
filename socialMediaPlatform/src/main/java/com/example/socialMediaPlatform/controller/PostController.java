package com.example.socialMediaPlatform.controller;

import com.example.socialMediaPlatform.dto.*;
import com.example.socialMediaPlatform.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService service;

    @PostMapping
    public String createPost(
            @RequestHeader("email") String email,
            @RequestBody CreatePostDto dto) {
        return service.createPost(email, dto);
    }

    @GetMapping
    public Page<?> getPosts(
            @RequestParam int page,
            @RequestParam int size) {
        return service.getAllPosts(page, size);
    }

    @PostMapping("/{postId}/comment")
    public String comment(
            @RequestHeader("email") String email,
            @PathVariable Long postId,
            @RequestBody CommentDto dto) {
        return service.addComment(email, postId, dto);
    }

    @PostMapping("/{postId}/like")
    public String like(
            @RequestHeader("email") String email,
            @PathVariable Long postId) {
        return service.likePost(email, postId);
    }

    @DeleteMapping("/{postId}")
    public String delete(
            @RequestHeader("email") String email,
            @PathVariable Long postId) {
        return service.deletePost(email, postId);
    }
}
