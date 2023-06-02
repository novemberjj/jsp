package repository;

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
	private String NS="BoardMapper.";
	
	public BoardDAOImpl() {
		new DatabaseBuilder();
		sql=DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(BoardVO bvo) {
		int isOk=sql.insert(NS+"reg",bvo);
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public List<BoardVO> selectList() {
		// TODO Auto-generated method stub
		return sql.selectList(NS+"list");
	}

	@Override
	public BoardVO selectOne(int bno) {
		// TODO Auto-generated method stub
		return sql.selectOne(NS+"detail", bno);
	}

	@Override
	public int upCount(int bno) {
		// TODO Auto-generated method stub
		int isOk=sql.update(NS+"cnt", bno);
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public int update(BoardVO bvo) {
		// TODO Auto-generated method stub
		int isOk=sql.update(NS+"edit", bvo);
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public int delete(int bno) {
		int isOk=sql.delete(NS+"del", bno);
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public int getTotal(PagingVO pgvo) {
		
		return sql.selectOne(NS+"tot",pgvo);
	}

	@Override
	public List<BoardVO> getPageList(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return sql.selectList(NS+"selectList",pgvo);
	}

	@Override
	public String getFileName(int bno) {
		// TODO Auto-generated method stub
		return sql.selectOne(NS+"fileName",bno);
	}

}
