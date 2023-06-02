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
	private static final Logger log= LoggerFactory.getLogger(MemberController.class);
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
		
		String uri=request.getRequestURI();
		log.info(">>> uri : "+uri);
		String path=uri.substring(uri.lastIndexOf("/")+1);
		log.info(">>> path : "+path);
		
		switch(path) {
		case "join":
			destPage="/member/join.jsp";
			break;
			
		case "register":
			try {
				log.info(">>> register page");
				MemberVO mvo= new MemberVO(request.getParameter("id"), request.getParameter("password"), request.getParameter("email"), Integer.parseInt(request.getParameter("age")));
				isOk=msv.register(mvo);
				log.info(">>> register > "+(isOk > 0 ? "success":"fail"));
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
				MemberVO mvo=new MemberVO(request.getParameter("id"), request.getParameter("password"));
				log.info(">>> login 요청"+mvo);
				MemberVO loginMvo= msv.login(mvo);
				//같은 정보가 있다면 리턴, 없다면 null이 리턴
				//객체가 있다면 세션에 저장
				if(loginMvo != null) {
					HttpSession ses= request.getSession();
					ses.setAttribute("ses", loginMvo);
					ses.setMaxInactiveInterval(10*60);
				}else {
					request.setAttribute("msg_login", 0);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			destPage="/";
			break;
			
		case "logout":
			try {
				HttpSession ses= request.getSession();
				MemberVO mvo= (MemberVO)(ses.getAttribute("ses"));
				String id=mvo.getId();
				log.info(">>> login id : "+id);
				//id를 주고 마지막 로그인 시간 기록
				isOk=msv.lastLogin(id);
				log.info(">>> LOGOUT "+(isOk>0 ? "success":"fail"));
				ses.invalidate();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			destPage="/";
			break;
			
		case "modify":
			destPage="/member/modify.jsp";
			break;
			
		case "edit":
			try {
				MemberVO mvo= new MemberVO(request.getParameter("id"), request.getParameter("password"), request.getParameter("email"), Integer.parseInt(request.getParameter("age")));
				isOk=msv.modify(mvo);				
				log.info(">>> modify "+(isOk>0 ? "success":"fail"));
				log.info(">>> modify 완료, session 변경시작");
				if(isOk>0) {
					msv.login(mvo);
					request.setAttribute("mvo", mvo);
					log.info(">>> session 변경 완료");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			destPage="login_auth";
			break;
			
		case "remove":
			try {
				HttpSession ses= request.getSession();
				MemberVO mvo=(MemberVO)(ses.getAttribute("ses"));
				String id=mvo.getId();
				isOk=msv.remove(id);
				log.info(">>> remove "+(isOk>0 ? "success":"fail"));
				ses.invalidate();
				log.info(">>> logout 완료");

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			destPage="/";
			break;
			
		case "list":
			try {
				List<MemberVO>list = new ArrayList<>();
				list=msv.getList();
				request.setAttribute("list", list);
				log.info(">>> list 성공");
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
		service(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
		
	}

}