package com.ssafit.rh.model.service;

import java.util.List;

import com.ssafit.rh.model.dto.LikeVideo;

public interface LikeVideoService {

	//user媛� 醫������� videoList 媛��몄�ㅺ린
	List<LikeVideo> selectOne(String userId);
	
	LikeVideo select(LikeVideo likevideo);
	
	int insert(LikeVideo likeVideo);
	
	int deleteOne(LikeVideo likeVideo);
	
}
