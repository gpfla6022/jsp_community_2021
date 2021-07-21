package com.yhr.proj.proj1.service;

import java.util.List;

import com.yhr.proj.proj1.container.Container;
import com.yhr.proj.proj1.dto.Article;
import com.yhr.proj.proj1.dto.Member;
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

	public List<Article> getForPrintArticles(Member user, String searchKeywordTypeCode, String searchKeyword, int itemsCountInAPage, int page) {
		
		int limitFrom = (page - 1) * itemsCountInAPage;
		int limitTake = itemsCountInAPage;
		
		List<Article> articles = articleRepository.getForPrintArticles(searchKeywordTypeCode, searchKeyword, limitFrom, limitTake);
		
		for(Article article : articles) {
			updateForPrintData(user, article);
		}
		return articles;
	}

	public Article getForPrintArticleById(Member user, int id) {
		Article article = articleRepository.getForPrintArticleById(id);
		
		updateForPrintData(user, article);
		
		return article;
	}

	private void updateForPrintData(Member user, Article article) {
		
		if(user == null) {
			return;
		}
		
		boolean userCanModify = userCanModify(user, article).isSuccess();
		boolean userCanDelete = userCanDelete(user, article).isSuccess();
		
		article.setExtra__userCanModify(userCanModify);
		article.setExtra__userCanDelete(userCanDelete);
	}

	public ResultData delete(int id) {
		articleRepository.delete(id);

		return ResultData.from("S-1", Ut.f("%d번 게시물이 삭제되었습니다.", id), "id", id);

	}

	public ResultData modify(int id, String title, String body) {
		articleRepository.modify(id, title, body);

		return ResultData.from("S-1", Ut.f("%d번 게시물이 수정되었습니다.", id), "id", id);
	}

	public ResultData userCanModify(Member member, Article article) {
		int memberId = member.getId();
		int writerMemberId = article.getMemberId();
		// memberId(회원 번호)와 writerMemberId(글의 작성자의 번호)의 번호를 가지고 온다. 
		// 같으면 수정할 수 있게 해준다.
		
		if (memberId != writerMemberId) {
			return ResultData.from("F-1", "권한이 없습니다."); 
					
		}
		
		return ResultData.from("S-1", "수정이 가능합니다."); 
	}

	public ResultData userCanDelete(Member member, Article article) {
		int memberId = member.getId();
		int writerMemberId = article.getMemberId();
		
		if (memberId != writerMemberId) {
			return ResultData.from("F-1", "권한이 없습니다."); 
					
		}
		
		return ResultData.from("S-1", "삭제가 가능합니다."); 
	}

	public int getArticlesCount(String searchKeywordTypeCode, String searchKeyword) {
		return articleRepository.getArticlesCount(searchKeywordTypeCode, searchKeyword);
	}

}
