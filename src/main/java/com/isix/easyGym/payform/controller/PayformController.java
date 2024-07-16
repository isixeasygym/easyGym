package com.isix.easyGym.payform.controller;

import com.isix.easyGym.payform.dto.PayformDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public interface PayformController {
    ModelAndView payformForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
    ModelAndView payformDone(HttpServletRequest request, HttpServletResponse response) throws Exception;
    ModelAndView payformCancel(HttpServletRequest request, HttpServletResponse response) throws Exception;
    ModelAndView payformRefund(HttpServletRequest request, HttpServletResponse response) throws Exception;
    ModelAndView payformTosspay(HttpServletRequest request, HttpServletResponse response) throws Exception;
}