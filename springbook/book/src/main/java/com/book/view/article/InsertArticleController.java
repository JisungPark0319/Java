package com.book.view.article;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.book.model.article.ArticleService;
import com.book.model.article.ArticleVO;
import com.book.model.article.Writer;
import com.book.model.articlecontent.ArticleContentService;
import com.book.model.articlecontent.ArticleContentVO;
import com.book.model.member.MemberVO;

@Controller
public class InsertArticleController {
	@Autowired
	ArticleService articleService;
	@Autowired
	ArticleContentService contentService;
	
	@RequestMapping(value="/article/write.do", method = RequestMethod.GET)
	public String InsertArticleForm() {
		System.out.println("[InsertArticleController] ===> InsertArticleForm");
		return "newArticleForm.jsp";
	}
	
	@RequestMapping(value="/article/write.do", method = RequestMethod.POST)
	public String InsertArticle(ArticleVO vo, ArticleContentVO content, HttpSession session, Model model) {
		System.out.println("[InsertArticleController] ===> InsertArticle");
		
		MemberVO member = (MemberVO) session.getAttribute("authUser");
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		
		if(vo.getTitle() == null || vo.getTitle().isEmpty()) {
			errors.put("title", Boolean.TRUE);
			model.addAttribute("errors", errors);
			return "newArticleForm.jsp";
		}
		
		Writer writer = new Writer();
		writer.setId(member.getMemberId());
		writer.setName(member.getName());
		vo.setWriter(writer);
		vo.setRegDate(new Date());
		vo.setModifiedDate(new Date());
		articleService.insertArticle(vo);
		content.setNumber(articleService.selectArticleNo());
		contentService.insertContent(content);
		
		return "newArticleSuccess.jsp";
	}
}
