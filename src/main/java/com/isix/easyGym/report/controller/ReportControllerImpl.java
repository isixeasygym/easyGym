package com.isix.easyGym.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.isix.easyGym.report.dto.ReportDTO;
import com.isix.easyGym.report.service.ReportService;

@Controller("reportController")
public class ReportControllerImpl implements ReportController {

	@Autowired
	private ReportService reportService;
	
	@Autowired
	private ReportDTO reportDTO;
	
	
}
