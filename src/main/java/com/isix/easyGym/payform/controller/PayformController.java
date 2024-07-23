package com.isix.easyGym.payform.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface PayformController {
    public ModelAndView payformForm(@RequestParam(value = "memberNo") int memberNo, @RequestParam(value = "detailNo") int detailNo, HttpServletRequest request, HttpServletResponse response) throws DataAccessException;

    public ModelAndView payformCredit(@RequestParam(value = "memberNo") int memberNo, @RequestParam(value = "detailNo") int detailNo, @RequestParam(value = "detailNa") String detailNa, @RequestParam(value = "name") String name, @RequestParam(value = "phoneNumber") String phoneNumber, @RequestParam(value = "subscriptionMonths") int subscriptionMonths, @RequestParam(value = "payformPayment") int payformPayment, @RequestParam(value = "price") int price, HttpServletRequest request, HttpServletResponse response) throws DataAccessException;

    public ModelAndView payformProcess(@RequestParam(value = "memberNo") int memberNo, @RequestParam(value = "detailNo") int detailNo, @RequestParam(value = "subscriptionMonths") int subscriptionMonths, @RequestParam(value = "price") int price, @RequestParam(value = "payformPayment") int payformPayment, HttpServletRequest request, HttpServletResponse response) throws DataAccessException;

    public ModelAndView payformDone(@RequestParam(value = "payformNo") int payformNo, HttpServletRequest request, HttpServletResponse response) throws DataAccessException;

    public ModelAndView payformCancel(@RequestParam(value = "payformNo") int payformNo, HttpServletRequest request, HttpServletResponse response) throws DataAccessException;

    public ModelAndView payformRefund(@RequestParam(value = "payformNo") int payformNo, @RequestParam(value = "payformPrice") int payformPrice, HttpServletRequest request, HttpServletResponse response) throws DataAccessException;

}