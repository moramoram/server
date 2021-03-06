package com.moram.ssafe.controller.board;

import com.moram.ssafe.controller.user.annotation.AuthenticationPrincipal;
import com.moram.ssafe.controller.user.annotation.CurrentUser;
import com.moram.ssafe.controller.user.annotation.PreAuthorize;
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
    public ResponseEntity<CommonResponseDto> createBoardComment(@AuthenticationPrincipal CurrentUser currentUser,
                                                                @RequestBody @Valid BoardCommentSaveRequest request) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.CREATED, SUCCESS_POST_COMMENT, boardCommentService.createBoardComment(currentUser.getId(), request)));
    }

    @GetMapping("/{boardType}/{boardId}")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> findBoardCommentList(@PathVariable int boardType,
                                                                  @PathVariable Long boardId) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_COMMENT_LIST, boardCommentService.findBoardCommentList(boardType,boardId)));
    }

    @PutMapping("/{commentId}")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> updateBoardComment(@AuthenticationPrincipal CurrentUser currentUser,
                                                                @PathVariable Long commentId,
                                                                @RequestBody @Valid BoardCommentUpdateRequest request) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.CREATED, SUCCESS_PUT_COMMENT, boardCommentService.updateBoardComment(currentUser.getId(), commentId, request)));
    }

    @DeleteMapping("/{commentId}")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> deleteBoardComment(@AuthenticationPrincipal CurrentUser currentUser,
                                                                @PathVariable Long commentId) {
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.NO_CONTENT, SUCCESS_DELETE_COMMENT, boardCommentService.deleteBoardComment(currentUser.getId(), commentId)));
    }

}
