    package com.isix.easyGym.payform.controller;

    import com.isix.easyGym.payform.service.PayformServiceImpl;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;

    import com.isix.easyGym.payform.dto.PayformDTO;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.servlet.ModelAndView;

    import java.util.HashMap;
    import java.util.Map;

    @Controller("payformController")
    public class PayformControllerImpl implements PayformController {

        @Autowired
        private PayformServiceImpl payformService;

        @Autowired
        private PayformDTO payformDTO;

        @Override
        @GetMapping("payform/payformForm.do")
        public ModelAndView payformForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
            int memberNo = 1; //Integer.parseInt(request.getParameter("memberNo"));
            PayformDTO payform = payformService.payformForm(memberNo);
            ModelAndView mav = new ModelAndView("/payform/payformForm");
            mav.addObject("payform", payform);
            return mav;
        }

    }