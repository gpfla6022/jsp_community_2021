package com.yhr.proj.proj1.interceptor;

import com.yhr.proj.proj1.http.Rq;

public abstract class Interceptor {

	abstract public boolean runBeforeAction(Rq rq);
	 
}
