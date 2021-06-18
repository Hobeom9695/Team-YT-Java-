package com.study.springboot;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.springboot.dto.NoticeDto;
import com.study.springboot.dto.UserDto;
import com.study.springboot.service.ItProjectService;

@Controller
public class MyController {
	
	@Autowired
	ItProjectService svc;

	@RequestMapping("/")
	public String root() throws Exception {
		return "guest/main1";
	}

	@RequestMapping("/security/joinForm")
	public String joinView() {
		return "security/joinForm";
	}

	@RequestMapping("/security/join")
	public @ResponseBody String join(HttpServletRequest request, Model model,
			@ModelAttribute("dto") @Valid UserDto userDto, BindingResult result) {
		String page = "joinForm";

		if (result.hasErrors()) {
			System.out.println("getAllErrors : " + result.getAllErrors());

			if (result.getFieldError("userId") != null) {
				System.out.println("1:" + result.getFieldError("userId").getCode());
			}
			if (result.getFieldError("userPwd") != null) {
				System.out.println("2:" + result.getFieldError("userPwd").getCode());
			}
			if (result.getFieldError("userName") != null) {
				System.out.println("3:" + result.getFieldError("userName").getCode());
			}
			if (result.getFieldError("userTelNum") != null) {
				System.out.println("4:" + result.getFieldError("userTelNum").getCode());
			}
			if (result.getFieldError("nickName") != null) {
				System.out.println("5:" + result.getFieldError("nickName").getCode());
			}
			if (result.getFieldError("eMail_id") != null) {
				System.out.println("6:" + result.getFieldError("eMail_id").getCode());
			}
			if (result.getFieldError("eMail_url") != null) {
				System.out.println("7:" + result.getFieldError("eMail_url").getCode());
			}

			return page;
		}

		userDto.setUserId(request.getParameter("userId"));
		userDto.setUserPwd(request.getParameter("userPwd"));
		userDto.setUserName(request.getParameter("userName"));
		userDto.setUserTelNum(request.getParameter("userTelNum"));
		userDto.setNickName(request.getParameter("nickName"));
		userDto.setEMail_id(request.getParameter("eMail_id"));
		userDto.setEMail_url(request.getParameter("eMail_url"));
		userDto.setEMail(userDto.getEMail_id() + "@" + userDto.getEMail_url());
		userDto.setCompany(request.getParameter("company"));

		String password = userDto.getUserPwd();

		password = new BCryptPasswordEncoder().encode(password);

		Map<String, String> map = new HashMap<String, String>();
		map.put("item1", userDto.getUserId());
		map.put("item2", password);
		map.put("item3", userDto.getUserName());
		map.put("item4", userDto.getUserTelNum());
		map.put("item5", userDto.getNickName());
		map.put("item6", userDto.getEMail());
		map.put("item7", userDto.getCompany());

		int nResult = svc.join(map);
		System.out.println("join : " + nResult);

		String json = "";
		if (nResult == 1) {
			json = "{\"code\":\"success\", \"desc\":\"회원가입을 완료하였습니다.\"}";
		} else {
			json = "{\"code\":\"fail\", \"desc\":\"에러가 발생하여 회원가입에 실패했습니다.\"}";
		}

		return json;
	}

