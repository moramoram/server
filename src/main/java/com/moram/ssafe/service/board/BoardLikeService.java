package com.moram.ssafe.service.board;

import com.moram.ssafe.domain.board.Board;
import com.moram.ssafe.domain.board.BoardLike;
import com.moram.ssafe.domain.board.BoardLikeRepository;
import com.moram.ssafe.domain.board.BoardRepository;
import com.moram.ssafe.domain.user.User;
import com.moram.ssafe.domain.user.UserRepository;
import com.moram.ssafe.dto.board.BoardLikeResponse;
import com.moram.ssafe.exception.board.BoardNotFoundException;
import com.moram.ssafe.exception.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BoardLikeService {

    private final BoardLikeRepository boardLikeRepository;
    private final UserRepository userRepository;
    private final BoardRepository allBoardRepository;

    @Transactional(readOnly = true)
    public List<BoardLikeResponse> findByUserId(Long userId){
        List<BoardLike> findLike = boardLikeRepository.findByUserId(userId);
        List<BoardLikeResponse> list = new ArrayList<>();
        for (BoardLike boardLike : findLike) {
            Long likedBoardId = boardLike.getBoard().getId();
            list.add(BoardLikeResponse.of(likedBoardId));
        }
        return list;
    }

    public Boolean pushLike(Long userId, Long boardId){
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Board board = allBoardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
        boardLikeRepository.save(BoardLike.builder().user(user).board(board).build()).getId();
        return true;
    }

    public Boolean deleteLike(Long likeId){
        boardLikeRepository.deleteById(likeId);
        return false;
    }
}
