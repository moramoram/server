package com.moram.ssafe.dto.study;

import com.moram.ssafe.domain.study.Study;
import com.moram.ssafe.domain.study.StudyComment;
import com.moram.ssafe.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class StudyCommentSaveRequest {

    @NotNull(message = "스터디 게시물 아이디가 없습니다.")
    private Long studyId;

    @NotBlank(message = "스터디 댓글 내용이 없습니다.")
    private String content;

    public static StudyComment from(User user, Study study, String content){
        return StudyComment.builder().user(user).study(study).content(content).build();
    }
}
