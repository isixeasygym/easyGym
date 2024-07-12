package com.isix.easyGym.contact.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("contactDTO")
public class ContactDTO {

	private int contactNo;
	private int memberNo;
	private String contactTitle;
	private String contactContent;
	private Date contactWriteDate;
	private String contactCategory;
	private String contactImgName;
	
	
	public ContactDTO() {
		
	}
}
