package com.ssafit.rh.model.dto;

import java.util.Date;

public class Review {

	private int idx;
	private int like;
	private String content;
	private String reviewTime;
	private String videoId;
	private String nickname;
	private String userId;
	
	public Review() {}

	public Review(int idx, int like, String content, String reviewTime, String videoId, String nickname,
			String userId) {
		super();
		this.idx = idx;
		this.like = like;
		this.content = content;
		this.reviewTime = reviewTime;
		this.videoId = videoId;
		this.nickname = nickname;
		this.userId = userId;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(String reviewTime) {
		this.reviewTime = reviewTime;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Review [idx=" + idx + ", like=" + like + ", content=" + content + ", reviewTime=" + reviewTime
				+ ", videoId=" + videoId + ", nickname=" + nickname + ", userId=" + userId + "]";
	}
	
}
