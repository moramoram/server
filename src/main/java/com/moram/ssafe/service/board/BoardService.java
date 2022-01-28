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
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardLikeRepository boardLikeRepository;

    @Transactional(readOnly = true)
    public List<BoardResponse> findAll(int boardType, int limit) {

        Page<Board> boards = boardRepository.findAll(boardType,
                PageRequest.of(limit - 1, 6, Sort.by(Sort.Direction.DESC, "id")));

        return boards.stream().map(board -> {
            Integer totalComment = board.getCommentList().size();
            Integer totalLike = board.getLikeList().size();
            return new BoardResponse(board, totalComment, totalLike);
        }).collect(Collectors.toList());
    }

    @Transactional
    public BoardResponse findById(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
        board.addView();
        Integer totalLike = board.getLikeList().size();
        boolean likeStatus = boardLikeRepository.existsByBoardIdAndUserId(boardId, UserContext.getCurrentUserId());
        return new BoardResponse(board, totalLike, likeStatus);
    }

    @Transactional(readOnly = true)
    public List<BoardResponse> findByUserId(Long userId) {
        return boardRepository.findByUserId(userId).stream().map(board -> {
            Integer totalComment = board.getCommentList().size();
            Integer totalLike = board.getLikeList().size();
            return new BoardResponse(board, totalComment, totalLike);
        }).collect(Collectors.toList());
    }

    @Transactional
    public Long save(BoardSaveRequest requestDto) {
        User user = userRepository.findById(UserContext.getCurrentUserId())
                .orElseThrow(BoardNotFoundException::new);
        return boardRepository.save(requestDto.of(user)).getId();
    }

    @Transactional
    public Long update(Long boardId, BoardUpdateRequest requestDto) {
        boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new)
                .update(requestDto.getTitle(), requestDto.getContent());
        return boardId;
    }

    @Transactional
    public Long delete(Long boardId) {
        boardRepository.deleteById(boardId);
        return boardId;
    }
}

