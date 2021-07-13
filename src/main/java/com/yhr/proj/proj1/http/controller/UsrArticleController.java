package com.yhr.proj.proj1.http.controller;

import java.util.List;

import com.yhr.proj.proj1.dto.Article;
import com.yhr.proj.proj1.dto.ResultData;
import com.yhr.proj.proj1.http.Rq;
import com.yhr.proj.proj1.service.ArticleService;

public class UsrArticleController extends Controller{
	private ArticleService articleService;
	
	public UsrArticleController(){
		articleService = new ArticleService();
	}
	
	@Override
	public void performAction(Rq rq) {
		switch(rq.getActionMethodName()) {
		case "list":
			actionShowList(rq);
			break;
		case "detail":
			actionShowDetail(rq);
			break;
		case "write":
			actionShowWrite(rq);
			break;
		case "doWrite":
			actionDoWrite(rq);
			break;
		default:
			rq.println("존재하지 않는 페이지 입니다.");
			break;
		}
		
	}


	private void actionShowDetail(Rq rq) {
		
		int id = rq.getIntParam("id", 0);
		
		if(id == 0) {
			rq.historyBack("id를 입력해주세요.");
			return;
		}
		
		Article article = articleService.getForPrintArticleById(id);
		
		
		rq.setAttr("article", article);
		
		rq.jsp("usr/article/detail");
		
	}

	private void actionShowList(Rq rq) {
		List<Article> articles = articleService.getForPrintArticles();
		
		// jsp안에서 "articles"라는 이름으로 articles변수에 접근 할 수 있다.
		rq.setAttr("articles", articles);
		
		rq.jsp("usr/article/list");
	}

	private void actionDoWrite(Rq rq) {
		String title = rq.getParam("title", "");
		String body = rq.getParam("body", "");
		String redirectUri = rq.getParam("redirectUri", "../article/list");
		
		
		if(title.length() == 0) {
			rq.historyBack("제목을 입력해주세요.");
			return;
		}
		
		if(body.length() == 0) {
			rq.historyBack("내용을 입력해주세요.");
			return;
		}
		
		ResultData writeRd = articleService.write(title, body);
		int id = (int)writeRd.getBody().get("id");
		
		redirectUri = redirectUri.replace("[NEW_ID]", id + "");
		
		rq.replace(writeRd.getMsg(), redirectUri);
	}


	private void actionShowWrite(Rq rq) {
		rq.jsp("usr/article/write");
		
	}

}
