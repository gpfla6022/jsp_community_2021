package com.yhr.proj.proj1.http.controller;

import java.util.List;

import com.yhr.proj.proj1.container.Container;
import com.yhr.proj.proj1.dto.Member;
import com.yhr.proj.proj1.dto.ResultData;
import com.yhr.proj.proj1.http.Rq;
import com.yhr.proj.proj1.service.MemberService;
import com.yhr.proj.proj1.util.Ut;


public class UsrMemberController extends Controller {
	private MemberService memberService;
	
	public UsrMemberController() {
		memberService = Container.getMemberService();
	}

	@Override
	public void performAction(Rq rq) {
		switch (rq.getActionMethodName()) {
		case "login":
			actionShowLogin(rq);
			break;
		case "doLogin":
			actionDoLogin(rq);
			break;
		case "doLogout":
			actionDoLogout(rq);
			break;
		default:
			rq.println("존재하지 않는 페이지 입니다.");
			break;
		}

	}


	private void actionDoLogout(Rq rq) {
		
		rq.removeSessionAttr("loginedMemberJson");
		rq.replace(null, "../article/list");
	}

	private void actionDoLogin(Rq rq) {
		String loginId = rq.getParam("loginId", "");
		String loginPw = rq.getParam("loginPw", "");

		if (loginId.length() == 0) {
			rq.historyBack("아이디를 입력해주세요.");
			return;
		}

		if (loginPw.length() == 0) {
			rq.historyBack("비밀번호를 입력해주세요.");
			return;

		}

		ResultData loginRd = memberService.login(loginId, loginPw);

		if (loginRd.isFail()) {
			rq.historyBack(loginRd.getMsg());
		}
		
		Member member = (Member)loginRd.getBody().get("member");
		
		rq.setSessionAttr("loginedMemberJson", Ut.toJson(member));
		rq.replace(loginRd.getMsg(), "../article/list");
	}

	private void actionShowLogin(Rq rq) {
		rq.jsp("usr/member/login");
	}
	

}
