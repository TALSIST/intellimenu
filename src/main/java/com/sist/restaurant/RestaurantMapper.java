package com.sist.restaurant;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.AddressVO;
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
	
	@Select("SELECT * FROM ADDRESS WHERE sigun LIKE '%'||#{addr}||'%'")
	public List<AddressVO> restaurantsigunAll(String addr);
	
	//admin 권한 계정 전부 가져오기
	@Select("select user_id from users_admin")
	public List<Integer> getAdminID();
	
	@Insert("INSERT INTO restaurant VALUES(restaurant_SEQ.nextval,#{user_id},#{address1},#{category},"+
			"#{address2},#{name},#{score},SYSDATE,#{tel, jdbcType=VARCHAR},#{price, jdbcType=VARCHAR},#{content, jdbcType=VARCHAR},0,#{parking, jdbcType=VARCHAR},#{holiday, jdbcType=VARCHAR},"+
			"#{busihour, jdbcType=VARCHAR},#{img_ori, jdbcType=VARCHAR},#{img_new, jdbcType=VARCHAR})")
	public void restaurantInsert(RestaurantVO vo);
	
	@Delete("DELETE FROM restaurant WHERE id=#{id}")
	public void restaurantDelete(int id);
}
