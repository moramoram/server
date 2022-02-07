package com.moram.ssafe.service.board;

import com.moram.ssafe.controller.user.annotation.UserContext;
import com.moram.ssafe.domain.board.*;
import com.moram.ssafe.domain.user.User;
import com.moram.ssafe.domain.user.UserRepository;
import com.moram.ssafe.dto.board.BoardResponse;
import com.moram.ssafe.dto.board.BoardSaveRequest;
import com.moram.ssafe.dto.board.BoardUpdateRequest;
import com.moram.ssafe.exception.auth.UserAuthenticationException;
import com.moram.ssafe.exception.board.BoardNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final BoardQueryRepository boardQueryRepository;
    private final BoardCommentRepository boardCommentRepository;

    @Transactional
    public Long createBoard(BoardSaveRequest requestDto) {
        User user = userRepository.findById(UserContext.getCurrentUserId())
                .orElseThrow(BoardNotFoundException::new);
        return boardRepository.save(requestDto.of(user)).getId();
    }

    public List<BoardResponse> findBoardList(int boardType, int offset) {
        Page<Board> boards = boardRepository.findBoardList(boardType,
                PageRequest.of(offset - 1, 12, Sort.by(Sort.Direction.DESC, "createdDate")));

        return PageToResponse(boards);
    }

    @Transactional
    public BoardResponse findBoard(Long boardId) {
        Board board = boardRepository.findBoard(boardId).orElseThrow(BoardNotFoundException::new);
        board.addView();
        Boolean likeStatus = boardLikeRepository.existsByBoardIdAndUserId(boardId, UserContext.getCurrentUserId());

        return new BoardResponse(board, likeStatus);
    }

    public List<BoardResponse> findUserBoard(Long userId, int offset) {

        Page<Board> boards = boardRepository.findUserBoard(userId,
                PageRequest.of(offset - 1, 12, Sort.by(Sort.Direction.DESC, "createdDate")));

        return PageToResponse(boards);
    }

    public List<BoardResponse> findByBoardName(int boardType, String name, int offset){

        Page<Board> boards = boardRepository.findByTitleContaining(boardType, name,
                PageRequest.of(offset - 1, 12, Sort.by(Sort.Direction.DESC, "createdDate")));

        return PageToResponse(boards);
    }

    public List<BoardResponse> findByLotsOfView(int boardType, int offset){
        Page<Board> boards = boardRepository.findBoardList(boardType,
                PageRequest.of(offset - 1, 12, Sort.by(Sort.Direction.DESC, "views")));

        return PageToResponse(boards);
    }

    public List<BoardResponse> findByLotsOfLike(int boardType, int offset){
        Page<Board> boards = boardRepository.findByLotsOfLike(boardType,
                PageRequest.of(offset - 1, 12));
        return PageToResponse(boards);
    }

    public List<BoardResponse> findByUserComments(){
        List<Board> boards =  boardQueryRepository.findByUserComment(UserContext.getCurrentUserId());

        return boards.stream().map(BoardResponse::new).collect(Collectors.toList());
    }

    @Transactional
    public Long updateBoard(Long boardId, BoardUpdateRequest requestDto) {
        Long userId = UserContext.getCurrentUserId();

        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);

        validBoardUser(userId, board.getUser().getId());

        board.update(requestDto.getTitle(), requestDto.getContent());
        return boardId;
    }

    @Transactional
    public Long deleteBoard(Long boardId) {
        Long userId = UserContext.getCurrentUserId();

        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);

        validBoardUser(userId, board.getUser().getId());
        boardLikeRepository.deleteByBoardId(boardId);
        boardCommentRepository.deleteByBoardId(boardId);
        boardRepository.deleteById(boardId);
        return boardId;
    }

    public List<BoardResponse> PageToResponse(Page<Board> boards){
        return boards.stream().map(BoardResponse::new).collect(Collectors.toList());
    }

    public void validBoardUser(Long currentUser, Long boardUser){
        if(currentUser == boardUser)
            return;
        throw new UserAuthenticationException();
    }
}

