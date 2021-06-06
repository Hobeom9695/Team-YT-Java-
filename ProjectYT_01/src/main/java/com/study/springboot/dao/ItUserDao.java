package com.study.springboot.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.springboot.dto.UserDto;

@Mapper
public interface ItUserDao {
	public List<UserDto> userList();
	public UserDto userView(String id);
	public int join(Map<String, String> map);
	public int checkid(Map<String, String> map);
	public int checkNick(Map<String, String> map);
	public int checkSns(Map<String, String> map);
	public int signUpSns(Map<String, String> map);
	public int deleteUser(@Param("_id")String id);
	public int articleCount();
	public int modifyUser(Map<String, String>map);
	public int roleUp(Map<String, String>map);
}
