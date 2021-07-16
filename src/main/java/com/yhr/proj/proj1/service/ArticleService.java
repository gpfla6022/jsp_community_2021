package com.yhr.proj.proj1.service;

import java.util.List;

import com.yhr.proj.proj1.container.Container;
import com.yhr.proj.proj1.dto.Article;
import com.yhr.proj.proj1.dto.ResultData;
import com.yhr.proj.proj1.repository.ArticleRepository;
import com.yhr.proj.proj1.util.Ut;

public class ArticleService {
	private ArticleRepository articleRepository;
	
	public ArticleService() {
		
		articleRepository = Container.getArticleRepository();
		
	}

	public ResultData write(int boardId, int memberId, String title, String body) {

		int id = articleRepository.write(boardId, memberId, title, body);

		return ResultData.from("S-1", Ut.f("%d번 게시물이 생성되었습니다.", id), "id", id);

	}

	public List<Article> getForPrintArticles() {
		return articleRepository.getForPrintArticles();
	}

	public Article getForPrintArticleById(int id) {
		return articleRepository.getForPrintArticleById(id);
	}

	public ResultData delete(int id) {
		articleRepository.delete(id);

		return ResultData.from("S-1", Ut.f("%d번 게시물이 삭제되었습니다.", id), "id", id);

	}

	public ResultData modify(int id, String title, String body) {
		articleRepository.modify(id, title, body);

		return ResultData.from("S-1", Ut.f("%d번 게시물이 수정되었습니다.", id), "id", id);
	}

}
