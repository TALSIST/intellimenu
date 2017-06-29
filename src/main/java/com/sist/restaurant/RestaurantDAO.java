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
	
	public int restaurantTotalPage(int rowSize){
		return restaurantMapper.restaurantTotalPage(rowSize);
	}
}
