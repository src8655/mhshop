package com.cafe24.mhshop.service;

import java.util.List;

import com.cafe24.mhshop.vo.CategoryVo;

public interface CategoryService {

	List<CategoryVo> get();			// 카테고리 리스트 조회
	CategoryVo add(CategoryVo categoryVo);	// 카테고리 추가
	boolean edit(CategoryVo categoryVo);		// 카테고리 수정
	boolean delete(Long no);		// 카테고리 삭제

}
