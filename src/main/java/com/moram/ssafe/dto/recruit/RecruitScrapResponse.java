package com.moram.ssafe.dto.recruit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecruitScrapResponse {
    private boolean scrap;

    public static RecruitScrapResponse from(boolean scrap){
        return new RecruitScrapResponse(scrap);
    }
}
