package com.book.model.article.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.book.model.article.ArticleVO;
import com.book.model.article.Writer;

@Repository
public class ArticleDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	private static final String ARTICLE_INSERT = "INSERT INTO ARTICLE(WRITER_ID, WRITER_NAME, TITLE, REGDATE, MODDATE, READ_CNT) "
			+ "VALUES(?, ?, ?, ?, ?, 0)";
	private static final String ARTICLE_UPDATE = "UPDATE ARTICLE SET TITLE = ?, MODDATE = NOW() WHERE ARTICLE_NO = ?";
	private static final String ARTICLE_READCOUNT_UPDATE = "UPDATE ARTICLE SET READ_CNT = READ_CNT + 1 WHERE ARTICLE_NO = ?";
	private static final String ARTICLE_DELETE = "DELETE FROM ARTICLE WHERE ARTICLE_NO = ?";
	private static final String ARTICLE_SELECT_LIST = "SELECT * FROM ARTICLE ORDER BY ARTICLE_NO DESC LIMIT ?,?";
	private static final String ARTICLE_SELECT = "SELECT * FROM ARTICLE WHERE ARTICLE_NO = ?";
	private static final String ARTICLE_GET_COUNT = "SELECT COUNT(*) FROM ARTICLE";
	private static final String ARTICLE_GET_NO = "SELECT LAST_INSERT_ID() FROM ARTICLE LIMIT 0, 1";

	public void insertArticle(ArticleVO vo) {
		System.out.println("[ArticleDAO] ===> insertAritlce : " + vo);
		jdbcTemplate.update(ARTICLE_INSERT, vo.getWriter().getId(), vo.getWriter().getName(), vo.getTitle(),
				vo.getRegDate(), vo.getModifiedDate());
	}

	public void updateArticle(ArticleVO vo) {
		System.out.println("[ArticleDAO] ===> updateArticle : " + vo);
		jdbcTemplate.update(ARTICLE_UPDATE, vo.getTitle(), vo.getNumber());
	}

	public void updaetArticleReadCount(ArticleVO vo) {
		System.out.println("[ArticleDAO] ===> updaetArticleReadCount : " +  vo);
		jdbcTemplate.update(ARTICLE_READCOUNT_UPDATE, vo.getNumber());
	}

	public void deleteArticle(ArticleVO vo) {
		System.out.println("[Article] ===> deleteArticle : " + vo);
		jdbcTemplate.update(ARTICLE_DELETE, vo.getNumber());
	}

	public List<ArticleVO> selectArticleList(int startRow, int endRow) {
		System.out.println("[ArticleDAO] ===> selectArticleList : " + startRow +", " + endRow);
		Object[] args = { startRow, endRow };
		return jdbcTemplate.query(ARTICLE_SELECT_LIST, args, new ArticleRowMapper());
	}

	public ArticleVO selectArticle(ArticleVO vo) {
		System.out.println("[ArticleDAO] ===> selectArticle : " + vo);
		Object[] args = { vo.getNumber() };
		return jdbcTemplate.queryForObject(ARTICLE_SELECT, args, new ArticleRowMapper());
	}

	public int selectCountArticleList() {
		System.out.println("[ArticleDAO] ===> countArticleList");
		return jdbcTemplate.queryForObject(ARTICLE_GET_COUNT, Integer.class);
	}
	
	public int selectArticleNo() {
		System.out.println("[ArticleDAO] ===> getArticleNo");
		return jdbcTemplate.queryForObject(ARTICLE_GET_NO, Integer.class);
	}
}

class ArticleRowMapper implements RowMapper<ArticleVO> {
	@Override
	public ArticleVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ArticleVO articleVO = new ArticleVO();
		Writer writer = new Writer();
		articleVO.setNumber(rs.getInt("article_no"));
		writer.setId(rs.getString("writer_id"));
		writer.setName(rs.getString("writer_name"));
		articleVO.setWriter(writer);
		articleVO.setTitle(rs.getString("title"));
		articleVO.setRegDate(rs.getDate("regdate"));
		articleVO.setModifiedDate(rs.getDate("moddate"));
		articleVO.setReadCount(rs.getInt("read_cnt"));
		return articleVO;
	}
}