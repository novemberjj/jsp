package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.BoardController;
import domain.BoardVO;
import domain.PagingVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;

public class BoarServiceImpl implements BoardService {
	 private static final Logger log = LoggerFactory.getLogger(BoarServiceImpl.class);
	 private BoardDAO bdao;
	 
	 public BoarServiceImpl() {
		 bdao=new BoardDAOImpl();
	 }

	@Override
	public int register(BoardVO bvo) {
		log.info(">>> register service 진입");
		// TODO Auto-generated method stub
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getList() {
		log.info(">>> list service 진입");
		// TODO Auto-generated method stub
		return bdao.selectList();
	}

	@Override
	public BoardVO getDetail(int bno) {
		log.info(">>> detail service 진입");
		// read_count update 요청 후 detail 값을 요청
		int isOk=bdao.upCount(bno);
		try {
			Thread.sleep(500); //0.5초 후에 selectOne()요청
		} catch (Exception e) {
			// TODO: handle exception
		}
		return (isOk>0) ? bdao.selectOne(bno) : null; //순서상의 차등을 둔다
	}

	@Override
	public int modify(BoardVO bvo) {
		log.info(">>> modify service 진입");
		// TODO Auto-generated method stub
		return bdao.update(bvo);
	}

	@Override
	public int remove(int bno) {
		log.info(">>> remove service 진입");
		// TODO Auto-generated method stub
		return bdao.delete(bno);
	}

	@Override
	public int upCount(int bno) {
		log.info(">>> upCount service 진입");
		// TODO Auto-generated method stub
		return bdao.upCount(bno);
	}

	@Override
	public int getTotal(PagingVO pgvo) {
		
		// TODO Auto-generated method stub
		return bdao.totCount(pgvo);
	}

	@Override
	public List<BoardVO> getPageList(PagingVO pgvo) {
		
		// TODO Auto-generated method stub
		return bdao.getPageList(pgvo);
	}

	@Override
	public String getFileName(int bno) {
		// delete fileName 호출
		return bdao.selectRemoveFile(bno);
	}



}