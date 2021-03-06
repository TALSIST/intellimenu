package com.sist.recipe;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.FavoriteVO;
import com.sist.vo.IngredientVO;
import com.sist.vo.RecipeContentVO;
import com.sist.vo.RecipeTagVO;
import com.sist.vo.RecipeVO;

public interface RecipeMapper {
	
	// 전체 레시피 수 가져오기
	@Select("SELECT COUNT(*) FROM recipe")
	public int recipeTotal();
	
	//전체 recipe리스트 가져오기
	@Select("SELECT * FROM ("
				+ " SELECT X.*, rownum as num FROM ("
					+ " SELECT id, user_id, title, hit, regdate, img_ori, img_new"
					+ "	FROM recipe"
					+ " ORDER BY id desc) X)"
			+ " WHERE num BETWEEN #{start} and #{end}")
	public List<RecipeVO> recipeList(Map map);
	
	
	


	
	
	
	/************************** cat_sub_id로 레시피리스트가져오기  ********************************/	
	// sub_cat별 레시피 수 가져오기
	@Select("SELECT COUNT(*) FROM recipe WHERE cat_sub_id=#{cat_sub_id}")
	public int recipeCatSubTotal(int cat_sub_id);
	
	//총페이지수 가져오기
	@Select("SELECT CEIL(COUNT(*)/9) FROM recipe WHERE cat_sub_id=#{cat_sub_id}")
	public int catSubRecipeListTotalPage(int cat_sub_id);
	
	//cat_sub_id로 recipe리스트 가져오기
	@Select("SELECT * FROM ("
				+ " SELECT X.*, rownum as num FROM ("
					+ " SELECT id, user_id, title, hit, regdate, img_ori, img_new"
					+ "	FROM recipe"
					+ " WHERE cat_sub_id=#{cat_sub_id}"
					+ " ORDER BY id desc) X)"
    			+ " WHERE num BETWEEN #{start} and #{end}")
	public List<RecipeVO> catSubRecipeListData(Map map);
	
	
	
	
	/************************** recipe id로 레시피 상세보기  ********************************/	
	//hit수 증가
	@Update("Update recipe set hit=hit+1 Where id=#{id}")
	public void recipeHitIncrease(int id);
	
	//id로 특정 recipe정보 가져오기
	@Select("SELECT * FROM recipe WHERE id=#{id}")
	public RecipeVO recipeDetail(int id);
	
	//id로 특정 recipe의 content정보(recipe순서) 가져오기
	@Select("Select * FROM recipe_content WHERE recipe_id=#{recipe_id} order by step")
	public List<RecipeContentVO> recipeDetailContent(int recipe_id);
	
	//id로 recipe의 재료정보 가져오기
	@Select("Select ingredient.ID AS id, ingredient.NAME AS name, ingredient.CAL AS cal, ingr_recipe.QUANTITY AS quantity"
			+ " From ingredient, ingr_recipe"
			+ " Where ingredient.id=ingr_recipe.INGREDIENT_ID AND recipe_id=#{recipe_id}")
	public List<IngredientVO> IngrRecipeJoin(int recipe_id);
		
	//id로 recipe의 태그정보 가져오기
	@Select("SELECT * FROM recipe_tag WHERE recipe_id=#{recipe_id}")
	public List<RecipeTagVO> recipeTagSelectListByRecipeId(int recipe_id);
	
	
	
	/*************** 레시피 메인 페이지에 태그이름으로 3개의 추천 레시피리스트가져오기  **********************/	
	@Select(" SELECT id, RECIPE_ID, NAME, hit, rownum AS num"
			+ " From (SELECT * FROM recipe_tag WHERE name=#{name} ORDER BY RECIPE_ID DESC)"
			+ " WHERE rownum BETWEEN 1 AND 3")
	public List<RecipeTagVO> recipeTagSelectList3ByName(String name);
	
	
	
	/************************** 태그이름으로 레시피리스트가져오기  ********************************/
	//hit수 증가
	@Update("Update recipe_tag set hit=hit+1 Where name=#{tagName}")
	public void recipeTagHitIncrease(String tagName);
	
	@Select("SELECT CEIL(COUNT(*)/9)"
			+ " FROM recipe, recipe_tag"
			+ " WHERE recipe.id=recipe_tag.RECIPE_ID AND recipe_tag.NAME=#{tagName}")
	public int recipeTagListTotalPage(String tagName);
	
