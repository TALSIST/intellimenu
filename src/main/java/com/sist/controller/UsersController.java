package com.sist.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sist.users.UsersDAO;
import com.sist.vo.UsersVO;

@Controller
public class UsersController {
	@Autowired
	private UsersDAO dao;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	   @RequestMapping("main/regist.do")
	   public String user_join(Model model) {
		   model.addAttribute("main_jsp", "member/join.jsp");
		   return "main/main";
	   }
	   
	   @RequestMapping("main/regist_ok.do")
	   public String user_regist_ok(UsersVO vo,Model model) {
		   vo.getEmail();
		   vo.getNickname();
		   vo.getName();
		   vo.getPwd();
		   dao.registUser(vo);
		   model.addAttribute("main_jsp", "member/join_ok.jsp");
		   return "main/main";
	   }
	   
		@RequestMapping("/login")
		public @ResponseBody Map<String, String> login(UsersVO vo, HttpSession session) {
			String reqEmail = vo.getEmail();
			String reqPwd = vo.getPwd();
//			System.out.println(reqEmail+reqPwd);
			Map<String, String> map = new HashMap();
			if (dao.selectUser(reqEmail)!=0) {
				vo = dao.selectUserData(reqEmail);
				if(passwordEncoder.matches(reqPwd ,vo.getPwd())){
					// email & pw 일치하므로 정보를 얻어 로그인정보를 세션에 넣는다
					session.setAttribute("user", vo.getEmail());
					map.put("result", "yes");
					
				} else {
					map.put("result", "no");
				}
			} else {
				map.put("result", "no");
			}
			return map;
		}
}
