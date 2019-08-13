package com.cafe24.mhmall.frontend.service;

import java.util.ArrayList;
import java.util.Optional;

import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.vo.OptionVo;

public interface OptionService {
	public static class ListOptionVo extends ArrayList<OptionVo> {}

	ResponseJSONResult<Boolean> add(String authorization, OptionVo optionVo);				// 옵션 추가
	ResponseJSONResult<ListOptionVo> getList(Long itemNo, Optional<Long> optionDetailNo1);	// 옵션 리스트(1차 없으면 1차 있으면 2차)
	ResponseJSONResult<Boolean> delete(String authorization, Long no);						// 옵션 삭제 요청

}
