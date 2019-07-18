package com.cafe24.mhshop.repository;

import java.util.List;

import com.cafe24.mhshop.vo.OptionVo;

public interface OptionDao {

	List<OptionVo> selectList(Long itemNo);			// 상품번호에 속한 옵션 리스트
	Integer countByOptionDetailNo(Long no);			// 상세옵션번호를 가지는 옵션이 있는지 확인 요청

}
