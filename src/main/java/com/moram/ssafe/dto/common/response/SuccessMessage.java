package com.moram.ssafe.dto.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SuccessMessage {

    //User
    public static String SUCCESS_POST_LOGIN = "Login 성공.";
    public static String SUCCESS_GET_USER_LIST = "User List 조회 성공";
    public static String SUCCESS_GET_USER_PROFILE = "User Profile 조회 성공";
    public static String SUCCESS_GET_USER_CHECK_NICKNAME = "User 닉네임 체크 결과";
    public static String SUCCESS_UPDATE_USER_NICKNAME = "User 닉네임 수정 성공";
    public static String SUCCESS_UPDATE_USER_PROFILE_IMG = "User 프로필 이미지 수정 성공";
    public static String SUCCESS_UPDATE_USER_ADD_AUTH = "User 추가 인증 업데이트 성공";
    public static String SUCCESS_DELETE_USER = "User 삭제 성공";
    public static String SUCCESS_WAITING_AUTH_USER = "인증대기 사용자 조회 성공";
    public static String SUCCESS_SSAFE_AUTH_USER = "SSAFE 승인 성공";
    public static String SUCCESS_PUT_REFRESH = "User Refresh token 발급";

    //Recruit
    public static String SUCCESS_GET_RECRUIT = "채용공고 조회 성공";
    public static String SUCCESS_GET_RECRUIT_LIST = "채용공고 리스트 조회 성공";
    public static String SUCCESS_GET_RECRUIT_BENEFIT = "싸피우대 채용공고 리스트 조회 성공";
    public static String SUCCESS_GET_RECRUIT_LATEST = "채용공고 최신순 리스트 조회 성공";
    public static String SUCCESS_GET_RECRUIT_POPULARITY = "채용공고 인기순 리스트 조회 성공";
    public static String SUCCESS_GET_RECRUIT_CLOSE_DATE = "채용공고 마감임박순 리스트 조회 성공";
    public static String SUCCESS_POST_RECRUIT = "채용공고 등록 성공";
    public static String SUCCESS_PUT_RECRUIT = "채용공고 수정 성공";
    public static String SUCCESS_DELETE_RECRUIT = "채용공고 삭제 성공";
    public static String SUCCESS_GET_RECRUIT_SEARCH_LIST = "채용공고 검색 리스트 성공";
    public static String SUCCESS_GET_RECRUIT_TITLE_TECH = "채용공고 제목 기술스택 검색 성공";
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
    public static String SUCCESS_GET_USER_COMPANY_LIST = "댓글 단 회사리스트 조회 성공";

    //Board
    public static String SUCCESS_GET_BOARD = "게시판 상세 조회 성공";
    public static String SUCCESS_GET_BOARD_LIST = "게시판 리스트 최신순 조회 성공";
    public static String SUCCESS_GET_BOARD_LIST_USER = "사용자 작성 게시판 리스트 조회 성공";
    public static String SUCCESS_POST_BOARD = "게시물 등록 성공";
    public static String SUCCESS_UPDATE_BOARD = "게시물 수정 성공";
    public static String SUCCESS_DELETE_BOARD = "게시물 삭제 성공";
    public static String SUCCESS_GET_BOARD_NAME = "게시물명 검색 조회 성공";
    public static String SUCCESS_GET_BOARD_VIEW = "게시물 조회순 조회 성공";
    public static String SUCCESS_GET_BOARD_LIKE = "게시물 좋아요순 조회 성공";
    public static String SUCCESS_GET_BOARD_LIST_COMMENTS = "사용자가 댓글 단 게시물 리스트 조회 성공";

    //Like
    public static String SUCCESS_PUT_LIKE = "좋아요 수정 성공";


    //Study
    public static String SUCCESS_GET_STUDY = "스터디 상세 조회 성공";
    public static String SUCCESS_GET_STUDY_LIST = "스터디 리스트 최신순 조회 성공";
    public static String SUCCESS_GET_STUDY_LIST_USER = "사용자 작성 스터디 리스트 조회 성공";
    public static String SUCCESS_POST_STUDY = "스터디 등록 성공";
    public static String SUCCESS_UPDATE_STUDY = "스터디 수정 성공";
    public static String SUCCESS_DELETE_STUDY = "스터디 삭제 성공";
    public static String SUCCESS_GET_STUDY_NAME = "스터디명 검색 조회 성공";
    public static String SUCCESS_GET_STUDY_VIEWS = "스터디 조회순 조회 성공";
    public static String SUCCESS_GET_STUDY_SCRAP = "스터디명 스크랩순 조회 성공";
    public static String SUCCESS_GET_STUDY_LIST_COMMENTS = "사용자가 댓글 단 스터디 리스트 조회 성공";

    //Scrap
    public static String SUCCESS_PUSH_SCRAP = "스크랩 성공";
    public static String SUCCESS_DELETE_SCRAP = "스크랩 삭제 성공";
    public static String SUCCESS_GET_STUDY_SCRAP_LIST = "스크랩 게시물 리스트 조회 성공";
}
