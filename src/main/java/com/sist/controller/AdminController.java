package com.sist.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.restaurant.RestaurantDAO;
import com.sist.util.PagingManager;
import com.sist.vo.RestaurantVO;

@Controller
public class AdminController {
	@Autowired
	private RestaurantDAO rDAO;
	
	@RequestMapping("admin/restaurant_list")
	public String restaurantAdminList(PagingManager page, Model model) {
<<<<<<< HEAD
		System.out.println("��ü�Խù�"+rDao.restaurantTotal());
		int total = rDao.restaurantTotal();
=======
//		System.out.println("전체게시물"+rDao.restaurantTotal());
		int total = rDAO.restaurantTotal();
>>>>>>> 0d420a7347bd8795c8718010cefeb5f014438736
		Map map = page.calcPage(total);
		List<RestaurantVO> list = rDAO.restaurantAdminList(map);

		model.addAttribute("page", map);
		model.addAttribute("list", list);
		return "admin/restaurant_list";
	}
	
}
