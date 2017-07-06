package com.sist.recipe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.vo.CatSubVO;
import com.sist.vo.IngredientVO;

@Service
public class RecipeService {
	@Autowired
	private IngredientMapper ingrMapper;
	
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
	
	@Transactional
	public void deleteIngredient(Map map) {
		ingrMapper.deleteIngredient(map);
	}
	
	//============================== 조건걸린 재료 정보 조회 ==============================//
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
	
}
