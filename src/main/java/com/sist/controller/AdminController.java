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
		System.out.println("전체게시물"+rDAO.restaurantTotal());
//		System.out.println("�쟾泥닿쾶�떆臾�"+rDao.restaurantTotal());
		int total = rDAO.restaurantTotal();
		Map map = page.calcPage(total);
		List<RestaurantVO> list = rDAO.restaurantAdminList(map);

		model.addAttribute("page", map);
		model.addAttribute("list", list);
		return "admin/restaurant_list";
	}
	
}
