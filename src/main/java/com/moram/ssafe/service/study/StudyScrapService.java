package com.moram.ssafe.service.study;

import com.moram.ssafe.controller.user.annotation.UserContext;
import com.moram.ssafe.domain.study.Study;
import com.moram.ssafe.domain.study.StudyRepository;
import com.moram.ssafe.domain.study.StudyScrap;
import com.moram.ssafe.domain.study.StudyScrapRepository;
import com.moram.ssafe.domain.user.User;
import com.moram.ssafe.domain.user.UserRepository;
import com.moram.ssafe.dto.study.StudyResponse;
import com.moram.ssafe.exception.auth.UserAuthenticationException;
import com.moram.ssafe.exception.study.StudyNotFoundException;
import com.moram.ssafe.exception.study.StudyScrapNotFound;
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
@Transactional
public class StudyScrapService {

    private final StudyScrapRepository studyScrapRepository;
    private final StudyRepository studyRepository;
    private final UserRepository userRepository;

    public List<StudyResponse> findUserScrap(Long userId, int offset){

        Page<StudyScrap> studies = studyScrapRepository.findUserScrap(userId,
                PageRequest.of(offset - 1, 12, Sort.by(Sort.Direction.DESC, "id")));

        return studies.stream().map(StudyResponse::from).collect(Collectors.toList());
    }

    public Boolean pushScrap(Long studyId){
        Long userId = UserContext.getCurrentUserId();
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Study study = studyRepository.findStudy(studyId).orElseThrow(StudyNotFoundException::new);

        studyScrapRepository.save(StudyScrap.builder().user(user).study(study).build());
        return true;
    }

    public Boolean deleteScrap(Long scrapId){
        Long userId = UserContext.getCurrentUserId();

        StudyScrap studyScrap = studyScrapRepository.findById(scrapId).orElseThrow(StudyScrapNotFound::new);

        if(userId != studyScrap.getUser().getId())
            throw new UserAuthenticationException();

        studyScrapRepository.deleteById(scrapId);
        return false;
    }
}
