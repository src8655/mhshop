package com.cafe24.mhshop.repository;

import java.util.List;

import com.cafe24.mhshop.vo.MemberVo;

public interface MemberDao {

	int countById(String id);				// 아이디 중복확인
	int insert(MemberVo memberVo);			// 입력
	MemberVo selectByIdAndPassword(String id, String password);		// 로그인
	List<MemberVo> selectList();			// 회원 리스트
	MemberVo selectOneById(String id);		// 아이디로 회원조회
	int delete(String id);					// 회원삭제

}
