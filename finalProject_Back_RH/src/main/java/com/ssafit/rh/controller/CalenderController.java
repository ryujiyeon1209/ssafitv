package com.ssafit.rh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.rh.model.dto.Calender;
import com.ssafit.rh.model.dto.LikeVideo;
import com.ssafit.rh.model.service.CalenderService;

@RestController
@RequestMapping("/ssafit")
public class CalenderController {
	
	CalenderService calenderService;

	@Autowired
	public void setCalenderService(CalenderService calenderService) {
		this.calenderService = calenderService;
	}
	
	//가져오기
	@GetMapping("/calender")
	public ResponseEntity<?> selectOne(Calender calender){
		Calender selectCal = calenderService.selectOne(calender);
		
		if(selectCal!=null) {
			return new ResponseEntity<Calender>(selectCal, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	//등록하기
	@PostMapping("/calender/create")
	public ResponseEntity<?> insert(Calender calender){
		int result = calenderService.insert(calender);
		
		if(result==1) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	

}
