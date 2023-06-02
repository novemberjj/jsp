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

import domain.ProductVO;
import service.ProductService;
import service.Service;

//HttpServlet 웹과 소통하기 위한 객체 targeted runtimes check되어있어야 함
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 상품등록, 상품리스트, 상품상세, 상품수정, 상품삭제
	//컨트롤러 -> 서비스, 서비스 -> DAO, DAO -> DBconnection
	private Service svc;
    

	//생성자
    public ProductController() { 
    	svc = new ProductService();
    	//인터페이스의 구현체로 객체 생성
    	/* List<String> list = new ArrayList<>();
    	 * List도 인터페이스 ArrayList는 구현체
    	 * */
    }


    //체크해서 나온 애들
    // 주 작업 영역
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get / post 모든 처리는 service에서 처리함.
		// post 방식으로 데이터 처리를 할 때 한글이 깨지는 것을 방지
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		
		String uri=req.getRequestURI(); //요청 경로(원하는 서비스) 컨트롤러주소/요청서비스
		System.out.println(">>> uri : "+uri); //로그데이터 표식 >>> 내가 남긴 로그
		String context=req.getContextPath(); // 프로젝트 명
		System.out.println(">>> context : "+context);
		
		//객체를 보내야하는 목적지 주소
		String destPage=""; 
		
		//오는 요청에 대한 처리
		switch(uri) {
		case "/register.pd":
			//	/폴더명/jsp파일명.jsp
			destPage="/register.jsp";
			break;
		case "/insert.pd":
			//DB연결, 등록처리
			//jsp에서 가져온 객체(이름, 가격, 세부정보)를 가지고 왔다.
			//service에게 가져온 데이터를 객체화 하여 db에 저장해달라고 요청
			//1. 객체를 생성한다.
			String pname=req.getParameter("pname");
			int price= Integer.parseInt(req.getParameter("price")); //getParameter()는 String으로 처리됨
			String madeby=req.getParameter("madeby"); //input name 값이다
			ProductVO pvo = new ProductVO(pname, price, madeby);
			//생성자가 없을 경우
			//ProductVO pvo= new ProductVO();
			//pvo.setPname(pname); 
			
			//2. service에게 객체 주고 요청
			int isOk=svc.register(pvo); //리턴값 1개의 행이 등록
			System.out.println(">>> 상품등록 "+(isOk>0 ? "성공":"실패"));
			
			destPage="/index.jsp";
			break;
			
		case "/list.pd":
			List<ProductVO> list= new ArrayList<>();
			
			list=svc.list();
			req.setAttribute("list", list);
			//req.setAttribute("list", svc.list());
			System.out.println(">>> 상품 리스트 성공");
			destPage="/list.jsp";
			break;
			
		case "/detail.pd":
			int pno = Integer.parseInt(req.getParameter("pno"));
			ProductVO p=new ProductVO();
			p=svc.detail(pno);
			req.setAttribute("pvo", p);
			destPage="/detail.jsp";
			System.out.println(">>> 상품 상세 성공");
			break;
			
		case "/modify.pd":
			pno=Integer.parseInt(req.getParameter("pno"));
			req.setAttribute("pvo", svc.detail(pno));
			System.out.println(">>> 상품 업데이트 상세 성공");
			
			destPage="/modify.jsp";
			break;
			
		case "/edit.pd":
			 pno=Integer.parseInt(req.getParameter("pno"));
			 pname=req.getParameter("pname");
			 price=Integer.parseInt(req.getParameter("price"));
			 madeby=req.getParameter("madeby");
			
			ProductVO product= new ProductVO(pno, pname, price, madeby);
			isOk=svc.edit(product);
			System.out.println(">>> 상품 수정"+(isOk>0 ? "성공":"실패"));
			destPage="/list.pd";
			break;
			
		case "/remove.pd":
			pno=Integer.parseInt(req.getParameter("pno"));
			isOk=svc.remove(pno);
			System.out.println(">>> 상품 삭제 "+(isOk>0 ? "성공":"실패"));
			destPage="/list.pd";
			break;
		}
		
		//if(uri.equals("/register.pd")) {} if로 처리할 때
		
		//웹의 목적지 주소로 연결해주는 객체
		//destPage로 이동시 응답(요청) 객체를 싣고 가야 함.
		RequestDispatcher rdp= req.getRequestDispatcher(destPage);
		rdp.forward(req, res);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get방식으로 오는 값을 체킹
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		service(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post 방식으로 오는 값을 체킹
		//doGet(request, response); //get으로 보냄
		service(request, response);
	}

}
