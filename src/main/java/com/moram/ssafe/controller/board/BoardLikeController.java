package com.moram.ssafe.controller.board;

import com.moram.ssafe.dto.common.response.CommonResponseDto;
import com.moram.ssafe.service.board.BoardLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.moram.ssafe.dto.common.response.SuccessMessage.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class BoardLikeController {

    private final BoardLikeService boardLikeService;

    @GetMapping("/{userId}")
    public ResponseEntity<CommonResponseDto> findByUserId(@PathVariable Long userId){
        return ResponseEntity.ok().body(
                CommonResponseDto.of(HttpStatus.OK, SUCCESS_GET_LIKE, boardLikeService.findByUserId(userId)));
    }

    @PostMapping("/users/{userId}/boards/{boardId}")
    public ResponseEntity<CommonResponseDto> pushLike(@PathVariable Long userId, @PathVariable Long boardId){
        return ResponseEntity.ok().body(
                CommonResponseDto.of(HttpStatus.OK, SUCCESS_PUSH_LIKE, boardLikeService.pushLike(userId, boardId)));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommonResponseDto> deleteLike(@PathVariable Long commentId){
        return ResponseEntity.ok().body(
                CommonResponseDto.of(HttpStatus.OK, SUCCESS_DELETE_LIKE, boardLikeService.deleteLike(commentId)));
    }
}
