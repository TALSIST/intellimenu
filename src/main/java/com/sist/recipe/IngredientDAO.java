package com.sist.recipe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.IngredientVO;

@Repository
public class IngredientDAO {

	@Autowired
	private IngredientMapper ingredientMapper;
	
	
	public List<IngredientVO> selectIngrListByMonth(int nowMonth){
		
		return ingredientMapper.selectIngrListByMonth(nowMonth);
	};

	
}
