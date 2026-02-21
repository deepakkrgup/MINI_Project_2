package com.example.socialMediaPlatform.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Userinfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bio;

    private String profilePicture;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
