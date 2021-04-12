package com.book.view.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.book.model.member.MemberService;
import com.book.model.member.MemberVO;

@Controller
public class ChangePasswordController {

	@Autowired
	MemberService memberService;

	@RequestMapping(value = "/changePwd.do", method = RequestMethod.GET)
	public String ChangePasswordForm() {
		System.out.println("[ChangePasswordController] ===> ChangePasswordForm");
		return "views/changePwdForm.jsp";
	}

	@RequestMapping(value = "/changePwd.do", method = RequestMethod.POST)
	public String ChangePassword(HttpSession session, Model model,
			@RequestParam(value = "curPwd", defaultValue = "") String curPwd,
			@RequestParam(value = "newPwd", defaultValue = "") String newPwd) {
		System.out.println("[ChangePasswordController] ===> ChangePassword : " + curPwd + ", " + newPwd);
		
		MemberVO member = (MemberVO) session.getAttribute("authUser");

		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		if (curPwd == "") {
			errors.put("curPwd", Boolean.TRUE);
		}
		if (newPwd == "") {
			errors.put("newPwd", Boolean.TRUE);
		}
		if (!member.getPassword().equals(curPwd)) {
			errors.put("badCurPwd", Boolean.TRUE);
		}
		
		model.addAttribute("errors", errors);
		if (!errors.isEmpty()) {
			return "views/changePwdForm.jsp";
		}
		
		member.setPassword(newPwd);
		memberService.updatePassword(member);
		
		session.invalidate();

		return "views/changePwdSuccess.jsp";
	}
}
