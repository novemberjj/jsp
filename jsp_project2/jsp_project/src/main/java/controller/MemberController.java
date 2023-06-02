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

@WebServlet("/mem/*") //어노테이션
//controller로 온다는 뜻 
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //log 설정
	//private static final Logger log = LoggerFactory.getLogger(클래스명.class);
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	private RequestDispatcher rdp;
	private MemberService msv;
	private int isOk;
	private String destPage;
	
    public MemberController() {
        msv= new MemberServiceImpl();
    }
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// characterEncoding 설정 / contentType / uri
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String uri = request.getRequestURI();
		log.info(">>> uri : "+uri);
		// 	/mem/join : 요청에 대한 path만 남기기
		String path=uri.substring(uri.lastIndexOf("/")+1);
		log.info(">>> path : "+path);
		
		switch(path) {
		case "join":
			destPage="/member/join.jsp"; //member => jsp로 이동
			break;
		case "register":
			//jsp에서 가져온 파라미터 저장
			String id=request.getParameter("id");
			String password=request.getParameter("password");
			String email=request.getParameter("email");
			int age=Integer.parseInt(request.getParameter("age"));
			
			// 파라미터로 mvo 객체 생성
			MemberVO mvo = new MemberVO(id, password, email, age);
			isOk = msv.register(mvo);
			log.info(">>> JOIN > "+(isOk>0 ? "success":"fail"));
			destPage="/"; //기본 정보는 index
			break;
			
		case "login": //로그인 페이지로 이동
			log.info(">>> login page 이동");
			destPage="/member/login.jsp";
			break;
			
		case "login_auth": //실제 로그인이 일어나는 케이스
			try {
				id=request.getParameter("id");
				password=request.getParameter("password");
				MemberVO mvo2 = new MemberVO(id, password);
				//해당 id, pasword가 db상에 있는지 체크
				//해당 객체(사용자)를 가져와서 
				//해당 객체(사용자)를 세션에 담기
				log.info(">>> login 요청"+mvo2);
				MemberVO loginMvo = msv.login(mvo2);
				//같은 정보가 있으면 객체를 리턴, 없다면 null이 리턴
				//객체가 있다면 세션에 저장
				if(loginMvo != null) {
					//세션 가져오기. 연결된 세션이 없다면 새로 생성
					HttpSession ses = request.getSession();
					ses.setAttribute("ses", loginMvo);
					//로그인 유지 시간(초단위)
					ses.setMaxInactiveInterval(10*60);
				}else {
					request.setAttribute("msg_login", 0);
				}
				destPage="/";
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
			
		case "logout":
			try {
				//세션 가져오기 (연결된 세션)
				HttpSession ses = request.getSession();
				//마지막 로그인 시간 기록
				MemberVO mvo2 = (MemberVO)(ses.getAttribute("ses")); //getAttribute => Object 리턴
				String id2= mvo2.getId();
				log.info(">>> login id : "+id2);
				//로그인정보(id)를 주고 마지막 로그인 시간 기록
				isOk=msv.lastLogin(id2);
				log.info(">>> LOGOUT "+(isOk>0 ? "success":"fail"));
				ses.invalidate(); //권한 해제 메서드
			} catch (Exception e) {
				// TODO: handle exception
			}
			destPage="/";
			break;
			
		case "modify":
			log.info(">>> modify page 접근");
			destPage="/member/modify.jsp";
			try {
				HttpSession ses = request.getSession();
				MemberVO mvo2 = (MemberVO)(ses.getAttribute("ses"));
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			break;
		
		case "edit":
			try {
				id=request.getParameter("id");
				password=request.getParameter("password");
				email=request.getParameter("email");
				age=Integer.parseInt(request.getParameter("age"));
				
				MemberVO mvo3= new MemberVO(id, password, email, age,
						request.getParameter("reg_date"),
						request.getParameter("last_login"));
				
				isOk=msv.modify(mvo3);
				log.info(">>> modify "+(isOk>0 ? "success":"fail"));
				log.info(">>> modify 완료, session 변경시작");
				if(isOk>0) {
					//case 1 session 객체에 값을 담기
					msv.login(mvo3); //modify에서 모든 정보를 가져오지 않았을 경우
					//HttpSession ses = request.getSession();
					//ses.setAttribute("ses", mvo3);
					
					//case 2 login_auth case로 mvo3 객체 보내기 //이게 더 확실
					request.setAttribute("mvo", mvo3);
					log.info(">>> session 변경 완료");
				}
				destPage="login_auth";
				//destPage="/";
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			

			break;
			
		case "remove":
			//ses 객체에서 login 정보 가져오기 
			try {
				//기존에 연결된 세션을 가져옴 (로그인한 객체를 가져옴)
				HttpSession ses = request.getSession();
				MemberVO reMvo = (MemberVO)(ses.getAttribute("ses"));
				id=reMvo.getId();
				isOk=msv.remove(id);
				log.info(">>> remove "+(isOk>0 ? "success":"fail"));
				ses.invalidate(); //세션 로그아웃
				log.info(">>> logout 완료");
				destPage="/";
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			break;
			
		case "list":
			try {
				List<MemberVO> list= new ArrayList<MemberVO>();
				list=msv.getList();
				request.setAttribute("list", list);
				log.info(">>> list 성공");
				destPage="/member/list.jsp";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
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
