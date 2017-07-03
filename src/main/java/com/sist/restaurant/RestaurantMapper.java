package com.sist.restaurant;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.RestaurantVO;

import java.util.*;

public interface RestaurantMapper {
	
	@Select("SELECT Y.* FROM ("
				+ " SELECT X.*, rownum as num FROM ("
					+ " SELECT id,address1,category,address2,name,score,regdate,tel,price,content,parking,holiday,busihour,img_new,img_ori"
					+ " FROM restaurant"
					+ " ORDER BY id DESC) X) Y"
			+ " WHERE num BETWEEN #{start} and #{end}")
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
