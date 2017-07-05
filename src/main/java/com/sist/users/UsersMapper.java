package com.sist.users;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.UsersVO;

public interface UsersMapper {
	
	/**
	 * 출력 & 로그인 관련 
	 */
	
	// 로그인 : 회원 존재 여부 확인
	@Select("SELECT count(*) FROM users WHERE email=#{email}")
	public int selectUser(String email);

	// 회원 정보 출력
	@Select("SELECT id,email,pwd,name,nickname,regdate,moddate FROM users WHERE email=#{email}")
	public UsersVO selectUserData(String email);
	
	// 전체 회원 수 출력
	@Select("SELECT count(*) FROM users")
	public int selectUserTotal();
	
	//관리자 여부 조회
	@Select("SELECT count(*) FROM users_admin WHERE user_id=#{id}")
	public int selectAdmin(int id);
	
	@Insert("INSERT INTO log_login(user_id,status,ip) VALUES(#{user_id},#{status},#{ip})")
	public void insertLoginLog(Map log);
	
	// 전체 회원을 잘라서 출력
	@Select("SELECT Y.*, num FROM ("
				+ "SELECT X.*, rownum as num FROM ("
					+ "SELECT id,email,pwd,name,nickname,regdate,moddate"
					+ " FROM users"
					+ " ORDER BY id DESC) X) Y"
			+ " WHERE num BETWEEN #{start} AND #{end}")
	public List<UsersVO> selectUserList(Map map);

	
	/**
	 * 가입, 정보수정 관련
	 */
	
	// 필드 존재여부 확인
	@Select("SELECT COUNT(*) FROM users WHERE ${field}=#{data}")
	public int selectUserInfoExist(Map map);
	
	// 회원 정보 입력
	@Insert("INSERT INTO users(id,email,pwd,name,nickname)"
			+ " VALUES(users_seq.nextval,#{email},#{pwd},#{name},#{nickname})")
	public void insertUserDefault(UsersVO vo);

	@Update("UPDATE users"
			+ " SET email=#{email},pwd=#{pwd},name=#{name},nickname=#{nickname},regdate=#{regdate},moddate=SYSDATE)")
	public List<UsersVO> updateUser(UsersVO vo);
	
	// 회원 삭제
	@Delete("DELETE FROM users WHERE id=#{id}")
	public void deleteUser(int id);
	
}
