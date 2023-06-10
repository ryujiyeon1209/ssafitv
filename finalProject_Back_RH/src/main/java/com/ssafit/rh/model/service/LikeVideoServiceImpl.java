package com.ssafit.rh.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.rh.model.dao.LikeVideoDao;
import com.ssafit.rh.model.dto.LikeVideo;


@Service
public class LikeVideoServiceImpl implements LikeVideoService {
	
	LikeVideoDao likeVideoDao;

	@Autowired
	public void setLikeVideoDao(LikeVideoDao likeVideoDao) {
		this.likeVideoDao = likeVideoDao;
	}

	@Override
	public List<LikeVideo> selectOne(String userId) {
		return likeVideoDao.selectOne(userId);
	}
	
	@Override
	public int insert(LikeVideo likeVideo) {
		return likeVideoDao.insert(likeVideo);
	}
	
	@Override
    public int deleteOne(LikeVideo likeVideo) {
        return likeVideoDao.deleteOne(likeVideo);
    }

	@Override
	public LikeVideo select(LikeVideo likevideo) {
		return likeVideoDao.select(likevideo);
	}
    
}

