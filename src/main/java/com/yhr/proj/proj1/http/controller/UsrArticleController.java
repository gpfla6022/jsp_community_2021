package com.yhr.proj.proj1.http.controller;

import com.yhr.proj.proj1.http.Rq;

public class UsrArticleController extends Controller{

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
		
		rq.printf("title : %s<br>", title);
		rq.printf("body : %s<br>", body);
	}


	private void actionShowWrite(Rq rq) {
		rq.jsp("usr/article/write");
		
	}

}
