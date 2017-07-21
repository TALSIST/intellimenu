package com.sist.bigdata;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.RecipeVO;

public interface MartMapper {
	
	@Select("SELECT * "
			+ " FROM (SELECT id, user_id, title, hit, img_ori, img_new, rownum AS num"
			+ " FROM (SELECT DISTINCT recipe.id AS id, recipe.USER_ID AS USER_ID, recipe.TITLE AS title, recipe.HIT AS hit, IMG_ORI, IMG_NEW"
			+ " FROM ingredient, INGR_RECIPE, RECIPE"
			+ " WHERE ingredient.ID=ingr_recipe.INGREDIENT_ID AND ingr_recipe.RECIPE_ID=recipe.ID"
			+ " AND ingredient.NAME like #{ingrName}"
			+ " ORDER BY recipe.ID desc))")
	public List<RecipeVO> RecipeBymartHitIngr(String ingrName);
}
