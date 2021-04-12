package com.book.model.articlecontent.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.book.model.articlecontent.ArticleContentService;
import com.book.model.articlecontent.ArticleContentVO;

@Repository
public class ArticleContentDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final String CONTENT_INSERT = "INSERT INTO ARTICLE_CONTENT VALUES(?, ?)";
	private static final String CONTENT_UPDATE = "UPDATE ARTICLE_CONTENT SET CONTENT = ? WHERE ARTICLE_NO = ?";
	private static final String CONTENT_SELECT = "SELECT * FROM ARTICLE_CONTENT WHERE ARTICLE_NO = ?";
	private static final String CONTENT_DELETE = "DELETE FROM ARTICLE_CONTENT WHERE ARTICLE_NO = ?";
	
	public void insertContent(ArticleContentVO vo) {
		jdbcTemplate.update(CONTENT_INSERT, vo.getNumber(), vo.getContent());
	}
	
	public void updateContent(ArticleContentVO vo) {
		jdbcTemplate.update(CONTENT_UPDATE, vo.getContent(), vo.getNumber());
	}
	
	public ArticleContentVO selectContent(ArticleContentVO vo) {
		Object[] args = { vo.getNumber() };
		return jdbcTemplate.queryForObject(CONTENT_SELECT, args, new ContentRowMapper());
	}
	
	public void deleteContent(ArticleContentVO vo) {
		jdbcTemplate.update(CONTENT_DELETE, vo.getNumber());
	}
}

class ContentRowMapper implements RowMapper<ArticleContentVO> {
	@Override
	public ArticleContentVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ArticleContentVO vo = new ArticleContentVO();
		vo.setNumber(rs.getInt("article_no"));
		vo.setContent(rs.getString("content"));
		return vo;
	}
}
