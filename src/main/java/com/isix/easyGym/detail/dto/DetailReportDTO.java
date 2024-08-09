package com.isix.easyGym.detail.dto;

import org.springframework.stereotype.Component;

import java.sql.Date;

@Component("detailReportDTO")
public class DetailReportDTO {
    private int reportNo;
    private Date reportDate;
    private int reportCount;
    private String reportContent;
    private int memberNo;
    private int detailNo;
    private int operatorNo;
    private String detailBusinessName;

    public int getReportNo() {
        return reportNo;
    }

    public void setReportNo(int reportNo) {
        this.reportNo = reportNo;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public int getReportCount() {
        return reportCount;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public int getDetailNo() {
        return detailNo;
    }

    public void setDetailNo(int detailNo) {
        this.detailNo = detailNo;
    }

    public int getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(int operatorNo) {
        this.operatorNo = operatorNo;
    }

   public String getDetailBusinessName() {
      return detailBusinessName;
   }

   public void setDetailBusinessName(String detailBusinessName) {
      this.detailBusinessName = detailBusinessName;
   }

}