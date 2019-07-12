package com.cafe24.mhshop.repository;

import java.util.List;

import com.cafe24.mhshop.vo.MemberVo;

public interface MemberDao {

	Integer countById(String id);						// 아이디 중복확인
	Integer insert(MemberVo memberVo);					// 입력
	MemberVo selectByIdAndPassword(MemberVo memberVo);	// 로그인
	List<MemberVo> selectList();						// 회원 리스트
	MemberVo selectOneById(String id);					// 아이디로 회원조회
	Integer delete(String id);							// 회원삭제
	MemberVo selectOneByNo(Long no);					// 회원번호로 찾기

}
