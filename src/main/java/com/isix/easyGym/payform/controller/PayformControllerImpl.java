package com.isix.easyGym.payform.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.isix.easyGym.payform.dto.PayformDTO;
import com.isix.easyGym.payform.service.PayformServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller("payformController")
public class PayformControllerImpl implements PayformController {

    @Autowired
    private PayformServiceImpl payformService;

    @Autowired
    private PayformDTO payformDTO;

    @Override
    @RequestMapping(value = "/payform/payformForm.do",method = RequestMethod.POST)
    //memberNo가 없으면(로그인을 안하면 -1 값으로 줘서 memberNo는 빈값으로 들어감)
    public ModelAndView payformForm(@RequestParam(value = "memberNo",defaultValue = "-1") int memberNo, @RequestParam(value = "detailNo") int detailNo, HttpServletRequest request, HttpServletResponse response) throws DataAccessException {
        Map payformData = new HashMap(); //테이블에 있는 쿼리들을 불러올 맵을 생성
        List payform = new ArrayList(); //불러와야 할 테이블이 2개이므로 리스트로 생성하며 0번 리스트에는 member, 1번 리스트에는 detail이 들어감
        payformData.put("member", memberNo);
        payformData.put("detail", detailNo);
        payform = payformService.getPayformData(payformData);
        ModelAndView mav = new ModelAndView("/payform/payformForm");
        mav.addObject("payform", payform);
        mav.addObject("loginCheck",memberNo);
        return mav;
    }

    @Override
    @RequestMapping(value = "/payform/payformCredit.do",method = RequestMethod.POST)
    public ModelAndView payformCredit(@RequestParam(value = "memberNo") int memberNo, @RequestParam(value = "detailNo") int detailNo, @RequestParam(value = "detailNa") String detailNa, @RequestParam(value = "name") String name, @RequestParam(value = "phoneNumber") String phoneNumber, @RequestParam(value = "subscriptionMonths") int subscriptionMonths, @RequestParam(value = "payformPayment") int payformPayment, @RequestParam(value = "price") int price, HttpServletRequest request, HttpServletResponse response) throws DataAccessException {
        Map payformData = new HashMap(); //테이블에 있는 쿼리들을 불러올 맵을 생성
        payformData.put("memberNo", memberNo);
        payformData.put("detailNo", detailNo);
        payformData.put("detailNa", detailNa);
        payformData.put("name", name);
        payformData.put("phoneNumber", phoneNumber);
        payformData.put("subscriptionMonths", subscriptionMonths);
        payformData.put("payformPayment", payformPayment);
        payformData.put("price", price);
        ModelAndView mav = new ModelAndView("/payform/payformCredit");
        mav.addObject("payform", payformData);
        return mav;
    }

    @Override
    @RequestMapping(value = "/payform/payformProcess.do",method = RequestMethod.POST)
    public ModelAndView payformProcess(@RequestParam(value = "memberNo") int memberNo, @RequestParam(value = "detailNo") int detailNo, @RequestParam(value = "subscriptionMonths") int subscriptionMonths, @RequestParam(value = "price") int price, @RequestParam(value = "payformPayment") int payformPayment, HttpServletRequest request, HttpServletResponse response) throws DataAccessException {
        Map payformMap = new HashMap(); //테이블에 있는 쿼리들을 불러올 맵을 생성
        payformMap.put("memberNo", memberNo);
        payformMap.put("detailNo", detailNo);
        payformMap.put("payformSub", subscriptionMonths);
        payformMap.put("payformPrice", price);
        payformMap.put("payformPayment", payformPayment);
        int payformNo = payformService.insertPayform(payformMap);

        ModelAndView mav = new ModelAndView("redirect:/payform/payformDone.do?payformNo=" + payformNo);
        return mav;
    }

    @Override
    @RequestMapping(value = "/payform/payformDone.do",method = RequestMethod.POST)
    public ModelAndView payformDone(@RequestParam(value = "payformNo") int payformNo, HttpServletRequest request, HttpServletResponse response) throws DataAccessException {
        PayformDTO payformDTO = payformService.selectPayform(payformNo);
        ModelAndView mav = new ModelAndView("/payform/payformDone");
        mav.addObject("payform", payformDTO);
        return mav;
    }

    //TODO 결제 취소 하는 기능, payformCancel로 payformNo 전달하는 코드 짜주면 됨
    @Override
    @RequestMapping(value = "/payform/payformCancel.do",method = RequestMethod.POST)
    public ModelAndView payformCancel(@RequestParam(value = "payformNo") int payformNo, HttpServletRequest request, HttpServletResponse response) throws DataAccessException {
        PayformDTO payformDTO = payformService.selectPayform(payformNo);
        ModelAndView mav = new ModelAndView("/payform/payformCancel");
        mav.addObject("payform", payformDTO);
        return mav;
    }

    @Override
    @RequestMapping(value = "/payform/payformRefund.do",method = RequestMethod.POST)
    public ModelAndView payformRefund(@RequestParam(value = "payformNo") int payformNo, @RequestParam(value = "refundPrice") int refundPrice, HttpServletRequest request, HttpServletResponse response) throws DataAccessException {
        int CancelSuccess = payformService.cancelPayform(payformNo);
        PayformDTO payformDTO = payformService.selectPayform(payformNo);
        ModelAndView mav = new ModelAndView("/payform/payformRefund");
        mav.addObject("payform", payformDTO);
        mav.addObject("refundPrice", refundPrice);
        return mav;
    }

}