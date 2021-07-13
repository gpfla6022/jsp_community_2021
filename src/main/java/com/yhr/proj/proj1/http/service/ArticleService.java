package com.yhr.proj.proj1.http.service;

import java.util.List;

import com.yhr.proj.proj1.dto.Article;
import com.yhr.proj.proj1.dto.ResultData;
import com.yhr.proj.proj1.http.repository.ArticleRepository;
import com.yhr.proj.proj1.util.Ut;

public class ArticleService {
	private ArticleRepository articleRepository;
	
	public ArticleService() {
		articleRepository = new ArticleRepository();
	}
	
	public ResultData write(String title, String body) {
		
		int id = articleRepository.write(title, body);
		
		return ResultData.from("S-1", Ut.f("%d번 게시물이 생성되었습니다.", id), "id", id);
				
	}

	public List<Article> getForPrintArticles() {
		return articleRepository.getForPrintArticles();
	}

	

}
