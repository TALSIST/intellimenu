package com.sist.users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sist.vo.UsersVO;

public class AdminInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	UsersService userSVC;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (request.getSession().getAttribute("user") != null ) {
			int id = ((UsersVO)request.getSession().getAttribute("user")).getId();
			int isAdmin = userSVC.selectAdmin(id);
			if (isAdmin!=0) { return true; }
		}
		response.sendRedirect("/");
		return false;
//		return true;
	}
}
