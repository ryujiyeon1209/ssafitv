package com.ssafit.rh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.rh.model.dto.Video;
import com.ssafit.rh.model.service.VideoService;

@RestController
@RequestMapping("/ssafit")
public class VideoController {

	VideoService videoService;

	@Autowired
	public void setVideoService(VideoService videoService) {
		this.videoService = videoService;
	}

	//紐⑤�� 鍮����� 媛��몄�ㅺ린
	@GetMapping("/video")
	public ResponseEntity<?> selectAll(){
		List<Video> list = videoService.selectAll();
		if (list != null) {
			return new ResponseEntity<List<Video>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	//鍮����� ���� 媛��몄�ㅺ린
	@GetMapping("video/{videoId}")
	public ResponseEntity<?> selectOne(@PathVariable String videoId){
		Video video = videoService.selectOne(videoId);
		if (video != null) {
			return new ResponseEntity<Video>(video, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	//youtubeAPI濡� 媛��몄�� ���� db�� �ｊ린
	@PostMapping("/video")
	public ResponseEntity<?> insert(@RequestBody Video video){
		
		int result = videoService.insert(video);
		if (result == 1) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	//��瑜� ��留��� 議고���� + 1
	@PutMapping("/video")
	public ResponseEntity<?> updateVideoViewCnt(Video video){
		int result = videoService.updateVideoViewCnt(video);
		if (result == 1) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	
	
}
