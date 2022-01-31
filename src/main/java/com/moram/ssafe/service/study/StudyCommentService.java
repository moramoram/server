package com.moram.ssafe.service.study;


import com.moram.ssafe.controller.user.annotation.UserContext;
import com.moram.ssafe.domain.study.Study;
import com.moram.ssafe.domain.study.StudyCommentRepository;
import com.moram.ssafe.domain.study.StudyRepository;
import com.moram.ssafe.domain.user.User;
import com.moram.ssafe.domain.user.UserRepository;
import com.moram.ssafe.dto.study.StudyCommentResponse;
import com.moram.ssafe.dto.study.StudyCommentSaveRequest;
import com.moram.ssafe.dto.study.StudyCommentUpdateRequest;
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
    public Long save(StudyCommentSaveRequest request){
        User user = userRepository.findById(UserContext.getCurrentUserId()).orElseThrow(UserNotFoundException::new);
        Study study = studyRepository.findById(request.getStudyId()).orElseThrow(StudyNotFoundException::new);
        return studyCommentRepository.save(StudyCommentSaveRequest.from(user, study, request.getContent())).getId();
    }

    public List<StudyCommentResponse> findByStudyId(Long studyId){
        return studyCommentRepository.findByStudyId(studyId).stream()
                .map(comment -> StudyCommentResponse.from(comment)).collect(Collectors.toList());
    }

    public List<StudyCommentResponse> findByUserId(Long userId){
        return studyCommentRepository.findByUserId(userId).stream()
                .map(comment -> StudyCommentResponse.from(comment)).collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long commentId, StudyCommentUpdateRequest request){
        studyCommentRepository
                .findById(commentId).orElseThrow(StudyCommentNotFound::new).update(request.getContent());
        return commentId;
    }

    @Transactional
    public Long delete(Long commentId){
        studyCommentRepository.deleteById(commentId);
        return commentId;
    }


}
