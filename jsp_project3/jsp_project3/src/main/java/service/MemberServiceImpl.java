package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import repository.MemberDAO;
import repository.MemberDAOImpl;

public class MemberServiceImpl implements MemberService {
	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	private MemberDAO mdao;
	
	public MemberServiceImpl() {
		mdao=new MemberDAOImpl();
	}

	@Override
	public int register(MemberVO mvo) {
		// TODO Auto-generated method stub
		int isOk=mdao.insert(mvo);
		return isOk;
	}

	@Override
	public MemberVO login(MemberVO mvo) {
		log.info(">>> login service 진입");
		return mdao.selectOne(mvo);
	}

	@Override
	public int lastlogin(String id) {
		log.info(">>> logout service 진입");
		return mdao.last_login(id);
	}

	@Override
	public int modify(MemberVO mvo) {
		log.info(">>> modify service 진입");
		// TODO Auto-generated method stub
		return mdao.update(mvo);
	}

	@Override
	public int remove(String id) {
		log.info(">>> remove service 진입");
		// TODO Auto-generated method stub
		return mdao.delete(id);
	}

	@Override
	public List<MemberVO> selectList() {
		log.info(">>> selectList service 진입");
		// TODO Auto-generated method stub
		return mdao.selectList();
	}

}
