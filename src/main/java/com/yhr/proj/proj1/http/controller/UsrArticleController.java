package com.yhr.proj.proj1.http.controller;

import java.util.List;

import com.yhr.proj.proj1.dto.Article;
import com.yhr.proj.proj1.dto.ResultData;
import com.yhr.proj.proj1.http.Rq;
import com.yhr.proj.proj1.service.ArticleService;
import com.yhr.proj.proj1.util.Ut;

public class UsrArticleController extends Controller {
	private ArticleService articleService;

	public UsrArticleController() {
		articleService = new ArticleService();
	}

	@Override
	public void performAction(Rq rq) {
		switch (rq.getActionMethodName()) {
		case "list":
			actionShowList(rq);
			break;
		case "detail":
			actionShowDetail(rq);
			break;
		case "write":
			actionShowWrite(rq);
			break;
		case "doWrite":
			actionDoWrite(rq);
			break;
		case "modify":
			actionShowModify(rq);
			break;
		case "doModify":
			actionDoModify(rq);
			break;
		case "doDelete":
			actionDoDelete(rq);
			break;
		default:
			rq.println("존재하지 않는 페이지 입니다.");
			break;
		}

	}

	private void actionDoDelete(Rq rq) {
		int id = rq.getIntParam("id", 0);
		String redirectUri = rq.getParam("redirectUri", "../article/list");

		if (id == 0) {
			rq.historyBack("id를 입력해주세요.");
			return;
		}

		Article article = articleService.getForPrintArticleById(rq.getLoginedMember(), id);
		
		if ( article == null ) {
			rq.historyBack(Ut.f("%d번 게시물이 존재하지 않습니다.", id));
			return;
		}
		
		ResultData userCanDeleteRd =  articleService.userCanDelete(rq.getLoginedMember(), article);
		
		if(userCanDeleteRd.isFail()) {
			rq.historyBack(userCanDeleteRd.getMsg());
			return;
		}
		
		articleService.delete(id);

		rq.replace(Ut.f("%d번 게시물을 삭제하였습니다.", id), redirectUri);
	}

	private void actionShowDetail(Rq rq) {

		int id = rq.getIntParam("id", 0);

		if (id == 0) {
			rq.historyBack("id를 입력해주세요.");
			return;
		}

		Article article = articleService.getForPrintArticleById(rq.getLoginedMember(), id);

		if (article == null) {
			rq.historyBack(Ut.f("%id번 게시물이 존재하지 않습니다.", id));
			return;
		}

		rq.setAttr("article", article);

		rq.jsp("usr/article/detail");

	}

	private void actionShowList(Rq rq) {
		
		int page = rq.getIntParam("page", 1);
		int itemsCountInAPage = 5;
		
		int totalItemsCount = articleService.getArticlesCount();
		List<Article> articles = articleService.getForPrintArticles(rq.getLoginedMember(), itemsCountInAPage, page);

		// jsp안에서 "articles"라는 이름으로 articles변수에 접근 할 수 있다.
		rq.setAttr("page", page);
		rq.setAttr("itemsCountInAPage", itemsCountInAPage);
		rq.setAttr("totalItemsCount", totalItemsCount);
		rq.setAttr("articles", articles);

		rq.jsp("usr/article/list");
	}

	private void actionDoWrite(Rq rq) {
		
		// 로그인된 회원의 정보를 가져다줌
		int memberId = rq.getLoginedMemberId();
		int boardId = 1;//임시구현 // rq.getIntParam("boardId", 0);
		String title = rq.getParam("title", "");
		String body = rq.getParam("body", "");
		String redirectUri = rq.getParam("redirectUri", "../article/list");

		if (title.length() == 0) {
			rq.historyBack("제목을 입력해주세요.");
			return;
		}

		if (body.length() == 0) {
			rq.historyBack("내용을 입력해주세요.");
			return;
		}

		ResultData writeRd = articleService.write(boardId, memberId, title, body);
		int id = (int) writeRd.getBody().get("id");

		redirectUri = redirectUri.replace("[NEW_ID]", id + "");

		rq.replace(writeRd.getMsg(), redirectUri);
	}

	private void actionShowWrite(Rq rq) {
		rq.jsp("usr/article/write");

	}
	
	private void actionDoModify(Rq rq) {
		
		int id = rq.getIntParam("id", 0);
		String title = rq.getParam("title", "");
		String body = rq.getParam("body", "");
		String redirectUri = rq.getParam("redirectUri", Ut.f("../article/detail?id=%d", id));

		if (id == 0) {
			rq.historyBack("id를 입력해주세요.");
			return;
		}
		
		if (title.length() == 0) {
			rq.historyBack("제목을 입력해주세요.");
			return;
		}

		if (body.length() == 0) {
			rq.historyBack("내용을 입력해주세요.");
			return;
		}
		
		Article article = articleService.getForPrintArticleById(rq.getLoginedMember(), id);
		
		if(article == null) {
			rq.historyBack(Ut.f("%id번 게시물이 존재하지 않습니다.", id));
			return;
		}
		
		if(article.getMemberId() == rq.getLoginedMemberId()) {
			rq.historyBack("권한이 없습니다.");
			return;
		}
		
		// 사용자가 수정할 수 있는지
		ResultData userCanModifyRd =  articleService.userCanModify(rq.getLoginedMember(), article);
	
		if(userCanModifyRd.isFail()) {
			rq.historyBack(userCanModifyRd.getMsg());
			return;
		}

		ResultData modifyRd = articleService.modify(id, title, body);

		rq.replace(modifyRd.getMsg(), redirectUri);
	}

	private void actionShowModify(Rq rq) {
		int id = rq.getIntParam("id", 0);

		if (id == 0) {
			rq.historyBack("id를 입력해주세요.");
			return;
		}

		Article article = articleService.getForPrintArticleById(rq.getLoginedMember(), id);
		
		ResultData userCanModifyRd =  articleService.userCanModify(rq.getLoginedMember(), article);
		
		if(userCanModifyRd.isFail()) {
			rq.historyBack(userCanModifyRd.getMsg());
			return;
		}

		if (article == null) {
			rq.historyBack(Ut.f("%id번 게시물이 존재하지 않습니다.", id));
			return;
		}

		rq.setAttr("article", article);

		rq.jsp("usr/article/modify");


	}

}
