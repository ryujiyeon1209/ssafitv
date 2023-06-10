package com.ssafit.rh.model.dto;

public class Video {

	private String videoId;
	private String title;
	private int viewCnt;
	private String videoType;

	public Video() {}

	public Video(String videoId, String title, int viewCnt, String videoType) {
		super();
		this.videoId = videoId;
		this.title = title;
		this.viewCnt = viewCnt;
		this.videoType = videoType;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public String getVideoType() {
		return videoType;
	}

	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}

	@Override
	public String toString() {
		return "Video [videoId=" + videoId + ", title=" + title + ", viewCnt=" + viewCnt + ", videoType=" + videoType
				+ "]";
	}

}
