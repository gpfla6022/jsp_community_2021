package com.yhr.proj.proj1.http.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yhr.mysqliutil.MysqlUtil;
import com.yhr.mysqliutil.SecSql;
import com.yhr.proj.proj1.container.Container;
import com.yhr.proj.proj1.http.Rq;
import com.yhr.proj.proj1.http.controller.Controller;
import com.yhr.proj.proj1.http.controller.UsrArticleController;
import com.yhr.proj.proj1.http.controller.UsrMemberController;
import com.yhr.proj.proj1.http.controller.UsrBoardController;
import com.yhr.proj.proj1.interceptor.Interceptor;

@WebServlet("/usr/*")
public class DispatcherServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Rq rq = new Rq(req, resp);

		if (rq.isInvalid()) {
			rq.print("올바른 요청이 아닙니다.");
			return;
		}
		
		if(runInterceptors(rq) == false) {
			return;
		}
		
		Controller controller = getControllerByRq(rq);

		if (controller != null) {

			controller.performAction(rq);
			
			MysqlUtil.closeConnection();
		}else {
			rq.print("올바른 요청이 아닙니다.");
		}
	}

	private Controller getControllerByRq(Rq rq) {
		
		switch (rq.getControllerTypeName()) {
		case "usr":
			switch (rq.getControllerName()) {
			case "article":
				return Container.getUsrArticleController();
			case "member":
				return Container.getUsrMemberController();
			}
			break;
		}
		return null;
	}

	private boolean runInterceptors(Rq rq) {

		if(Container.beforeActionInterceptor.runBeforeAction(rq) == false) {
			return false;
		}
		             
		if(Container.needLoginInterceptor.runBeforeAction(rq) == false) {
			return false;
		}
		
		if(Container.needLogoutInterceptor.runBeforeAction(rq) == false) {
			return false;
		}
		
		return true;
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}