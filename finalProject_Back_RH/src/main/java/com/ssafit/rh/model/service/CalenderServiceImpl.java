package com.ssafit.rh.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.rh.model.dao.CalenderDao;
import com.ssafit.rh.model.dto.Calender;

@Service
public class CalenderServiceImpl implements CalenderService {

	CalenderDao calenderDao;
	
	@Autowired
	public void setCalenderDao(CalenderDao calenderDao) {
		this.calenderDao = calenderDao;
	}

	@Override
	public Calender selectOne(Calender calender) {
		return calenderDao.selectOne(calender);
	}

	@Override
	public int insert(Calender calender) {
		return calenderDao.insert(calender);
	}

	@Override
	public int delete(String userId) {
		System.out.println(userId);
		return calenderDao.delete(userId);
	}

}
