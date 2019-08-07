package com.cafe24.mhmall.frontend.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.vo.ItemVo;

public interface ItemService {
	public static class ListItemVo extends ArrayList<ItemVo> {}
	
	ResponseJSONResult<Boolean> add(String mockToken, ItemVo itemVo, MultipartFile thumbnailFile);		// 상품작성 요청
	ResponseJSONResult<ListItemVo> getList(String mockToken, Optional<Long> categoryNo);				// 상품 리스트 요청
	ResponseJSONResult<ItemVo> get(Long no);															// 상품정보 요청
	ResponseJSONResult<Boolean> edit(String mockToken, ItemVo itemVo, MultipartFile thumbnailFile);		// 상품수정 요청
	ResponseJSONResult<Boolean> delete(String mockToken, Long no);										// 상품삭제 요청
	ResponseJSONResult<Boolean> display(String mockToken, Long no, String display);						// 상품 진열여부 수정 요청
	ResponseJSONResult<ListItemVo> getNewList(Long CategoryNo, Integer cnt);							// 최근상품리스트


}
