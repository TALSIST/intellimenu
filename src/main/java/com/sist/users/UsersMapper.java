package com.sist.users;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.UsersVO;

public interface UsersMapper {
	
	@Select("SELECT count(*) FROM users WHERE email=#{email}")
	public int selectUser(String email);

	@Select("SELECT id,email,pwd,name,nickname,regdate,moddate FROM users WHERE email=#{email}")
	public UsersVO selectUserData(String email);

	@Insert("INSERT INTO users VALUES(seq_user.nextval(),#{email},#{pwd},#{name},#{nickname},SYSDATE,SYSDATE)")
	public List<UsersVO> registUser(UsersVO vo);

	@Update("Update users SET email=#{email},pwd=#{pwd},name=#{name},nickname=#{nickname},regdate=#{regdate},moddate=SYSDATE)")
	public List<UsersVO> UpdateUser(UsersVO vo);
	
}
