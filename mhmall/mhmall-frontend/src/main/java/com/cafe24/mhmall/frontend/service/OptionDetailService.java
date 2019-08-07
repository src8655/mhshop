package com.cafe24.mhmall.frontend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.vo.OptionDetailVo;


public interface OptionDetailService {
	public static class ListOptionDetailVo extends ArrayList<OptionDetailVo> {}

	public ResponseJSONResult<ListOptionDetailVo> getOptionDetail(String authorization, Long itemNo, Integer level);		// 상세옵션 리스트
	public ResponseJSONResult<Boolean> add(String authorization, OptionDetailVo optionDetailVo);							// 상세옵션 추가
	public ResponseJSONResult<Boolean> delete(String authorization, Long no);												// 상세옵션 삭제 요청
}
