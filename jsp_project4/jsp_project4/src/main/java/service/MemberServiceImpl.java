package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import repository.MemberDAO;
import repository.MemberDAOImpl;

public class MemberServiceImpl implements MemberService {
	private static final Logger log= LoggerFactory.getLogger(MemberServiceImpl.class);
	private MemberDAO mdao;
	
	public MemberServiceImpl() {
		mdao=new MemberDAOImpl();
	}

	@Override
	public int register(MemberVO mvo) {
		log.info(">>> register service 진입");
		return mdao.insert(mvo);
	}

	@Override
	public MemberVO login(MemberVO mvo) {
		log.info(">>> login service 진입");
		// TODO Auto-generated method stub
		return mdao.selectOne(mvo);
	}

	@Override
	public int lastLogin(String id) {
		log.info(">>> last login service 진입");
		// TODO Auto-generated method stub
		return mdao.lastLogin(id);
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
	public List<MemberVO> getList() {
		log.info(">>> remove service 진입");
		// TODO Auto-generated method stub
		return mdao.getList();
	}

}