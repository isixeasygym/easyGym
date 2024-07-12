package com.isix.easyGym.report.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("reportDTO")
public class ReportDTO {
	private int reportNo;            //글번호
	private String reportTitle;      //글제목
	private int memberNo;            //회원번호
	private String reportContent;    //글내용
	private Date reportDate;         //작성일자
	private int reportDeleted;       //신고글 삭제 여부
	private int reportStatus;        //신고상태
	private String reportEntName;    //신고업체명
	private Date reportProcessedAt;  //처리일자
	private String reportAnswer;     //신고댓글
	
	
	//생성자 => ctrl + space => constructor 생성
	public ReportDTO() {
		
	}


	public int getReportNo() {
		return reportNo;
	}


	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}


	public String getReportTitle() {
		return reportTitle;
	}


	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}


	public int getMemberNo() {
		return memberNo;
	}


	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}


	public String getReportContent() {
		return reportContent;
	}


	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}


	public Date getReportDate() {
		return reportDate;
	}


	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}


	public int getReportDeleted() {
		return reportDeleted;
	}


	public void setReportDeleted(int reportDeleted) {
		this.reportDeleted = reportDeleted;
	}


	public int getReportStatus() {
		return reportStatus;
	}


	public void setReportStatus(int reportStatus) {
		this.reportStatus = reportStatus;
	}


	public String getReportEntName() {
		return reportEntName;
	}


	public void setReportEntName(String reportEntName) {
		this.reportEntName = reportEntName;
	}


	public Date getReportProcessedAt() {
		return reportProcessedAt;
	}


	public void setReportProcessedAt(Date reportProcessedAt) {
		this.reportProcessedAt = reportProcessedAt;
	}


	public String getReportAnswer() {
		return reportAnswer;
	}


	public void setReportAnswer(String reportAnswer) {
		this.reportAnswer = reportAnswer;
	}
	
	
}
