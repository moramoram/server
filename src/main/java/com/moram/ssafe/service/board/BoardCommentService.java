package com.moram.ssafe.service.board;

import com.moram.ssafe.domain.board.Board;
import com.moram.ssafe.domain.board.BoardComment;
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
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long createBoardComment(Long userId, BoardCommentSaveRequest request) {

        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        Board board = boardRepository.findBoard(request.getBoardId())
                .orElseThrow(BoardNotFoundException::new);

        return boardCommentRepository.save(request.from(user, board, request.getContent())).getId();
    }

    public List<BoardCommentResponse> findBoardCommentList(Long boardId) {
        return boardCommentRepository.findBoardCommentList(boardId).stream()
                .map(BoardCommentResponse::from).collect(Collectors.toList());
    }

    @Transactional
    public Long updateBoardComment(Long userId, Long commentId, BoardCommentUpdateRequest request) {
        BoardComment comment = boardCommentRepository.findById(commentId)
                .orElseThrow(BoardCommentNotFoundException::new);

        validCommentUser(userId, comment.getUser().getId());

        comment.update(request.getContent());

        return commentId;
    }

    @Transactional
    public Long deleteBoardComment(Long userId, Long commentId) {
        BoardComment comment = boardCommentRepository.findById(commentId)
                .orElseThrow(BoardCommentNotFoundException::new);

        validCommentUser(userId, comment.getUser().getId());

        boardCommentRepository.deleteById(commentId);

        return commentId;
    }

    public void validCommentUser(Long currentUser, Long commentUser) {
        if (currentUser == commentUser)
            return;
        throw new UserNotFoundException();
    }
}
