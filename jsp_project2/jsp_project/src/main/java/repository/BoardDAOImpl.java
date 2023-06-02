package repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import orm.DatabaseBuilder;

public class BoardDAOImpl implements BoardDAO {
	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
	private SqlSession sql;
	private String NS = "BoardMapper.";

	public BoardDAOImpl() {
		new DatabaseBuilder();
		sql=DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(BoardVO bvo) {
		log.info(">>> insert DAO 진입");
		int isOk=sql.insert(NS+"insert", bvo);
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public List<BoardVO> selectList() {
		log.info(">>> select DAO 진입");
		List<BoardVO> list= new ArrayList<BoardVO>();
		list=sql.selectList(NS+"list");
		return list;
	}

	@Override
	public BoardVO selectOne(int bno) {
		log.info(">>> selectOne DAO 진입");
		BoardVO bvo= sql.selectOne(NS+"detail", bno);
		return bvo;
	}

	@Override
	public int update(BoardVO bvo) {
		log.info(">>> update DAO 진입");
		int isOk=sql.update(NS+"update", bvo);
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int delete(int bno) {
		log.info(">>> delete DAO 진입");
		int isOk=sql.delete(NS+"del", bno);
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int upCount(int bno) {
		log.info(">>> upCount DAO 진입");
		int isOk=sql.update(NS+"cnt",bno);
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public int totCount(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return sql.selectOne(NS+"tot",pgvo);
	}

	@Override
	public List<BoardVO> getPageList(PagingVO pgvo) {
		// TODO Auto-generated method stub
		//return sql.selectList(NS+"pList",pgvo);
		return sql.selectList(NS+"selectList",pgvo);
	}

	@Override
	public String selectRemoveFile(int bno) {
		// TODO Auto-generated method stub
		return sql.selectOne(NS+"removeFile",bno);
	}

	

}