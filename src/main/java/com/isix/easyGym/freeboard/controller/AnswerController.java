package com.isix.easyGym.freeboard.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AnswerController {

	/*
	 * // 댓글 리스트 public ModelAndView answerList(HttpServletRequest request,
	 * HttpServletResponse response) throws Exception;
	 * 
	 * // 댓글 작성 public ModelAndView addFboard(@RequestParam(value="freeNo", required
	 * = false) String freeNo,@RequestParam(value="memberNo", required = false)
	 * String memberNo,@RequestParam(value="fbanswerContent", required = false)
	 * String fbanswerContent,MultipartHttpServletRequest mulReq,
	 * HttpServletResponse res) throws Exception;
	 * 
	 * // 댓글 삭제 public ModelAndView removeFboard(@RequestParam("fbanswerNo") int
	 * fbanswerNo, HttpServletRequest req, HttpServletResponse res) throws
	 * Exception;
	 */
	
	public Map<String, Object> getAnswerList(@RequestParam("freeNo") int freeNo) throws Exception;

	  public Map<String, Object> addAnswerAjax(@RequestParam(value="freeNo", required = false) Integer freeNo,
              @RequestParam(value="memberNo", required = false) Integer memberNo,
              @RequestParam(value="fbanswerContent", required = false) String fbanswerContent) throws Exception ;

    public Map<String, Object> removeAnswerAjax(@RequestParam("fbanswerNo") int fbanswerNo) throws Exception;
	
}
