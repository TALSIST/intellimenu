package com.sist.recipe;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.Recipe;

public interface RecipeMapper {
	
	//cat_sub_id로 recipe리스트 가져오기
	@Select("SELECT id, title, img_ori, img_new, hit, num"
			+ " FROM ( SELECT id, title, img_ori, img_new, hit, rownum as num"
			+ " FROM ( SELECT id, title, img_ori, img_new, hit"
			+ "	FROM recipe"
			+ " WHERE cat_sub_id=#{cat_sub_id} ORDER BY id desc))"
			+ " WHERE num BETWEEN #{start} and #{end}")
	public List<Recipe> catSubRecipeListData(Map map);
	
<<<<<<< HEAD
	@Insert("Insert into recipe(ID,USER_ID,CAT_SUB_ID) values  ")
	public void insertRecipe(Recipe vo);
=======
	//총페이지수 가져오기
	@Select("SELECT CEIL(COUNT(*)/10) FROM recipe WHERE cat_sub_id=#{cat_sub_id}")
	public int catSubRecipeListTotalPage(int cat_sub_id);
	
	//id로 특정 recipe정보 가져오기
	@Select("SELECT * FROM RECIPE WHERE id=#{id}")
	public Recipe recipeDetail(int id);
>>>>>>> 1f0923ea10a13554334b17a59842bd59fb3ff01b
	
}
