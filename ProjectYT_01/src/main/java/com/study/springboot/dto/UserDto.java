package com.study.springboot.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class UserDto {
	private int userNum;
	private String userId;
	private String userPwd;
	private String userName;
	private String userTelNum;
	private String userBirth;
	private Timestamp createDate;
	private Timestamp modifyDate;
	private String eMail;
	private String role;
	private String company;
	private String nickName;
}
