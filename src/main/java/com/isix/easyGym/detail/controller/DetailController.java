package com.isix.easyGym.detail.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface DetailController{
	
	
	public ModelAndView selectAll(@RequestParam("detailClassification") String detailClassification, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView detailForm(
			@RequestParam("detailNo") String detailNo,HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	public String deleteReview(@RequestParam("companyId") String detailNo, @RequestParam("userId") String memberNo,
            @RequestParam(value = "action", required = false) String action,
            RedirectAttributes rAttr, HttpServletRequest request,
            HttpServletResponse response) throws Exception;
	
	public ModelAndView writeReview(@RequestParam("companyId") String detailNo, @RequestParam("userId") String memberNo,
            @RequestParam(value = "action", required = false) String action,
            RedirectAttributes rAttr, HttpServletRequest request,
            HttpServletResponse response) throws Exception;
	
	public String dibs(@RequestParam("companyId") String companyId, @RequestParam("userId") String userId,
            @RequestParam(value = "action", required = false) String action,
            RedirectAttributes rAttr, HttpServletRequest request,
            HttpServletResponse response) throws Exception;
  
}