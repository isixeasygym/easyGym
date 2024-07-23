package com.isix.easyGym;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class WebErrorController implements ErrorController {

	@RequestMapping(value = "/error")
	public ModelAndView handleNoHandlerFoundException(HttpServletResponse response, HttpServletRequest request,
			Model model) {
		Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		ModelAndView modelAndView = new ModelAndView();

		if (statusCode != null) {
			Integer status = Integer.valueOf(statusCode.toString());
			if (status == HttpStatus.NOT_FOUND.value()) {
				modelAndView.addObject("errorCode", status);
				modelAndView.setViewName("/error/404");
			} else if (status == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				modelAndView.addObject("errorCode", status);
				modelAndView.setViewName("/error/500");
			} else if (status == HttpStatus.FORBIDDEN.value()) {
				modelAndView.setViewName("/error/403");
			} else
				modelAndView.setViewName("/error/common");
		}
		return modelAndView;
	}
}
