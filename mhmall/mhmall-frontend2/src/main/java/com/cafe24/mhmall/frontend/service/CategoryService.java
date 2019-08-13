package com.cafe24.mhmall.frontend.service;

import java.util.ArrayList;

import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.vo.CategoryVo;

public interface CategoryService {

	ResponseJSONResult<Boolean> add(String mockToken, String categoryName);				// 카테고리 추가요청
	ResponseJSONResult<ListCategoryVo> getList();										// 카테고리 리스트 요청
	ResponseJSONResult<Boolean> delete(String mockToken, Long no);						// 카테고리 삭제요청
	ResponseJSONResult<Boolean> edit(String mockToken, Long no, String categoryName);	// 카테고리 수정요청

	public static class ListCategoryVo extends ArrayList<CategoryVo> {}
}
