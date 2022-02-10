package com.moram.ssafe.dto.user;

import com.moram.ssafe.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthResponse {

    private Long userId;
    private String email;
    private String profileImg;
    private String nickname;
    private String realName;
    private int ordinal;
    private String campus;
    private int authCheck;
    private String likeJob;
    private String role;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static UserAuthResponse from(User user) {
        return new UserAuthResponse(user.getId(), user.getEmail(),
                user.getProfileImg(), user.getNickname(), user.getRealName(), user.getOrdinal(),
                user.getCampus(), user.getAuthCheck(), user.getLikeJob(),user.getRoleType().getRole(), user.getCreatedDate(), user.getModifiedDate());
    }
}
