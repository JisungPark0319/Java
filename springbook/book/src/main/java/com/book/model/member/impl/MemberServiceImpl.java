package com.book.model.member.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.model.member.MemberService;
import com.book.model.member.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public void insertMember(MemberVO memberVO) {
		memberDAO.insertMember(memberVO);
	}

	@Override
	public MemberVO selectById(MemberVO memberVO) {
		return memberDAO.selectById(memberVO);
	}
	
	@Override
	public MemberVO selectLogin(MemberVO memberVO) {
		return memberDAO.selectLogin(memberVO);
	}
	
	@Override
	public void updatePassword(MemberVO memberVO) {
		memberDAO.updatePassword(memberVO);
	}

}
