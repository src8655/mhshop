package com.cafe24.mhshop.repository;

import java.util.List;

import com.cafe24.mhshop.vo.CategoryVo;

public interface CategoryDao {

	List<CategoryVo> getList();				// 카테고리 리스트 조회
	Integer insert(CategoryVo categoryVo);	// 카테고리 추가
	Integer update(CategoryVo categoryVo);	// 카테고리 수정
	Integer delete(Long no);				// 카테고리 삭제
	Integer countByNo(Long categoryNo);		// 카테고리 번호별 개수

}
