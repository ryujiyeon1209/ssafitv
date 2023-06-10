package com.ssafit.rh.model.dto;

public class LikeVideo {
	
	private String videoId;
	private String userId;
	
	public LikeVideo() {}

	public LikeVideo(String videoId, String userId) {
		super();
		this.videoId = videoId;
		this.userId = userId;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "likeVideo [videoId=" + videoId + ", userId=" + userId + "]";
	}

}
