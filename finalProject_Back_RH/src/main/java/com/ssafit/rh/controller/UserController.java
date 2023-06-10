package com.ssafit.rh.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.rh.model.dto.LikeVideo;
import com.ssafit.rh.model.dto.Review;
import com.ssafit.rh.model.dto.User;
import com.ssafit.rh.model.service.CalenderService;
import com.ssafit.rh.model.service.LikeVideoService;
import com.ssafit.rh.model.service.ReviewService;
import com.ssafit.rh.model.service.UserService;
import com.ssafit.rh.util.JwtUtil;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/ssafit")
public class UserController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	private static final String SUCCESS = "succes";
	private static final String FAIL = "fail";

	private UserService userService;
	
	private ReviewService reviewService;
	
	private LikeVideoService likeVideoService;
	
	private CalenderService calenderService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setReviewService(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@Autowired
	public void setLikeVideoService(LikeVideoService likeVideoService) {
		this.likeVideoService = likeVideoService;
	}
	
	@Autowired
	public void setCalenderService(CalenderService calenderService) {
		this.calenderService = calenderService;
	}

	//�ъ�⑹�� ����, 濡�洹몄�� �� 
	@GetMapping("/user/{id}")
	public ResponseEntity<?> selectOne(@PathVariable String id){
		User user = userService.selectOne(id);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/user/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user){
        Map<String, Object> result = new HashMap<String, Object>();

        //우리가 입력한 아이디와 같은 아이디를 가진 사용자를 데이터 베이스에서 가져오기
        User selectUser = userService.selectOne(user.getId());
        System.out.println("user : " + user);
        System.out.println("selectuser : " +selectUser);
        
        HttpStatus status = null;
        if(selectUser==null) {
        	 result.put("message", FAIL);
             status = HttpStatus.NO_CONTENT;
             return new ResponseEntity<Map<String,Object>>(result, status);
        }
        
        
        try {
            //사용자가 존재하며, 비밀번호까지 같다면
        	System.out.println("user.pwd : " +user.getPassword() );
        	System.out.println("selectUser.pwd : " +selectUser.getPassword() );
        	
        	if(selectUser!=null && user.getPassword().equals(selectUser.getPassword())) {
        		result.put("access-token", jwtUtil.createToken("id", selectUser.getId()));
                result.put("message", SUCCESS);
                result.put("data", selectUser);
                status = HttpStatus.OK;
            } else {
                result.put("message", FAIL);
                status = HttpStatus.NO_CONTENT;
            }
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String,Object>>(result, status);
    }
	
	//����媛���
	@PostMapping("/user/signUp")
	public ResponseEntity<?> Insert(@RequestBody User user){
		System.out.println("����媛���");
		int result = userService.insert(user);
		if (result == 1) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	//��������
	@DeleteMapping("/user/{id}/delete")
	public ResponseEntity<?> delete(@PathVariable String id){
		
		
		//癒쇱�� �ъ�⑹���� 由щ럭�����댁＜湲�
		List<Review> list = reviewService.selectOne(id);
		System.out.println(list);
		for(int i=0; i<list.size(); i++) {
			reviewService.delete(list.get(i));
		}
		
		//�ъ�⑹��媛� 醫�����瑜� ��瑜� ���� ������湲�
		List<LikeVideo> videoList = likeVideoService.selectOne(id);
		System.out.println(videoList);
		for(int i=0; i<videoList.size(); i++) {
			likeVideoService.deleteOne(videoList.get(i));
		}
		
		System.out.println("硫����� �ㅼ�댁�ㅺ린" +id);

		//�ъ�⑹���� 罹�由곕�� 湲곕� ������湲�
		System.out.println("deleteCal");
		int deleteCal = calenderService.delete(id);
		System.out.println("deleteCal : " +deleteCal);
		
		//�ъ�⑹�� ������湲�
		int result = userService.delete(id);
		if (result == 1) {
			System.out.println("ok");
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}


	//�ъ�⑹�� ��濡��� ����
	@PutMapping("/user/modify/profile")
	public ResponseEntity<?> updateProfile(User user){
		
		int result = userService.updateProfile(user);
		System.out.println(result);
		if (result == 1) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	//�ъ�⑹�� ���ㅼ�� ����
	@PutMapping("/user/modify/nickname")
	public ResponseEntity<?> updateNickname(User user){
		int result = userService.updateNickname(user);
		//User modifyUser = userService.selectOne(user.getId());
		if (result == 1) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	//�ъ�⑹�� 鍮�諛�踰��� ����
	@PutMapping("/user/modify/password")
	public ResponseEntity<?> updatePassword(User user){
		int result = userService.updatePassword(user);
		if (result == 1) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	//�ъ�⑹�� ���ㅼ�� ����
	@GetMapping("/user/check/{nickname}")
	public ResponseEntity<?> searchNickname(@PathVariable String nickname){
		User user = userService.searchNickname(nickname);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	//紐⑤�� �ъ�⑹�� 媛��몄�ㅺ린
	@GetMapping("/user")
	public ResponseEntity<?> selectAll(){
		List<User> list = userService.selectAll();
		if (list != null) {
			return new ResponseEntity<List<User>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

}
