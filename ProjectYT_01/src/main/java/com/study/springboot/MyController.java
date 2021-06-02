package com.study.springboot;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.springboot.service.ItProjectService;

@Controller
public class MyController {
	
	@Autowired
	ItProjectService svc;
	
	@RequestMapping("/")
	public String root() throws Exception {
		return "security/loginForm";
	}
	
	@RequestMapping("/security/joinForm")
	public String joinView() {
		return "security/joinForm";
	}
	
	@RequestMapping("/security/join")
	public String join(HttpServletRequest request, Model model) {
		
		String userId = request.getParameter("userid");
		String userName = request.getParameter("user_name");
		String userPassword = request.getParameter("password");
		String telNum = request.getParameter("user_telNum");
		String nickName = request.getParameter("nickname");
		String eMail = request.getParameter("eMail");
		String company = request.getParameter("company");
		userPassword = new BCryptPasswordEncoder().encode(userPassword);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("item1", userId);
		map.put("item2", userPassword);
		map.put("item3", userName);
		map.put("item4", telNum);
		map.put("item5", nickName);
		map.put("item6", eMail);
		map.put("item7", company);
		
		int nResult = svc.join(map);
		System.out.println("join : " + nResult);
		
		return "redirect:/security/loginForm";
	}
	
	@RequestMapping("/loginForm")
	public String loginFrom() {
		return "security/loginForm";
	}
	
	@RequestMapping("/member/main2")
	public String main2() {
		return "member/main2";
	}
	
	
	
//	@RequestMapping("/delete")
//	public String delete(HttpServletRequest request, Model model) {
//		String sId = request.getParameter("id");
//		int nResult = dao.deleteDao(sId);
//		System.out.println("Delete : " + nResult);
//		
//		return "redirect:list";
//	}
}
