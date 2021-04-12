package com.book.model.member;

public interface MemberService {

	MemberVO selectById(MemberVO memberVO);

	void insertMember(MemberVO memberVO);
	
	MemberVO selectLogin(MemberVO memberVO);
	
	void updatePassword(MemberVO memberVO);
}