	@Select("SELECT id, USER_ID, title, hit, IMG_ORI, IMG_NEW, rownum, num"
			+ " FROM(SELECT id, USER_ID, title, hit, img_new, IMG_ori, rownum AS num FROM"
			+ " (SELECT recipe.id AS id, recipe.USER_ID AS USER_ID, recipe.TITLE AS title, img_new, img_ori, recipe.HIT AS hit"
			+ " FROM recipe, recipe_tag"
			+ " WHERE recipe.id=recipe_tag.RECIPE_ID AND recipe_tag.NAME=#{tagName}"
			+ " ORDER BY recipe.id DESC))"
			+ " WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> recipeTagListByTagName(Map map);
	
	
	
	/************************** 태그전체 출력  ********************************/
	@Select("SELECT COUNT(*) FROM recipe_tag")
	public int recipeTagTotal();
	
	@Select("SELECT * FROM ("
				+ " SELECT X.*,rownum as num FROM ("
					+ " SELECT id,recipe_id,name,hit"
					+ " FROM recipe_tag"
					+ " ORDER BY HIT DESC) X)"
			+ " WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeTagVO> recipeTagList(Map map);
	
	
	
	/************************** 재료이름으로 레시피리스트가져오기  ********************************/
	@Select("SELECT COUNT(*)"
			+ " FROM ingredient, INGR_RECIPE, RECIPE"
			+ " WHERE ingredient.ID=ingr_recipe.INGREDIENT_ID AND ingr_recipe.RECIPE_ID=recipe.ID"
			+ " AND ingredient.NAME=#{ingrName}")
	public int recipeIngrListTotal(String ingrName);
	
	@Select("SELECT * "
			+ " FROM (SELECT id, user_id, title, hit, img_ori, img_new, rownum AS num"
			+ " FROM (SELECT DISTINCT recipe.id AS id, recipe.USER_ID AS USER_ID, recipe.TITLE AS title, recipe.HIT AS hit, IMG_ORI, IMG_NEW"
			+ " FROM ingredient, INGR_RECIPE, RECIPE"
			+ " WHERE ingredient.ID=ingr_recipe.INGREDIENT_ID AND ingr_recipe.RECIPE_ID=recipe.ID"
			+ " AND ingredient.NAME like #{ingrName}"
			+ " ORDER BY recipe.ID desc))"
			+ " WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> recipeIngrListByIngrName(Map map);
	

	/*********************************재료이름으로 검색****************************************/
	@Select("SELECT COUNT(*)"
			+ " FROM ingredient, INGR_RECIPE, RECIPE"
			+ " WHERE ingredient.ID=ingr_recipe.INGREDIENT_ID AND ingr_recipe.RECIPE_ID=recipe.ID"
			+ " AND ingredient.NAME like '%'||#{searchKeyword}||'%'")
	public int searchRecipeIngrListTotal(String searchKeyword);
	
	//재료는 정확히 검색하는 것이 좋겠다. 같은재료가 두개 이상으로 잘못 입력되었을경우 두번이상 출력되므로 id를 distinct로!
	@Select("SELECT * "
			+ " FROM (SELECT id, user_id, title, hit, img_ori, img_new, rownum AS num"
			+ " FROM (SELECT DISTINCT recipe.id AS id, recipe.USER_ID AS USER_ID, recipe.TITLE AS title, recipe.HIT AS hit, IMG_ORI, IMG_NEW"
			+ " FROM ingredient, INGR_RECIPE, RECIPE"
			+ " WHERE ingredient.ID=ingr_recipe.INGREDIENT_ID AND ingr_recipe.RECIPE_ID=recipe.ID"
			+ " AND ingredient.NAME=#{searchKeyword}"
			+ " ORDER BY recipe.ID desc))"
			+ " WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> searchRecipeIngrListByIngrName(Map map);
	
	/*********************************레시피 제목으로 검색****************************************/
	@Select("SELECT COUNT(*) FROM RECIPE WHERE title LIKE '%'||#{searchKeyword}||'%'")
	public int searchRecipeListTotal(String searchKeyword);
	
	@Select(" SELECT *"
			+ " From(SELECT id, USER_ID, CAT_SUB_ID, hit, TITLE, IMG_ORI, IMG_NEW, rownum AS num"
			+ " FROM(SELECT * FROM RECIPE"
			+ " WHERE title LIKE '%'||#{searchKeyword}||'%'"
			+ " ORDER BY ID DESC))"
			+ " WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> searchRecipeListByRecipeTitle(Map map);

	
	/******************************     태그이름으로 검색      ************************************/
	@Select("SELECT COUNT(*)"
			+ " FROM recipe, recipe_tag"
			+ " WHERE recipe.id=recipe_tag.RECIPE_ID AND recipe_tag.NAME like '%'||#{searchKeyword}||'%'")
	public int searchRecipeTagListTotal(String searchKeyword);
	
	@Select("SELECT *"
			+ " FROM (SELECT id, USER_ID, title, hit, img_new, IMG_ori, rownum AS num"
			+ " FROM (SELECT DISTINCT recipe.id AS id, recipe.USER_ID AS USER_ID, recipe.TITLE AS title, img_new, img_ori, recipe.HIT AS hit"
			+ " FROM recipe, recipe_tag"
			+ " WHERE recipe.id=recipe_tag.RECIPE_ID AND recipe_tag.NAME like '%'||#{searchKeyword}||'%'"
			+ " ORDER BY recipe.id DESC))"
			+ " WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> searchRecipeTagListByTagName(Map map);
	//recipe id에 distinct를 해야 북어 북어국 태그를 모두 가진 recipe가 중복해서 나오지 않는다.
	
	
	
	/******************************  닉네임으로 레시피 리스트 얻기      ************************************/
	@Select("SELECT COUNT(*) FROM recipe, users"
			+ " WHERE recipe.USER_ID=users.ID"
			+ " AND users.NICKNAME=#{nickname}")
	public int getRecipeListTotalByNick(String nickname);
	
	@Select("SELECT id, USER_ID, title, hit, IMG_ORI, IMG_NEW, rownum, num"
			+ " FROM(SELECT id, USER_ID, title, hit, img_new, IMG_ori, rownum AS num FROM"
			+ " (SELECT recipe.ID AS id, USER_ID, title, hit, img_new, IMG_ori"
			+ " FROM recipe, users"
			+ " WHERE recipe.USER_ID=users.ID"
			+ " AND users.NICKNAME=#{nickname}"
			+ " ORDER BY recipe.ID desc))"
			+ " WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> getRecipeListByNick(Map map);
	
	@Select("Select * From tagNameRank")
	public List<RecipeTagVO> tagNameRankList();
	
	
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
