package com.cafe24.mhmall.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mhmall.repository.ItemDao;
import com.cafe24.mhmall.service.ItemService;
import com.cafe24.mhmall.vo.ItemVo;
import com.cafe24.mhmall.vo.ItemsVo;
import com.cafe24.mhmall.vo.MainImgVo;
import com.cafe24.mhmall.vo.Paging;

@Service
public class ItemServiceImpl implements ItemService {
	public static final int BOARD_CNT = 6;	//한번에 보여질 게시글
	public static final int PAGE_CNT = 5;	//페이지 버튼 개수
	
	@Autowired
	ItemDao itemDao;
	
	
	// 카테고리번호에 해당하는 아이템이 있는지?
	@Override
	public boolean hasItemByCategory(Long categoryNo) {
		Integer count = itemDao.countByCategory(categoryNo);
		return count != 0;
	}

	
	// 상품 리스트
	@Override
	public List<ItemVo> getList(ItemVo itemVo) {
		if(itemVo.getCategoryNo() != null && itemVo.getCategoryNo() == -1) itemVo.setCategoryNo(null);
		return itemDao.selectList(itemVo);
	}


	// 상품 등록
	@Override
	public boolean add(ItemVo itemVo) {
		itemVo.setDisplay("FALSE");
		Integer result = itemDao.insert(itemVo);
		return result == 1;
	}


	// 상품 삭제
	@Override
	public boolean delete(Long no) {
		Integer result = itemDao.delete(no);
		return result == 1;
	}


	// 상품번호로 상품정보
	@Override
	public ItemVo getByNo(Long no) {
		return itemDao.selectOne(no);
	}


	// 상품 수정
	@Override
	public boolean edit(ItemVo itemVo) {
		Integer result = itemDao.update(itemVo);
		return result == 1;
	}


	// 상품진열여부 수정
	@Override
	public boolean editDisplay(Long no, String display) {
		ItemVo itemVo = new ItemVo();
		itemVo.setNo(no);
		itemVo.setDisplay(display);
		Integer result = itemDao.updateDisplay(itemVo);
		return result == 1;
	}


	// 사용자 상품리스트
	@Override
	public ItemsVo getListU(Optional<Long> categoryNo, Optional<Integer> pages, Optional<String> kwd) {
		Long categoryNoPath = null;
		Integer pagesPath = 1;
		String kwdPath = null;
		if(categoryNo.isPresent()) categoryNoPath = categoryNo.get();
		if(pages.isPresent()) pagesPath = pages.get();
		if(kwd.isPresent()) kwdPath = kwd.get();
		
		
		// 회원 총 상품 개수
		Map<String, Object> mapCnt = new HashMap<String, Object>();
		mapCnt.put("categoryNo", categoryNoPath);
		mapCnt.put("kwd", kwdPath);
		int count = itemDao.countU(mapCnt);
		System.out.println("----------------------------------------- count : " + count);

		
		// 페이징 만들기
		int lastPage = (int) Math.ceil((double)count/(double)BOARD_CNT);	//마지막 페이지
		int startNum = ((pagesPath-1) * BOARD_CNT);		//시작번호
		int rangeStart = ((pagesPath-1)/PAGE_CNT) * PAGE_CNT + 1;		//페이지 범위
		Paging paging = new Paging(count, lastPage, startNum, rangeStart, BOARD_CNT, PAGE_CNT);
		System.out.println("----------------------------------------- paging : " + paging);
		
		
		// 리스트 구하기
		Map<String, Object> daoMap = new HashMap<String, Object>();
		daoMap.put("startNum", startNum);
		daoMap.put("boardCnt", BOARD_CNT);
		daoMap.put("kwd", kwdPath);
		daoMap.put("categoryNo", categoryNoPath);
		List<ItemVo> itemList = itemDao.selectListU(daoMap);
		System.out.println("----------------------------------------- itemList : " + itemList);
		
		return new ItemsVo(itemList, paging);
	}


	// 최근 상품리스트
	@Override
	public List<ItemVo> getNewList(ItemVo vo) {
		if(vo.getCategoryNo() != null && vo.getCategoryNo() == -1) vo.setCategoryNo(null);
		return itemDao.selectNewList(vo);
	}


	// 최근 메인 이미지 리스트 요청
	@Override
	public List<MainImgVo> getNewImgList(Integer showCnt) {
		return itemDao.getNewItemList(showCnt);
	}

}
