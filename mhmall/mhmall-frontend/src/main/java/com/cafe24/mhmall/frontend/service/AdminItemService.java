package com.cafe24.mhmall.frontend.service;

import org.springframework.web.multipart.MultipartFile;

import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.vo.ItemVo;

public interface AdminItemService {

	ResponseJSONResult<Boolean> add(String mockToken, ItemVo itemVo, MultipartFile thumbnailFile);		// 상품작성 요청

}
