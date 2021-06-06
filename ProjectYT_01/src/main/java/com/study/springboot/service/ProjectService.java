package com.study.springboot.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.dao.ItUserDao;
import com.study.springboot.dto.UserDto;

@Service
public class ProjectService implements ItProjectService {
	
	@Autowired
	ItUserDao dao;

//	@Override
//	public List<UserDto> list() {
//		return dao.list();
//	}
//
//	@Override
//	public UserDto view(String id) {
//		return dao.viewDao(id);
//	}
	
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
