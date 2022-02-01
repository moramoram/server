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
    @PreAuthorize(roles = {"ROLE_USER"})
    public ResponseEntity<CommonResponseDto> save(@RequestBody @Valid StudyCommentSaveRequest request){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_POST_COMMENT, studyCommentService.save(request)));
    }

    @GetMapping("/{studyId}")
    @PreAuthorize(roles = {"ROLE_USER"})
    public ResponseEntity<CommonResponseDto> findByStudyId(@PathVariable Long studyId){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_COMMENT_LIST, studyCommentService.findByStudyId(studyId)));
    }

    @GetMapping("/users")
    @PreAuthorize(roles = {"ROLE_USER"})
    public ResponseEntity<CommonResponseDto> findByUserId(){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_COMMENT_LIST, studyCommentService.findByUserId(UserContext.getCurrentUserId())));
    }

    @PutMapping("/{commentId}")
    @PreAuthorize(roles = {"ROLE_USER"})
    public ResponseEntity<CommonResponseDto> update(@PathVariable Long commentId, @RequestBody @Valid StudyCommentUpdateRequest request){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_PUT_COMMENT, studyCommentService.update(commentId, request)));
    }

    @DeleteMapping("/{commentId}")
    @PreAuthorize(roles = {"ROLE_USER"})
    public ResponseEntity<CommonResponseDto> delete(@PathVariable Long commentId){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_DELETE_COMMENT, studyCommentService.delete(commentId)));
    }

}