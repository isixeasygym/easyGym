package com.isix.easyGym.detail.dto;

import org.springframework.stereotype.Component;

@Component("dibsDTO")
public class DetailDibsDTO {
	private int dibsNo; // au 객체 선언해야 하는지
	private String wholeNo;
	private String memberNo;
	
	
	public int getDibsNo() {
		return dibsNo;
	}
	public void setDibsNo(int dibsNo) {
		this.dibsNo = dibsNo;
	}
	public String getWholeNo() {
		return wholeNo;
	}
	public void setWholeNo(String wholeNo) {
		this.wholeNo = wholeNo;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

}
