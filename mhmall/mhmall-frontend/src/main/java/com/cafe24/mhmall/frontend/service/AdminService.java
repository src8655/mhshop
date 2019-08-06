package com.cafe24.mhmall.frontend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.vo.MemberVo;

public interface AdminService {
	
	ResponseJSONResult<ListMemberVo> getMemberList(String authorization, Optional<String> search);		// 관리자 회원목록
	
	
	
	public static class ListMemberVo extends ArrayList<MemberVo> {}
}
