package com.sist.recipe;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.IngrRecipeVO;
import com.sist.vo.IngredientVO;
import com.sist.vo.RecipeVO;

public interface RecipeInsertMapper {

	
	@Insert("Insert into recipe(ID,USER_ID,cat_sub_id,title,summary,reqmember,lvl,time,img_ori,img_new) values"
			+ "(recipe_seq.nextval,1,#{cat_sub_id},#{title},#{summary},#{reqmember},#{lvl},#{time},#{img_ori},#{img_new}"
			+ ")")
	public void insertRecipe(RecipeVO vo);
	
	//재료 입력
	@Insert("Insert into ingr_recipe values(#{ingredient_id},#{recipe_id},#{quantity})")
	public void insert_RecipeIngr(IngrRecipeVO vo);
	
	//id 값가져오기
	@Select("select max(id) from recipe")
	public int recipeMId();
	
	@Select("Select id,name,cal from ingredient where name like #{value}||'%'")
	public List<IngredientVO> selectIngr(String value);
	
	@Select("Select count(*) from ingredient where name=#{value}")
	public int selectIngCk(String value);
	@Select("Select id from ingredient where name=#{value}")
	public int selectIngId(String value);

	
}
