package com.moram.ssafe.controller.study;

import com.moram.ssafe.controller.user.annotation.PreAuthorize;
import com.moram.ssafe.controller.user.annotation.UserContext;
import com.moram.ssafe.dto.common.response.CommonResponseDto;
import com.moram.ssafe.dto.study.StudyCommentSaveRequest;
import com.moram.ssafe.dto.study.StudyCommentUpdateRequest;
import com.moram.ssafe.service.study.StudyCommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.moram.ssafe.dto.common.response.SuccessMessage.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/study-comments")
public class StudyCommentController {
    private final StudyCommentService studyCommentService;

    @PostMapping
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> createStudyComment(@RequestBody @Valid StudyCommentSaveRequest request){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_POST_COMMENT, studyCommentService.createStudyComment(request)));
    }

    @GetMapping("/{studyId}")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> findStudyComment(@PathVariable Long studyId){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_COMMENT_LIST, studyCommentService.findStudyComment(studyId)));
    }

    @GetMapping("/users")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> findUserStudyComment(){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_COMMENT_LIST, studyCommentService.findUserStudyComment(UserContext.getCurrentUserId())));
    }

    @PutMapping("/{commentId}")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> updateStudyComment(@PathVariable Long commentId, @RequestBody @Valid StudyCommentUpdateRequest request){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_PUT_COMMENT, studyCommentService.updateStudyComment(commentId, request)));
    }

    @DeleteMapping("/{commentId}")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> deleteStudyComment(@PathVariable Long commentId){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_DELETE_COMMENT, studyCommentService.deleteStudyComment(commentId)));
    }

}
