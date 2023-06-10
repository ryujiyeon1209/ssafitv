package com.ssafit.rh.model.dto;

public class User {
	
	private String id;
	private String password;
	private String name;
	private String email;
	private String nickname;
	private String profile;
	
	public User() {}

	public User(String id, String password, String name, String email, String nickname, String profile) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.nickname = nickname;
		this.profile = profile;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email + ", nickname="
				+ nickname + ", profile=" + profile + "]";
	}

}
