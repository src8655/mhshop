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
	
	
	
	// 가짜DB
	private List<CategoryVo> getCategoryTable() {
		List<CategoryVo> categoryTable = new ArrayList<CategoryVo>();
		categoryTable.add(new CategoryVo(1L, "test_category1"));
		categoryTable.add(new CategoryVo(2L, "test_category2"));
		
		return categoryTable;
	}
	
	
	

	// 카테고리 리스트 조회
	@Override
	public List<CategoryVo> getList() {
		
		// DAO에 요청
		
		
		// 가짜
		return getCategoryTable();
	}

	// 카테고리 추가
	@Override
	public CategoryVo add(CategoryVo categoryVo) {
		
		
		// DAO에 요청
		
		
		// 가짜 중복확인
		List<CategoryVo> categoryTable = getCategoryTable();
		for(CategoryVo vo : categoryTable) if(vo.getName().equals(categoryVo.getName())) return null;
		
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



	// 카테고리 No로 존재하는지 확인
	@Override
	public boolean isExistByNo(Long categoryNo) {
		
		// DAO에 요청
		
		
		// 가짜 존재 확인
		List<CategoryVo> categoryTable = getCategoryTable();
		for(CategoryVo vo : categoryTable) if(vo.getNo() == categoryNo) return true;
		
		return false;
	}


}
