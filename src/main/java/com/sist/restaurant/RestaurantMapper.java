package com.sist.restaurant;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.RestaurantVO;

import java.util.*;

public interface RestaurantMapper {
	@Select("SELECT id,address1,category,address2,name,score,regdate,tel,price,content,parking,holiday,busihour,img_new,img_ori FROM restaurant")
	public List<RestaurantVO> restaurantListData();
	
	@Select("SELECT id,address1,category,address2,name,score,regdate,tel,price,content,parking,holiday,busihour,img_new,img_ori,num FROM (SELECT id,address1,category,address2,name,score,regdate,tel,price,content,parking,holiday,busihour,img_new,img_ori,rownum as num FROM (SELECT id,address1,category,address2,name,score,regdate,tel,price,content,parking,holiday,busihour,img_new,img_ori FROM restaurant ORDER BY id DESC)) WHERE num BETWEEN #{start} AND #{end}")
	public List<RestaurantVO> restaurantAdminList(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/#{rowSize}) FROM restaurant")
	public int restaurantTotalPage(int rowSize);
}
