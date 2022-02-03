package com.moram.ssafe.service.board;

import com.moram.ssafe.controller.user.annotation.UserContext;
import com.moram.ssafe.domain.board.Board;
import com.moram.ssafe.domain.board.BoardLikeRepository;
import com.moram.ssafe.domain.board.BoardRepository;
import com.moram.ssafe.domain.user.User;
import com.moram.ssafe.domain.user.UserRepository;
import com.moram.ssafe.dto.board.BoardResponse;
import com.moram.ssafe.dto.board.BoardSaveRequest;
import com.moram.ssafe.dto.board.BoardUpdateRequest;
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

    @Transactional
    public Long createBoard(BoardSaveRequest requestDto) {
        User user = userRepository.findById(UserContext.getCurrentUserId())
                .orElseThrow(BoardNotFoundException::new);
        return boardRepository.save(requestDto.of(user)).getId();
    }

    public List<BoardResponse> findBoardList(int boardType, int limit) { //쿼리 1번인지 체크. 아니면 paing 없는 findAll 만들기

        Page<Board> boards = boardRepository.findBoardList(boardType,
                PageRequest.of(limit - 1, 6, Sort.by(Sort.Direction.DESC, "createdDate")));

        return PageToResponse(boards);
    }

    @Transactional
    public BoardResponse findBoard(Long boardId) {
        Board board = boardRepository.findBoard(boardId).orElseThrow(BoardNotFoundException::new);
        board.addView();
        Integer totalLike = board.getLikeList().size();
        boolean likeStatus = boardLikeRepository.existsByBoardIdAndUserId(boardId, UserContext.getCurrentUserId());
        return new BoardResponse(board, totalLike, likeStatus);
    }

    public List<BoardResponse> findUserBoard(Long userId) {
        return boardRepository.findUserBoard(userId).stream().map(board -> {
            Integer totalComment = board.getCommentList().size();
            Integer totalLike = board.getLikeList().size();
            return new BoardResponse(board, totalComment, totalLike);
        }).collect(Collectors.toList());
    }

    public List<BoardResponse> findByBoardName(int boardType, String name, int limit){

        Page<Board> boards = boardRepository.findByTitleContaining(boardType, name,
                PageRequest.of(limit - 1, 6, Sort.by(Sort.Direction.DESC, "createdDate")));

        return PageToResponse(boards);
    }

    public List<BoardResponse> findByLotsOfView(int boardType, int limit){
        Page<Board> boards = boardRepository.findBoardList(boardType,
                PageRequest.of(limit - 1, 6, Sort.by(Sort.Direction.DESC, "views")));

        return PageToResponse(boards);
    }

    public List<BoardResponse> findByLotsOfLike(int boardType, int limit){
        Page<Board> boards = boardRepository.findByLotsOfLike(boardType,
                PageRequest.of(limit - 1, 6));
        return PageToResponse(boards);
    }

    @Transactional
    public Long updateBoard(Long boardId, BoardUpdateRequest requestDto) {
        boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new)
                .update(requestDto.getTitle(), requestDto.getContent());
        return boardId;
    }

    @Transactional
    public Long deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
        return boardId;
    }

    public List<BoardResponse> PageToResponse(Page<Board> boards){
        return boards.stream().map(board -> {
            Integer totalComment = board.getCommentList().size();
            Integer totalLike = board.getLikeList().size();
            return new BoardResponse(board, totalComment, totalLike);
        }).collect(Collectors.toList());
    }
}

