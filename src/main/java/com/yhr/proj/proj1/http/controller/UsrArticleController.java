package com.yhr.proj.proj1.http.controller;

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
		case "write":
			actionShowWrite(rq);
			break;
		case "doWrite":
			actionDoWrite(rq);
			break;
		}
		
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
