package com.moram.ssafe.service.board;

import com.moram.ssafe.domain.board.Board;
import com.moram.ssafe.domain.board.BoardLike;
import com.moram.ssafe.domain.board.BoardLikeRepository;
import com.moram.ssafe.domain.board.BoardRepository;
import com.moram.ssafe.domain.user.UserRepository;
import com.moram.ssafe.exception.board.BoardNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BoardLikeService {

    private final BoardLikeRepository boardLikeRepository;
    private final BoardRepository allBoardRepository;

    public Boolean pushLike(Long boardId){
        Board board = allBoardRepository.findBoard(boardId).orElseThrow(BoardNotFoundException::new);
        boardLikeRepository.save(BoardLike.builder().user(board.getUser()).board(board).build()).getId();
        return true;
    }

    public Boolean deleteLike(Long likeId){
        boardLikeRepository.deleteById(likeId);
        return false;
    }
}
