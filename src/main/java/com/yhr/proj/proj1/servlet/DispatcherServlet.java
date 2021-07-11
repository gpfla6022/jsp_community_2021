package com.yhr.proj.proj1.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yhr.mysqliutil.MysqlUtil;
import com.yhr.mysqliutil.SecSql;
import com.yhr.proj.proj1.http.Rq;

@WebServlet("/usr/*")
public class DispatcherServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 들어오는 파라미터를 UTF-8로 해석
		req.setCharacterEncoding("UTF-8");

		// 서블릿이 HTML 파일을 만들때 UTF-8로 쓰기
		resp.setCharacterEncoding("UTF-8");

		// HTML이 UTF-8 형식이라는 것을 브라우저에게 알린다.
		resp.setContentType("text/html; charset=UTF-8");
				
		Rq rq = new Rq(req, resp);
		
		if( rq.isInvalid()) {
			rq.print("올바른 요청이 아닙니다.");
			return;
		}

		rq.println("controllerTypeName : " + rq.getControllerTypeName());
		rq.println("<br>");
		rq.println("controllerName : " + rq.getControllerName());
		rq.println("<br>");
		rq.println("actionMethodName : " + rq.getActionMethodName());
		
		
		MysqlUtil.setDBInfo("localhost", "sky", "blue", "jsp_community");
		MysqlUtil.setDevMode(true);

		MysqlUtil.closeConnection();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}