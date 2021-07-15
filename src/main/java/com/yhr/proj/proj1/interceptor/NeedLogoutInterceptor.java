package com.yhr.proj.proj1.interceptor;

import com.yhr.proj.proj1.http.Rq;

public class NeedLogoutInterceptor extends Interceptor{

	@Override
	public boolean runBeforeAction(Rq rq) {
		return true;
		
	}
}
