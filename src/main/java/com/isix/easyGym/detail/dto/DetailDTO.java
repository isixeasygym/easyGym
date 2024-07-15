package com.isix.easyGym.detail.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;


@Component("detailDTO")
public class DetailDTO {
		private int wholeNo;
		private Date wholeDate;
		private String wholeRoadAddress;
		private String wholeBusinessName;
		private String wholeBusinessEng;
		private String wholeDailyTicket;
		private String wholeMonthlyTicket;
		private Double wholeScope;
		private String wholeDetail;
		private String wholeServiceProgram;
		private int reviewNo;
		private String wholeStatus;
		private String wholeClassification;
		private String wholeFreeService;
		private int wholeMonthlyPrice;
		private String wholeKoClassification;
		private int wholeTest;
		
		public int getWholeTest() {
			return wholeTest;
		}
		public void setWholeTest(int wholeTest) {
			this.wholeTest = wholeTest;
		}
		public int getWholeNo() {
			return wholeNo;
		}
		public void setWholeNo(int wholeNo) {
			this.wholeNo = wholeNo;
		}
		public Date getWholeDate() {
			return wholeDate;
		}
		public void setWholeDate(Date wholeDate) {
			this.wholeDate = wholeDate;
		}
		public String getWholeRoadAddress() {
			return wholeRoadAddress;
		}
		public void setWholeRoadAddress(String wholeRoadAddress) {
			this.wholeRoadAddress = wholeRoadAddress;
		}
		public String getWholeBusinessName() {
			return wholeBusinessName;
		}
		public void setWholeBusinessName(String wholeBusinessName) {
			this.wholeBusinessName = wholeBusinessName;
		}
		public String getWholeBusinessEng() {
			return wholeBusinessEng;
		}
		public void setWholeBusinessEng(String wholeBusinessEng) {
			this.wholeBusinessEng = wholeBusinessEng;
		}
		public String getWholeDailyTicket() {
			return wholeDailyTicket;
		}
		public void setWholeDailyTicket(String wholeDailyTicket) {
			this.wholeDailyTicket = wholeDailyTicket;
		}
		public String getWholeMonthlyTicket() {
			return wholeMonthlyTicket;
		}
		public void setWholeMonthlyTicket(String wholeMonthlyTicket) {
			this.wholeMonthlyTicket = wholeMonthlyTicket;
		}
		public Double getWholeScope() {
			return wholeScope;
		}
		public void setWholeScope(Double wholeScope) {
			this.wholeScope = wholeScope;
		}
		public String getWholeDetail() {
			return wholeDetail;
		}
		public void setWholeDetail(String wholeDetail) {
			this.wholeDetail = wholeDetail;
		}
		public String getWholeServiceProgram() {
			return wholeServiceProgram;
		}
		public void setWholeServiceProgram(String wholeServiceProgram) {
			this.wholeServiceProgram = wholeServiceProgram;
		}
		public int getReviewNo() {
			return reviewNo;
		}
		public void setReviewNo(int reviewNo) {
			this.reviewNo = reviewNo;
		}
		public String getWholeStatus() {
			return wholeStatus;
		}
		public void setWholeStatus(String wholeStatus) {
			this.wholeStatus = wholeStatus;
		}
		public String getWholeClassification() {
			return wholeClassification;
		}
		public void setWholeClassification(String wholeClassification) {
			this.wholeClassification = wholeClassification;
		}
		public String getWholeFreeService() {
			return wholeFreeService;
		}
		public void setWholeFreeService(String wholeFreeService) {
			this.wholeFreeService = wholeFreeService;
		}
	
		
		public int getWholeMonthlyPrice() {
			return wholeMonthlyPrice;
		}
		public void setWholeMonthlyPrice(int wholeMonthlyPrice) {
			this.wholeMonthlyPrice = wholeMonthlyPrice;
		}
		public String getWholeKoClassification() {
			return wholeKoClassification;
		}
		public void setWholeKoClassification(String wholeKoClassification) {
			this.wholeKoClassification = wholeKoClassification;
		}
}
