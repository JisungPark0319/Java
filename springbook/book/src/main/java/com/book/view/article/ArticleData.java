package com.book.view.article;

import com.book.model.article.ArticleVO;
import com.book.model.articlecontent.ArticleContentVO;

public class ArticleData {
	private ArticleVO article;
	private ArticleContentVO content;

	public ArticleVO getArticle() {
		return article;
	}

	public void setArticle(ArticleVO article) {
		this.article = article;
	}

	public ArticleContentVO getContent() {
		return content;
	}

	public void setContent(ArticleContentVO content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ArticleData [article=" + article + ", content=" + content + "]";
	}
	

}
