package com.book.model.articlecontent;

public interface ArticleContentService {

	void insertContent(ArticleContentVO vo);

	void updateContent(ArticleContentVO vo);

	ArticleContentVO selectContent(ArticleContentVO vo);
	
	void deleteContent(ArticleContentVO vo);

}