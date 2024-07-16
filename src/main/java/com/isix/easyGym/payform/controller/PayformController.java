package com.isix.easyGym.payform.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public interface PayformController {
    ModelAndView buyForm(HttpServletRequest request, HttpServletResponse response) throws Exception;  //memberForm으로 이동할 거라서 addObject는 필요없음
}
