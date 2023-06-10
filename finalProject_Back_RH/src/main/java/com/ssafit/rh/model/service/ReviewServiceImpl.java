package com.ssafit.rh.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.rh.model.dao.ReviewDao;
import com.ssafit.rh.model.dto.Review;


@Service
public class ReviewServiceImpl implements ReviewService {
	
	ReviewDao reviewDao;
	
	@Autowired
	public void setReviewDao(ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
	}

	@Override
	public List<Review> selectAll() {
		return reviewDao.selectAll();
	}

	@Override
	public List<Review> selectOne(String userId) {
		return reviewDao.selectOne(userId);
	}

	@Override
	public List<Review> selectReview(String videoId) {
		return reviewDao.selectReview(videoId);
	}
	
	@Override
	public int insert(Review review) {
		return reviewDao.insert(review);
	}

	@Override
	public int update(Review review) {
		return reviewDao.update(review);
	}

	@Override
	public int delete(Review review) {
		return reviewDao.delete(review);
	}

	@Override
	public int updateLikeReview(Review review) {
		return reviewDao.updateLikeReview(review);
	}
	
}
