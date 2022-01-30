package com.moram.ssafe.domain.user;

import com.moram.ssafe.domain.BaseEntity;
import com.moram.ssafe.dto.user.UserUpdateAddAuth;
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

    private String email;

    @Enumerated(EnumType.STRING)
    private Role roleType;

    private String socialId;

    private String profileImg;

    private String nickname;

    private String realName;

    private int ordinal;

    private String campus;

    private String authImg;

    private int authCheck;

    private String likeJob;

    @Builder
    public User(String socialId,String email, Role roleType,String nickname) {
        this.socialId =socialId;
        this.email = email;
        this.roleType = roleType;
        this.nickname=nickname;
    }

    public void update(UserUpdateAddAuth user) {
        this.profileImg = user.getProfileImg();
        this.nickname = user.getNickname();
        this.realName = user.getRealName();
        this.ordinal = user.getOrdinal();
        this.campus = user.getCampus();
        this.authImg = user.getAuthImg();
        this.authCheck = 1;
    }

    public void authApprove() {
        this.authCheck = 2;
    }

    public void deleteUser(){
        this.email = "";
        this.socialId ="";
        this.nickname = "익명의 사용자";
        this.realName = "탈퇴한 사용자";
        this.authImg ="";
    }

    public void updateLikeJob(String likeJob) {
        this.likeJob = likeJob;
    }

    public Role getRole() {
        return roleType;
    }
}
