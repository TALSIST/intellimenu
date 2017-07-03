package com.sist.search;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.vo.RecipeVO;
import com.sist.vo.RestaurantVO;

public interface SearchMapper {
	/**
	 * 레시피 검색 
	 */
	
	// 
	@Select("")
	public List<RecipeVO> recipeList();
	
	/**
	 * 음식점 검색
	 */
	
	// 
	@Select("")
	public List<RestaurantVO> restaurantList();
	
}
