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
	
	   @RequestMapping("regist")
	   public String user_join(Model model) {
		   model.addAttribute("main_jsp", "user/join.jsp");
		   return "";
	   }
	   
	   @RequestMapping("/user/regist_ok")
	   public String user_regist_ok(UsersVO vo,Model model) {
		   //String pwd=passwordEncoder.encode(vo.getPwd());
		   vo.getEmail();
		   vo.getNickname();
		   vo.getName();
		   vo.getPwd();
		   //vo.setPwd(pwd);
		   dao.registUser(vo);
		   model.addAttribute("regist", "regist_ok");
		   return "regist_ok";
	   }
	   @RequestMapping("user/regist")
	   public String regist(){
		   return "user/regist";
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
