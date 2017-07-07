package com.sist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sist.recipe.RecipeService;
import com.sist.users.UsersService;
import com.sist.vo.CatSubVO;
import com.sist.vo.UsersVO;

@Controller
public class UsersController {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UsersService userSVC;
	@Autowired
	private RecipeService recipeSVC;

	@RequestMapping("/signup")
	public String userSignUp(Model model) {
		return "users/users_regist";
	}
	
	@RequestMapping("/signup/dupchk")
	public @ResponseBody Map<String, String> userSignUpDupchk(String field, String data) {
		Map<String, String> result = new HashMap();
		result.put("field", field);
		result.put("data", data);
		int num = userSVC.selectUserInfoExist(result);
		result.put("result", Integer.toString(num));
		return result;
	}

	@RequestMapping("/signup/apply")
	public @ResponseBody String userSignUpApply(@Valid UsersVO vo, BindingResult binding,
			String admin, HttpSession session) {
		if (admin==null) { admin="n"; }
		StringBuffer result = new StringBuffer();
		try {
			if (binding.hasErrors()) {
				System.out.println(binding.getFieldErrors().size());
				throw new RuntimeException();
			}
			String pwd = vo.getPwd();
			vo.setPwd(passwordEncoder.encode(pwd));
			userSVC.registUser(vo);
			vo.setPwd("");
			session.setAttribute("user", vo);
			result.append("<script>");
			if (admin.equals("y")) {
				result.append("location.href='/admin/users/list';");
			} else {
				result.append("location.href='/signup/after';");
			}
			result.append("</script>");
			
		} catch (Exception e) {
			e.printStackTrace();
			result.append("<script>");
			result.append("alert('입력된 값이 조건에 맞지 않습니다');");
			result.append("history.back();");
			result.append("</script>");
		}
		return result.toString();
	}
	
	@RequestMapping("/signup/after")
	public String userSignUpAfter() {
		return "/users/users_after_regist";
	}
	
	@RequestMapping("/signup/addinfo")
	public String userSignUpAddinfo(Model model) {
		Map<String, String> map = new HashMap();
		map.put("tablename", "religion");
		List<CatSubVO> religion = recipeSVC.selectCatInfo(map);
		map.put("tablename", "vegeterian");
		List<CatSubVO> vegeterian = recipeSVC.selectCatInfo(map);
		
		model.addAttribute("religion", religion);
		model.addAttribute("vegeterian", vegeterian);
		return "users/users_addinfo";
	}
	
	// 추가정보 입력
	@RequestMapping("/signup/addinfo/apply")
	public @ResponseBody String userSignUpAddinfoApply(UsersVO vo, HttpSession session) {
		String result = null;
		try {
			UsersVO user = (UsersVO) session.getAttribute("user");
			vo.setId(user.getId());
			
			if (userSVC.selectUserExtInfoExist(vo.getId())==0) {
				userSVC.registUserAddinfo(vo);
			} else {
				userSVC.updateUserExt(vo);
			}
			result = "<script>"
					+ "alert('추가정보 등록 성공');"
					+ "location.href='/';"
					+ "</script>";
			
		} catch (Exception e) {
			System.out.println("오류발생");
			e.printStackTrace();
			result = "<script>"
					+ "alert('입력에 문제가 발생했습니다');"
					+ "location.href='/';"
					+ "</script>";
		}
		return result;
	}
	
	@RequestMapping("/signout")
	public String userSignOut(int id) {
		
		return "redirect:/";
	}

	@RequestMapping("/login")
	public @ResponseBody Map<String, String> login(UsersVO vo, HttpServletRequest req, HttpSession session) {
		String reqEmail = vo.getEmail();
		String reqPwd = vo.getPwd();
		
		Map<String, String> result = new HashMap();
		if (userSVC.selectUser(reqEmail)!=0) {
			
			vo = userSVC.selectUserData(reqEmail);
			Map log = new HashMap();
			log.put("user_id", vo.getId());
			log.put("ip", req.getRemoteAddr());
			// 이메일이 존재하므로 비밀번호 일치여부 확인
			if(passwordEncoder.matches(reqPwd ,vo.getPwd())) {
				vo.setAdmin(userSVC.selectAdmin(vo.getId()));
				vo.setPwd("");
				session.setAttribute("user", vo);
				result.put("result", "y");
				// 정상 접근 로그
				log.put("status", "success");
				userSVC.insertLoginLog(log);
				
			} else {
				// 이메일은 있으나 비밀번호 불일치
				result.put("result", "n");
				// 비정상 접근 로그
				log.put("status", "fail");
				userSVC.insertLoginLog(log);
			}
			
		} else { result.put("result", "n"); }
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
	
	// 비로그인 경고
	@RequestMapping("/users/alert")
	public @ResponseBody String alert() {
		return "<script>"
				+ "alert('로그인이후 이용가능합니다');"
				+ "history.back(-2);"
				+ "</script>";
	}
	
}
