package repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.MemberController;
import domain.MemberVO;
import orm.DatabaseBuilder;

public class MemberDAOimpl implements MemberDAO {
	private static final Logger log = LoggerFactory.getLogger(MemberDAOimpl.class);
	private SqlSession sql;
	//NameSpace = SQL이 mapper를 구분하기 위한 이름
	//NameSpace.SQL구문이름 
	private String NS = "MemberMapper.";
	
	public MemberDAOimpl() {
		new DatabaseBuilder();
		sql=DatabaseBuilder.getFactory().openSession();
	}
	
	@Override
	public int insert(MemberVO mvo) {
		//sql.insert(MemberMapper.reg, 객체); //실제로 인식하는 이름
		//transaction 처리가 자동 이루어짐
		int isOk=sql.insert(NS+"reg",mvo);
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public MemberVO selectOne(MemberVO mvo2) {
		log.info(">>> login DAO 진입");
		return sql.selectOne(NS+"login", mvo2);
	}

	@Override
	public int lastLogin(String id2) {
		log.info(">>> logout DAO 진입");
		int isOk= sql.update(NS+"logout",id2);
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int update(MemberVO mvo2) {
		log.info(">>> modify DAO 진입");
		int isOk=sql.update(NS+"mod",mvo2);
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int delete(String id) {
		log.info(">>> delete DAO 진입");
		int isOk=sql.delete(NS+"del",id);
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public List<MemberVO> selectList() {
		log.info(">>> select DAO 진입");
		List<MemberVO> list= new ArrayList<MemberVO>();
		list=sql.selectList(NS+"list");
		return list;
	}

}
