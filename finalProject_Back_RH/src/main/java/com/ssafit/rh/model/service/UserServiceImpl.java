package com.ssafit.rh.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.rh.model.dao.LikeVideoDao;
import com.ssafit.rh.model.dao.ReviewDao;
import com.ssafit.rh.model.dao.UserDao;
import com.ssafit.rh.model.dto.User;


@Service
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	@Override
	public int insert(User user) {
		return userDao.insert(user);
	}

	@Override
	public User selectOne(String id) {
		return userDao.selectOne(id);
	}

	@Override
	public int delete(String id) {
		return userDao.delete(id);
	}

	@Override
	public int updateProfile(User User) {
		return userDao.updateProfile(User);
	}

	@Override
	public int updateNickname(User User) {
		return userDao.updateNickname(User);
	}

	@Override
	public int updatePassword(User User) {
		return userDao.updatePassword(User);
	}

	@Override
	public User searchNickname(String nickName) {
		return userDao.searchNickname(nickName);
	}

	@Override
	public List<User> selectAll() {
		return userDao.selectAll();
	}

}
