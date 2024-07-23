package com.isix.easyGym.detail.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.isix.easyGym.detail.dto.DetailReviewDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface DetailController{
	
	
	public ResponseEntity<List<DetailReviewDTO>> getReviews(@RequestParam("companyId") int detailNo,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView searchData(@RequestParam("query") String query, 
			@RequestParam("detailClassification") String detailClassification,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView signUpForm(@RequestParam("detailBusinessEng") String detailBusinessEng,
			@RequestParam("operatorNo") int operatorNo,@RequestParam("detailClassification") String detailClassification, 
			MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;
	
	public ModelAndView selectAll(@RequestParam("detailClassification") String detailClassification,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView selectPopular(@RequestParam("detailClassification") String detailClassification,
			HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView detailForm(
			@RequestParam("detailNo") int detailNo,
			@RequestParam(value = "memberNo", required = false) String memberNo,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	public String deleteReview(@RequestParam("reviewNo") int reviewNo, @RequestParam("userId") int memberNo,
            @RequestParam(value = "action", required = false) String action,
            RedirectAttributes rAttr, HttpServletRequest request,
            HttpServletResponse response) throws Exception;
	

	
	public String writeReview(@RequestParam("companyId") String detailNo, @RequestParam("memberNo") String memberNo,
            @RequestParam(value = "action", required = false) String action,
            @RequestParam(value = "reviewComment", required = false) String reviewComment,
            @RequestParam(value = "reviewRating", required = false) String reviewRating,
            @RequestParam(value = "reviewImageName", required= false) MultipartFile reviewImage,
            MultipartHttpServletRequest MultipartRequest,
            HttpServletResponse response) throws Exception;
	
	public String dibs(@RequestParam("companyId") String companyId, @RequestParam("userId") String userId,
            @RequestParam(value = "action", required = false) String action,
            RedirectAttributes rAttr, HttpServletRequest request,
            HttpServletResponse response) throws Exception;
  
}