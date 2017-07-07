package com.sist.users;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.recipe.IngredientMapper;
import com.sist.util.FileManager;
import com.sist.vo.IngredientVO;
import com.sist.vo.LogLoginVO;
import com.sist.vo.UsersVO;

@Service
public class UsersService {
	@Autowired
	private FileManager fileMgr;
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
	
	
	//============================== 회원정보 입력  ==============================//
	@Transactional
	public void registUser(UsersVO vo) {
		uMapper.insertUserDefault(vo);
	}
	
	public List<UsersVO> UpdateUser(UsersVO vo) {
		return uMapper.updateUser(vo);
	}
	
	@Transactional
	public void registUserAddinfo(UsersVO vo) throws IllegalStateException, IOException {
		List<Integer> ingrList = vo.getIngrv();
		for (int ingredient_id : ingrList) {
			Map map = new HashMap();
			map.put("user_id", vo.getId());
			map.put("ingredient_id", ingredient_id);
			map.put("type", "hate");
			uMapper.InsertUserIngrList(map);
		}
		// null 확인 후 변경
		userNullDataRefine(vo);
		uMapper.InsertUserExtendedInfo(vo);
	}

	//============================== 회원정보 수정  ==============================//
	
	public int selectUserExtInfoExist(int id) {
		return uMapper.selectUserExtInfoExist(id);
	}
	
	public void updateUserExt(UsersVO vo) throws IllegalStateException, IOException {
		userNullDataRefine(vo);
		uMapper.updateUserExt(vo);
	}
	
	private UsersVO userNullDataRefine(UsersVO vo) throws IllegalStateException, IOException {
		if (vo.getGender()==null) { vo.setGender(""); }
		if (vo.getAddress2()==null) { vo.setAddress2(""); }
		if (!vo.getImg().isEmpty()) {
			vo.setImg_ori(vo.getImg().getName());
			vo.setImg_new(fileMgr.insertFile(vo.getImg(), "users"));
		} else {
			vo.setImg_ori("");
			vo.setImg_new("");
		}
		return vo;
	}
	
	
	//============================== 회원정보 제거  ==============================//
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
	
	//============================== 로그 출력  ==============================//
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

}
