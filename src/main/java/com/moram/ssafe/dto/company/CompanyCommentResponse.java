package com.moram.ssafe.dto.company;

import com.moram.ssafe.domain.company.CompanyComment;
import com.moram.ssafe.dto.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CompanyCommentResponse {

    private Long commentId;

    private UserResponse writerInfo;

    private String content;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public static CompanyCommentResponse from(CompanyComment comment){
        return new CompanyCommentResponse(comment.getId(),UserResponse.from(comment.getUser()),comment.getContent(),comment.getCreatedDate(),comment.getModifiedDate());
    }

}
