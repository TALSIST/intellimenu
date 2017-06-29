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
	
<<<<<<< HEAD
	@Select("SELECT CEIL(COUNT(*)/#{rowSize}) FROM restaurant")
	public int restaurantTotalPage(int rowSize);
=======
	@Select("SELECT COUNT(*) FROM restaurant")
	public int restaurantTotal();
>>>>>>> 13a93c214a48b263ddbf0f2b10b56dc1e03796dd
}
