package com.moram.ssafe.controller.board;

import com.moram.ssafe.controller.user.annotation.PreAuthorize;
import com.moram.ssafe.controller.user.annotation.UserContext;
import com.moram.ssafe.dto.board.BoardSaveRequest;
import com.moram.ssafe.dto.board.BoardSearch;
import com.moram.ssafe.dto.board.BoardUpdateRequest;
import com.moram.ssafe.dto.common.response.CommonResponseDto;
import com.moram.ssafe.service.board.BoardService;
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
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> createBoard(@RequestBody @Valid BoardSaveRequest request){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_POST_BOARD, boardService.createBoard(request)));
    }

    @GetMapping("/types/{boardType}")
    public ResponseEntity<CommonResponseDto> findBoardList(@PathVariable int boardType, @RequestParam int offset){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_BOARD_LIST, boardService.findBoardList(boardType, offset))
        );
    }

    @GetMapping("/{boardId}")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> findBoard(@PathVariable Long boardId){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_BOARD, boardService.findBoard(boardId)
        ));
    }

    @GetMapping("/users")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> findUserBoard(@RequestParam int offset){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_BOARD_LIST_USER,
                boardService.findUserBoard(UserContext.getCurrentUserId(), offset)
        ));
    }

    @GetMapping("/types/{boardType}/name")
    public ResponseEntity<CommonResponseDto> findByBoardName(@PathVariable int boardType, @RequestParam String name,
                                                             @RequestParam int offset){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_BOARD_NAME, boardService.findByBoardName(boardType, name, offset)));
    }

    @GetMapping("/types/{boardType}/views")
    public ResponseEntity<CommonResponseDto> findByLotsOfView(@PathVariable int boardType, @RequestParam int offset){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_BOARD_VIEW, boardService.findByLotsOfView(boardType, offset)));
    }

    @GetMapping("/types/{boardType}/likes")
    public ResponseEntity<CommonResponseDto> findByLotsOfLike(@PathVariable int boardType, @RequestParam int offset){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_BOARD_LIKE, boardService.findByLotsOfLike(boardType, offset)));
    }

    @GetMapping("/search")
    public ResponseEntity<CommonResponseDto> searchBoard(@RequestParam(required = false) Integer boardType,
                                                         @RequestParam(required = false) String title,
                                                         @RequestParam(required = false) String criteria,
                                                         @RequestParam(required = false) int offset){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_BOARD_LIST,
                boardService.searchBoard(offset, BoardSearch.of(boardType, title, criteria))));
    }

    @GetMapping("/comments/users")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> findByUserComments(){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_BOARD_LIST_COMMENTS, boardService.findByUserComments()));
    }

    @PutMapping("/{boardId}")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> updateBoard(@PathVariable Long boardId,
                                                         @RequestBody @Valid BoardUpdateRequest request){
          return ResponseEntity.ok().body(CommonResponseDto.of(
                  HttpStatus.OK, SUCCESS_UPDATE_BOARD, boardService.updateBoard(boardId, request)));
    }

    @PutMapping("/{boardId}/scraps")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> toggleBoardLikes(@PathVariable Long boardId){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_PUT_LIKE, boardService.toggleBoardLikes(boardId)));
    }

    @DeleteMapping("/{boardId}")
    @PreAuthorize(roles = {"ROLE_AUTH"})
    public ResponseEntity<CommonResponseDto> deleteBoard(@PathVariable Long boardId){
            return ResponseEntity.ok().body(CommonResponseDto.of(
                    HttpStatus.OK, SUCCESS_DELETE_BOARD, boardService.deleteBoard(boardId)));
    }

}
