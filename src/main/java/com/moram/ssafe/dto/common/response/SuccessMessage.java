package com.moram.ssafe.dto.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SuccessMessage {
    //User
    public static String SUCCESS_POST_LOGIN = "Login 성공.";
    public static String SUCCESS_GET_USER_PROFILE = "User Profile 조회 성공";
    public static String SUCCESS_UPDATE_USER_ADD_AUTH = "User 추가 인증 업데이트 성공";
    public static String SUCCESS_DELETE_USER = "User 삭제 성공";
    public static String SUCCESS_WAITING_AUTH_USER = "인증대기 사용자 조회 성공";

    //Recruit
    public static String SUCCESS_GET_RECRUIT = "채용공고 조회 성공";
    public static String SUCCESS_GET_RECRUIT_LIST = "채용공고 리스트 조회 성공";
    public static String SUCCESS_POST_RECRUIT = "채용공고 등록 성공";
    public static String SUCCESS_PUT_RECRUIT = "채용공고 수정 성공";
    public static String SUCCESS_DELETE_RECRUIT = "채용공고 삭제 성공";
    public static String SUCCESS_GET_RECRUIT_SEARCH_LIST = "채용공고 검색 리스트 성공";
}
