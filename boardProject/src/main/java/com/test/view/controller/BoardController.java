package com.test.view.controller;

import java.io.File;
import java.io.IOException;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.test.hi.board.BoardService;
import com.test.hi.board.BoardVO;
import com.test.hi.user.UserService;
import com.test.hi.user.UserVO;

@Controller
@SessionAttributes("board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private UserService userService;
	
	String realPath = "/resources/" ;
	
	// �� �ۼ��Ϸ�
	// ���� ���ε� ó�������ΰ����� �Ǵܵǰ�����
		@RequestMapping(value = "/insertBoard.do", method=RequestMethod.POST)
		public String insertBoard(UserVO vo, BoardVO bvo, MultipartHttpServletRequest request) throws IOException{
			System.out.println("�۵�ϿϷ��ϰ� �Խ������� �̵���...1");
			//���� ���ε� ó�� 
			MultipartFile uploadFile = bvo.getUploadFile();
			
			if(!uploadFile.isEmpty()) {
				String fileName = uploadFile.getOriginalFilename();
				
				File file = new File(realPath+fileName);
				bvo.setFile_name(fileName);
				if(!file.exists()) {
					file.mkdirs();
					System.out.println("�۵�ϿϷ��ϰ� �Խ������� �̵���...2");
				}
				uploadFile.transferTo(file);
			}

			
			boardService.insertBoard(bvo);
			System.out.println("�۵�ϿϷ��ϰ� �Խ������� �̵���...3");
			return "getBoardList.do";
		}
		
	
	// �� ����
		@RequestMapping("/updateBoard.do")
		public String updateBoard(@ModelAttribute("board") BoardVO vo, HttpSession session) {
			if( vo.getUser_name().equals(session.getAttribute("user_name").toString()) ){
				boardService.updateBoard(vo);
				return "getBoardList.do";
			}else {
				return "getBoard.do?error=1";
			}
			
		}

		// �� ����
		@RequestMapping("/deleteBoard.do")
		public String deleteBoard(@ModelAttribute("board") BoardVO vo, HttpSession session) {
			if( vo.getUser_name().equals(session.getAttribute("user_name").toString()) ) {
				if(vo.getFile_name()!=null) {
					System.out.println("���ϻ���: "+realPath + vo.getFile_name());
					File f = new File(realPath + vo.getFile_name());		
					f.delete();
				}
			}
			boardService.deleteBoard(vo);
			return "getBoardList.do";
		}

		// �� �� ��ȸ
		@RequestMapping("/getBoard.do")
		public String getBoard(BoardVO vo, Model model) {
			model.addAttribute("board", boardService.getBoard(vo));
			System.out.println("77:"+boardService.getBoard(vo).getFile_name());
			return "getBoard.jsp";
		}

		// �� ���
		@RequestMapping("/getBoardList.do")
		public String getBoardListPost(UserVO vo, BoardVO bvo, Model model, HttpServletRequest request) {
			System.out.println("�� ��� ������...1");
			
			model.addAttribute("boardList", boardService.getBoardList(bvo));
			System.out.println("�� ��� ������...2");
			
			vo.setName((String) request.getSession().getAttribute("UserInfo"));
			System.out.println("�� ��� ������...3");
			return "getBoardList.jsp";
		}
		
		@RequestMapping(value="/download.do", method=RequestMethod.GET)
	    public void fileDownLoad(@RequestParam(value="file_name",defaultValue = "", required=false) String file_name, HttpServletRequest request, HttpServletResponse response) throws IOException {
			System.out.println("���� �ٿ�ε�");
			if (!file_name.equals("")) {
		        //(2) ��û���� ���� �ҷ�����
		        File file = new File(realPath+file_name);
		
				// �ѱ��� http ����� ����� �� ���� ������ ���ϸ��� �������� ���ڵ��Ͽ� ����� �����Ѵ�
				String fn = new String(file.getName().getBytes(), "iso_8859_1");
				System.out.println("fn: "+fn);
		
				
				//(3) ContentType����
				byte[] bytes = org.springframework.util.FileCopyUtils.copyToByteArray(file);
				response.setHeader("Content-Disposition", "attachment; file_name=\""+ fn + "\"");
				response.setContentLength(bytes.length);
		//			response.setContentType("image/jpeg");
		        
				response.getOutputStream().write(bytes);
		        response.getOutputStream().flush();
		        response.getOutputStream().close();
	        }
	    }
		
	}
