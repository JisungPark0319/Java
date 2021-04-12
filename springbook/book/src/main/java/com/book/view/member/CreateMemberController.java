package com.book.view.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.book.model.member.MemberService;
import com.book.model.member.MemberVO;

@Controller
public class CreateMemberController {

	@Autowired
	MemberService memberService;
	
	@RequestMapping(value="/join.do", method=RequestMethod.GET)
	public String createMemberForm(MemberVO vo) {
		System.out.println("[createMember] ===> 회원가입 화면 이동");
		return "views/joinForm.jsp";
	}
	
	@RequestMapping(value="/join.do", method = RequestMethod.POST)
	public String createMember(MemberVO vo, Model model, 
			@RequestParam(value="confirmPassword", defaultValue = "") String confirmPassword) {
		System.out.println("[createMember] ===> 회원가입 중...");
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		
		if(vo.getMemberId() == null || vo.getMemberId().isEmpty()) {
			errors.put("id", Boolean.TRUE);
		} else if(memberService.selectById(vo) != null) {
			errors.put("duplicatedId", Boolean.TRUE);
		}
		if(vo.getName() == null || vo.getName().isEmpty()) {
			errors.put("name", Boolean.TRUE);
		}
		if(vo.getPassword() == null || vo.getPassword().isEmpty()) {
			errors.put("password", Boolean.TRUE);
		}
		if(confirmPassword == null || confirmPassword == "") {
			errors.put("confirmPassword", Boolean.TRUE);
		} else if(!vo.getPassword().equals(confirmPassword)) {
			errors.put("notMatch", Boolean.TRUE);
		}
		
		model.addAttribute("errors", errors);
		
		if(!errors.isEmpty()) {
			return "views/joinForm.jsp";
		}
		
		memberService.insertMember(vo);
		return "views/joinSuccess.jsp";
	}
	
}
