package com.yhr.proj.proj1.servlet;

import java.io.IOException;
import java.util.List;

import com.yhr.proj.proj1.dto.Article;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yhr.mysqliutil.MysqlUtil;
import com.yhr.mysqliutil.SecSql;
import com.yhr.proj.proj1.dto.Article;

@WebServlet("/usr/article/list")
public class UsrArticleListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 들어오는 파라미터를 UTF-8로 해석
		request.setCharacterEncoding("UTF-8");

		// 서블릿이 HTML 파일을 만들때 UTF-8로 쓰기
		response.setCharacterEncoding("UTF-8");

		// HTML이 UTF-8 형식이라는 것을 브라우저에게 알린다.
		response.setContentType("text/html; charset=UTF-8");

		MysqlUtil.setDBInfo("localhost", "sky", "blue", "jsp_community");

		MysqlUtil.setDevMode(true);
		
		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM article");
		sql.append("ORDER BY id DESC");
		List<Article> articles = MysqlUtil.selectRows(sql, Article.class);

		request.setAttribute("articles", articles);
		
		MysqlUtil.closeConnection();
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/usr/article/list.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
