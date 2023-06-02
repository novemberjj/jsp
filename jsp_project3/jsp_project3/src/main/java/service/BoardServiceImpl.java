package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;

public class BoardServiceImpl implements BoardService {
	private static final Logger log= LoggerFactory.getLogger(BoardServiceImpl.class);
	private BoardDAO bdao;
	
	public BoardServiceImpl() {
		bdao= new BoardDAOImpl();
	}

	@Override
	public int register(BoardVO bvo) {
		log.info(">>> register service 진입");
		// TODO Auto-generated method stub
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getList() {
		log.info(">>> getList service 진입");
		// TODO Auto-generated method stub
		return bdao.selectList();
	}

	@Override
	public BoardVO selectOne(int bno) {
		log.info(">>> selectOne service 진입");
		return  bdao.selectOne(bno);
	}

	@Override
	public int upCount(int bno) {
		log.info(">>> upCount service 진입");
		return bdao.upCount(bno);
	}

	@Override
	public int modify(BoardVO bvo) {
		log.info(">>> modify service 진입");
		// TODO Auto-generated method stub
		return bdao.update(bvo);
	}

	@Override
	public int delete(int bno) {
		log.info(">>> delete service 진입");
		// TODO Auto-generated method stub
		return bdao.delete(bno);
	}

	@Override
	public int getTotal(PagingVO pgvo) {
		log.info(">>> paging-getTotal service 진입");
		// TODO Auto-generated method stub
		return bdao.getTotal(pgvo);
	}

	@Override
	public List<BoardVO> getPageList(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.getPageList(pgvo);
	}

	@Override
	public String getFileName(int bno) {
		// TODO Auto-generated method stub
		return bdao.getFileName(bno);
	}

}
