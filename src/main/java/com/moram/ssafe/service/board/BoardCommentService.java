package com.moram.ssafe.service.board;

import com.moram.ssafe.controller.user.annotation.UserContext;
import com.moram.ssafe.domain.board.Board;
import com.moram.ssafe.domain.board.BoardCommentRepository;
import com.moram.ssafe.domain.board.BoardRepository;
import com.moram.ssafe.domain.user.User;
import com.moram.ssafe.domain.user.UserRepository;
import com.moram.ssafe.dto.board.BoardCommentResponse;
import com.moram.ssafe.dto.board.BoardCommentSaveRequest;
import com.moram.ssafe.dto.board.BoardCommentUpdateRequest;
import com.moram.ssafe.exception.board.BoardCommentNotFoundException;
import com.moram.ssafe.exception.board.BoardNotFoundException;
import com.moram.ssafe.exception.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardCommentService {
    private final BoardCommentRepository boardCommentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardCommentSaveRequest request){
        User user = userRepository.findById(UserContext.getCurrentUserId()).orElseThrow(UserNotFoundException::new);
        Board board = boardRepository.findById(request.getBoardId()).orElseThrow(BoardNotFoundException::new);
        return boardCommentRepository.save(request.from(user, board, request.getContent())).getId();
    }

    public List<BoardCommentResponse> findByBoardId(Long boardId){
        return boardCommentRepository.
                findByBoardId(boardId).stream().map(comment -> BoardCommentResponse.from(comment)).collect(Collectors.toList());
    }

    public List<BoardCommentResponse> findByUserId(Long userId){
        return boardCommentRepository
                .findByUserId(userId).stream().map(comment -> BoardCommentResponse.from(comment)).collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long commentId, BoardCommentUpdateRequest request){
        boardCommentRepository
                .findById(commentId).orElseThrow(BoardCommentNotFoundException::new).update(request.getContent());
        return commentId;
    }

    @Transactional
    public Long delete(Long commentId){
        boardCommentRepository.deleteById(commentId);
        return commentId;
    }
}
