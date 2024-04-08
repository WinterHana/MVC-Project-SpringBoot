package com.springboot.project.service.domain;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
	
	@Id
	private String userId;
	
	@Column
	private String userName;
	
	@Column
	private String password;
	
	@Column
	private String role;
	
	@Column
	private String ssn;
	
	@Column(name = "cell_phone")
	private String phone;
	
	@Column
	private String addr;
	
	@Column
	private String email;
	
	@Column
	private Date regDate;
	
	@Transient
	private String phone1;
	
	@Transient
	private String phone2;
	
	@Transient
	private String phone3;
	
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
