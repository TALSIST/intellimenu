package com.sist.recipe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.CatSubVO;
import com.sist.vo.CatTopVO;
import com.sist.vo.RecipeVO;

@Repository
public class CatSubDAO {
	@Autowired
	private CatSubMapper catSubMapper;
	

	
	
	public List<CatSubVO> selectList(int catTopId){	
		
		return catSubMapper.selectList(catTopId);
	}
	public List<CatTopVO> selectTopList(){
		return catSubMapper.selectTopList();
	};
}
