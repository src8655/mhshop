package com.cafe24.mhshop.repository;

import java.util.List;

import com.cafe24.mhshop.vo.CategoryVo;

public interface CategoryDao {

	List<CategoryVo> getList();				// 카테고리 리스트 조회

}
