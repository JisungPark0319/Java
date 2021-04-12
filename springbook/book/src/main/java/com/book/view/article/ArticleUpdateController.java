package com.book.view.article;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.book.model.article.ArticleService;
import com.book.model.article.ArticleVO;
import com.book.model.articlecontent.ArticleContentService;
import com.book.model.articlecontent.ArticleContentVO;

@Controller
public class ArticleUpdateController {
	@Autowired
	ArticleService articleService;
	
	@Autowired
	ArticleContentService contentService;

	@RequestMapping(value="/article/modify.do", method=RequestMethod.GET)
	public String ArticleUpdateForm(@RequestParam(value="title", defaultValue="") String title,
			@RequestParam(value="content", defaultValue="") String content,
			@RequestParam(value="no", defaultValue="") int no,
			Model model) {
		System.out.println("[ArticleUpdaetController] ===> ArticleUpdaetForm : " + no + ", " + title + ", " + content);
		ModifyRequest modRequest = new ModifyRequest();
		modRequest.setArticleNumber(no);
		modRequest.setTitle(title);
		modRequest.setContent(content);
		
		model.addAttribute("modReq", modRequest);
		
		return "modifyForm.jsp";
	}
	
	@RequestMapping(value="/article/modify.do", method=RequestMethod.POST)
	public String ArticleUpdate(ModifyRequest modReq, Model model) {
		System.out.println("[ArticleUpdaetController] ===> ArticleUpdaete : " + modReq);
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		
		if(modReq.getTitle() == null || modReq.getTitle().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
		model.addAttribute("errors", errors);
		if(!errors.isEmpty()) {
			return "modifyForm.jsp";
		}
		
		ArticleVO article = new ArticleVO();
		ArticleContentVO content = new ArticleContentVO();
		
		article.setNumber(modReq.getArticleNumber());
		article.setTitle(modReq.getTitle());
		
		content.setNumber(modReq.getArticleNumber());
		content.setContent(modReq.getContent());
		
		articleService.updateArticle(article);
		contentService.updateContent(content);
		
		return "modifySuccess.jsp";
	}
}
