package com.sist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.recipe.RecipeDAO;
import com.sist.vo.FavoriteVO;
import com.sist.vo.UsersVO;

@RestController
@RequestMapping("/favorite/*")
public class FavoriteRestController {

	@Autowired
	private RecipeDAO dao;

	// 스크랩추가하기
	@RequestMapping(value = "favorite_insert", method = RequestMethod.POST)
	public int favorite_insert(@RequestBody FavoriteVO vo, HttpSession session) {
		
	
		int count = 0;
		try {
			UsersVO user = (UsersVO) session.getAttribute("user");
			vo.setUser_id(user.getId());
			
			System.out.println(user+"이건유저아이디");
			System.out.println(user.getId()+"이건유저아이디");

			// 기존 스크랩 유무 검사
			Map map = new HashMap();
			map.put("user_id", user.getId());
			map.put("recipe_id", vo.getRecipe_id());

			// 스크랩 중복체크
			count = dao.countFavorite(map);

			if (count == 0) {
				// 스크랩 인서트
				dao.favoriteInsert(vo);

			}

		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return count;
	}
	//스크랩 삭제

	
}
