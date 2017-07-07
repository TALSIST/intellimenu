package com.sist.users;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.util.FileManager;
import com.sist.vo.LogLoginVO;
import com.sist.vo.UsersVO;

@Service
public class UsersService {
	@Autowired
	private FileManager fMgr;
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
	
	public void registUserAddinfo(UsersVO vo) {
		uMapper.InsertUserExtendedInfo(vo);
	}

	public List<UsersVO> UpdateUser(UsersVO vo) {
		return uMapper.updateUser(vo);
	}
	
	// 회원 한 명 삭제
	public void deleteUser(int id) {
		uMapper.deleteUser(id);
	}
	
	@Transactional
	public void deleteUsers(int[] id) {
		for (int i : id) {
			uMapper.deleteUser(i);
		}
	}
	
	// 로그 출력
	public int selectLogLoginTotal() {
		return uMapper.selectLogLoginTotal();
	}
	
	public List<LogLoginVO> selectLogLoginList(Map map) {
		return uMapper.selectLogLoginList(map);
	}
	
	//닉네임 가져오기
	public String selectNickName(int id){
		
		return uMapper.selectNickName(id);
	};
	
	//유저랭킹 가져오기
	public List<UsersVO> selectUserRank(){
		
		return uMapper.selectUserRank();
	};


}
