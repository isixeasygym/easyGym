package com.isix.easyGym.payform.controller;

import com.isix.easyGym.payform.service.PayformServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;

import com.isix.easyGym.payform.dto.PayformDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("payformController")
public class PayformControllerImpl implements PayformController {

    @Autowired
    private PayformServiceImpl payformService;

    @Autowired
    private PayformDTO payformDTO;

    @Override
    @RequestMapping("/payform/payformForm.do")
    public ModelAndView payformForm(@RequestParam(value = "memberNo") int memberNo, @RequestParam(value = "detailNo") int detailNo, HttpServletRequest request, HttpServletResponse response) throws DataAccessException {
        Map payformData = new HashMap(); //테이블에 있는 쿼리들을 불러올 맵을 생성
        List payform = new ArrayList(); //불러와야 할 테이블이 2개이므로 리스트로 생성하며 0번 리스트에는 member, 1번 리스트에는 detail이 들어감
        payformData.put("member", memberNo);
        payformData.put("detail", detailNo);
        payform = payformService.getPayformData(payformData);
        ModelAndView mav = new ModelAndView("/payform/payformForm");
        mav.addObject("payform", payform);
        return mav;
    }

    @Override
    @RequestMapping("/payform/payformCredit.do")
    public ModelAndView payformCredit(@RequestParam(value = "memberNo") int memberNo, @RequestParam(value = "detailNo") int detailNo, @RequestParam(value = "detailNa") String detailNa, @RequestParam(value = "name") String name, @RequestParam(value = "phoneNumber") String phoneNumber, @RequestParam(value = "subscriptionMonths") int subscriptionMonths, @RequestParam(value = "paymentMethod") int paymentMethod, @RequestParam(value = "price") int price, HttpServletRequest request, HttpServletResponse response) throws DataAccessException {
        Map payformData = new HashMap(); //테이블에 있는 쿼리들을 불러올 맵을 생성
        payformData.put("memberNo", memberNo);
        payformData.put("detailNo", detailNo);
        payformData.put("detailNa", detailNa);
        payformData.put("name", name);
        payformData.put("phoneNumber", phoneNumber);
        payformData.put("subscriptionMonths", subscriptionMonths);
        payformData.put("paymentMethod", paymentMethod);
        payformData.put("price", price);
        ModelAndView mav = new ModelAndView("/payform/payformCredit");
        mav.addObject("payformData", payformData);
        return mav;
    }

    /*@Override
    @RequestMapping("/payform/payformDone.do")
    public ModelAndView payformDone(@RequestParam(value = "memberNo") int memberNo, @RequestParam(value = "detailNo") int detailNo, @RequestParam(value = "subscriptionMonths") int subscriptionMonths, @RequestParam(value = "price") int price, @RequestParam(value = "paymentMethod") int paymentMethod, HttpServletRequest request, HttpServletResponse response) throws DataAccessException {
        Map payformData = new HashMap(); //테이블에 있는 쿼리들을 불러올 맵을 생성
        payformData.put("memberNo", memberNo);
        payformData.put("detailNo", detailNo);
        payformData.put("payformSub", subscriptionMonths);
        payformData.put("payformPrice", price);
        payformData.put("paymentMethod", paymentMethod);
        Map paywww = payformService.getPayformData(payformData);
        ModelAndView mav = new ModelAndView("/payform/payformDone");
        Object payformDone = null;
        mav.addObject("payformData", payformDone);
        return mav;
    }*/
}