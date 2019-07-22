package com.cafe24.mhmall.service;

import java.util.List;

import com.cafe24.mhmall.vo.CategoryVo;

public interface CategoryService {

	List<CategoryVo> getList();				// 카테고리 리스트 조회
	boolean add(CategoryVo categoryVo);		// 카테고리 추가
	boolean edit(CategoryVo categoryVo);	// 카테고리 수정
	boolean delete(Long no);				// 카테고리 삭제
	boolean isExistByNo(Long categoryNo);	// 카테고리 No로 존재하는지 확인

}
