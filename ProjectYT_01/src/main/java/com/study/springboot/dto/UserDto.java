package com.study.springboot.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDto {
	private int userNum;
	@NotNull(message="userId is null.")
	@NotEmpty(message="userId is empty.")
	@Size(min=6, max=15, message="userId min=6, max=15.")
	private String userId;
	@NotNull(message="userPwd is null.")
	@NotEmpty(message="userPwd is empty.")
	@Size(min=6, max=15, message="userPwd min=8, max=15.")
	private String userPwd;
	@NotNull(message="userName is null.")
	@NotEmpty(message="userName is empty.")
	@Size(min=2, max=15, message="userName min=2, max=15.")
	private String userName;
	@NotNull(message="userTelNum is null.")
	@NotEmpty(message="userTelNum is empty.")
	private String userTelNum;
	@NotNull(message="nickName is null.")
	@NotEmpty(message="nickName is empty.")
	@Size(min=2, max=15, message="nickName min=2, max=15.")
	private String nickName;
	private Timestamp createDate;
	private Timestamp modifyDate;
	@NotNull(message="eMail_id is null.")
	@NotEmpty(message="eMail_id is empty.")
	private String eMail_id;
	@NotNull(message="eMail_url is null.")
	@NotEmpty(message="eMail_url is empty.")
	private String eMail_url;
//	private String eMail = eMail_id+"@"+eMail_url;
	private String eMail;
	private String authority;
	private String company;
	private int enabled;
}
