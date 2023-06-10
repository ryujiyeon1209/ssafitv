package com.ssafit.rh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.rh.model.dto.Review;
import com.ssafit.rh.model.service.ReviewService;


@RestController
@RequestMapping("/ssafit")
public class ReviewController {

	ReviewService reviewService;
	
	@Autowired
	public void setReviewService(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	//紐⑤�� 由щ럭 媛��몄�ㅺ린
	@GetMapping("/review")
	public ResponseEntity<?> selectAll(){
		System.out.println("�ш린");
		List<Review> list = reviewService.selectAll();
		if (list != null) {
			return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	//user占쏙옙 占쌜쇽옙占쏙옙 占쏙옙占썰리占쏙옙트 占쏙옙占쏙옙占쏙옙占쏙옙
	@GetMapping("/review/{userId}")
	public ResponseEntity<?> selectOne(@PathVariable String userId){
		List<Review> list = reviewService.selectOne(userId);
		if (list != null) {
			return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	//鍮����� 由щ럭 媛��몄�ㅺ린
	@GetMapping("/review/video/{videoId}")
	public ResponseEntity<?> selectReview(@PathVariable String videoId){
		System.out.println("鍮����� 由щ럭 異���");
		List<Review> list = reviewService.selectReview(videoId);
		if (list != null) {
			return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	//由щ럭�깅�
	@PostMapping("/review/create")
	public ResponseEntity<?> insert(Review review){
		System.out.println("리뷰등록");
		System.out.println(review.getVideoId());
		int result = reviewService.insert(review);
		if (result == 1) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	//由щ럭 ����
	@PutMapping("/review/modify")
	public ResponseEntity<?> update(Review review){
		System.out.println("由щ럭����");
		int result = reviewService.update(review);
		System.out.println(result);
		if (result == 1) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	//由щ럭 ����
	@PutMapping("/review/delete")
	public ResponseEntity<?> delete(Review review){
		int result = reviewService.delete(review);
		if (result == 1) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	//占쏙옙占쏙옙 占쏙옙회占쏙옙 + 1
	@PutMapping("/review")
	public ResponseEntity<?> updateLikeReview(Review review){
		int result = reviewService.updateLikeReview(review);
		if (result == 1) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
}
