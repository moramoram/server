package com.moram.ssafe.domain.recruit;

import com.moram.ssafe.exception.recruit.RecruitScrapRemovalFailureException;
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
public class RecruitScraps {

    @OneToMany(mappedBy = "recruit", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private final List<RecruitScrap> recruitScraps = new ArrayList<>();

    private RecruitScraps(List<RecruitScrap> recruitScraps){
        addRecruitList(recruitScraps);
    }

    private void addRecruitList(List<RecruitScrap> recruitScraps) {
        for (RecruitScrap recruitScrap : recruitScraps) {
            addRecruit(recruitScrap);
        }
    }

    private void addRecruit(RecruitScrap recruitScrap) {
        recruitScraps.add(recruitScrap);
    }

    public void addRecruits(RecruitScraps recruitScraps) {
        addRecruitList(recruitScraps.recruitScraps);
    }

    private boolean contains(RecruitScrap recruitScrap) {
        Long userId = recruitScrap.getUserId();
        return contains(userId);
    }

    public boolean toggleRecruitScrap(RecruitScrap recruitScrap) {
        if (contains(recruitScrap)) {
            removeRecruitScrap(recruitScrap);
            return false;
        }
        recruitScraps.add(recruitScrap);
        return true;
    }

    public boolean contains(Long userId) {
        return recruitScraps.parallelStream()
                .anyMatch(h -> h.ownedBy(userId));
    }

    public int size() {
        return recruitScraps.size();
    }

    private void removeRecruitScrap(RecruitScrap recruitScrap) {
        Long userId = recruitScrap.getUserId();
        RecruitScrap removalTarget = recruitScraps.parallelStream()
                .filter(h -> h.ownedBy(userId))
                .findAny()
                .orElseThrow(RecruitScrapRemovalFailureException::new);
        recruitScraps.remove(removalTarget);
    }
}
