package com.moram.ssafe.service.study;


import com.moram.ssafe.domain.study.Study;
import com.moram.ssafe.domain.study.StudyComment;
import com.moram.ssafe.domain.study.StudyCommentRepository;
import com.moram.ssafe.domain.study.StudyRepository;
import com.moram.ssafe.domain.user.User;
import com.moram.ssafe.domain.user.UserRepository;
import com.moram.ssafe.dto.study.StudyCommentResponse;
import com.moram.ssafe.dto.study.StudyCommentSaveRequest;
import com.moram.ssafe.dto.study.StudyCommentUpdateRequest;
import com.moram.ssafe.exception.auth.UserAuthenticationException;
import com.moram.ssafe.exception.study.StudyCommentNotFound;
import com.moram.ssafe.exception.study.StudyNotFoundException;
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
public class StudyCommentService {

    private final StudyCommentRepository studyCommentRepository;
    private final StudyRepository studyRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long createStudyComment(Long userId,StudyCommentSaveRequest request){
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        Study study = studyRepository.findStudy(request.getStudyId()).orElseThrow(StudyNotFoundException::new);

        return studyCommentRepository
                .save(StudyCommentSaveRequest.from(user, study, request.getContent())).getId();
    }

    public List<StudyCommentResponse> findStudyComment(Long studyId){
        return studyCommentRepository.findStudyComment(studyId).stream()
                .map(StudyCommentResponse::from).collect(Collectors.toList());
    }

    @Transactional
    public Long updateStudyComment(Long userId,Long commentId, StudyCommentUpdateRequest request){
        StudyComment comment = studyCommentRepository.findById(commentId).orElseThrow(StudyCommentNotFound::new);

        validCommentUser(userId, comment.getUser().getId());

        comment.update(request.getContent());
        return commentId;
    }

    @Transactional
    public Long deleteStudyComment(Long userId,Long commentId){
        StudyComment comment = studyCommentRepository.findById(commentId).orElseThrow(StudyCommentNotFound::new);

        validCommentUser(userId, comment.getUser().getId());

        studyCommentRepository.deleteById(commentId);
        return commentId;
    }

    public void validCommentUser(Long currentUser, Long commentUser){
        if(currentUser == commentUser)
            return;
        throw new UserAuthenticationException();
    }
}
