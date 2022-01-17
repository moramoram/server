package com.moram.ssafe.domain.user;

import com.moram.ssafe.domain.BaseEntity;
import com.moram.ssafe.dto.user.UserUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tbl_user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private String profileImg;

    private String nickname;

    private String realName;

    private String campus;

    private String authImg;

    private int ssafyCheck;

    private String likeJob;

    @Builder
    public User(String email, Role role, SocialType socialType) {
        this.email = email;
        this.role = role;
        this.socialType = socialType;
    }

    public void update(UserUpdateRequest user) {
        this.profileImg = user.getProfileImg();
        this.nickname = user.getNickname();
        this.realName = user.getRealName();
        this.campus = user.getCampus();
        this.authImg = user.getAuthImg();
        this.ssafyCheck = 1;
    }

    public void updateLikeJob(String likeJob) {
        this.likeJob = likeJob;
    }
    public Role getRole() {
        return role;
    }
}
