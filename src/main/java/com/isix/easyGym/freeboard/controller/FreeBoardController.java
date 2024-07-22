package com.isix.easyGym.freeboard.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface FreeBoardController {

	// 전체 리스트 보기
	public ModelAndView listFboard(@RequestParam("section") String section, @RequestParam("pageNum") String pageNum, HttpServletRequest req, HttpServletResponse res) throws Exception;
	
	// 글 쓰기 
	public ModelAndView fboardForm(HttpServletRequest req, HttpServletResponse res) throws Exception;

	// 글 추가  이미지를 추가할 수 있기 때문에 (MultipartHttpServletRequest 로 사용)
	public ModelAndView addFboard(MultipartHttpServletRequest mulReq, HttpServletResponse res) throws Exception;
	
	//상세 글보기
	public ModelAndView viewFboard(@RequestParam("freeNo") int freeNo, HttpServletRequest req, HttpServletResponse res) throws Exception;
	
	// 글 수정
	public ModelAndView modFboard(MultipartHttpServletRequest mulReq, HttpServletResponse res) throws Exception;
	
	// 글 삭제
	public ModelAndView removeFboard(@RequestParam("freeNo") int freeNo, HttpServletRequest req, HttpServletResponse res) throws Exception;
}
