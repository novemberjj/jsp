package service;

import java.util.List;

import domain.MemberVO;

public interface MemberService {

	int register(MemberVO mvo);

	MemberVO login(MemberVO mvo2);

	int lastLogin(String id2);

	int modify(MemberVO mvo2);

	int remove(String id);

	List<MemberVO> getList();

}