package com.moram.ssafe.service.study;


import com.moram.ssafe.domain.study.*;
import com.moram.ssafe.domain.user.User;
import com.moram.ssafe.domain.user.UserRepository;
import com.moram.ssafe.dto.study.StudyRequestDto;
import com.moram.ssafe.dto.study.StudyResponse;
import com.moram.ssafe.dto.study.StudyScrapResponse;
import com.moram.ssafe.dto.study.StudySearch;
import com.moram.ssafe.exception.auth.UserAuthenticationException;
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
    private final StudyQueryRepository studyQueryRepository;
    private final StudyCommentRepository studyCommentRepository;
    private final StudyScrapQueryRepository studyScrapQueryRepository;

    @Transactional
    public Long createStudy(Long userId, StudyRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        return studyRepository.save(request.toStudy(user)).getId();
    }

    public List<StudyResponse> findStudyList(int offset) {
        Page<Study> studies = studyRepository.findAll(
                PageRequest.of(offset - 1, 12, Sort.by(Sort.Direction.DESC, "createdDate")));
        return studies.stream().map(StudyResponse::from).collect(Collectors.toList());
    }

    public List<StudyResponse> findCompanyNameStudy(String companyName) {
        List<Study> studies = studyQueryRepository.findCompanyNameStudy(companyName);
        return studies.stream().map(StudyResponse::from).collect(Collectors.toList());
    }

    @Transactional
    public StudyResponse findStudy(Long userId, Long studyId) {
        Study study = studyRepository.findStudy(studyId).orElseThrow(StudyNotFoundException::new);
        study.addView();
        Boolean scrapStatus = studyScrapRepository.existsByStudyIdAndUserId(studyId, userId);
        return new StudyResponse(study, scrapStatus);
    }

    public List<StudyResponse> findUserStudy(Long userId, int offset) {
        Page<Study> studies = studyRepository.findUserStudy(userId,
                PageRequest.of(offset - 1, 12, Sort.by(Sort.Direction.DESC, "createdDate")));
        return studies.stream().map(StudyResponse::from).collect(Collectors.toList());
    }

    public List<StudyResponse> findByStudyNameAndType(int offset, StudySearch studySearch) {

        return studyQueryRepository.findByTitleAndTypeContaining(studySearch,
                PageRequest.of(offset - 1, 12))
                .stream().map(StudyResponse::from).collect(Collectors.toList());
    }

    public List<StudyResponse> findByLotsOfView(int offset) {
        Page<Study> studies = studyRepository.findAll(
                PageRequest.of(offset - 1, 12, Sort.by(Sort.Direction.DESC, "views")));
        return studies.stream().map(StudyResponse::from).collect(Collectors.toList());
    }

    public List<StudyResponse> findByLotsOfScrap(int offset) {
        Page<Study> studies = studyRepository.findByLotsOfScrap(
                PageRequest.of(offset - 1, 12, Sort.by(Sort.Direction.DESC, "createdDate")));
        return studies.stream().map(StudyResponse::from).collect(Collectors.toList());
    }

    public List<StudyResponse> findByUserComments(Long userId) {
        return studyQueryRepository.findByUserComment(userId)
                .stream().map(StudyResponse::from).collect(Collectors.toList());
    }

    public List<StudyResponse> findUserScrap(Long userId, int offset) {

        return studyScrapQueryRepository.findByUserScrap(userId,
                PageRequest.of(offset - 1, 12, Sort.by(Sort.Direction.DESC, "createdDate")))
                .stream().map(StudyResponse::from).collect(Collectors.toList());
    }

    @Transactional
    public StudyScrapResponse toggleStudyScraps(Long userId, Long studyId) {
        Study study = studyRepository.findStudy(studyId).orElseThrow(StudyNotFoundException::new);
        StudyScrap studyScrap = StudyScrap.builder().userId(userId).study(study).build();

        return StudyScrapResponse.from(study.getStudyScraps().toggleStudyScraps(studyScrap));

    }

    @Transactional
    public Long updateStudy(Long userId, Long studyId, StudyRequestDto request) {
        Study study = studyRepository.findById(studyId).orElseThrow(StudyNotFoundException::new);

        validStudyUser(userId, study.getUser().getId());

        study.update(request);
        return studyId;
    }

    @Transactional
    public Boolean updateRecruitment(Long userId, Long studyId) {
        Study study = studyRepository.findById(studyId).orElseThrow(StudyNotFoundException::new);
        validStudyUser(userId, study.getUser().getId());
        if (study.getRecruitment()) {
            return study.updateRecruitment(false);
        }
        return study.updateRecruitment(true);
    }

    @Transactional
    public Long deleteStudy(Long userId, Long studyId) {
        Study study = studyRepository.findById(studyId).orElseThrow(StudyNotFoundException::new);

        validStudyUser(userId, study.getUser().getId());

        studyCommentRepository.deleteByStudyId(studyId);
        studyScrapRepository.deleteByStudyId(studyId);
        studyRepository.deleteById(studyId);
        return studyId;
    }

    public void validStudyUser(Long currentUser, Long studyUser) {
        if (currentUser == studyUser)
            return;
        throw new UserAuthenticationException();
    }

}
