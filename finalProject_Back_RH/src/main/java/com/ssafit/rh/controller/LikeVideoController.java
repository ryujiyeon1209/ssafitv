package com.ssafit.rh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.rh.model.dto.LikeVideo;
import com.ssafit.rh.model.service.LikeVideoService;


@RestController
@RequestMapping("/ssafit")
public class LikeVideoController {
	
	LikeVideoService likeVideoService;

	@Autowired
	public void setLikeVideoService(LikeVideoService likeVideoService) {
		this.likeVideoService = likeVideoService;
	}

	//user揶�� �ル��釉�?���� videoList 揶��?紐��ㅶ묾?
	@GetMapping("/likevideo/{userId}")
	public ResponseEntity<?> selectOne(@PathVariable String userId){
		List<LikeVideo> list = likeVideoService.selectOne(userId);
		System.out.println(list);
		if (list != null) {
			return new ResponseEntity<List<LikeVideo>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/likevideo")
	public ResponseEntity<?> select(LikeVideo likevideo){
		System.out.println("userId : " +likevideo.getUserId() +", videoId : " +likevideo.getVideoId());
		LikeVideo video = likeVideoService.select(likevideo);
		System.out.println(video);
		if (video != null) {
			return new ResponseEntity<LikeVideo>(video, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
		}
	}
	
	//likevideo �곕떽??��由�
	@PostMapping("/likevideo")
	public ResponseEntity<?> insert(LikeVideo likeVideo){
		int result = likeVideoService.insert(likeVideo);
		if (result == 1) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	//�ル��釉�??���λ��??野�怨���
	@DeleteMapping("/likevideo/delete")
	public ResponseEntity<?> deleteOne(LikeVideo likeVideo){
		int result = likeVideoService.deleteOne(likeVideo);

		if (result != 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	
}
