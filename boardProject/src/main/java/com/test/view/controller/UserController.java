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
	      
	    //��Ű�� �����ϸ� ������ �����س��� session_id�� �����ͼ� 
		Cookie cookie = WebUtils.getCookie(request,"loginCookie");
		if(cookie!=null) {
			System.out.println("if(cookie!=null)");
			
			String session_id=cookie.getValue();
			System.out.println(session_id);
			
			//selectSession: SELECT * FROM USER_TABLE WHERE SESSION_ID=#{session_Id} and LIMIT_DATE > now()
			//��ȿ�Ⱓ�� ���������鼭 �ش� sessionID�� ������ ����� ���� ��ȸ
			UserVO vo=userService.selectSession(session_id);
			if(vo!=null) {
				System.out.println("if(vo!=null) vo: "+vo);
				UserVO user = userService.getUser(vo);
		        session = request.getSession();
				session.setAttribute("UserInfo", user.getName());
				System.out.println("�α��μ��� get"); 
	            return "redirect:index.jsp";
			}
		}
	      return "login.jsp";
	   }
	
	  //�α��������� �̵�
	   @RequestMapping(value="login.do", method=RequestMethod.POST)
		public String login(UserVO vo, HttpSession session,
				HttpServletRequest request, HttpServletResponse response, 
				@RequestParam("pw") String pw) {
			System.out.println("Controller >> login");
	        System.out.println(vo);
	      //ȸ������ �� ����ߴ� user������ �����Ѵٸ� ����
			if(session.getAttribute("user")!= null) {
		         System.out.println("login-post//session(user) delete");
		         session.invalidate();
			}
	        UserVO user = userService.getUser(vo);
	        System.out.println("user: "+user);
	        session = request.getSession();
	        if(user != null) {
	        	session.setAttribute("UserInfo", user.getName());
	            //����ڰ� �ڵ��α����� üũ�����ÿ� ����
	            if(vo.isAutoLogin()) {
	                System.out.println("autoLogin if");
	                //�ڵ��α��� �Ⱓ ����
	                long second = 60 * 60 * 24 * 90; //3������ (��)
	                //��Ű����
	                Cookie cookie = new Cookie("loginCookie",session.getId());
	                System.out.println("session.getId(): "+session.getId());
	                cookie.setPath("/"); // ��Ű�� ã�� ��θ� ���ؽ�Ʈ ���(����� PC���� ��Ű�� ������ ��ΰ� "/" �� ���������ν� contextPath ������ ��� ��û�� ���ؼ� ��Ű�� ������ �� �ֵ��� ����)
	                cookie.setMaxAge((int)second);
	                response.addCookie(cookie);
	                
	                //3�������� �и��ʸ� ��¥�� ��ȯ
	                long millis = System.currentTimeMillis() + (second * 1000); 
	                Date limitDate = new Date(millis);
	                System.out.println(limitDate);
	                
	                //DB�� ���Ǿ��̵�,��Ű���ᳯ¥,ȸ�� ���̵� ����
	                
	                userService.autoLogin(session.getId(),limitDate,user.getId(), user.getName());
	            }
	            if(user.getRole()==0) {
	            	System.out.println("�α��μ���(������)"); 
		            return "redirect:adminMain.jsp";
	        	}
	            System.out.println("�α��μ��� post"); 
	            System.out.println(user.getName()+"�� ȯ���մϴ�. ");
	            return "redirect:index.jsp";
	        }else {
	        	System.out.println("�α��ν���");
	            return "redirect:login.jsp";
	        }
	  }
	   
	   
	   
	   

	 //�α׾ƿ�ó��
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
	          
	          //�ڵ��α����� �� ������ ����ڰ� �α׾ƿ��� �� ���
	          Cookie cookie = WebUtils.getCookie(request,"loginCookie");
	          if(cookie != null) {
	        	  System.out.println("�ڵ��α����� �� ������ ����ڰ� �α׾ƿ��� �� ���");
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
	 * loginView(UserVO vo) { System.out.println("�α��� ȭ������ �̵�"); vo.setId("test");
	 * vo.setPw("test1234"); return "login.jsp"; }
	 * 
	 * @RequestMapping(value = "login.do", method = RequestMethod.POST) public
	 * String login(UserVO vo, HttpSession session, HttpServletRequest request) {
	 * System.out.println("�α��� ���� ó��...");
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
