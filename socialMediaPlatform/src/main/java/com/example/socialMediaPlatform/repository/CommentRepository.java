package com.example.socialMediaPlatform.repository;

import com.example.socialMediaPlatform.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
