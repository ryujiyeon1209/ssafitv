package com.ssafit.rh.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.rh.model.dao.VideoDao;
import com.ssafit.rh.model.dto.Video;


@Service
public class VideoServiceImpl implements VideoService {
	
	VideoDao videoDao;
	
	@Autowired
	public void setVideoDao(VideoDao videoDao) {
		this.videoDao = videoDao;
	}

	@Override
	public List<Video> selectAll() {
		return videoDao.selectAll();
	}

	@Override
	public Video selectOne(String videoId) {
		return videoDao.selectOne(videoId);
	}

	@Override
	public int updateVideoViewCnt(Video video) {
		return videoDao.updateVideoViewCnt(video);
	}

	@Override
	public int insert(Video video) {
		return videoDao.insert(video);
	}
	
}
