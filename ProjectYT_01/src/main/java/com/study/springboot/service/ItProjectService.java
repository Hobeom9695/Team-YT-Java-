package com.study.springboot.service;

import java.util.Map;

public interface ItProjectService {

//	public List<UserDto> list();
//	public UserDto view(String id);
	public int join(Map<String, String> map);
	public int checkid(Map<String, String> map);
	public int checkNick(Map<String, String> map);
//	public int delete(@Param("_id")String id);
//	public int count();
}
