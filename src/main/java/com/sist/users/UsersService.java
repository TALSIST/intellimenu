package com.sist.users;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.vo.UsersVO;

@Service
public class UsersService {
	@Autowired
	private UsersMapper uMapper;
	
	public int selectUser(String email) {
		return uMapper.selectUser(email);
	}
	
	public int selectUserTotal() {
		return uMapper.selectUserTotal();
	}

	public int selectAdmin(int id) {
		return uMapper.selectAdmin(id);
	}
	
	public void insertLoginLog(Map log) {
		uMapper.insertLoginLog(log);
	}
	
	public UsersVO selectUserData(String email) {
		return uMapper.selectUserData(email);
	}
	
	public List<UsersVO> selectUserList(Map map) {
		return uMapper.selectUserList(map);
	}
	
	public int selectUserInfoExist(Map map) {
		return uMapper.selectUserInfoExist(map);
	}
	
	@Transactional
	public void registUser(UsersVO vo) {
		// TODO : 회원정보 validation check
		uMapper.insertUserDefault(vo);
	}

	public List<UsersVO> UpdateUser(UsersVO vo) {
		return uMapper.updateUser(vo);
	}
	
	// 회원 한 명 삭제
	public void deleteUser(int id) {
	
	}
	
	@Transactional
	public void deleteUsers(int[] id) {
		for (int i : id) {
			uMapper.deleteUser(i);
		}
	}
	
}
