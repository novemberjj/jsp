package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.MemberController;
import domain.MemberVO;
import repository.MemberDAO;
import repository.MemberDAOimpl;

public class MemberServiceImpl implements MemberService {
	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	private MemberDAO mdao;
	
	public MemberServiceImpl() {
		mdao= new MemberDAOimpl();
	}
	
	@Override
	public int register(MemberVO mvo) {
		log.info(">>> register service 진입");
		// TODO Auto-generated method stub
		return mdao.insert(mvo);
	}

	@Override
	public MemberVO login(MemberVO mvo2) {
		log.info(">>> login service 진입");
		// TODO Auto-generated method stub
		return mdao.selectOne(mvo2);
	}

	@Override
	public int lastLogin(String id2) {
		log.info(">>> logout service 진입");
		// TODO Auto-generated method stub
		return mdao.lastLogin(id2);
	}

	@Override
	public int modify(MemberVO mvo2) {
		log.info(">>> modify service 진입");
		// TODO Auto-generated method stub
		return mdao.update(mvo2);
	}

	@Override
	public int remove(String id) {
		log.info(">>> delete service 진입");
		// TODO Auto-generated method stub
		return mdao.delete(id);
	}

	@Override
	public List<MemberVO> getList() {
		log.info(">>> list service 진입");
		// TODO Auto-generated method stub
		return mdao.selectList();
	}

}
