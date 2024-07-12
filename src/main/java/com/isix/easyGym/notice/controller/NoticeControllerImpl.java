package com.isix.easyGym.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.isix.easyGym.notice.dto.NoticeDTO;
import com.isix.easyGym.notice.service.NoticeService;

@Controller("noticeController")
public class NoticeControllerImpl implements NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private NoticeDTO noticeDTO;
	
	
}
