package controller;

import java.io.File;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.ognl.OgnlRuntime.ArgsCompatbilityReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.cj.Session;

import domain.BoardVO;
import domain.PagingVO;
import handler.FileHandler;
import handler.PagingHandler;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import service.BoarServiceImpl;
import service.BoardService;

@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(BoardController.class);
    private RequestDispatcher rdp; //웹의 목적지 주소로 객체를 전달해주는 객체
    private String destPage; //목적지 주소
    private int isOk; //db의 insert, update, delte의 결과를 받는 변수
    private BoardService bsv; //interface
    int cnt;
    //파일 경로를 저장할 변수
    private String savePath;
    private final String UTF8="utf-8"; //인코딩 설정시 사용
    
    
    
    public BoardController() {
       bsv=new BoarServiceImpl(); //interface 구현체
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 캐릭터 인코딩 설정, 컨텐츠 타입 설정
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html charset=UTF-8");
		
		String uri= request.getRequestURI();
		log.info(">>> uri > "+uri);
		String path= uri.substring(uri.lastIndexOf("/")+1);
		log.info(">>> path > "+path);
		
		switch(path) {
		case "register":
			destPage="/board/register.jsp";
			break;
			
		case "insert":
			try {
				//파일을 업로드할 물리적인 경로를 설정
				savePath=getServletContext().getRealPath("/_fileUpload");
				log.info(">>> 파일 저장 경로 : "+savePath);
				File fileDir= new File(savePath);
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				//파일의 저장위치를 담고 있는 객체를 저장
				fileItemFactory.setRepository(fileDir); 
				//파일 저장을 위한 임시 메모리 용량설정 : byte단위
				fileItemFactory.setSizeThreshold(2*1024*1024); //2MB
				
				BoardVO bvo= new BoardVO();
				//multipart/form-data 형식으로 넘어온 값은 getparameter로 못 받음 
				//servletFileUpload request 객체를 다루기 쉽게 변환
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory); 
				
				List<FileItem> itemList= fileUpload.parseRequest(request);
				for(FileItem item : itemList) {
					switch(item.getFieldName()) { //key의 값
					case "title":
						bvo.setTitle(item.getString(UTF8)); //인코딩 형식을 담아서 변환
						break;
						
					case "writer":						
						bvo.setWriter(item.getString(UTF8)); 
						break;
						
					case "content":
						bvo.setContent(item.getString(UTF8));
						break;
						
					case "image_file":	
						//이미지가 있는지 없는지 체크
						if(item.getSize() > 0) { //데이터의 크기를 이용하여 유무 결정
							//경로를 포함한 파일이름 ~~/dog.jpg => dog.jpg만 분리
							String fileName=item.getName().substring(item.getName().lastIndexOf("/")+1);
							fileName= System.currentTimeMillis()+"_"+fileName;
							log.info(">>> fileName : "+fileName);
							File uploadFilePath= new File(fileDir+File.separator+fileName);
							log.info(">>> 실제 파일 경로 : "+uploadFilePath);
							
							//저장
							try {
								item.write(uploadFilePath); //자바 객체를 디스크에 쓰기
								bvo.setImg_file(fileName);
								
								//썸네일 작업 : 리스트 페이지에서 트래픽 과다사용 방지
								Thumbnails.of(uploadFilePath).size(75, 75).toFile(new File(fileDir+File.separator+"th_"+fileName));
								isOk=bsv.register(bvo);
								log.info(">>> register >"+(isOk>0 ? "success":"fail"));
								
							} catch (Exception e) {
								log.info(">>> file writer on disk fail");
								e.printStackTrace();
							}
							
						}
						break;
					}
				}
				
//				log.info(">>> register page");
//				BoardVO bvo= new BoardVO(request.getParameter("title"), request.getParameter("writer"), request.getParameter("content"));
//				log.info(bvo.getContent());
//				//insert, update, delete => 리턴타입 isOk
//				//select => BoardVO의 객체값 (여러개 리턴이면 List)
//				isOk=bsv.register(bvo);
//				log.info(">>> register >"+(isOk>0 ? "success":"fail"));
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			destPage="page"; //destPage는 밑에
			break;
			
		case "list":
			try {
				log.info(">>> list page");
				List<BoardVO> list= new ArrayList<BoardVO>();
				list=bsv.getList();
				log.info(">>> list 성공");
				request.setAttribute("list", list);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			destPage="/board/list.jsp";
			
			break;
			
		case "page":
			try {
				int pageNo=1;
				int qty=10;
//				String type=request.getParameter("type");
//				String keyword=request.getParameter("keyword");
				String type="";
				String keyword="";
				if(request.getParameter("type")!=null) {
					type=request.getParameter("type");
					keyword=request.getParameter("keyword");
					log.info(">>> type :"+type+">>> keyword : "+keyword);
				}
				if(request.getParameter("pageNo")!=null) {
					pageNo=Integer.parseInt(request.getParameter("pageNo"));
					qty=Integer.parseInt(request.getParameter("qty"));
				}
				log.info(">>> page controller");
				PagingVO pgvo = new PagingVO(pageNo,qty); //안넣어도 된다
				pgvo.setType(type);
				pgvo.setKeyword(keyword);
				log.info(">>> pgvo : "+pgvo);
				//전체 페이지 개수
				int totCount=bsv.getTotal(pgvo);
				log.info(">>> totCount : "+totCount);
				//limit을 이용한 select List 호출
				//한 페이지에 나와야 하는 리스트
				List<BoardVO> list= bsv.getPageList(pgvo);
				log.info(">>> list : "+list);
				PagingHandler ph= new PagingHandler(pgvo, totCount);
				log.info(">>"+ph.getStartPage() +">>>>"+ ph.getEndPage());
				request.setAttribute("pgh", ph);
				request.setAttribute("list", list);
				log.info("pageList 성공");
				destPage="/board/list.jsp";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
			
		case "detail":
			try {
				log.info(">>> detail page");
				int bno=Integer.parseInt(request.getParameter("bno"));
				//isOk=bsv.upCount(bno);
				//log.info(">>> lead_count >"+(isOk>0 ? "success":"fail"));
				BoardVO bvo2=bsv.getDetail(bno);
				request.setAttribute("bvo", bvo2);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			destPage="/board/detail.jsp";
			break;
			
		case "modify":
			try {
				log.info(">>> modify page 접근");
				int bno=Integer.parseInt(request.getParameter("bno"));
				BoardVO bvo2=bsv.getDetail(bno);
				request.setAttribute("bvo", bvo2);
				
				log.info(">>> modify page 성공");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			destPage="/board/modify.jsp";
			break;
			
		case "edit":
			try {
				savePath=getServletContext().getRealPath("/_fileUpload");
				File fileDir= new File(savePath);
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir);
				fileItemFactory.setSizeThreshold(2*1024*1024);
				
				BoardVO bvo= new BoardVO();
				
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				log.info(">>> update 준비");
				
				List<FileItem> itemList=fileUpload.parseRequest(request);
				
				String old_file=null;
				for(FileItem item : itemList) {
					switch(item.getFieldName()) {
					case "bno":
						bvo.setBno(Integer.parseInt(item.getString(UTF8)));
						break;
						
					case "title":
						bvo.setTitle(item.getString(UTF8));
						break;
						
					case "content":
						bvo.setContent(item.getString(UTF8));
						break;
						
					case "img_file":
						//기존 파일의 이름을 가져와서 담기
						old_file=item.getString(UTF8);
						break;
						
					case "new_file":
						if(item.getSize()>0) { //새로운 파일이 등록됨
							if(old_file!=null) {
								//파일 핸들러 호출 (기존 파일을 삭제)
								FileHandler fileHandler = new FileHandler();
								isOk=fileHandler.deleteFile(old_file, savePath);								
							}
							//이름 설정 .../dog.jpg
							String fileName=item.getName().substring(item.getName().lastIndexOf(File.separator)+1);
							log.info(">>> new_fileName : "+fileName);
							//실제 저장이름
							fileName=System.currentTimeMillis()+"_"+fileName;
							File uploadFilePath = new File(fileDir+File.separator+fileName);
							try {
								item.write(uploadFilePath);
								bvo.setImg_file(fileName);
								log.info(">>> bvo.image_file"+bvo.getImg_file());
								//썸네일 작업
								Thumbnails.of(uploadFilePath).size(75, 75).toFile(new File(fileDir+File.separator+"th_"+fileName));
							} catch (Exception e2) {
								log.info(">>> file update on disk fail");
								e2.printStackTrace();
								// TODO: handle exception
							}
						}else { //새로운 파일을 넣지 않았다면
							//기존 파일을 다시 bvo객체에 저장
							bvo.setImg_file(old_file);
						}
						break;
					}
				}
				
//				log.info(">>> edit 시작");
//				log.info(request.getParameter("bno"));
//				BoardVO bvo=new BoardVO(Integer.parseInt(request.getParameter("bno")), request.getParameter("title"), request.getParameter("content"));
				isOk=bsv.modify(bvo);
				log.info(">>> modify >"+(isOk>0 ? "success":"fail"));
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			//destPage="/board/list.jsp"; //빈 페이지만 띄워줌
			destPage="page"; //컨트롤러 list에서 db검색 후 객체 가지고 list.jsp로 이동
			
			break;
			
		case "remove":
			try {
				log.info(">>> remove 시작");
				int bno=Integer.parseInt(request.getParameter("bno"));
				//파일 삭제 추가
				//image_file 호출, savePath
				String imageFileName=bsv.getFileName(bno); //삭제할 파일이름 호출
				log.info(">>> removeFileName : "+imageFileName);
				savePath=getServletContext().getRealPath("_fileUpload");
				FileHandler fileHandler= new FileHandler();
				isOk=fileHandler.deleteFile(imageFileName, savePath);
				log.info(">>> removeFile > "+(isOk>0 ? "success":"fail"));
//				if(request.getParameter("img_file")!=null) {
//					String fileName=request.getParameter("img_file");
//					FileHandler fileHandler = new FileHandler();
//					savePath=getServletContext().getRealPath("/_fileUpload");
//					fileHandler.deleteFile(fileName, savePath); 
//				}
				isOk=bsv.remove(bno);
				log.info(">>> remove > "+(isOk>0 ? "success":"fail"));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			destPage="page";
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
