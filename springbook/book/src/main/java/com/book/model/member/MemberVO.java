package com.book.model.member;

import java.util.Date;

public class MemberVO {
	private String memberId;
	private String name;
	private String password;
	private Date regDate;

	public String getMemberId() {
		return memberId;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	

	@Override
	public String toString() {
		return "MemberVO [memberId=" + memberId + ", name=" + name + ", password=" + password + ", regDate=" + regDate
				+ "]";
	}
}
