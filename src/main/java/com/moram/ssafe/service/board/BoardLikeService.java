package com.moram.ssafe.service.board;

import com.moram.ssafe.controller.user.annotation.UserContext;
import com.moram.ssafe.domain.board.Board;
import com.moram.ssafe.domain.board.BoardLike;
import com.moram.ssafe.domain.board.BoardLikeRepository;
import com.moram.ssafe.domain.board.BoardRepository;
import com.moram.ssafe.domain.user.User;
import com.moram.ssafe.domain.user.UserRepository;
import com.moram.ssafe.exception.auth.UserAuthenticationException;
import com.moram.ssafe.exception.board.BoardLikeNotFoundException;
import com.moram.ssafe.exception.board.BoardNotFoundException;
import com.moram.ssafe.exception.user.UserNotFoundException;
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
    private final UserRepository userRepository;

    public Boolean pushLike(Long boardId){
        Long userId = UserContext.getCurrentUserId();
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        Board board = allBoardRepository.findBoard(boardId).orElseThrow(BoardNotFoundException::new);

        boardLikeRepository.save(BoardLike.builder().user(user).board(board).build()).getId();
        return true;
    }

    public Boolean deleteLike(Long likeId){
        Long userId = UserContext.getCurrentUserId();

        BoardLike like = boardLikeRepository.findById(likeId).orElseThrow(BoardLikeNotFoundException::new);

        if(userId != like.getUser().getId())
            throw new UserAuthenticationException();

        boardLikeRepository.deleteById(likeId);
        return false;
    }
}
