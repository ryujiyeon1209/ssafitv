package com.ssafit.rh.model.service;

import java.util.List;

import com.ssafit.rh.model.dto.User;


public interface UserService {

	//회원가입
	int insert(User user);

	//로그인, 임시 비밀번호 발급, 아이디 중복확인
	User selectOne(String id);

	//회원탈퇴
	int delete(String id);

	//회원 수정(프로필)
	int updateProfile(User User);

	//회원수정(닉네임)
	int updateNickname(User User);

	//회원수정(비밀번호)
	int updatePassword(User User);

	//닉네임 중복확인
	User searchNickname(String nickName);

	//언젠가 쓸 일이 있을 수도 있으니 보류
	List<User> selectAll();

}
