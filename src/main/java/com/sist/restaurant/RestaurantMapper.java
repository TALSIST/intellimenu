package com.sist.restaurant;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.RestaurantVO;

import java.util.*;

public interface RestaurantMapper {
	
	@Select("SELECT id,address1,category,address2,name,score,regdate,tel,price,content,parking,holiday,busihour,img_new,img_ori "
			+ "FROM restaurant")
	public List<RestaurantVO> restaurantListData();
	
	@Select("SELECT id,address1,category,address2,name,score,regdate,tel,price,content,"
			+ "parking,holiday,busihour,img_new,img_ori,num "
			+ "FROM (SELECT id,address1,category,address2,name,score,regdate,tel,price,content,"
			+ "parking,holiday,busihour,img_new,img_ori,rownum as num FROM restaurant ORDER BY id DESC) "
			+ "WHERE num BETWEEN ${start} AND ${end}")
	public List<RestaurantVO> restaurantAdminList(Map map);
	
	@Select("SELECT COUNT(*) FROM restaurant")
	public int restaurantTotal();
	
	@Select("SELECT R.id,address1,address2,category,name,score,regdate,tel,price,content,"
			+ "parking,holiday,busihour,img_new,img_ori "
			+ "FROM RESTAURANT R FULL OUTER JOIN ADDRESS A ON R.address1=A.id where R.id=#{id}")
	public RestaurantVO restaurantDetail(int id);
	
	@Select("SELECT sigun FROM RESTAURANT R FULL OUTER JOIN ADDRESS A ON R.address1=A.id where R.id=#{id}")
	public String restaurantsigun(int id);
	
}
