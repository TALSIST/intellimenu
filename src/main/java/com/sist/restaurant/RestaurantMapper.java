package com.sist.restaurant;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.RestaurantVO;

import java.util.*;

public interface RestaurantMapper {
	@Select("SELECT id,address1,category,address2,name,score,regdate,tel,price,content,parking,holiday,busihour,img_new,img_ori FROM restaurant")
	public List<RestaurantVO> restaurantListData();
	
	@Select("SELECT id,address1,category,address2,name,score,regdate,tel,price,content,"+
			"parking,holiday,busihour,img_new,img_ori,rownum AS num " + 
			"FROM restaurant " + 
			"WHERE rownum BETWEEN ${start} AND ${end} " + 
			"ORDER BY id DESC")
	public List<RestaurantVO> restaurantAdminList(Map map);
	
	@Select("SELECT COUNT(*) FROM restaurant")
	public int restaurantTotal();
}
