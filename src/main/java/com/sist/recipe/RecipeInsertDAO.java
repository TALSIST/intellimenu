package com.sist.recipe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.IngredientVO;
import com.sist.vo.RecipeVO;
@Repository
public class RecipeInsertDAO {
	@Autowired
	RecipeInsertMapper rim;
	public void insertRecipe(RecipeVO vo){
		rim.insertRecipe(vo);
		
	}

	//pk 가져오기
	public int recipeMId(){
		return rim.recipeMId();
	}
	
	public void insert_RecipeIngr(int recipe_id,String quantity){
		rim.insert_RecipeIngr(recipe_id, quantity);
	}
	
	public List<IngredientVO> selectIngr(String value){
		return rim.selectIngr(value);
	}
	//값유효성체크
	public int selectIngCk(String value){
		return rim.selectIngCk(value);
	}
	
	
	
}
