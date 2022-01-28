package com.moram.ssafe.domain.recruit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitScrapRepository extends JpaRepository<RecruitScrap, Long> {



//    @Query(value = "select r.recruit_id from tbl_recruit_scrap r " +
//            "where r.user_id = :userId", nativeQuery = true)
//    List<Long> findByUserScrap(@Param("userId") Long userId);

}
