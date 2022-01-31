package com.moram.ssafe.controller.board;

import com.moram.ssafe.controller.user.annotation.PreAuthorize;
import com.moram.ssafe.controller.user.annotation.UserContext;
import com.moram.ssafe.dto.board.BoardSaveRequest;
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
    @PreAuthorize(roles = {"ROLE_USER"})
    public ResponseEntity<CommonResponseDto> save(@RequestBody @Valid BoardSaveRequest request){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_POST_BOARD, boardService.save(request)));
    }

    @GetMapping("/types/{boardType}")
    public ResponseEntity<CommonResponseDto> findAll(@PathVariable int boardType,
                                                     @RequestParam(value = "limit", defaultValue = "1") int limit){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_BOARD_LIST, boardService.findAll(boardType, limit))
        );
    }

    @GetMapping("/{boardId}")
    @PreAuthorize(roles = {"ROLE_USER"})
    public ResponseEntity<CommonResponseDto> findById(@PathVariable Long boardId){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_BOARD, boardService.findById(boardId)
        ));
    }

    @GetMapping("/users")
    @PreAuthorize(roles = {"ROLE_USER"})
    public ResponseEntity<CommonResponseDto> findByUserId(){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_BOARD_LIST_USER, boardService.findByUserId(UserContext.getCurrentUserId())
        ));
    }

    @GetMapping("/name")
    public ResponseEntity<CommonResponseDto> findByBoardName(@RequestParam String name,
                                                             @RequestParam(value = "limit", defaultValue = "1") int limit){
        return ResponseEntity.ok().body(CommonResponseDto.of(
                HttpStatus.OK, SUCCESS_GET_BOARD_NAME, boardService.findByBoardName(name, limit)));
    }

    @PutMapping("/{boardId}")
    @PreAuthorize(roles = {"ROLE_USER"})
    public ResponseEntity<CommonResponseDto> update(@PathVariable Long boardId, @RequestBody @Valid BoardUpdateRequest request){
          return ResponseEntity.ok().body(CommonResponseDto.of(
                  HttpStatus.OK, SUCCESS_UPDATE_BOARD, boardService.update(boardId, request)));
    }

    @DeleteMapping("/{boardId}")
    @PreAuthorize(roles = {"ROLE_USER"})
    public ResponseEntity<CommonResponseDto> delete(@PathVariable Long boardId){
            return ResponseEntity.ok().body(CommonResponseDto.of(
                    HttpStatus.OK, SUCCESS_DELETE_BOARD, boardService.delete(boardId)));
    }

}
