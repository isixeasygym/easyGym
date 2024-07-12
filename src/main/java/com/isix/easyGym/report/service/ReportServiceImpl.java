package com.isix.easyGym.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isix.easyGym.report.dao.ReportDAO;

@Service("reportService")
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportDAO reportDAO;
	
	
}
