package com.isix.easyGym.payform.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface PayformController {
    public ModelAndView payformForm(@RequestParam(value = "memberNo") int memberNo, @RequestParam(value = "detailNo") int detailNo, HttpServletRequest request, HttpServletResponse response) throws DataAccessException;
}