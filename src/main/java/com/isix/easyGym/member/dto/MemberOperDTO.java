package com.isix.easyGym.member.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("memberoperDTO")
public class MemberOperDTO {

	private int operatorNo;
	private String operatorName;
	private String operatorId;
	private String operatorPwd;
	private String operatorPhone;
	private String operatorEmail;
	private int operatorResNo;
	private String operatorImgName;
	private Date operatorCreatedAt;
	private int operatorState;
	
	public MemberOperDTO() {
		
	}

	public int getOperatorNo() {
		return operatorNo;
	}

	public void setOperatorNo(int operatorNo) {
		this.operatorNo = operatorNo;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorPwd() {
		return operatorPwd;
	}

	public void setOperatorPwd(String operatorPwd) {
		this.operatorPwd = operatorPwd;
	}

	public String getOperatorPhone() {
		return operatorPhone;
	}

	public void setOperatorPhone(String operatorPhone) {
		this.operatorPhone = operatorPhone;
	}

	public String getOperatorEmail() {
		return operatorEmail;
	}

	public void setOperatorEmail(String operatorEmail) {
		this.operatorEmail = operatorEmail;
	}

	public int getOperatorResNo() {
		return operatorResNo;
	}

	public void setOperatorResNo(int operatorResNo) {
		this.operatorResNo = operatorResNo;
	}

	public String getOperatorImgName() {
		return operatorImgName;
	}

	public void setOperatorImgName(String operatorImgName) {
		this.operatorImgName = operatorImgName;
	}

	public Date getOperatorCreatedAt() {
		return operatorCreatedAt;
	}

	public void setOperatorCreatedAt(Date operatorCreatedAt) {
		this.operatorCreatedAt = operatorCreatedAt;
	}

	public int getOperatorState() {
		return operatorState;
	}

	public void setOperatorState(int operatorState) {
		this.operatorState = operatorState;
	}
	
	
}
