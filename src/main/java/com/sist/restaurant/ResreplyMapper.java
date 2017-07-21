package com.sist.restaurant;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.RestaurantReplyVO;

public interface ResreplyMapper {
	@Select("SELECT * FROM restaurant_reply where restaurant_id=#{restaurant_id} ORDER BY id")
	public List<RestaurantReplyVO> list(int restaurant_id);
	
	@Insert("INSERT INTO"
			+ " restaurant_reply(id, user_id, restaurant_id, reply, score, img_ori, img_new)"
			+ " VALUES(restaurant_reply_SEQ.nextval, #{user_id}, #{restaurant_id}, #{reply}, #{score}, #{img_ori}, #{img_new})")
	public void insert(RestaurantReplyVO vo);

	@Update("Update restaurant_reply set report=(report+1) where id=#{id}")
	public void report(int id);
	
	// 전체레코드 수 가져오기
	@Select("SELECT count(*) from restaurant_reply WHERE restaurant_id=#{restaurant_id}")
	public int totalRecord(int restaurant_id);
	
	// 총페이지수 가져오기
	@Select("SELECT CEIL(COUNT(*)/9) FROM restaurant_reply WHERE restaurant_id=#{restaurant_id}")
	public int totalPage(int restaurant_id);

}
