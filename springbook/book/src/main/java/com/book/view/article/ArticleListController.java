package com.book.view.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.book.model.article.ArticleService;
import com.book.model.article.ArticleVO;

@Controller
public class ArticleListController {
	@Autowired
	ArticleService articleService;
	
	private int size = 10; 
	
	@RequestMapping(value="/article/list.do")
	public String getArticleList(Model modle, @RequestParam(value="pageNo", defaultValue = "0") int pageNo) {
		System.out.println("[ArticleListController] ===> getArticleList : " + pageNo);
		if(pageNo == 0) {
			pageNo = 1;
		}
		int total = articleService.selectCountArticleList();
		List<ArticleVO> articleList = articleService.selectArticleList((pageNo-1)*size, size);
		
		ArticlePage articlePage = new ArticlePage(total, pageNo, size, articleList);
		
		modle.addAttribute("articlePage", articlePage);
		
		return "listArticle.jsp";
	}
}
