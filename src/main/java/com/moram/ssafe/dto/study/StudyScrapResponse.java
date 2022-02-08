package com.moram.ssafe.dto.study;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudyScrapResponse {
    private boolean scrapStatus;

    public static StudyScrapResponse from(boolean scrap){
        return new StudyScrapResponse(scrap);
    }
}
