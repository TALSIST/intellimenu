package com.sist.restaurant;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.RestaurantReplyVO;

import java.util.*;
public interface ResreplyMapper {
/*	@Select("SELECT id, user_id, restaurant_id, reply, score, regdate, img_ori, img_new "
			+ "from restaurant_reply where restaurant_id=#{restaurantId} ORDER BY id")*/
	@Select("SELECT * FROM restaurant_reply where restaurant_id=#{restaurantId} ORDER BY id")
	public List<RestaurantReplyVO> list(int restaurantid);
	
	@Select("SELECT img_new FROM restaurant_reply where id=#{id}")
	public String getImgName(int id);
	
	@Insert("INSERT INTO restaurant_reply(id, user_id, restaurant_id, reply, score, regdate, img_ori, img_new) "
			+ "VALUES(restaurant_reply_SEQ.nextval, #{userId}, #{restaurantId}, #{reply}, #{score}, SYSDATE, #{imgOri}, #{imgNew})")
	public void insert(RestaurantReplyVO vo);

}

