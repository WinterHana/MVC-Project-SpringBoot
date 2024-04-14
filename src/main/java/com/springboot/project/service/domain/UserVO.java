package com.springboot.project.service.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserVO {
	
	private String userId;
	private String userName;
	private String password;
	private String role;
	private String ssn;
	private String phone;
	private String addr;
	private String email;
	private Date regDate;
	private String phone1;
	private String phone2;
	private String phone3;
	private int mileage;
	
	public void setPhone(String phone) {
		this.phone = phone;
		
		if(phone != null && phone.length() != 0) {
			phone1 = phone.split("-")[0];
			phone2 = phone.split("-")[1];
			phone3 = phone.split("-")[2];
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append(phone1 + "-");
			sb.append(phone2+ "-");
			sb.append(phone3);
			this.phone = sb.toString();
		}
	}
}
