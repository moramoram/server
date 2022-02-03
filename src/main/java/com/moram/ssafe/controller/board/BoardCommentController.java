package com.moram.ssafe.controller.board;

import com.moram.ssafe.controller.user.annotation.PreAuthorize;
import com.moram.ssafe.controller.user.annotation.UserContext;
import com.moram.ssafe.dto.board.BoardCommentSaveRequest;
import com.moram.ssafe.dto.board.BoardCommentUpdateRequest;
import com.moram.ssafe.dto.common.response.CommonResponseDto;
import com.moram.ssafe.service.board.BoardCommentService;
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
@RequestMapping("/board-comments")
public class BoardCommentController {
    private final BoardCommentService boardCommentService;

    @PostMapping
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> createBoardComment(@RequestBody @Valid BoardCommentSaveRequest request){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_POST_COMMENT, boardCommentService.createBoardComment(request)));
    }

    @GetMapping("/{boardId}")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> findBoardCommentList(@PathVariable Long boardId){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_COMMENT_LIST, boardCommentService.findBoardCommentList(boardId)));
    }

    @GetMapping("/users")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> findUserBoardComment(){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_COMMENT_LIST, boardCommentService.findUserBoardComment(UserContext.getCurrentUserId())));
    }

    @PutMapping("/{commentId}")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> updateBoardComment(@PathVariable Long commentId, @RequestBody @Valid BoardCommentUpdateRequest request){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                        HttpStatus.OK, SUCCESS_PUT_COMMENT, boardCommentService.updateBoardComment(commentId, request)));
    }

    @DeleteMapping("/{commentId}")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> deleteBoardComment(@PathVariable Long commentId){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                        HttpStatus.OK, SUCCESS_DELETE_COMMENT, boardCommentService.deleteBoardComment(commentId)));
    }

}
