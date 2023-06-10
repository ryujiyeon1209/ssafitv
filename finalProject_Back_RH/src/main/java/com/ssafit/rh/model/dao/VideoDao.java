package com.ssafit.rh.model.dao;

import java.util.List;

import com.ssafit.rh.model.dto.Video;

public interface VideoDao {
	
	//모든 비디오 가져오기
	List<Video> selectAll();

	//video 하나 가져오기
	Video selectOne(String videoId);
	
	//누를 때마다 조회수 + 1
	int updateVideoViewCnt(Video video);
	
	//youtubeAPI로 가져온 영상 db에 넣기
	int insert(Video video);
	
}
