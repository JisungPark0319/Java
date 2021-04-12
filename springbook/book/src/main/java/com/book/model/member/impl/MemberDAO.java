package com.book.model.member.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.book.model.member.MemberVO;

@Repository
public class MemberDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String MEMBER_SELECT_ID = "SELECT * FROM MEMBER WHERE MEMBERID = ?";
	private static final String MEMBER_SELECT_ID_PW = "SELECT * FROM MEMBER WHERE MEMBERID = ? AND PASSWORD = ?";
	private static final String MEMBER_INSERT = "INSERT INTO MEMBER VALUES(?,?,?,?)";
	private static final String MEMBER_UPDATE_PASSWORD = "UPDATE MEMBER SET PASSWORD = ? WHERE MEMBERID = ?";
	
	public MemberVO selectById(MemberVO memberVO) {
		System.out.println("[MemberDAO] ===> selectById : " + memberVO.getMemberId());
		Object[] args = { memberVO.getMemberId() };
		try {
			return jdbcTemplate.queryForObject(MEMBER_SELECT_ID, args, new MemberRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public MemberVO selectLogin(MemberVO memberVO) {
		System.out.println("[MemberDAO] ===> selectLogin : " + memberVO.getMemberId() + 
				", " + memberVO.getPassword());
		Object[] args = { memberVO.getMemberId() , memberVO.getPassword()};
		try {
			return jdbcTemplate.queryForObject(MEMBER_SELECT_ID_PW, args, new MemberRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public void insertMember(MemberVO memberVO) {
		System.out.println("[MemberDAO] ===> insertMember : " + memberVO);
		
		jdbcTemplate.update(MEMBER_INSERT, memberVO.getMemberId(), memberVO.getName(), 
				memberVO.getPassword(), new Date());
	}
	
	public void updatePassword(MemberVO memberVO) {
		System.out.println("[MemberDAO] ===> updatePassword : " + memberVO);
		jdbcTemplate.update(MEMBER_UPDATE_PASSWORD, memberVO.getPassword(), memberVO.getMemberId());
	}
}

class MemberRowMapper implements RowMapper<MemberVO> {
	public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberVO memberVO = new MemberVO();
		memberVO.setMemberId(rs.getString("memberId"));
		memberVO.setName(rs.getString("name"));
		memberVO.setPassword(rs.getString("password"));
		memberVO.setRegDate(rs.getDate("regDate"));
		return memberVO;
	}
}
