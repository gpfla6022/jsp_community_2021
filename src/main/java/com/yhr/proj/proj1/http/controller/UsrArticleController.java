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
		
	}


	private void actionShowWrite(Rq rq) {
		rq.jsp("usr/article/write");
		
	}

}
