package com.moram.ssafe.service.study;

import com.moram.ssafe.domain.study.Study;
import com.moram.ssafe.domain.study.StudyRepository;
import com.moram.ssafe.domain.study.StudyScrap;
import com.moram.ssafe.domain.study.StudyScrapRepository;
import com.moram.ssafe.dto.study.StudyResponse;
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
@Transactional
public class StudyScrapService {

    private final StudyScrapRepository studyScrapRepository;
    private final StudyRepository studyRepository;

    public List<StudyResponse> findUserScrap(Long userId){
        return studyScrapRepository.findUserScrap(userId).stream()
                .map(scrap -> StudyResponse.of(scrap)).collect(Collectors.toList());

    }

    public Boolean pushScrap(Long studyId){
        Study study = studyRepository.findStudy(studyId).orElseThrow(StudyNotFoundException::new);
        studyScrapRepository.save(StudyScrap.builder().user(study.getUser()).study(study).build());
        return true;
    }

    public Boolean deleteScrap(Long scrapId){
        studyScrapRepository.deleteById(scrapId);
        return false;
    }
}
