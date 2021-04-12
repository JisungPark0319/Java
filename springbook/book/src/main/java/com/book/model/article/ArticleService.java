package com.book.model.article;

import java.util.List;

public interface ArticleService {

	void insertArticle(ArticleVO vo);

	void updateArticle(ArticleVO vo);

	void updaetArticleReadCount(ArticleVO vo);

	void deleteArticle(ArticleVO vo);

	List<ArticleVO> selectArticleList(int startRow, int endRow);

	ArticleVO selectArticle(ArticleVO vo);

	int selectCountArticleList();

	int selectArticleNo();
}