package com.sist.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.RestaurantVO;

import java.util.*;
@Repository
public class RestaurantDAO {
	@Autowired
	private RestaurantMapper restaurantMapper;
	
	public List<RestaurantVO> restaurantListData(){
		return restaurantMapper.restaurantListData();
	}
	public List<RestaurantVO> restaurantAdminList(Map map){
		return restaurantMapper.restaurantAdminList(map);
	}
	
<<<<<<< HEAD
	public int restaurantTotalPage(int rowSize){
		return restaurantMapper.restaurantTotalPage(rowSize);
=======
	public int restaurantTotal(){
		return restaurantMapper.restaurantTotal();
>>>>>>> 13a93c214a48b263ddbf0f2b10b56dc1e03796dd
	}
}
