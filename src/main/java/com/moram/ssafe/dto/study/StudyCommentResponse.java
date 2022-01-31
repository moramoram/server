package com.moram.ssafe.dto.study;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.moram.ssafe.domain.study.StudyComment;
import com.moram.ssafe.dto.user.UserResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StudyCommentResponse {

    private Long commentId;

    private String content;

    private UserResponse writerInfo;

    public static StudyCommentResponse from(StudyComment comment){
        return StudyCommentResponse.builder().commentId(comment.getId())
                .content(comment.getContent())
                .writerInfo(UserResponse.from(comment.getUser())).build();
    }
}
