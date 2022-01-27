package com.moram.ssafe.controller.board;

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
    public ResponseEntity<CommonResponseDto> save(@RequestBody @Valid BoardCommentSaveRequest requestDto){
        return ResponseEntity.ok().body(CommonResponseDto.of(HttpStatus.OK, SUCCESS_POST_COMMENT, boardCommentService.save(requestDto)));
    }

    @GetMapping("/boards/{boardId}")
    public ResponseEntity<CommonResponseDto> findByBoardId(@PathVariable Long boardId){
        return ResponseEntity.ok().body(CommonResponseDto.of(HttpStatus.OK, SUCCESS_GET_COMMENT, boardCommentService.findByBoardId(boardId)));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<CommonResponseDto> findByUserId(@PathVariable Long userId){
        return ResponseEntity.ok().body(CommonResponseDto.of(HttpStatus.OK, SUCCESS_GET_COMMENT, boardCommentService.findByUserId(userId)));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommonResponseDto> update(@PathVariable Long commentId, @RequestBody @Valid BoardCommentUpdateRequest requestDto){
        return ResponseEntity.ok()
                .body(CommonResponseDto.of(HttpStatus.OK, SUCCESS_PUT_COMMENT, boardCommentService.update(commentId, requestDto)));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommonResponseDto> delete(@PathVariable Long commentId){
        return ResponseEntity.ok()
                .body(CommonResponseDto.of(HttpStatus.OK, SUCCESS_DELETE_COMMENT, boardCommentService.delete(commentId)));
    }

}
