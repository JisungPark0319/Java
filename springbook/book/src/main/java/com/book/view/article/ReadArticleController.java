	package com.book.view.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.book.model.article.ArticleService;
import com.book.model.article.ArticleVO;
import com.book.model.articlecontent.ArticleContentService;
import com.book.model.articlecontent.ArticleContentVO;

@Controller
public class ReadArticleController {
	@Autowired
	ArticleService articleService;
	@Autowired
	ArticleContentService contentService;

	@RequestMapping(value = "article/read.do")
	public String ReadArticle(int no, Model model) {
		System.out.println("[ReadArticleController] ===> ReadArticle : " + no);
		
		ArticleData articleData = new ArticleData();
		ArticleVO article = new ArticleVO();
		ArticleContentVO content = new ArticleContentVO();
		article.setNumber(no);
		content.setNumber(no);
		
		articleData.setArticle(articleService.selectArticle(article));
		articleData.setContent(contentService.selectContent(content));
		articleService.updaetArticleReadCount(article);
		model.addAttribute("articleData", articleData);
		return "readArticle.jsp";
	}
}
