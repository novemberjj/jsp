package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import orm.DatabaseBuilder;

public class CommentDAOImpl implements CommentDAO {
	private static final Logger log = LoggerFactory.getLogger(CommentDAOImpl.class);
	private SqlSession sql;
	private final String NS="CommentMapper.";
	private int isOk;
	
	public CommentDAOImpl() {
		new DatabaseBuilder();
		sql=DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(CommentVO cvo) {
		log.info(">>> Comment post > DAO in");
		isOk=sql.insert(NS+"post",cvo);
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public List<CommentVO> getList(int bno) {
		log.info(">>> Comment List > DAO in");
		// TODO Auto-generated method stub
		return sql.selectList(NS+"list",bno);
	}

	@Override
	public int delete(int cno) {
		log.info(">>> Comment delete > DAO in");
		// TODO Auto-generated method stub
		isOk=sql.delete(NS+"del",cno);
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public int update(CommentVO cvo) {
		log.info(">>> Comment update > DAO in");
		isOk=sql.delete(NS+"up",cvo);
		if(isOk>0) sql.commit();
		return isOk;
	}

}
