package com.book.model.article.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.model.article.ArticleService;
import com.book.model.article.ArticleVO;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	ArticleDAO articleDAO;

	@Override
	public int selectCountArticleList() {
		return articleDAO.selectCountArticleList();
	}

	@Override
	public void deleteArticle(ArticleVO vo) {
		articleDAO.deleteArticle(vo);
	}

	@Override
	public void insertArticle(ArticleVO vo) {
		articleDAO.insertArticle(vo);
	}

	@Override
	public ArticleVO selectArticle(ArticleVO vo) {
		return articleDAO.selectArticle(vo);
	}

	@Override
	public List<ArticleVO> selectArticleList(int startRow, int endRow) {
		return articleDAO.selectArticleList(startRow, endRow);
	}

	@Override
	public void updaetArticleReadCount(ArticleVO vo) {
		articleDAO.updaetArticleReadCount(vo);
	}

	@Override
	public void updateArticle(ArticleVO vo) {
		articleDAO.updateArticle(vo);
	}
	
	@Override
	public int selectArticleNo() {
		return articleDAO.selectArticleNo();
	}
}
