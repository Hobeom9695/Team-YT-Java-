package com.study.springboot.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.study.springboot.dao.ItNoticeDao;
import com.study.springboot.dao.ItUserDao;
import com.study.springboot.dto.NoticeDto;
import com.study.springboot.dto.UserDto;

@Service
public class ProjectService implements ItProjectService {
	
	@Autowired
	ItUserDao dao;
	
	@Autowired
	ItNoticeDao notice;

//	@Override
//	public List<UserDto> list() {
//		return dao.list();
//	}
//
	@Override
	public UserDto userSelect(String uId) {
		return dao.userSelect(uId);
	}
	
	@Override
	public int join(Map<String, String> map) {
		
		int nResult = dao.join(map);
		return nResult;
	}
	
	@Override
	public int checkid(Map<String, String> map) {
		
		int nResult = dao.checkid(map);
		return nResult;
	}
	
	@Override
	public int checkNick(Map<String, String> map) {
		
		int nResult = dao.checkNick(map);
		return nResult;
	}
	
	@Override
	public int checkSns(Map<String, String> map) {
		
		int nResult = dao.checkSns(map);
		return nResult;
	}
	
	@Override
	public int signUpSns(Map<String, String> map) {
		
		int nResult = dao.signUpSns(map);
		return nResult;
	}
	
	@Override
	public int noticeWrite(Map<String, String> map) {
		
		int nResult = notice.noticeWrite(map);
		return nResult;
	}
	
	@Override
	public List<NoticeDto> noticeList() {
		return notice.noticeList();
	}
	
	@Override
	public int noticeCount() {
		int nTotalCount = notice.noticeCount();
		return nTotalCount;
	}
//	@Override
//	public int deleteUser(String id) {
//		int nResult = dao.deleteUser(id);
//		return nResult;
//	}
//
//	@Override
//	public int count() {
//		int nTotalCount = dao.articleCount();
//		return nTotalCount;
//	}

}
