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
    private Role roleType;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private String profileImg;

    private String nickname;

    private String realName;

    private int ordinal;

    private String campus;

    private String authImg;

    private int authCheck;

    private String likeJob;

    @Builder
    public User(String email, Role roleType, SocialType socialType) {
        this.email = email;
        this.roleType = roleType;
        this.socialType = socialType;
    }

    public void update(UserUpdateRequest user) {
        this.profileImg = user.getProfileImg();
        this.nickname = user.getNickname();
        this.realName = user.getRealName();
        this.ordinal=user.getOrdinal();
        this.campus = user.getCampus();
        this.authImg = user.getAuthImg();
        this.authCheck = 1;
    }

    public void updateLikeJob(String likeJob) {
        this.likeJob = likeJob;
    }
    public Role getRole() {
        return roleType;
    }
}
