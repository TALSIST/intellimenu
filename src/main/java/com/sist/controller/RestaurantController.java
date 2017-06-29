package com.sist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.restaurant.RestaurantDAO;
import com.sist.vo.RestaurantVO;

@Controller
public class RestaurantController {
	@Autowired
	private RestaurantDAO restaurantDAO;

	@RequestMapping("restaurant/restaurant_list")
	public String restaurantList(Model model) {
		List<RestaurantVO> list = restaurantDAO.restaurantListData();
		model.addAttribute("list", list);
		return "restaurant/restaurant_list";
	}


	@RequestMapping("restaurant/restaurant_admin_list")
	public String restaurantAdminList(String page, Model model) {
		if (page == null)
			page = "1";
		int curpage = Integer.parseInt(page);
		Map map = new HashMap();
		int rowSize = 25;
		int start = (rowSize * curpage) - (rowSize - 1);
		// 1=> 1~10 , 2=> 11~20
		int end = rowSize * curpage;
		// fromPage=(5*curpage/(5)-(5)) [1][2][3][4][5]
		map.put("start", start);
		map.put("end", end);
		List<RestaurantVO> list = restaurantDAO.restaurantAdminList(map);
		int totalpage = restaurantDAO.restaurantTotalPage(rowSize);
		// totalpage
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("list", list);
		return "restaurant/restaurant_admin_list";
	}
}
