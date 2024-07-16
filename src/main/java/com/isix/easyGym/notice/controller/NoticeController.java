package com.isix.easyGym.notice.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface NoticeController {

	// 공지사항 리스트
	public ModelAndView noticeList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 공지사항 작성폼
	public ModelAndView noticeForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 공지사항 추가
	public ModelAndView addNotice(MultipartHttpServletRequest MultipartRequest, HttpServletResponse response) throws Exception;
	//Multipart => 게시판에 글 외에 이미지도 추가될 수 있어서 Multipart를 넣어서 요청 받아와야 함
	
	public ModelAndView viewNotice(@RequestParam("noticeNo") int noticeNo, HttpServletRequest request, HttpServletResponse response) throws Exception;
	//상세 글 보기 => RequestParam으로 articleNo를 가져와서 상세 글을 볼 수 있게 함
	
	public ModelAndView modNotice(MultipartHttpServletRequest MultipartRequest, HttpServletResponse response) throws Exception;
	
	public ModelAndView removeNotice(@RequestParam("noticeNo") int noticeNo, HttpServletRequest request, HttpServletResponse response) throws Exception;
	//게시글 삭제
}
