package com.yhr.proj.proj1.app;

import com.yhr.mysqliutil.MysqlUtil;
import com.yhr.proj.proj1.container.Container;

public class App {
	
	public static boolean isDevMode() {
		// 이 부분을 false로 바꾸면 production 모드 이다.
		return true;
	}
	
	public static void init() {
		// DB 세팅
		MysqlUtil.setDBInfo("localhost", "sky", "blue", "jsp_community");
		MysqlUtil.setDevMode(isDevMode());
		
		// 공용 객체 세팅
		Container.init();
	}

}
