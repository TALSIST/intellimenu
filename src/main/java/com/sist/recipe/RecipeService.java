package com.sist.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.users.UsersService;
import com.sist.vo.CatSubVO;
import com.sist.vo.IngredientVO;
import com.sist.vo.RecipeContentVO;
import com.sist.vo.RecipeTagVO;
import com.sist.vo.RecipeVO;

@Service
public class RecipeService {
	@Autowired
	private RecipeMapper recipeDAO;
	@Autowired
	private RecipeMapper recipeMapper;
	@Autowired
	private UsersService usersService; 
	@Autowired
	private IngredientMapper ingrMapper;
	@Autowired
	private CatSubDAO catSubDAO;
	
	public int selectIngrTotal() {
		return ingrMapper.selectIngrTotal();
	}
	
	public List<IngredientVO> selectIngrList(Map map) {
		return ingrMapper.selectIngrList(map);
	}
	
	// 재료 입력 삭제
	@Transactional
	public void insertIngr(IngredientVO vo) {
		Map<String, String> map = new HashMap();
		int result = ingrMapper.insertIngr(vo);
		for (int i : vo.getSeason()) {
			Map season = new HashMap();
			season.put("ingr_id", vo.getId());
			season.put("month", i);
			ingrMapper.insertIngrSeason(season);
		}
		if (result!=0) {
			map.put("result", "y");
		} else {
			map.put("result", "n");
		}
	}
	
	// 재료 삭제
	@Transactional
	public void deleteIngredient(List<String> list) {
		for (String id : list) {
			Map map = new HashMap();
			map.put("id", Integer.parseInt(id));
			ingrMapper.deleteIngredient(map);
		}
	}
	
	//============================== 조건걸린 재료 정보 조회 ==============================//
	public List<CatSubVO> getingrCatData(String cat) {
		List<CatSubVO> result = null;
		Map<String, String> map = new HashMap();
		switch (cat) {
		case "religion":
			map.put("tablename", "religion");
			result = ingrMapper.selectCatInfo(map);
			break;
		
		case "vegeterian":
			map.put("tablename", "vegeterian");
			result = ingrMapper.selectCatInfo(map);
			break;
		
		case "season":
			result = new ArrayList();
			for (int i=1; i<=12; i++) {
				CatSubVO vo = new CatSubVO();
				vo.setId(i);
				vo.setName(Integer.toString(i)+"월");
				result.add(vo);
			}
			break;
		}
		return result;
	}
	
	public List<CatSubVO> selectCatInfo(Map map) {
		return ingrMapper.selectCatInfo(map);
	}
	
	public int selectIngrExistTotal(Map map) {
		return ingrMapper.selectIngrExistTotal(map);
	}
	
	public List<IngredientVO> selectIngrExistList(Map map) {
		return ingrMapper.selectIngrExistList(map);
	}
	
	public int selectIngrNotExistTotal(Map map) {
		return ingrMapper.selectIngrNotExistTotal(map);
	}
	
	public List<IngredientVO> selectIngrNotExistList(Map map) {
		return ingrMapper.selectIngrNotExistList(map);
	}
	
	//============================== 재료 정보 조건 변경 ==============================//
	@Transactional
	public void insertIngrAttribute(Map map) {
		for (String id : (List<String>) map.get("list")) {
			map.put("ingredient_id", Integer.parseInt(id));
			switch (map.get("top").toString()) {
			case "religion":
				ingrMapper.insertIngrReligion(map);
				break;
			case "vegeterian":
				ingrMapper.insertIngrVegeterian(map);
				break;
			case "season":
				ingrMapper.insertIngrSeason(map);
				break;
			}
		}
	}
	
	@Transactional
	public void deleteIngrAttribute(Map map) {
		for (String id : (List<String>) map.get("list")) {
			map.put("ingredient_id", Integer.parseInt(id));
			switch (map.get("top").toString()) {
			case "religion":
				ingrMapper.deleteIngrReligion(map);
				break;
			case "vegeterian":
				ingrMapper.deleteIngrVegeterian(map);
				break;
			case "season":
				ingrMapper.deleteIngrSeason(map);
				break;
			}
		}
	}
	
	//============================== 카테고리 입력,삭제 ==============================//
	@Transactional
	public void modifyCatInfo(String cat, List<String> insert, List<Integer> delete) {
		if (cat.equals("religion")) {
			for (String name : insert) {
				ingrMapper.insertReligion(name);
			}
			for (int id : delete) {
				ingrMapper.deleteReligion(id);
				ingrMapper.deleteReligionIngrLinked(id);
			}
			
		} else if (cat.equals("vegeterian")) {
			for (String name : insert) {
				ingrMapper.insertVegeterian(name);
			}
			for (int id : delete) {
				ingrMapper.deleteVegeterian(id);
				ingrMapper.deleteVegeterianIngrLinked(id);
			}
		}
		
	}
	
	//============================== 재료 리스트 검색 관련 ==============================//
	public int selectSearchIngrTotal(Map map) {
		return ingrMapper.selectSearchIngrTotal(map);
	}
	
	public List<IngredientVO> selectSearchIngrList(Map map){
		return ingrMapper.selectSearchIngrList(map);
	}
	
	//============================== 조건걸린 재료 검색 ==============================//
	public int selectSearchIngrExistTotal(Map map) {
		return ingrMapper.selectSearchIngrExistTotal(map);
	}
	
	public List<IngredientVO> selectSearchIngrExistList(Map map) {
		return ingrMapper.selectSearchIngrExistList(map);
	}
	
	public int selectSearchIngrNotExistTotal(Map map) {
		return ingrMapper.selectSearchIngrNotExistTotal(map);
	}
	
	public List<IngredientVO> selectSearchIngrNotExistList(Map map) {
		return ingrMapper.selectSearchIngrNotExistList(map);
	}
	
	
	//============================== 태그 전체 조회 ==============================//
	public int recipeTagTotal() {
		return recipeMapper.recipeTagTotal();
	}
	
	public List<RecipeTagVO> recipeTagList(Map map) {
		return recipeMapper.recipeTagList(map);
	}
	
	
	// 레시피 상세 정보 조회
	public RecipeVO recipeDetail(int recipe_id){
		//조회수 증가
		recipeDAO.recipeHitIncrease(recipe_id);
		
		RecipeVO recipeVO=recipeDAO.recipeDetail(recipe_id);
		recipeVO.setImgAuto();
		recipeVO.setNickname(usersService.selectNickName(recipeVO.getUser_id()));
		recipeVO.setSubCategoryName(catSubDAO.selectCatSubName(recipeVO.getCat_sub_id()));
		List<RecipeContentVO> contentList=recipeDAO.recipeDetailContent(recipe_id);
		for (RecipeContentVO vo : contentList) {			
			vo.setImgAuto();			
		}
		recipeVO.setContentList(contentList);
		recipeVO.setIngredientList(recipeDAO.IngrRecipeJoin(recipe_id));
		recipeVO.setTagList(recipeDAO.recipeTagSelectListByRecipeId(recipe_id));
		
		return recipeVO;
	}
	
}
