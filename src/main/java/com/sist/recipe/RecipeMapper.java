package com.sist.recipe;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.FavoriteVO;
import com.sist.vo.IngrRecipeVO;
import com.sist.vo.IngredientVO;
import com.sist.vo.RecipeVO;
import com.sist.vo.RecipeContentVO;
import com.sist.vo.RecipeTagVO;

public interface RecipeMapper {
	
	// 전체 레시피 수 가져오기
	@Select("SELECT COUNT(*) FROM recipe")
	public int recipeTotal();
	
	//전체 recipe리스트 가져오기
	@Select("SELECT Y.*"
			+ " FROM ("
				+ " SELECT X.*, rownum as num"
				+ " FROM ("
					+ " SELECT id, title, img_ori, img_new, hit"
					+ "	FROM recipe"
					+ " ORDER BY id desc) X) Y"
			+ " WHERE num BETWEEN #{start} and #{end}")
	public List<RecipeVO> recipeList(Map map);
	
	// sub_cat별 레시피 수 가져오기
	@Select("SELECT COUNT(*) FROM recipe WHERE cat_sub_id=#{cat_sub_id}")
	public int recipeCatSubTotal(int cat_sub_id);
	
	//cat_sub_id로 recipe리스트 가져오기
	@Select("SELECT id, title, img_ori, img_new, hit, num"
			+ " FROM ( SELECT id, title, img_ori, img_new, hit, rownum as num"
			+ " FROM ( SELECT id, title, img_ori, img_new, hit"
			+ "	FROM recipe"
			+ " WHERE cat_sub_id=#{cat_sub_id} ORDER BY id desc))"
			+ " WHERE num BETWEEN #{start} and #{end}")
	public List<RecipeVO> catSubRecipeListData(Map map);
	
	@Insert("Insert into recipe(ID,USER_ID,CAT_SUB_ID) values  ")
	public void insertRecipe(RecipeVO vo);
	
	//총페이지수 가져오기
	@Select("SELECT CEIL(COUNT(*)/9) FROM recipe WHERE cat_sub_id=#{cat_sub_id}")
	public int catSubRecipeListTotalPage(int cat_sub_id);
	
	//id로 특정 recipe정보 가져오기
	@Select("SELECT * FROM recipe WHERE id=#{id}")
	public RecipeVO recipeDetail(int id);
	
	//id로 특정 recipe의 content정보(recipe) 가져오기
	@Select("Select * FROM recipe_content WHERE recipe_id=#{recipe_id}")
	public List<RecipeContentVO> recipeDetailContent(int recipe_id);
	
	@Select("Select ingredient.ID AS id, ingredient.NAME AS name, ingredient.CAL AS cal, ingr_recipe.QUANTITY AS quantity"
			+ " From ingredient, ingr_recipe"
			+ " Where ingredient.id=ingr_recipe.INGREDIENT_ID AND recipe_id=#{recipe_id}")
	public List<IngredientVO> IngrRecipeJoin(int recipe_id);
	
	@Select("SELECT * FROM recipe_tag WHERE recipe_id=#{recipe_id}")
	public List<RecipeTagVO> recipeTagSelectListByRecipeId(int recipe_id);
	
	@Select(" SELECT id, RECIPE_ID, NAME, hit, rownum AS num"
			+ " From (SELECT * FROM recipe_tag WHERE name=#{name} ORDER BY RECIPE_ID DESC)"
			+ " WHERE rownum BETWEEN 1 AND 3")
	public List<RecipeTagVO> recipeTagSelectList3ByName(String name);
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//test 유저 
	@Select("select id from users where id=#{user_id}")
	public int getuserId(int user_id);
	
	//스크랩 인설트
	@Insert("insert INTO FAVORITE (ID, USER_ID, RECIPE_ID)"
			+ "VALUES (favorite_seq.nextval,#{user_id},#{recipe_id})")
	public void favoriteInsert(FavoriteVO vo);
	
	//스크랩 유무 여부 
	@Select("select count(*) from favorite where user_id=#{user_id} and recipe_id=#{recipe_id}")
	public int countFavorite(Map map);
	
	//스크랩 목록
	@Select("select img_ori,title,user_id,id,num,favorite_id from(select img_ori,title,user_id,id,rownum as num,favorite_id"
			+ " from(select r.IMG_ori as img_ori,r.TITLE as title,f.USER_ID as user_id,r.id as id,f.ID as favorite_id from RECIPE r,FAVORITE f where f.RECIPE_ID=r.id and f.user_id=#{user_id} ORDER BY f.ID DESC)) "
			+ "where num between #{start} and #{end}")
	public List<RecipeVO> favoriteList(Map map);
	
	//스크랩 총페이지 구하기
	@Select("SELECT CEIL(COUNT(*)/9) FROM favorite WHERE user_id=#{user_id}")
	public int totalFavoritepage(int user_id);
	
	//스크랩 삭제
	@Delete("delete from favorite where id=#{id}")
	public int favoriteDelete(int id);
}
