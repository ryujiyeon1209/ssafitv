package com.ssafit.rh.model.dao;

import java.util.List;

import com.ssafit.rh.model.dto.Review;


public interface ReviewDao {

	//모든 리뷰 가져오기
	List<Review> selectAll();

	//user가 작성한 리뷰 리스트 가져오기
	List<Review> selectOne(String userId);

	//해당 비디오의 리뷰 리스트 가져오기
	List<Review> selectReview(String videoId);

	//리뷰 작성
	int insert(Review review);

	//리뷰 수정
	int update(Review review);

	//리뷰 삭제
	int delete(Review review);

	//리뷰 좋아요 + 1
	int updateLikeReview(Review review);

}
