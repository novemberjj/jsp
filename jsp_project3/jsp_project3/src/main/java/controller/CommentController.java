package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonParser;

import domain.CommentVO;
import service.CommentService;
import service.CommentServiceImpl;


@WebServlet("/cmt/*")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log= LoggerFactory.getLogger(CommentController.class);
	//목적지 주소로 보내는 destPage 쓰지 않음
	//비동기 통신으로 요청이 온 곳으로 돌려보냄
	private int isOk;
	private CommentService csv;
       
 
    public CommentController() {
       csv=new CommentServiceImpl();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//contentType은 사용하지 않음
		
		//REST API 방식
		String uri=request.getRequestURI();
		log.info(uri);
		String pathUri=uri.substring("/cmt/".length());
		String path=pathUri;
		String pathVar="";
		if(pathUri.contains("/")) {
			path=pathUri.substring(0,pathUri.lastIndexOf("/"));
			pathVar=pathUri.substring(pathUri.lastIndexOf("/")+1);
		}
		log.info(pathUri);
		log.info(path);
		log.info(pathVar);
		
		switch(path) {
		case "post":
			try {
				//js에서 보낸 데이터를 StringBuffer로 읽어들이는 작업
				StringBuffer sb = new StringBuffer();
				String line="";
				BufferedReader br= request.getReader(); //댓글 객체
				while((line=br.readLine())!=null){ //
					sb.append(line);
				}
				log.info(">>> sb : "+sb.toString());
				
				//객체로 생성
				//JSON <-> text 변경
				JSONParser parser= new JSONParser();
				JSONObject jsonObj=(JSONObject) parser.parse(sb.toString());
				
				//jsp -> js -> controller
				int bno=Integer.parseInt(jsonObj.get("bno").toString());
				String writer=jsonObj.get("writer").toString();
				String content=jsonObj.get("content").toString();
				CommentVO cvo= new CommentVO(bno, writer, content);
				isOk=csv.post(cvo);
				log.info(">>> post : "+(isOk>0 ? "success":"fail"));
				
				// isOk 결과 전송
				PrintWriter out=response.getWriter();
				out.print(isOk);
				
				
			} catch (Exception e) {
				// TODO: handle exception
				log.info(">>> comment post > error ");
				e.printStackTrace();
			}
			break;
			
		case "list":
			try {
				int bno=Integer.parseInt(pathVar);
				List<CommentVO> list = csv.getList(bno);
				log.info(">>> Comment List > DB ok");
				
				//json 형태로 변환해서 printWriter
				JSONObject[] jsonObjArr = new JSONObject[list.size()];
				JSONArray jsonObjList= new JSONArray();
				for(int i=0;i<list.size();i++) {
					jsonObjArr[i]=new JSONObject(); //key : value 형태로 저장되는 객체
					jsonObjArr[i].put("cno",list.get(i).getCno());
					jsonObjArr[i].put("bno",list.get(i).getBno());
					jsonObjArr[i].put("writer",list.get(i).getWriter());
					jsonObjArr[i].put("content",list.get(i).getContent());
					jsonObjArr[i].put("reg_date",list.get(i).getReg_date());
					
					jsonObjList.add(jsonObjArr[i]);
				}
				String jsonData= jsonObjList.toJSONString();
				
				PrintWriter out= response.getWriter();
				out.print(jsonData);
				
			} catch (Exception e) {
				log.info(">>> comment List > error");
				e.printStackTrace();
			}
			break;
			
		case "remove":
			try {
				int cno=Integer.parseInt(request.getParameter("cnoVal"));
				log.info(">>> cno >"+cno);
				isOk=csv.remove(cno);
				log.info(">>> remove > "+((isOk>0) ? "success":"fail"));
				PrintWriter out=response.getWriter();
				out.print(isOk);
			} catch (Exception e) {
				log.info(">>> comment remove > error");
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
			
		case "modify":
			try {
				StringBuffer sb = new StringBuffer();
				String line=null;
				BufferedReader br=request.getReader();
				while((line=br.readLine())!=null) {
					sb.append(line);
				}
				log.info(">>> sb : "+sb.toString());
				
				JSONParser parser = new JSONParser();
				JSONObject jsonObj= (JSONObject) parser.parse(sb.toString());
				
				int cno= Integer.parseInt(jsonObj.get("cno").toString());
				String content=jsonObj.get("content").toString();
				CommentVO cvo= new CommentVO(cno, content);
				isOk=csv.modify(cvo);
				log.info(">>> modify > "+((isOk>0) ? "success":"fail"));
				
				PrintWriter out= response.getWriter();
				out.print(isOk);
			} catch (Exception e) {
				log.info(">>> comment modify > error");
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
			
			
			
		}
		
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
		
	}

}
