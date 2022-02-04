package com.moram.ssafe.service.board;

import com.moram.ssafe.domain.board.Board;
import com.moram.ssafe.domain.board.BoardCommentRepository;
import com.moram.ssafe.domain.board.BoardRepository;
import com.moram.ssafe.dto.board.BoardCommentResponse;
import com.moram.ssafe.dto.board.BoardCommentSaveRequest;
import com.moram.ssafe.dto.board.BoardCommentUpdateRequest;
import com.moram.ssafe.exception.board.BoardCommentNotFoundException;
import com.moram.ssafe.exception.board.BoardNotFoundException;
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

    @Transactional
    public Long createBoardComment(BoardCommentSaveRequest request){ //고치기. board.getUser가 아니라 현재 user를 넣어야 함.
        Board board = boardRepository.findBoard(request.getBoardId())
                .orElseThrow(BoardNotFoundException::new);

        return boardCommentRepository.save(request.from(board.getUser(), board, request.getContent())).getId();
    }

    public List<BoardCommentResponse> findBoardCommentList(Long boardId){
        return boardCommentRepository.
                findBoardCommentList(boardId).stream().map(BoardCommentResponse::from).collect(Collectors.toList());
    }

    @Transactional
    public Long updateBoardComment(Long commentId, BoardCommentUpdateRequest request){ //CUD에 유저 검증 다 걸기
        boardCommentRepository
                .findById(commentId).orElseThrow(BoardCommentNotFoundException::new).update(request.getContent());
        return commentId;
    }

    @Transactional
    public Long deleteBoardComment(Long commentId){
        boardCommentRepository.deleteById(commentId);
        return commentId;
    }
}
