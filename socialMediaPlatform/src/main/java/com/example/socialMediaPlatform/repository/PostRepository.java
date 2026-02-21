package com.example.socialMediaPlatform.repository;

import com.example.socialMediaPlatform.entity.Post;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.domain.*;
import java.util.*;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByUserId(Long userId, Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.content LIKE %:keyword%")
    Page<Post> searchPosts(String keyword, Pageable pageable);
}
