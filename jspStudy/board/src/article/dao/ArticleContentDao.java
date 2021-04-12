package article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

import article.model.ArticleContent;
import jdbc.JdbcUtil;

public class ArticleContentDao {

		public ArticleContent insert(Connection conn, ArticleContent content) throws SQLException {
			try(PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO ARTICLE_CONTENT(ARTICLE_NO, CONTENT) VALUES(?,?)")) {
				pstmt.setInt(1, content.getNumber());
				pstmt.setString(2, content.getContent());
				int insertCount = pstmt.executeUpdate();
				if(insertCount > 0) {
					return content;
				} else {
					return null;
				}
			}
		}
		
		public ArticleContent selectById(Connection conn, int no) throws SQLException {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = conn.prepareStatement(
						"SELECT * FROM ARTICLE_CONTENT WHERE ARTICLE_NO=?");
				pstmt.setInt(1, no);
				rs = pstmt.executeQuery();
				ArticleContent content = null;
				if(rs.next()) {
					content = new ArticleContent(
							rs.getInt("article_no"), rs.getString("content"));
				}
				return content;
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
		}
		
		public int update(Connection conn, int no, String content) throws SQLException {
			try(PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ARTICLE_CONTENT SET CONTENT=? WHERE ARTICLE_NO=?")) {
				pstmt.setString(1, content);
				pstmt.setInt(2, no);
				return pstmt.executeUpdate();
			}
		}
}
