package com.ssafit.rh.model.dao;

import com.ssafit.rh.model.dto.Calender;

public interface CalenderDao {
	
	//userId로 선택한 날짜의 comment와 mood를 가져오기
	Calender selectOne(Calender calender);

	//선택한 날짜의 comment 등록하기
	int insert(Calender calender);
	
	//사용자의 모든 기록 삭제하기
	int delete(String userId);
	

}
