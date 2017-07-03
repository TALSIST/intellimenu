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

import com.sist.users.UsersService;
import com.sist.vo.UsersVO;

@Controller
public class UsersController {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UsersService uSVC;

	@RequestMapping("/regist")
	public String user_join(Model model) {
		return "users/users_regist";
	}

	@RequestMapping("/regist/apply")
	public String user_regist_apply(UsersVO vo, Model model) {
		String pwd = vo.getPwd();
		vo.setPwd(passwordEncoder.encode(pwd));
		uSVC.registUser(vo);
		// TODO: 가입성공여부 반환
		return "";
	}

	@RequestMapping("/login")
	public @ResponseBody Map<String, String> login(UsersVO vo, HttpSession session) {
		String reqEmail = vo.getEmail();
		String reqPwd = vo.getPwd();
		Map<String, String> result = new HashMap();
		if (uSVC.selectUser(reqEmail)!=0) {
			vo = uSVC.selectUserData(reqEmail);
			if(passwordEncoder.matches(reqPwd ,vo.getPwd())) {
				vo.setPwd("");
				session.setAttribute("user", vo);
				result.put("result", "y");
			} else {
				result.put("result", "n");
			}
		} else {
			result.put("result", "n");
		}
		return result;
	}

	@RequestMapping("/logout")
	public @ResponseBody Map<String,String> logout(HttpSession session) {
		Map<String,String> result = new HashMap();
		if (!session.isNew()) {
			session.invalidate();
			result.put("result", "y");
		} else {
			result.put("result", "n");
		}
		return result;
	}

}
