package com.isix.easyGym.detail.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.isix.easyGym.detail.dto.DetailReviewDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface DetailController {

	public String selectReport(@RequestParam("memberNo") int memberNo,
			   				   @RequestParam("detailNo") int detailNo,
			   HttpServletRequest request, HttpServletResponse response) throws Exception ;

	public String doReport(@RequestParam("memberNo") int memberNo, @RequestParam("detailNo") int detailNo,
						   @RequestParam("reportContent") String reportContent, HttpServletRequest request,
						   HttpServletResponse response) throws Exception;

	public ModelAndView reviewViewer(@RequestParam(value = "section", required = false) String _section,
									 @RequestParam(value = "pageNum", required = false) String _pageNum,
									 @RequestParam(value= "detailNo", required= false) int detailNo,
									 HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView registration(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView searchData(@RequestParam("query") String query,
								   @RequestParam(value = "detailClassification",required = false) String detailClassification,
								   HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView signUpForm(
			@RequestParam("detailBusinessEng") String detailBusinessEng,
			@RequestParam("operatorNo") int operatorNo,
			@RequestParam("detailClassification") String detailClassification,
			MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;

	public ModelAndView detailForm(
			@RequestParam("detailNo") int detailNo,
			@RequestParam(value = "memberNo", required = false) String memberNo,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	public List<DetailReviewDTO> getReviews(@RequestParam("detailNo") int detailNo, HttpServletRequest request,
											HttpServletResponse response) throws Exception;

	public String dibs(@RequestParam("detailNo") String detailNo,
					   @RequestParam("memberNo") int memberNo,
					   @RequestParam(value = "action", required = false) String action,
					   HttpServletRequest request,
					   HttpServletResponse response) throws Exception;

	public String getFavoriteStatus(@RequestParam("detailNo") String detailNo,
									@RequestParam("memberNo") String memberNo,HttpServletRequest request,
									HttpServletResponse response) throws Exception;

	public String writeReview(
			@RequestParam("detailNo") String detailNo,
			@RequestParam(value="memberNo", required = false) int memberNo,
			@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "reviewComment", required = false) String reviewComment,
			@RequestParam(value = "reviewRating", required = false) String reviewRating,
			@RequestParam(value = "reviewImageName", required = false) MultipartFile reviewImageName,
			MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response) throws Exception;

	public List<DetailReviewDTO> getReviewImages(@RequestParam("detailNo") int detailNo, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	public String deleteReview(@RequestParam("detailNo") int detailNo,
			   @RequestParam("reviewNo") int reviewNo,
               @RequestParam("memberNo") int memberNo,
               @RequestParam(value = "action", required = false) String action,
               RedirectAttributes rAttr,
               HttpServletRequest request,
               HttpServletResponse response) throws Exception ;

}
