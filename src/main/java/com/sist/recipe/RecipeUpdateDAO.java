package com.sist.recipe;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.CatSubVO;
import com.sist.vo.IngrRecipeVO;
import com.sist.vo.RecipeVO;

@Repository
public class RecipeUpdateDAO {
	@Autowired
	private RecipeInsertMapper rim;
	
	public RecipeVO selectRecipe(int id){
		return rim.selectRecipe(id);
	}
	public int selectStepCount(int id){
		return rim.selectStepCount(id);
	}
	public CatSubVO selectCatsub(int id){
		return rim.selectCatsub(id);
	}
	public List<IngrRecipeVO> selectIngRecipe(int id){
		return rim.selectIngRecipe(id);
	}
	
}
