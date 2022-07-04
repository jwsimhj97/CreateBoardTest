package com.test.view.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.WebUtils;

import com.test.hi.user.UserService;
import com.test.hi.user.UserVO;

@Controller
@SessionAttributes("user")
public class UserController extends HttpServlet{

	@Autowired
	private UserService userService;
	
//	join  insertUser
	@RequestMapping(value="/join.do", method=RequestMethod.POST)
	public String join(UserVO vo, Model model) throws InterruptedException {
		System.out.println("Controller >> join");
		model.addAttribute("user",vo);
		System.out.println(vo.toString());
		
		return "join_welcome.jsp";
	}	
	
	@RequestMapping(value="/join_welcome.do", method=RequestMethod.POST)
	public String join_welcome(@ModelAttribute("user") UserVO vo, Model model) {
		System.out.println("Controller >> Join process done. Redircet to Login");
		System.out.println(vo.toString());
		userService.insertUser(vo);
		
		return "login.jsp";
	}
	
	
	// LOGIN-get
	   @RequestMapping(value="login.do",method=RequestMethod.GET)
	   public String login_View(HttpSession session, HttpServletRequest request) {
	      if(session.getAttribute("user")!= null) {
	         System.out.println("login-get//session(user) delete");
	         session.invalidate();
	      }
	      
	    //쿠키가 존재하면 이전에 저장해놓은 session_id를 가져와서 
		Cookie cookie = WebUtils.getCookie(request,"loginCookie");
		if(cookie!=null) {
			System.out.println("if(cookie!=null)");
			
			String session_id=cookie.getValue();
			System.out.println(session_id);
			
			//selectSession: SELECT * FROM USER_TABLE WHERE SESSION_ID=#{session_Id} and LIMIT_DATE > now()
			//유효기간이 남아있으면서 해당 sessionID를 가지는 사용자 정보 조회
			UserVO vo=userService.selectSession(session_id);
			if(vo!=null) {
				System.out.println("if(vo!=null) vo: "+vo);
				UserVO user = userService.getUser(vo);
		        session = request.getSession();
				session.setAttribute("UserInfo", user.getName());
				System.out.println("로그인성공 get"); 
	            return "redirect:index.jsp";
			}
		}
	      return "login.jsp";
	   }
	
	  //로그인페이지 이동
	   @RequestMapping(value="login.do", method=RequestMethod.POST)
		public String login(UserVO vo, HttpSession session,
				HttpServletRequest request, HttpServletResponse response, 
				@RequestParam("pw") String pw) {
			System.out.println("Controller >> login");
	        System.out.println(vo);
	      //회원가입 시 사용했던 user세션이 존재한다면 삭제
			if(session.getAttribute("user")!= null) {
		         System.out.println("login-post//session(user) delete");
		         session.invalidate();
			}
	        UserVO user = userService.getUser(vo);
	        System.out.println("user: "+user);
	        session = request.getSession();
	        if(user != null) {
	        	session.setAttribute("UserInfo", user.getName());
	            //사용자가 자동로그인을 체크했을시에 실행
	            if(vo.isAutoLogin()) {
	                System.out.println("autoLogin if");
	                //자동로그인 기간 설정
	                long second = 60 * 60 * 24 * 90; //3개월뒤 (초)
	                //쿠키생성
	                Cookie cookie = new Cookie("loginCookie",session.getId());
	                System.out.println("session.getId(): "+session.getId());
	                cookie.setPath("/"); // 쿠키를 찾을 경로를 컨텍스트 경로(사용자 PC에서 쿠키를 보내는 경로가 "/" 로 설정함으로써 contextPath 이하의 모든 요청에 대해서 쿠키를 전송할 수 있도록 설정)
	                cookie.setMaxAge((int)second);
	                response.addCookie(cookie);
	                
	                //3개월뒤의 밀리초를 날짜로 변환
	                long millis = System.currentTimeMillis() + (second * 1000); 
	                Date limitDate = new Date(millis);
	                System.out.println(limitDate);
	                
	                //DB에 세션아이디,쿠키만료날짜,회원 아이디 전달
	                
	                userService.autoLogin(session.getId(),limitDate,user.getId(), user.getName());
	            }
	            if(user.getRole()==0) {
	            	System.out.println("로그인성공(관리자)"); 
		            return "redirect:adminMain.jsp";
	        	}
	            System.out.println("로그인성공 post"); 
	            System.out.println(user.getName()+"님 환영합니다. ");
	            return "redirect:index.jsp";
	        }else {
	        	System.out.println("로그인실패");
	            return "redirect:login.jsp";
	        }
	  }
	   
	   
	   
	   

	 //로그아웃처리
	  @RequestMapping(value="logout.do")
	  public String logout(UserVO vo, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		  System.out.println("Controller >> logout");
		  System.out.println(vo);
		  
		  String UserInfo = (String) session.getAttribute("UserInfo");
		  vo.setName(UserInfo);
		  userService.getUser(vo);
		  System.out.println(vo);
		  
	      if(UserInfo!= null) {
	          session.removeAttribute("UserInfo"); 
	          session.invalidate();
	          
	          //자동로그인을 한 상태의 사용자가 로그아웃을 할 경우
	          Cookie cookie = WebUtils.getCookie(request,"loginCookie");
	          if(cookie != null) {
	        	  System.out.println("자동로그인을 한 상태의 사용자가 로그아웃을 할 경우");
	        	  cookie.setPath("/");
	              cookie.setMaxAge(0);
	              response.addCookie(cookie);
	              userService.autoLogin("none",new Date(),vo.getId(), vo.getName());
	          }
	      }
	      return "redirect:/index.jsp";
	  }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * @RequestMapping(value = "login.do", method = RequestMethod.GET) public String
	 * loginView(UserVO vo) { System.out.println("로그인 화면으로 이동"); vo.setId("test");
	 * vo.setPw("test1234"); return "login.jsp"; }
	 * 
	 * @RequestMapping(value = "login.do", method = RequestMethod.POST) public
	 * String login(UserVO vo, HttpSession session, HttpServletRequest request) {
	 * System.out.println("로그인 인증 처리...");
	 * 
	 * session = request.getSession(); session.setAttribute("userVO", vo);
	 * 
	 * if (userService.getUser(vo) != null) { session.setAttribute("userId",
	 * userService.getUser(vo).getId()); session.setAttribute("userName",
	 * userService.getUser(vo).getName()); return "getBoardList.do"; } else { return
	 * "login.jsp?error=1"; } }
	 * 
	 * @RequestMapping("/logout.do") public String logout(HttpSession session) {
	 * session.invalidate(); return "login.jsp"; }
	 */
}