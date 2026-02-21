package com.example.socialMediaPlatform.repository;

import com.example.socialMediaPlatform.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserAndPost(User user, Post post);
}

