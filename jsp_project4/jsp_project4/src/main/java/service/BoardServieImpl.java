package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;

public class BoardServieImpl implements BoardService {
	private static final Logger log= LoggerFactory.getLogger(BoardServieImpl.class);
	private BoardDAO bdao;
	
	public BoardServieImpl() {
		bdao= new BoardDAOImpl();
	}

	@Override
	public int register(BoardVO bvo) {
		log.info(">>> board register service");
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getList() {
		log.info(">>> board getList service");
		// TODO Auto-generated method stub
		return bdao.getList();
	}

	@Override
	public BoardVO detail(int bno) {
		log.info(">>> board detail service");
		int isOk=bdao.upCount(bno);
		try {
			Thread.sleep(500);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return (isOk>0) ? bdao.selectOne(bno) : null;
	}

	@Override
	public int modify(BoardVO bvo) {
		log.info(">>> board modify service");
		// TODO Auto-generated method stub
		return bdao.update(bvo);
	}

	@Override
	public String getImg(int bno) {
		// TODO Auto-generated method stub
		return bdao.getImg(bno);
	}

	@Override
	public int delete(int bno) {
		// TODO Auto-generated method stub
		return bdao.delete(bno);
	}

	@Override
	public int getTotal(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.getTotal(pgvo);
	}

	@Override
	public List<BoardVO> getPageList(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.getPageList(pgvo);
	}

}
