package com.isix.easyGym.detail.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface DetailController{
	
	public ModelAndView selectPopular(@RequestParam("status") String status,@RequestParam("wholeClassification") String WholeClassification,HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView selectAll(@RequestParam("wholeClassification") String WholeClassification, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView detailForm(@RequestParam("wholeNo") int wholeNo,HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	public ModelAndView doReport(@RequestParam("memberNo")int memberNo,@RequestParam("wholeNo")int wholeNo, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	public ModelAndView review(@RequestParam("memberNo")int memberNo,@RequestParam("wholeNo") int wholeNo,MultipartHttpServletRequest MultipartRequest, HttpServletResponse response) throws Exception;
	
	/*public String dibs(@RequestParam("companyId") int companyId, @RequestParam("userId") int userId
			, @RequestParam(value="action" ,required=false) String action,
			RedirectAttributes rAttr, HttpServletRequest request,
			HttpServletResponse response) throws Exception;*/
}