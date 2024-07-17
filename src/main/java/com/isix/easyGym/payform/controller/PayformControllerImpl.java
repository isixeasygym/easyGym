package com.isix.easyGym.payform.controller;

import com.isix.easyGym.payform.service.PayformServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;

import com.isix.easyGym.payform.dto.PayformDTO;
import org.springframework.web.bind.annotation.GetMapping;
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
        Map payformData = new HashMap();
        List payform = new ArrayList();
        payformData.put("member", memberNo);
        payformData.put("detail", detailNo);
        payform = payformService.getPayformData(payformData);
        ModelAndView mav = new ModelAndView("/payform/payformForm");
        mav.addObject("payform", payform);
        return mav;
    }

}