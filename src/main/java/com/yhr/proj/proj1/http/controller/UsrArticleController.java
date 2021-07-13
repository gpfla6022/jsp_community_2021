package com.yhr.proj.proj1.http.controller;

import java.util.List;

import com.yhr.proj.proj1.dto.Article;
import com.yhr.proj.proj1.dto.ResultData;
import com.yhr.proj.proj1.http.Rq;
import com.yhr.proj.proj1.http.service.ArticleService;

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
		case "write":
			actionShowWrite(rq);
			break;
		case "doWrite":
			actionDoWrite(rq);
			break;
		}
		
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
		
		
		if(title.length() == 0) {
			rq.historyBack("제목을 입력해주세요.");
			return;
		}
		
		if(body.length() == 0) {
			rq.historyBack("내용을 입력해주세요.");
			return;
		}
		
		ResultData writeRd = articleService.write(title, body);
		
		rq.printf(writeRd.getMsg());
	}


	private void actionShowWrite(Rq rq) {
		rq.jsp("usr/article/write");
		
	}

}
