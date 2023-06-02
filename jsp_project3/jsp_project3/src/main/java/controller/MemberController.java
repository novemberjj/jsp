package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import service.MemberService;
import service.MemberServiceImpl;


@WebServlet("/mem/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//log 설정
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	private RequestDispatcher rdp;
	private MemberService msv;
	private int isOk;
	private String destPage;
       
    public MemberController() {
        msv=new MemberServiceImpl();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String uri= request.getRequestURI();
		log.info(">>> uri : "+uri);
		String path=uri.substring(uri.lastIndexOf("/")+1);
		log.info(">>> path : "+path);
		
		switch(path) {
		case "join":
			destPage="/member/join.jsp";
			break;
		case "register":
			try {
				log.info(">>> register page"+request.getParameter("age"));
				int age=Integer.parseInt(request.getParameter("age"));
				log.info(">>>"+age);
				MemberVO mvo= new MemberVO(request.getParameter("id"), request.getParameter("password"), 
						request.getParameter("email"), age);
				int show=mvo.getAge();
				System.out.println(show);
				
				isOk=msv.register(mvo);
				log.info(">>> register > "+(isOk>0 ? "success":"fail"));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			destPage="/";
			break;
			
		case "login":
			destPage="/member/login.jsp";
			break;
			
		case "login_auth":
			try {
				log.info(">>> login page");
				MemberVO mvo = new MemberVO(request.getParameter("id"), request.getParameter("password"));
				//해당 id, passowrd가 db상에 있는지 체크
				//해당 객체(사용자)를 가져와서
				//해당 객체(사용자)를 세션에 담기
				log.info(">>> login 요청"+mvo);
				MemberVO loginMvo=msv.login(mvo);
				//같은 정보가 있으면 객체를 리턴, 없다면 null 리턴
				//객체가 있다면 세션에 저장
				if(loginMvo != null) {
					//세션 가져오기 연결된 세션이 없다면 새로 생성
					HttpSession ses = request.getSession();
					ses.setAttribute("ses", loginMvo);
					ses.setMaxInactiveInterval(10*60);
				}else {
					request.setAttribute("msg_login", 0);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			destPage="/";
			break;
			
		case "logout":
			try {
				//세션 가져오기(연결된 세션)
				HttpSession ses = request.getSession();
				//마지막 로그인 시간 기록
				MemberVO mvo =(MemberVO)(ses.getAttribute("ses"));
				String id=mvo.getId();
				log.info(">>> login id : "+id);
				//로그인 정보(id)를 주고 마지막 로그인 시간 기록
				isOk=msv.lastlogin(id);
				log.info(">>> LOGOUT > "+(isOk>0 ? "success":"fail"));
				ses.invalidate();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			destPage="/";
			break;
			
		case "modify":
			try {
				HttpSession ses = request.getSession();
				MemberVO mvo =(MemberVO)(ses.getAttribute("ses"));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			destPage="/member/modify.jsp";
			break;
			
		case "edit":
			try {
				MemberVO mvo = new MemberVO(request.getParameter("id"), request.getParameter("password"), request.getParameter("email"), Integer.parseInt(request.getParameter("age")));
				isOk=msv.modify(mvo);
				log.info(">>> MODIFY > "+(isOk>0 ? "success":"fail"));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			destPage="login_auth";
			break;
			
		case "remove":
			try {
				HttpSession ses = request.getSession();
				MemberVO mvo =(MemberVO)(ses.getAttribute("ses"));
				String id=mvo.getId();
				isOk=msv.remove(id);
				log.info(">>> REMOVE > "+(isOk>0 ? "success":"fail"));
				ses.invalidate();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			destPage="/";
			break;
			
		case "list":
			try {
				List<MemberVO> list = new ArrayList<>();
				list=msv.selectList();
				request.setAttribute("list", list);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			destPage="/member/list.jsp";
			break;
		}
		
		rdp=request.getRequestDispatcher(destPage);
		rdp.forward(request, response);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(request, response);
	}

}
