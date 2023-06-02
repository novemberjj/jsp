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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.MemberVO;
import domain.PagingVO;
import handler.FileHandler;
import handler.PagingHandler;
import net.coobird.thumbnailator.Thumbnails;
import service.BoardService;
import service.BoardServiceImpl;


@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger log= LoggerFactory.getLogger(BoardController.class);
    private RequestDispatcher rdp;
    private int isOk;
    private BoardService bsv;
    private String destPage;
    //파일 경로를 저장할 변수
    private String savePath;
    private final String UTF8="utf-8"; //인코딩 설정시 사용
    
    
    
  
    public BoardController() {
    	bsv= new BoardServiceImpl(); 
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String uri=request.getRequestURI();
		log.info(">>> uri > "+uri);
		String path=uri.substring(uri.lastIndexOf("/")+1);
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
				File fileDir= new File(savePath);//파일 저장경로로 파일 생성
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				//파일의 저장위치를 담고 있는 객체를 저장
				fileItemFactory.setRepository(fileDir);
				//파일 저장을 위한 임시 메모리 용량설정 : byte 단위
				fileItemFactory.setSizeThreshold(2*1024*1024); //2MB
				
				BoardVO bvo= new BoardVO();
				//mulitpart/form-data 형식으로 넘어온 값은 getparameter로 못 받음
				//servletFileUpload request 객체를 다루기 쉽게 변환
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				
				List<FileItem> itemList= fileUpload.parseRequest(request);
				for(FileItem item: itemList) {
					switch(item.getFieldName()) { //key
					case "title":
						bvo.setTitle(item.getString(UTF8)); //인코딩 형식을 담아서 변환
						break;
					case "writer":
						bvo.setWriter(item.getString(UTF8)); //인코딩 형식을 담아서 변환
						break;
					case "content":
						bvo.setContent(item.getString(UTF8)); //인코딩 형식을 담아서 변환
						break;
					case "img_file":
						//이미지가 있는지 없는지 체크
						if(item.getSize()>0) { //데이터의 크기를 이용하여 유무 결정
							//경로를 포함한 파일이름 .../dog.jpg => dog.jpg만 분리
							String fileName=item.getName().substring(item.getName().lastIndexOf("/")+1);
							fileName=System.currentTimeMillis()+"_"+fileName;
							log.info(">>> fileName > "+fileName);
							File uploadFilePath= new File(fileDir+File.separator+fileName); 
							log.info(">>> 실제 파일 경로 : "+uploadFilePath);
							
							//저장
							try {
								item.write(uploadFilePath); //자바 객체를 디스크에 쓰기
								bvo.setImg_file(fileName);
								
								//썸네일 작업 : 리스트 페이지에서 트래픽 과다사용 방지
								Thumbnails.of(uploadFilePath).size(75, 75).toFile(new File(fileDir+File.separator+"th_"+fileName));
								isOk=bsv.register(bvo);
								log.info(">>> REGISTER > "+(isOk>0 ? "success":"fail"));
								
							}catch (Exception e) {
								log.info(">>> file writer on disk fail");
								e.printStackTrace();
							}
							
						}
						break;
					}
				}
				
//				BoardVO bvo= new BoardVO(request.getParameter("title"), request.getParameter("writer"), request.getParameter("content"));
//				isOk=bsv.register(bvo);
//				log.info(">>> REGISTER > "+(isOk>0 ? "success":"fail"));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			destPage="page";
			break;
			
		case "list":
			try {
				List<BoardVO> list= new ArrayList<>();
				list=bsv.getList();
				request.setAttribute("list", list);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			destPage="/board/list.jsp";
			break;
			
		case "page":
			try {
				log.info(">>> page controller");
				int pageNo=1;
				int qty=10;
				String type="";
				String keyword="";
				String id=(String)request.getParameter("id");
				log.info(">> id : " + id);
				
				if(request.getParameter("type")!=null) {
						type=request.getParameter("type");
						keyword=request.getParameter("keyword");
					log.info(">>> type : "+type+">>> keyword : "+keyword);
				}
				if(request.getParameter("pageNo")!=null) {
					pageNo=Integer.parseInt(request.getParameter("pageNo"));
					qty=Integer.parseInt(request.getParameter("qty"));
				}
				
				PagingVO pgvo= new PagingVO(pageNo, qty);
				pgvo.setType(type);
				pgvo.setKeyword(keyword);
				pgvo.setId(id);
				log.info(">>> pgvo : "+pgvo);
				
				//전체 페이지 개수
				int totCount=bsv.getTotal(pgvo);
				log.info(">>> totCount : "+totCount);
				
				//limit을 이용한 selectList 호출
				//한 페이지에 나와야 하는 리스트
				List<BoardVO> list = bsv.getPageList(pgvo);
				log.info(">>> list : "+list);
				PagingHandler ph= new PagingHandler(pgvo, totCount);
				
				log.info(">>"+ph.getStartPage()+">>"+ph.getEndPage());
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
				int bno= Integer.parseInt(request.getParameter("bno"));
				isOk=bsv.upCount(bno);
				log.info(">>> UPCOUNT > "+(isOk>0 ? "success":"fail"));
				BoardVO bvo=bsv.selectOne(bno);
				request.setAttribute("bvo", bvo);
		
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			destPage="/board/detail.jsp";
			break;
			
		case "modify":
			try {
				int bno= Integer.parseInt(request.getParameter("bno"));
				BoardVO bvo=bsv.selectOne(bno);
				request.setAttribute("bvo", bvo);
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
				
				DiskFileItemFactory fileItemFactory= new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir);
				fileItemFactory.setSizeThreshold(2*1024*1024);
				
				BoardVO bvo= new BoardVO();
				
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				log.info(">>> update 준비");
				
				List<FileItem> itemList = fileUpload.parseRequest(request);
				
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
								//파일 핸들러 호출(기존 파일을 삭제)
								FileHandler fileHandler= new FileHandler();
								isOk=fileHandler.deleteFile(old_file, savePath);
							}
							//이름 설정 .../dog.jpg
							String fileName=item.getName().substring(item.getName().lastIndexOf(File.separator)+1);
							log.info(">>> new_fileName : "+fileName);
							//실제 저장이름
							fileName=System.currentTimeMillis()+"_"+fileName;
							File uploadFilePath = new File(fileDir+File.separator+fileName);
							
							//저장
							try {
								item.write(uploadFilePath);
								bvo.setImg_file(fileName);
								log.info(">>> bvo.img_file : "+bvo.getImg_file());
								//썸네일작업
								Thumbnails.of(uploadFilePath).size(75, 75).toFile(new File(fileDir+File.separator+"th_"+fileName));
							} catch (Exception e) {
								log.info(">>> file update on disk fail");
								e.printStackTrace();
							}
						}else { //새로운 파일을 넣지 않았다면
							bvo.setImg_file(old_file);
						}
						break;
					}
				}
//				BoardVO bvo= new BoardVO(Integer.parseInt(request.getParameter("bno")), request.getParameter("title"), request.getParameter("content"));
				isOk=bsv.modify(bvo);
				log.info(">>> MODIFY > "+(isOk>0 ? "success":"fail"));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			destPage="page";
			break;
			
		case "delete":
			try {
				int bno= Integer.parseInt(request.getParameter("bno"));
				String fileName=bsv.getFileName(bno);
				savePath=getServletContext().getRealPath("/_fileUpload");
				FileHandler fh= new FileHandler();
				isOk=fh.deleteFile(fileName, savePath);
				isOk=bsv.delete(bno);
				log.info(">>> DELETE > "+(isOk>0 ? "success":"fail"));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			destPage="list";
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