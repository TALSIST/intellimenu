package com.sist.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.UsersVO;

@Repository
public class UsersDAO {
	
	@Autowired
	private UsersMapper uMapper;

	public int selectUser(String email) {
		return uMapper.selectUser(email);
	}
	
	public UsersVO selectUserData(String email) {
		return uMapper.selectUserData(email);
	}

	public void registUser(UsersVO vo) {
	}

	public List<UsersVO> updateUser(UsersVO vo) {
		return uMapper.UpdateUser(vo);
	}
}
