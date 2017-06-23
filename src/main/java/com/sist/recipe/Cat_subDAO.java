package com.sist.recipe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.Cat_subVO;

@Repository
public class Cat_subDAO {
	@Autowired
	private Cat_subMapper cat_subMapper;
	
	
	public List<Cat_subVO> select_list(int cat_top_id){	
		
		System.out.println("cat_top_id = "+cat_top_id);
		
		List<Cat_subVO> list= cat_subMapper.select_list(cat_top_id);
		
		return list;
	}
}
