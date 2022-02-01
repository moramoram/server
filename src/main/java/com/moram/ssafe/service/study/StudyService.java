package com.moram.ssafe.service.study;


import com.moram.ssafe.controller.user.annotation.UserContext;
import com.moram.ssafe.domain.study.Study;
import com.moram.ssafe.domain.study.StudyRepository;
import com.moram.ssafe.domain.study.StudyScrapRepository;
import com.moram.ssafe.domain.user.User;
import com.moram.ssafe.domain.user.UserRepository;
import com.moram.ssafe.dto.study.StudyResponse;
import com.moram.ssafe.dto.study.StudySaveRequest;
import com.moram.ssafe.dto.study.StudyUpdateRequest;
import com.moram.ssafe.exception.study.StudyNotFoundException;
import com.moram.ssafe.exception.user.UserNotFoundException;
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
@Transactional(readOnly = true)
public class StudyService {

    private final StudyRepository studyRepository;
    private final StudyScrapRepository studyScrapRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long save(StudySaveRequest request){
        User user = userRepository.findById(UserContext.getCurrentUserId())
                .orElseThrow(UserNotFoundException::new);
        return studyRepository.save(request.of(user)).getId();
    }

    public List<StudyResponse> findAll(int limit){

        Page<Study> studies = studyRepository.findAll(
                PageRequest.of(limit - 1, 6, Sort.by(Sort.Direction.DESC, "createdDate")));

        return PageToResponse(studies);
    }

    @Transactional
    public StudyResponse findById(Long studyId){
        Study study = studyRepository.findById(studyId).orElseThrow(StudyNotFoundException::new);
        study.addView();
        boolean scrapStatus = studyScrapRepository.existsByStudyIdAndUserId(studyId, UserContext.getCurrentUserId());
        return new StudyResponse(study, scrapStatus);
    }

    public List<StudyResponse> findByUserId(Long userId){
        return studyRepository.findByUserId(userId).stream()
                .map(study -> {
                    Integer totalComment = study.getCommentList().size();
                    return new StudyResponse(study, totalComment);
                }).collect(Collectors.toList());
    }

    public List<StudyResponse> findByStudyName(String name, int limit){
        Page<Study> studies = studyRepository.findByTitleContaining(name,
                PageRequest.of(limit - 1, 6, Sort.by(Sort.Direction.DESC, "createdDate")));
        return PageToResponse(studies);
    }

    public List<StudyResponse> findByLotsOfView(int limit){
        Page<Study> studies = studyRepository.findAll(
                PageRequest.of(limit - 1, 6, Sort.by(Sort.Direction.DESC, "views")));
        return PageToResponse(studies);
    }

    public List<StudyResponse> findByLotsOfScrap(int limit){
        Page<Study> studies = studyRepository.findByLotsOfScrap(
                PageRequest.of(limit - 1, 6));
        return PageToResponse(studies);
    }

    @Transactional
    public Long update(Long studyId, StudyUpdateRequest request){
        studyRepository.findById(studyId).orElseThrow(StudyNotFoundException::new)
                .update(request);

        return studyId;
    }

    @Transactional
    public Long delete(Long studyId){
        studyRepository.deleteById(studyId);
        return studyId;
    }

    public List<StudyResponse> PageToResponse(Page<Study> studies){
        return studies.stream().map(study -> {
            Integer totalComment = study.getCommentList().size();
            return new StudyResponse(study, totalComment);
        }).collect(Collectors.toList());
    }
}