	@RequestMapping("/security/checkId")
	public @ResponseBody String checkId(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		System.out.println("userid=" + userId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("item1", userId);

		int nResult = svc.checkid(map);

		System.out.println("[" + nResult + "]");

		String json = "";

		if (nResult != 0) {// 결과 값이 있으면 아이디 존재
			json = "{\"code\":\"fail\", \"desc\":\"해당 아이디가 이미 존재합니다.\"}";
		} else { // 없으면 아이디 존재 X
			json = "{\"code\":\"success\", \"desc\":\"사용가능한 아이디입니다.\"}";
		}
		return json;
	}

	@RequestMapping("/security/checkNick")
	public @ResponseBody String checkNick(HttpServletRequest request) {
		String nickName = request.getParameter("nickName");
		System.out.println("nickname=" + nickName);
		Map<String, String> map = new HashMap<String, String>();
		map.put("item1", nickName);

		System.out.println(map);

		int nResult = svc.checkNick(map);

		System.out.println("[" + nResult + "]");

		String json = "";

		if (nResult != 0) {// 결과 값이 있으면 아이디 존재
			json = "{\"code\":\"fail\", \"desc\":\"해당 닉네임이 이미 존재합니다.\"}";
		} else { // 없으면 아이디 존재 X
			json = "{\"code\":\"success\", \"desc\":\"사용가능한 닉네임입니다.\"}";
		}
		return json;
	}

	@RequestMapping("/loginForm")
	public String loginFrom() {
		return "security/loginForm";
	}
	
	@RequestMapping("/security/login_naver")
	public String login_naver(HttpServletRequest request) throws UnsupportedEncodingException {
		System.out.println("login_naver");
		HttpSession session = request.getSession();
		
		String clientId = "nior9v26kcvpaRLlFZCi";//애플리케이션 클라이언트 아이디값";
    	String redirectURI = URLEncoder.encode("http://localhost:8081/security/signup_sns", "UTF-8");
    	SecureRandom random = new SecureRandom();
    	String state = new BigInteger(130, random).toString();
    	String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
    	apiURL += "&client_id=" + clientId;
   		apiURL += "&redirect_uri=" + redirectURI;
    	apiURL += "&state=" + state;
    	session.setAttribute("state", state);
    	
    	System.out.println(session.toString());
    	
    	System.out.println(apiURL.toString());
    	
    	return apiURL;
	}

	@RequestMapping("/security/signup_sns")
	public String signup_sns(HttpServletRequest request) throws UnsupportedEncodingException {
		String json = "";
		String page = "";
		
		String clientId = "nior9v26kcvpaRLlFZCi";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "UGgrKaA_LQ";// 애플리케이션 클라이언트 시크릿값";
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String redirectURI = URLEncoder.encode("http://localhost:8081/security/signup_sns", "UTF-8");
		String apiURL;
		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		apiURL += "client_id=" + clientId;
		apiURL += "&client_secret=" + clientSecret;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&code=" + code;
		apiURL += "&state=" + state;
		String access_token = "";
		String refresh_token = "";
		System.out.println("apiURL=" + apiURL);
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader br;
			System.out.print("responseCode=" + responseCode);
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if (responseCode == 200) {
				System.out.println("res="+res.toString());
				JSONParser parsing = new JSONParser();
				Object obj = parsing.parse(res.toString());
				JSONObject jsonObj = (JSONObject) obj;

				access_token = (String) jsonObj.get("access_token");
				refresh_token = (String) jsonObj.get("refresh_token");

				String token = access_token; // 네이버 로그인 접근 토큰;
				String header = "Bearer " + token; // Bearer 다음에 공백 추가

				String apiURL1 = "https://openapi.naver.com/v1/nid/me";

				Map<String, String> requestHeaders = new HashMap<>();
				requestHeaders.put("Authorization", header);
				String responseBody = get(apiURL1, requestHeaders);

//				System.out.println(responseBody);
				
				obj = parsing.parse(responseBody.toString());
//				System.out.println("obj = "+obj.toString());
				jsonObj = (JSONObject) obj;
				JSONObject response = (JSONObject)jsonObj.get("response");
//				System.out.println("["+response+"]");

				String snsId = (String) response.get("id");
				String snsName = (String) response.get("name");
				String eMail = (String) response.get("email");
				
				Map<String, String> map = new HashMap<String, String>();
				map.put("item1", snsId);
				map.put("item2", snsName);
				map.put("item3", eMail);
				System.out.println(map);
				int nResult = svc.checkid(map);
				System.out.println("check="+nResult);

				if (nResult != 0) {// 결과 값이 있으면 아이디 존재
					json = "{\"code\":\"success\"}";
					page = "member/main2";
				} else { // 없으면 아이디 존재 X
					nResult = svc.signUpSns(map);
					System.out.println("signUp = "+nResult);
					if (nResult == 1) {
						json = "{\"code\":\"signup\", \"desc\":\"회원가입을 완료하였습니다.\"}";
						page = "security/set_nickname";
					} else {
						json = "{\"code\":\"fail\", \"desc\":\"에러가 발생하여 회원가입에 실패했습니다.\"}";
						page = "redirect:loginForm";
					}
				}
				return page;
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return page;
	}
	
	private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
    
    @RequestMapping("/notice")
	public String userNoticeList(Model model) {
		model.addAttribute("noticeList", svc.noticeList());
		
		int nTotalCount = svc.noticeCount();
		System.out.println("Count : " + nTotalCount);
		return "/guest/notice1";
	}
    
    @RequestMapping("/admin/notice")
	public String adminNoticeList(Model model) {
		model.addAttribute("noticeList", svc.noticeList());
		
		int nTotalCount = svc.noticeCount();
		System.out.println("Count : " + nTotalCount);
		return "admin/notice3";
	}
    
    @RequestMapping("/admin/noticeForm")
	public String noticeForm(HttpServletRequest request, Model model, String uId) {
    	
    	uId = userInfo(null);
//    	System.out.println(uId.toString());
    	
    	UserDto uDto = svc.userSelect(uId);
    	System.out.println("nickname = " + uDto.getNickName().toString());
    	
    	model.addAttribute("nickname", uDto.getNickName());
    	
		return "admin/noticeForm";
	}
    
    public String userInfo(@AuthenticationPrincipal User user) {
    	String uId = (SecurityContextHolder.getContext().getAuthentication().getName().toString());
//    	System.out.println(uId.toString());
		return uId;
    }
    
    @RequestMapping("/fileUpload")
    public String imgUpload(HttpServletRequest request, HttpServletResponse response) {
    	
    	String fileInfo = "";
    	String oriFileName = "";
//    	String fileName = "";
    	String fileName = request.getHeader("file-name");
//    	System.out.println("filename = " + fileName);
//    	System.out.println("filesize = " + size);
    	JSONObject obj = new JSONObject();
    	
    	try {
    		String path = ResourceUtils
					.getFile("classpath:static/upload/").toPath().toString();
//    		System.out.println(path);
    		
    		oriFileName = fileName;
    		String reFileName = path + "/" + oriFileName;
    		System.out.println("1."+reFileName);
    		
    		InputStream input = request.getInputStream();
    		OutputStream output = new FileOutputStream(reFileName);
    		int numRead;
    		byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
    		while((numRead = input.read(b,0,b.length)) != -1) {
    			output.write(b,0,numRead);
    		}
    		if(input != null) {
    			input.close();
    		}
    		System.out.println("2."+path);
    		output.flush();
    		output.close();
    		
    		fileInfo += "&bNewLine=true";
    		fileInfo += "&sFileName="+fileName;
    		fileInfo += "&sFileURL="+"/upload/"+oriFileName;
    		System.out.println("fileInfo = "+fileInfo);
    		PrintWriter out = response.getWriter();
    		out.print(fileInfo);
    		out.flush();
    		out.close(); 
    		
    	} catch(Exception e) {
    	}
		return "";
    }
    
    @RequestMapping("/admin/noticeWrite")
	public @ResponseBody String noticeWrite(HttpServletRequest request, Model model,
			@ModelAttribute("dto") @Valid NoticeDto nDto, BindingResult result)
    {
    	String page = "admin/noticeForm";

		if (result.hasErrors()) {
			System.out.println("getAllErrors : " + result.getAllErrors());

			if (result.getFieldError("userId") != null) {
				System.out.println("1:" + result.getFieldError("writer").getCode());
			}
			if (result.getFieldError("userPwd") != null) {
				System.out.println("2:" + result.getFieldError("title").getCode());
			}
			if (result.getFieldError("userName") != null) {
				System.out.println("3:" + result.getFieldError("content_text").getCode());
			}

			return page;
		}

		nDto.setWriter(request.getParameter("writer"));
		nDto.setTitle(request.getParameter("title"));
		nDto.setContent_text(request.getParameter("content_text"));

		Map<String, String> map = new HashMap<String, String>();
		map.put("item1", nDto.getWriter());
		map.put("item2", nDto.getTitle());
		map.put("item3", nDto.getContent_text());
		System.out.println(map.toString());
		
		int nResult = svc.noticeWrite(map);
		System.out.println("noticeWrite : " + nResult);

		String json = "";
		if (nResult == 1) {
			json = "{\"code\":\"success\", \"desc\":\"작성을 완료하였습니다.\"}";
		} else {
			json = "{\"code\":\"fail\", \"desc\":\"에러가 발생하여 작성에 실패했습니다.\"}";
		}

		return json;
	}
    
    @RequestMapping("/admin/noticeView")
	public String noticeView(HttpServletRequest request, Model model) {
    	System.out.println("noticeView");
		String nId = request.getParameter("id");
		System.out.println("id="+nId);
		model.addAttribute("noticeView", svc.noticeView(nId));
		System.out.println(model.toString());
		
		return "admin/notice_view_con";
	}
    
	@RequestMapping("/member/main2")
	public String main2() {
		return "member/main2";
	}
	
	@RequestMapping("/admin/main")
	public String main3() {
		return "admin/main3";
	}
	
	@RequestMapping("/admin/map")
	public String searchMap() {
		return "admin/search_map";
	}
	
	@RequestMapping("/search")
	public @ResponseBody String search(HttpServletRequest request) throws ParseException {
        String clientId = "oMd64C0j0eZ36VsOiutc"; //애플리케이션 클라이언트 아이디값"
        String clientSecret = "us5Tf80Enx"; //애플리케이션 클라이언트 시크릿값"


        String text = null;
        try {
            text = URLEncoder.encode(request.getParameter("search"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }


        String apiURL = "https://openapi.naver.com/v1/search/local?query=" + text;    // json 결과
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);
        
        JSONParser parsing = new JSONParser();
		Object obj = parsing.parse(responseBody.toString());
		System.out.println(obj.toString());
		
		JSONObject jsonObj = (JSONObject) obj;
		
		JSONArray items = (JSONArray) jsonObj.get("items");
		System.out.println(items.toJSONString());
		
//		String link = (String)items.get("link");
		
		obj = parsing.parse(items.toString());
		System.out.println(obj.toString());
		JSONArray jsonArr = (JSONArray) obj;
		System.out.println(jsonArr.toJSONString());
		
//		JSONArray link = (JSONArray) jsonArr.get("link");
//		
//		String url = (String) link.toString();
//		System.out.println("url = " + url);
		
		return jsonArr.toJSONString();
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
