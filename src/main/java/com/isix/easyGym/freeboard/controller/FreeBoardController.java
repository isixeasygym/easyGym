package com.isix.easyGym.freeboard.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface FreeBoardController {
    public ModelAndView listFBoard(@RequestParam(value = "section", required = false) String section, 
                                   @RequestParam(value = "pageNum", required = false) String pageNum, 
                                   HttpServletRequest request, 
                                   HttpServletResponse response) throws Exception;
    
    public ModelAndView fBoardForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
    
    public ModelAndView addFBoard(MultipartHttpServletRequest MultipartRequest, HttpServletResponse response) throws Exception;
    
    public ModelAndView viewFBoard(@RequestParam("articleNo") int articleNo, HttpServletRequest request, HttpServletResponse response) throws Exception;
    
    public ModelAndView modFBoard(MultipartHttpServletRequest MultipartRequest, HttpServletResponse response) throws Exception;
    
    public ModelAndView removeFBoard(@RequestParam("articleNo") int articleNo, HttpServletRequest request, HttpServletResponse response) throws Exception;
}