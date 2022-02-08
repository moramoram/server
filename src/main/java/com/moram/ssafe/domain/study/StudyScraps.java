package com.moram.ssafe.domain.study;

import com.moram.ssafe.exception.study.StudyScrapRemovalFailure;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Embeddable
public class StudyScraps {
    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyScrap> studyScraps = new ArrayList<>();

    public boolean toggleStudyScraps(StudyScrap studyScrap){
        if(contains(studyScrap.getUserId())){
            removeStudyScrap(studyScrap);
            return false;
        }
        studyScraps.add(studyScrap);
        return true;
    }

    public boolean contains(Long userId){
        return studyScraps.parallelStream()
                .anyMatch(s -> s.ownedBy(userId));
    }

    private void removeStudyScrap(StudyScrap studyScrap){
        Long userId = studyScrap.getUserId();
        StudyScrap removalTarget = studyScraps.parallelStream()
                .filter(s -> s.ownedBy(userId))
                .findAny().orElseThrow(StudyScrapRemovalFailure::new);
        studyScraps.remove(removalTarget);
    }

}
