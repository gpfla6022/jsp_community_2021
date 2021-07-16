package com.yhr.proj.proj1.interceptor;

import com.yhr.proj.proj1.dto.Member;
import com.yhr.proj.proj1.http.Rq;
import com.yhr.proj.proj1.util.Ut;

public class BeforeActionInterceptor extends Interceptor{

	@Override
	public boolean runBeforeAction(Rq rq) {
		
		String loginedMemberJson = rq.getSessionAttr("loginedMemberJson", "");

		if (loginedMemberJson.length() > 0) {
			rq.setLogined(true);
			rq.setLoginedMember(Ut.toObjFromJson(loginedMemberJson, Member.class));
			rq.setLoginedMemberId(rq.getLoginedMember().getId());
		}
		
		rq.setAttr("rq", rq);

		return true;
	}

} 
