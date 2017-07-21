package com.sist.users;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.LogLoginVO;
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
	@Select("SELECT * FROM ("
				+ "SELECT X.*, rownum as num FROM ("
					+ "SELECT id,email,pwd,name,nickname,regdate,moddate"
					+ " FROM users"
					+ " ORDER BY id DESC) X)"
			+ " WHERE num BETWEEN #{start} AND #{end}")
	public List<UsersVO> selectUserList(Map map);
	
	// 로그인 로그
	@Select("SELECT COUNT(*) FROM log_login")
	public int selectLogLoginTotal();

	@Results(value= {
			@Result(property="user_id", column="user_id"),
			@Result(property="ip", column="ip"),
			@Result(property="reqdate", column="reqdate"),
			@Result(property="status", column="staus"),
			@Result(property="user", column="user_id", javaType=UsersVO.class,
				one=@One(select="selectLogLogin"))
	})
	@Select("SELECT * FROM ("
				+ "SELECT X.*, rownum as num FROM ("
					+ "SELECT user_id,ip,reqdate,status"
					+ " FROM log_login"
					+ " ORDER BY reqdate DESC) X)"
			+ " WHERE num BETWEEN #{start} AND #{end}")
	public List<LogLoginVO> selectLogLoginList(Map map);
	
	@Select("SELECT id,email,nickname FROM users WHERE id=#{user_id}")
	public UsersVO selectLogLogin(int user_id);
	
	
	/**
	 * 가입, 정보수정 관련
	 */
	
	// 필드 존재여부 확인
	@Select("SELECT COUNT(*) FROM users WHERE ${field}=#{data}")
	public int selectUserInfoExist(Map map);
	
	// 추가정보 존재 확인
	@Select("SELECT COUNT(*) FROM users_ext WHERE user_id=#{id}")
	public int selectUserExtInfoExist(int id);
	
	// 회원 정보 입력
	@Insert("INSERT INTO users(id,email,pwd,name,nickname)"
			+ " VALUES(users_seq.nextval,#{email},#{pwd},#{name},#{nickname})")
	public void insertUserDefault(UsersVO vo);

	@Insert("INSERT INTO users_ext VALUES(#{id},#{religion_id},#{vegeterian_id},#{address1},#{address2},"
			+ "#{gender},#{img_ori},#{img_new})")
	public void InsertUserExtendedInfo(UsersVO vo);
	
	// 회원 기피 재료 입력
	@Insert("INSERT INTO users_ingr VALUES(users_ingr_seq.nextval,#{user_id},#{ingredient_id},#{type})")
	public void InsertUserIngrList(Map map);
	
	@Update("UPDATE users"
			+ " SET email=#{email},pwd=#{pwd},name=#{name},nickname=#{nickname},regdate=#{regdate},moddate=SYSDATE)")
	public List<UsersVO> updateUser(UsersVO vo);
	
	@Update("UPDATE users_ext"
			+ " SET religion_id=#{religion_id},vegeterian_id=#{vegeterian_id},address1=#{address1},address2=#{address2}," 
			+ "gender=#{gender},img_ori=#{img_ori},img_new=#{img_new}")
	public void updateUserExt(UsersVO vo);
	
	// 회원 삭제
	@Delete("DELETE FROM users WHERE id=#{id}")
	public void deleteUser(int id);
	
	//회원 nickname 가져오기
	@Select("Select nickname from users where id=#{id}")
	public String selectNickName(int id);
	
	//회원랭킹10위까지 가져오기
	@Select("Select * from user_rank")
	public List<UsersVO> selectUserRank();
	
}
