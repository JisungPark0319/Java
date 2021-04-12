package com.book.view.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.book.model.article.ArticleService;
import com.book.model.article.ArticleVO;
import com.book.model.articlecontent.ArticleContentService;
import com.book.model.articlecontent.ArticleContentVO;

@Controller
public class ArticleDeleteController {
	@Autowired
	ArticleService articleService;
	@Autowired
	ArticleContentService contentService;
	
	@RequestMapping(value="/article/delete.do")
	public String deleteArticle(@RequestParam(value = "no", defaultValue = "") String no) {
		if(no == "") {
			return "article/list.do";
		}
		
		int number = Integer.parseInt(no);
		ArticleVO article = new ArticleVO();
		ArticleContentVO content = new ArticleContentVO();
		
		article.setNumber(number);
		content.setNumber(number);
		
		articleService.deleteArticle(article);
		contentService.deleteContent(content);
		
		return "deleteSuccess.jsp";
	}

}
