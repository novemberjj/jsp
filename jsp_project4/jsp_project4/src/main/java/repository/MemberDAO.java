package repository;

import java.util.List;

import domain.MemberVO;

public interface MemberDAO {

	int insert(MemberVO mvo);

	MemberVO selectOne(MemberVO mvo);

	int lastLogin(String id);

	int update(MemberVO mvo);

	int delete(String id);

	List<MemberVO> getList();

}
