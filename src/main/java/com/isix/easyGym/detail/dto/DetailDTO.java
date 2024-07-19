package com.isix.easyGym.detail.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;


@Component("detailDTO")
public class DetailDTO {
		private int detailNo;
		private Date detailDate;
		private String detailRoadAddress;
		private String detailBusinessName;
		private String detailBusinessEng;
		private String detailDailyTicket;
		private String detailMonthlyTicket;
		private Double detailScope;
		private String detailComment;
		private String detailServiceProgram;
		private int reviewNo;
		private String detailStatus;
		private String detailClassification;
		private String detailFreeService;
		private int detailMonthlyPrice;
		private String detailKoClassification;
		private String detailLatitude;
		private String detailLongitude;
		
	
		public String getDetailLatitude() {
			return detailLatitude;
		}
		public void setDetailLatitude(String detailLatitude) {
			this.detailLatitude = detailLatitude;
		}
		public String getDetailLongitude() {
			return detailLongitude;
		}
		public void setDetailLongitude(String detailLongitude) {
			this.detailLongitude = detailLongitude;
		}
		public int getdetailNo() {
			return detailNo;
		}
		public void setdetailNo(int detailNo) {
			this.detailNo = detailNo;
		}
		public Date getdetailDate() {
			return detailDate;
		}
		public void setdetailDate(Date detailDate) {
			this.detailDate = detailDate;
		}
		public String getdetailRoadAddress() {
			return detailRoadAddress;
		}
		public void setdetailRoadAddress(String detailRoadAddress) {
			this.detailRoadAddress = detailRoadAddress;
		}
		public String getdetailBusinessName() {
			return detailBusinessName;
		}
		public void setdetailBusinessName(String detailBusinessName) {
			this.detailBusinessName = detailBusinessName;
		}
		public String getdetailBusinessEng() {
			return detailBusinessEng;
		}
		public void setdetailBusinessEng(String detailBusinessEng) {
			this.detailBusinessEng = detailBusinessEng;
		}
		public String getdetailDailyTicket() {
			return detailDailyTicket;
		}
		public void setdetailDailyTicket(String detailDailyTicket) {
			this.detailDailyTicket = detailDailyTicket;
		}
		public String getdetailMonthlyTicket() {
			return detailMonthlyTicket;
		}
		public void setdetailMonthlyTicket(String detailMonthlyTicket) {
			this.detailMonthlyTicket = detailMonthlyTicket;
		}
		public Double getdetailScope() {
			return detailScope;
		}
		public void setdetailScope(Double detailScope) {
			this.detailScope = detailScope;
		}
		public String getdetailComment() {
			return detailComment;
		}
		public void setdetailComment(String detailComment) {
			this.detailComment = detailComment;
		}
		public String getdetailServiceProgram() {
			return detailServiceProgram;
		}
		public void setdetailServiceProgram(String detailServiceProgram) {
			this.detailServiceProgram = detailServiceProgram;
		}
		public int getReviewNo() {
			return reviewNo;
		}
		public void setReviewNo(int reviewNo) {
			this.reviewNo = reviewNo;
		}
		public String getdetailStatus() {
			return detailStatus;
		}
		public void setdetailStatus(String detailStatus) {
			this.detailStatus = detailStatus;
		}
		public String getdetailClassification() {
			return detailClassification;
		}
		public void setdetailClassification(String detailClassification) {
			this.detailClassification = detailClassification;
		}
		public String getdetailFreeService() {
			return detailFreeService;
		}
		public void setdetailFreeService(String detailFreeService) {
			this.detailFreeService = detailFreeService;
		}
	
		
		public int getdetailMonthlyPrice() {
			return detailMonthlyPrice;
		}
		public void setdetailMonthlyPrice(int detailMonthlyPrice) {
			this.detailMonthlyPrice = detailMonthlyPrice;
		}
		public String getdetailKoClassification() {
			return detailKoClassification;
		}
		public void setdetailKoClassification(String detailKoClassification) {
			this.detailKoClassification = detailKoClassification;
		}
}
