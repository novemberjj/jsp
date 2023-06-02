package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import orm.DatabaseBuilder;

public class MemberDAOImpl implements MemberDAO {
	private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);
	private SqlSession sql;
	private String NS="MemberMapper.";
	
	public MemberDAOImpl() {
		new DatabaseBuilder();
		sql=DatabaseBuilder.getFactory().openSession();
	}
	
	@Override
	public int insert(MemberVO mvo) {
		int isOk=sql.insert(NS+"register",mvo);
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public MemberVO selectOne(MemberVO mvo) {
		log.info(">>> login DAO 진입");
		return sql.selectOne(NS+"login", mvo);
	}

	@Override
	public int last_login(String id) {
		log.info(">>> last_login DAO 진입");
		int isOk=sql.update(NS+"last",id);
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public int update(MemberVO mvo) {
		log.info(">>> update DAO 진입");
		int isOk=sql.update(NS+"edit",mvo);
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public int delete(String id) {
		log.info(">>> delete DAO 진입");
		int isOk=sql.delete(NS+"del",id);
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public List<MemberVO> selectList() {
		
		// TODO Auto-generated method stub
		return sql.selectList(NS+"list");
	}

}