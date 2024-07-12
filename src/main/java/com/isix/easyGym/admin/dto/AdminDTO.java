package com.isix.easyGym.admin.dto;

import org.springframework.stereotype.Component;

@Component("adminDTO")
public class AdminDTO {

	private int adminNo;
	
	
	public AdminDTO() {
		
	}


	public int getAdminNo() {
		return adminNo;
	}


	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}
	
	
}
