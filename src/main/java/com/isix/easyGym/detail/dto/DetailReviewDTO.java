package com.isix.easyGym.detail.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("detailReviewDTO")
public class DetailReviewDTO {

   private int reviewNo;
   private String reviewComment;
   private String reviewImgName;
   private Date reviewDate;
   private int reviewRating;
   private int memberNo;
   private int payformNo;
   private int detailNo;
   private String detailBusinessName; // 추가된 부분


   public Date getReviewDate() {
      return reviewDate;
   }
   public void setReviewDate(Date reviewDate) {
      this.reviewDate = reviewDate;
   }

   
   
   public int getReviewNo() {
      return reviewNo;
   }
   public void setReviewNo(int reviewNo) {
      this.reviewNo = reviewNo;
   }
   public String getReviewComment() {
      return reviewComment;
   }
   public void setReviewComment(String reviewComment) {
      this.reviewComment = reviewComment;
   }
   public String getReviewImgName() {
      return reviewImgName;
   }
   public void setReviewImgName(String reviewImgName) {
      this.reviewImgName = reviewImgName;
   }
   public int getReviewRating() {
      return reviewRating;
   }
   public void setReviewRating(int reviewRating) {
      this.reviewRating = reviewRating;
   }
   public int getMemberNo() {
      return memberNo;
   }
   public void setMemberNo(int memberNo) {
      this.memberNo = memberNo;
   }
   public int getpayformNo() {
      return payformNo;
   }
   public void setpayformNo(int payformNo) {
      this.payformNo = payformNo;
   }
   public int getDetailNo() {
      return detailNo;
   }
   public void setDetailNo(int detailNo) {
      this.detailNo = detailNo;
   }
   public String getDetailBusinessName() {
      return detailBusinessName;
   }
   public void setDetailBusinessName(String detailBusinessName) {
      this.detailBusinessName = detailBusinessName;
   }

}