package com.sist.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.vo.UsersVO;

@Service
public class UsersService {
	@Autowired
	private UsersMapper uMapper;
	
	public int selectUser(String email) {
		return uMapper.selectUser(email);
	}

	public UsersVO selectUserData(String email) {
		return uMapper.selectUserData(email);
	}
	
	public String registUser(UsersVO vo) {
		uMapper.registUser(vo);
		// TODO: 성공여부 반환
		return "";
	}

	public List<UsersVO> UpdateUser(UsersVO vo) {
		return uMapper.UpdateUser(vo);
	}
	
}
