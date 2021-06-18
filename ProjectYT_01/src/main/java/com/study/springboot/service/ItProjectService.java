package com.study.springboot.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.study.springboot.dto.NoticeDto;
import com.study.springboot.dto.UserDto;

public interface ItProjectService {

//	public List<UserDto> list();
	public int join(Map<String, String> map);
	public int checkid(Map<String, String> map);
	public int checkNick(Map<String, String> map);
	public int checkSns(Map<String, String> map);
	public int signUpSns(Map<String, String> map);
	public UserDto userSelect(String uId);
	public int noticeWrite(Map<String, String> map);
	public List<NoticeDto> noticeList();
	public int noticeCount();
	public NoticeDto noticeView(String id);
//	public int delete(@Param("_id")String id);
//	public int count();
}
