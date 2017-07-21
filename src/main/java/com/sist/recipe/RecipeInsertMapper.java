package com.sist.recipe;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.jsoup.select.Evaluator.IndexGreaterThan;

import com.sist.vo.CatSubVO;
import com.sist.vo.IngrRecipeVO;
import com.sist.vo.IngredientVO;
import com.sist.vo.RecipeContentVO;
import com.sist.vo.RecipeTagVO;
import com.sist.vo.RecipeVO;

public interface RecipeInsertMapper {

	
	@Insert("Insert into recipe(ID,USER_ID,cat_sub_id,title,summary,reqmember,lvl,time,img_ori,img_new) values"
			+ "(recipe_seq.nextval,#{user_id},#{cat_sub_id},#{title},#{summary},#{reqmember},#{lvl},#{time},#{img_ori},#{img_new}"
			+ ")")
	public void insertRecipe(RecipeVO vo);
	
	//재료 입력
	@Insert("Insert into ingr_recipe values(#{ingredient_id},#{recipe_id},#{quantity})")
	public void insert_RecipeIngr(IngrRecipeVO vo);
	//컨텐츠입력
	@Insert("Insert into recipe_content values(recipe_content_seq.nextval,#{recipe_id},#{step},#{content},#{img_ori},#{img_new})")
	public void insertRecipeContent(RecipeContentVO vo);
	
	@Insert("Insert into recipe_tag(id,recipe_id,name,hit) values(recipe_tag_seq.nextval,#{recipe_id},#{name},0)")
	public void insertRecipeTag(RecipeTagVO vo);
	
	//id 값가져오기
	@Select("select max(id) from recipe")
	public int recipeMId();
	
	@Select("Select id,name,cal from ingredient where name like #{value}||'%'")
	public List<IngredientVO> selectIngr(String value);
	
	@Select("Select count(*) from ingredient where name=#{value}")
	public int selectIngCk(String value);
	@Select("Select id from ingredient where name=#{value}")
	public int selectIngId(String value);
	
	///update 용
	@Select("Select * from recipe where id=#{id}")
	public RecipeVO selectRecipe(int id);
	
	@Select("Select * from cat_sub where id=#{id}")
	public CatSubVO selectCatsub(int id);
	//재료 가져오기
	@Select("Select ingredient_id,quantity,recipe_id,name from ingr_rid where recipe_id=#{id}")
	public List<IngrRecipeVO> selectIngRecipe(int id);
	@Select("Select * from recipe_content where recipe_id=#{id} order by step")
	public List<RecipeContentVO> selectStesCon(int id);
	@Select("Select name from recipe_tag where recipe_id=#{id}")
	public List<String> selectRTag(int id);
	@Update("update recipe set title=#{title},lvl=#{lvl},reqmember=#{reqmember},time=#{time},cat_sub_id=#{cat_sub_id},img_ori=#{img_ori},img_new=#{img_new} where id=#{updateid}")
	public void updateRecipe(RecipeVO recipeVO);
	@Delete("Delete from ingr_recipe where recipe_id=#{id}")
	public void deleteIngrR(int id);
	@Update("Update recipe_content set content=#{content},img_ori=#{img_ori},img_new=#{img_new} where id=#{id}")
	public void updateStep(RecipeContentVO vo);
	//스텝한건 제거
	@Delete("Delete from recipe_content where id=#{id}")
	public void deleteStep(int id);
	
	@Delete("Delset from recipe_tag where recipe_id=#{recipe_id}")
	public void deleteTag(int recipe_id);
	


}



//view 생성
/*
create view Ingr_rid as(
select INGR_RECIPE.INGREDIENT_ID,INGR_RECIPE.QUANTITY,INGR_RECIPE.RECIPE_ID,INGREDIENT.NAME from INGR_RECIPE left OUTER join INGREDIENT 
on INGR_RECIPE.INGREDIENT_ID=INGREDIENT.ID)
*/

