package com.sist.recipe;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.Recipe;

public interface RecipeMapper {
		
	@Select("SELECT id, title, img_ori, img_new, hit, num"
			+ " FROM ( SELECT id, title, img_ori, img_new, hit, rownum as num"
			+ " FROM ( SELECT id, title, img_ori, img_new, hit"
			+ "	FROM recipe WHERE cat_sub_id=#{cat_sub_id} ORDER BY id desc))"
			+ " WHERE num BETWEEN #{start} and #{end}")
	public List<Recipe> CatSubRecipeListData(Map map);
	
	@Insert("Insert into recipe(ID,USER_ID,CAT_SUB_ID) values  ")
	public void insertRecipe(Recipe vo);
	
}
