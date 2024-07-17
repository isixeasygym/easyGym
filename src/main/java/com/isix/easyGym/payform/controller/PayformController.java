package com.isix.easyGym.payform.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public interface PayformController {
    ModelAndView payformForm(HttpServletRequest request, HttpServletResponse response) throws Exception;


}