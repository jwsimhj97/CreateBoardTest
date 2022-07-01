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
	
	// 글 작성완료
	// 파일 업로드 처리때문인것으로 판단되고있음
		@RequestMapping(value = "/insertBoard.do", method=RequestMethod.POST)
		public String insertBoard(UserVO vo, BoardVO bvo, MultipartHttpServletRequest request) throws IOException{
			System.out.println("글등록완료하고 게시판으로 이동함...1");
			//파일 업로드 처리 
			MultipartFile uploadFile = bvo.getUploadFile();
			
			if(!uploadFile.isEmpty()) {
				String fileName = uploadFile.getOriginalFilename();
				
				File file = new File(realPath+fileName);
				bvo.setFile_name(fileName);
				if(!file.exists()) {
					file.mkdirs();
					System.out.println("글등록완료하고 게시판으로 이동함...2");
				}
				uploadFile.transferTo(file);
			}

			
			boardService.insertBoard(bvo);
			System.out.println("글등록완료하고 게시판으로 이동함...3");
			return "getBoardList.do";
		}
		
	
	// 글 수정
		@RequestMapping("/updateBoard.do")
		public String updateBoard(@ModelAttribute("board") BoardVO vo, HttpSession session) {
			if( vo.getUser_name().equals(session.getAttribute("user_name").toString()) ){
				boardService.updateBoard(vo);
				return "getBoardList.do";
			}else {
				return "getBoard.do?error=1";
			}
			
		}

		// 글 삭제
		@RequestMapping("/deleteBoard.do")
		public String deleteBoard(@ModelAttribute("board") BoardVO vo, HttpSession session) {
			if( vo.getUser_name().equals(session.getAttribute("user_name").toString()) ) {
				if(vo.getFile_name()!=null) {
					System.out.println("파일삭제: "+realPath + vo.getFile_name());
					File f = new File(realPath + vo.getFile_name());		
					f.delete();
				}
			}
			boardService.deleteBoard(vo);
			return "getBoardList.do";
		}

		// 글 상세 조회
		@RequestMapping("/getBoard.do")
		public String getBoard(BoardVO vo, Model model) {
			model.addAttribute("board", boardService.getBoard(vo));
			System.out.println("77:"+boardService.getBoard(vo).getFile_name());
			return "getBoard.jsp";
		}

		// 글 목록
		@RequestMapping("/getBoardList.do")
		public String getBoardListPost(UserVO vo, BoardVO bvo, Model model, HttpServletRequest request) {
			System.out.println("글 목록 페이지...1");
			
			model.addAttribute("boardList", boardService.getBoardList(bvo));
			System.out.println("글 목록 페이지...2");
			
			vo.setName((String) request.getSession().getAttribute("UserInfo"));
			System.out.println("글 목록 페이지...3");
			return "getBoardList.jsp";
		}
		
		@RequestMapping(value="/download.do", method=RequestMethod.GET)
	    public void fileDownLoad(@RequestParam(value="file_name",defaultValue = "", required=false) String file_name, HttpServletRequest request, HttpServletResponse response) throws IOException {
			System.out.println("파일 다운로드");
			if (!file_name.equals("")) {
		        //(2) 요청파일 정보 불러오기
		        File file = new File(realPath+file_name);
		
				// 한글은 http 헤더에 사용할 수 없기 때문에 파일명은 영문으로 인코딩하여 헤더에 적용한다
				String fn = new String(file.getName().getBytes(), "iso_8859_1");
				System.out.println("fn: "+fn);
		
				
				//(3) ContentType설정
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
