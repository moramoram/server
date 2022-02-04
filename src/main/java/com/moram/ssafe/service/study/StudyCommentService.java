package com.moram.ssafe.service.study;


import com.moram.ssafe.domain.study.Study;
import com.moram.ssafe.domain.study.StudyCommentRepository;
import com.moram.ssafe.domain.study.StudyRepository;
import com.moram.ssafe.dto.study.StudyCommentResponse;
import com.moram.ssafe.dto.study.StudyCommentSaveRequest;
import com.moram.ssafe.dto.study.StudyCommentUpdateRequest;
import com.moram.ssafe.exception.study.StudyCommentNotFound;
import com.moram.ssafe.exception.study.StudyNotFoundException;
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

    @Transactional
    public Long createStudyComment(StudyCommentSaveRequest request){
        Study study = studyRepository.findStudy(request.getStudyId()).orElseThrow(StudyNotFoundException::new);
        return studyCommentRepository.save(StudyCommentSaveRequest.from(study.getUser(), study, request.getContent())).getId();
    }

    public List<StudyCommentResponse> findStudyComment(Long studyId){
        return studyCommentRepository.findStudyComment(studyId).stream()
                .map(StudyCommentResponse::from).collect(Collectors.toList());
    }

    @Transactional
    public Long updateStudyComment(Long commentId, StudyCommentUpdateRequest request){
        studyCommentRepository
                .findById(commentId).orElseThrow(StudyCommentNotFound::new).update(request.getContent());
        return commentId;
    }

    @Transactional
    public Long deleteStudyComment(Long commentId){
        studyCommentRepository.deleteById(commentId);
        return commentId;
    }
}
