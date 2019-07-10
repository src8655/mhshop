package com.cafe24.mhshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mhshop.repository.CategoryDao;
import com.cafe24.mhshop.service.CategoryService;
import com.cafe24.mhshop.vo.CategoryVo;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryDao categoryDao;

	// 카테고리 리스트 조회
	@Override
	public List<CategoryVo> get() {
		
		// DAO에 요청
		
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setNo(1L);
		categoryVo.setName("test_category");
		
		List<CategoryVo> list = new ArrayList<CategoryVo>();
		list.add(categoryVo);
		
		return list;
	}

	// 카테고리 추가
	@Override
	public CategoryVo add(CategoryVo categoryVo) {
		
		
		// DAO에 요청
		
		return categoryVo;
	}

	// 카테고리 수정
	@Override
	public boolean edit(CategoryVo categoryVo) {
		
		// DAO에 요청
		
		return true;
	}

	// 카테고리 삭제
	@Override
	public boolean delete(Long no) {
		
		// DAO에 요청
		
		return true;
	}


}
