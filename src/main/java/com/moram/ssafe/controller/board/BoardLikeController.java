package com.moram.ssafe.controller.board;

import com.moram.ssafe.controller.user.annotation.PreAuthorize;
import com.moram.ssafe.controller.user.annotation.UserContext;
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

    @GetMapping
    @PreAuthorize(roles = {"ROLE_USER"})
    public ResponseEntity<CommonResponseDto> findByUserId(){
        return ResponseEntity.ok().body(
                CommonResponseDto.of(HttpStatus.OK, SUCCESS_GET_LIKE, boardLikeService.findByUserId(UserContext.getCurrentUserId())));
    }

    @PostMapping("/boards/{boardId}")
    @PreAuthorize(roles = {"ROLE_USER"})
    public ResponseEntity<CommonResponseDto> pushLike(@PathVariable Long boardId){
        return ResponseEntity.ok().body(
                CommonResponseDto.of(HttpStatus.OK, SUCCESS_PUSH_LIKE, boardLikeService.pushLike(UserContext.getCurrentUserId(), boardId)));
    }

    @DeleteMapping("/{likeId}")
    @PreAuthorize(roles = {"ROLE_USER"})
    public ResponseEntity<CommonResponseDto> deleteLike(@PathVariable Long likeId){
        return ResponseEntity.ok().body(
                CommonResponseDto.of(HttpStatus.OK, SUCCESS_DELETE_LIKE, boardLikeService.deleteLike(likeId)));
    }
}
