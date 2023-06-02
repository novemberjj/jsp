package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import orm.DatabaseBuilder;

public class BoardDAOImpl implements BoardDAO {
	private static final Logger log= LoggerFactory.getLogger(BoardDAOImpl.class);
	private SqlSession sql;
	private String NS="BoardMapper.";
	
	public BoardDAOImpl() {
		new DatabaseBuilder();
		sql=DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(BoardVO bvo) {
		log.info(">>> board insert DAO 진입");
		int isOk=sql.insert(NS+"insert",bvo);
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public List<BoardVO> getList() {
		log.info(">>> board list DAO 진입");
		// TODO Auto-generated method stub
		return sql.selectList(NS+"list");
	}

	@Override
	public BoardVO selectOne(int bno) {
		log.info(">>> board list DAO 진입");
		// TODO Auto-generated method stub
		return sql.selectOne(NS+"detail",bno);
	}

	@Override
	public int upCount(int bno) {
		log.info(">>> board upCount DAO 진입");
		int isOk=sql.update(NS+"up",bno);
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public int update(BoardVO bvo) {
		log.info(">>> board update DAO 진입");
		int isOk=sql.update(NS+"mod",bvo);
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public String getImg(int bno) {
		// TODO Auto-generated method stub
		return sql.selectOne(NS+"img",bno);
	}

	@Override
	public int delete(int bno) {
		log.info(">>> board delete DAO 진입");
		int isOk=sql.update(NS+"del",bno);
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public int getTotal(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return sql.selectOne(NS+"tot",pgvo);
	}

	@Override
	public List<BoardVO> getPageList(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return sql.selectList(NS+"page",pgvo);
	}

}
