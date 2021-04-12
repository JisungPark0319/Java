package com.book.model.articlecontent.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.model.articlecontent.ArticleContentService;
import com.book.model.articlecontent.ArticleContentVO;

@Service("articleContentService")
public class ArticleContentImpl implements ArticleContentService {
	@Autowired
	ArticleContentDAO contentDAO;
	
	@Override
	public void insertContent(ArticleContentVO vo) {
		contentDAO.insertContent(vo);
	}

	@Override
	public ArticleContentVO selectContent(ArticleContentVO vo) {
		return contentDAO.selectContent(vo);
	}

	@Override
	public void updateContent(ArticleContentVO vo) {
		contentDAO.updateContent(vo);
	}
	
	@Override
	public void deleteContent(ArticleContentVO vo) {
		contentDAO.deleteContent(vo);
	}

}
