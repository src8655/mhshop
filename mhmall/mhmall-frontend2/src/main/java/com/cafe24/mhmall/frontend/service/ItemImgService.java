package com.cafe24.mhmall.frontend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.vo.ItemImgVo;

public interface ItemImgService {
	public static class ListItemImgVo extends ArrayList<ItemImgVo> {}

	ResponseJSONResult<Boolean> add(String mockToken, Long itemNo, MultipartFile itemImgFile);		// 상품이미지 추가 요청
	ResponseJSONResult<ListItemImgVo> getList(Long itemNo);											// 상품이미지리스트 요청
	ResponseJSONResult<Boolean> delete(String mockToken, Long no);									// 상품이미지 삭제 요청

}
