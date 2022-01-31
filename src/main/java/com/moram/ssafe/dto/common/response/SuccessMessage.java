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
    public static String SUCCESS_PUT_RECRUIT_SCRAP = "채용공고 스크랩 성공";
    public static String SUCCESS_GET_RECRUIT_SCRAP_LIST = "사용자 스크랩 리스트 조회 성공";

    //Company
    public static String SUCCESS_POST_COMPANY = "회사 등록 성공";
    public static String SUCCESS_GET_COMPANY = "회사 조회 성공";
    public static String SUCCESS_GET_COMPANY_LIST = "회사리스트 조회 성공";
    public static String SUCCESS_PUT_COMPANY = "회사 수정 성공";
    public static String SUCCESS_DELETE_COMPANY = "회사 삭제 성공";
    public static String SUCCESS_GET_COMPANY_COMMENT_LIST = "회사 댓글리스트 조회 성공";
    public static String SUCCESS_GET_COMPANY_NAME = "회사명 조회 성공";
    public static String SUCCESS_PUT_COMPANY_NAME = "회사명 수정 성공";
    public static String SUCCESS_PUT_COMPANY_LOGO = "회사 로고이미지 수정 성공";

    //Comment
    public static String SUCCESS_POST_COMMENT = "댓글 등록 성공";
    public static String SUCCESS_GET_COMMENT_LIST = "댓글 리스트 조회 성공";
    public static String SUCCESS_PUT_COMMENT = "댓글 수정 성공";
    public static String SUCCESS_DELETE_COMMENT = "댓글 삭제 성공";

    //Board
    public static String SUCCESS_GET_BOARD = "게시판 상세 조회 성공";
    public static String SUCCESS_GET_BOARD_LIST = "게시판 리스트 조회 성공";
    public static String SUCCESS_GET_BOARD_LIST_USER = "사용자 작성 게시판 리스트 조회 성공";
    public static String SUCCESS_POST_BOARD = "게시물 등록 성공";
    public static String SUCCESS_UPDATE_BOARD = "게시물 수정 성공";
    public static String SUCCESS_DELETE_BOARD = "게시물 삭제 성공";

    //Like
    public static String SUCCESS_PUSH_LIKE = "좋아요 표시 성공";
    public static String SUCCESS_DELETE_LIKE = "좋아요 삭제 성공";
    public static String SUCCESS_GET_LIKE = "좋아요 조회 성공";

    //Study
    public static String SUCCESS_GET_STUDY = "스터디 상세 조회 성공";
    public static String SUCCESS_GET_STUDY_LIST = "스터디 리스트 조회 성공";
    public static String SUCCESS_GET_STUDY_LIST_USER = "사용자 작성 스터디 리스트 조회 성공";
    public static String SUCCESS_POST_STUDY = "스터디 등록 성공";
    public static String SUCCESS_UPDATE_STUDY = "스터디 수정 성공";
    public static String SUCCESS_DELETE_STUDY = "스터디 삭제 성공";

    //Scrap
    public static String SUCCESS_PUSH_SCRAP = "스크랩 성공";
    public static String SUCCESS_DELETE_SCRAP = "스크랩 삭제 성공";
    public static String SUCCESS_GET_STUDY_SCRAP_LIST = "스크랩 게시물 리스트 조회 성공";
}
