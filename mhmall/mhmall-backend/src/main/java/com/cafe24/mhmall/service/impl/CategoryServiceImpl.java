package com.cafe24.mhmall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.cafe24.mhmall.repository.CategoryDao;
import com.cafe24.mhmall.service.CategoryService;
import com.cafe24.mhmall.vo.CategoryVo;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryDao categoryDao;

	// 카테고리 리스트 조회
	@Override
	public List<CategoryVo> getList() {

		return categoryDao.getList();
	}

	// 카테고리 추가
	@Override
	public boolean add(CategoryVo categoryVo) {
		Integer result = categoryDao.insert(categoryVo);
		return result == 1;
	}

	// 카테고리 수정
	@Override
	public boolean edit(CategoryVo categoryVo) {
		Integer result = categoryDao.update(categoryVo);
		return result == 1;
	}

	// 카테고리 삭제
	@Override
	public boolean delete(Long no) {
		Integer result = categoryDao.delete(no);
		return result == 1;
	}



	// 카테고리 No로 존재하는지 확인
	@Override
	public boolean isExistByNo(Long categoryNo) {
		Integer count = categoryDao.countByNo(categoryNo);
		return count != 0;
	}


}
