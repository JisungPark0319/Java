package com.book.view.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.book.model.member.MemberService;
import com.book.model.member.MemberVO;

@Controller
public class LoginController {
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginView() {
		System.out.println("[LoginController] ===> 로그인 화면 이동");
		return "views/loginForm.jsp";
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(MemberVO vo, Model model, HttpSession session) {
		System.out.println("[LoginController] ===> 로그인 인증...");

		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		
		if(vo.getMemberId() == null || vo.getMemberId().isEmpty()) {
			errors.put("id", Boolean.TRUE);
		}
		
		if(vo.getPassword() == null || vo.getPassword().isEmpty()) {
			errors.put("password", Boolean.TRUE);
		}
		
		model.addAttribute("errors", errors);
			
		if(!errors.isEmpty()) {
			return "views/loginForm.jsp";
		}
		
		MemberVO member = memberService.selectLogin(vo);
		if(member != null) {
			session.setAttribute("authUser", member);
			return "index.jsp";
		} else {
			errors.put("idOrPwNotMatch", Boolean.TRUE);
			return "views/loginForm.jsp";
		}
	}
	
}
