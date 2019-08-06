package com.cafe24.mhmall.frontend.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.vo.ItemVo;

public interface AdminItemService {

	ResponseJSONResult<Boolean> add(String mockToken, ItemVo itemVo, MultipartFile thumbnailFile);		// 상품작성 요청
	ResponseJSONResult<ListItemVo> getList(String mockToken, Optional<Long> categoryNo);				// 상품 리스트 요청

	public static class ListItemVo extends ArrayList<ItemVo> {}

}
