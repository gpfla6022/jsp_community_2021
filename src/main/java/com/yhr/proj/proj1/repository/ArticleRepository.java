package com.yhr.proj.proj1.repository;

import java.util.List;

import com.yhr.mysqliutil.MysqlUtil;
import com.yhr.mysqliutil.SecSql;
import com.yhr.proj.proj1.dto.Article;

public class ArticleRepository {

	public int write(int boardId, int memberId, String title, String body) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO article");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", boardId = ?", boardId);
		sql.append(", memberId = ?", memberId);
		sql.append(", title = ?", title);
		sql.append(", body = ?", body);

		int id = MysqlUtil.insert(sql);

		return id;
	}

	public List<Article> getForPrintArticles(String searchKeywordTypeCode, String searchKeyword, int limitFrom,
			int limitTake) {
		SecSql sql = new SecSql();
		sql.append("SELECT A.*");
		sql.append(", IFNULL(M.nickname, '탈퇴한회원') AS extra__writerName");
		sql.append("FROM article AS A");
		sql.append("LEFT JOIN member M");
		sql.append("ON A.memberId = M.id");
		sql.append("WHERE 1");

		if (searchKeyword != null && searchKeyword.length() > 0) {
			switch (searchKeywordTypeCode) {
			case "body":
				sql.append("AND A.body LIKE CONCAT('%', ?, '%')", searchKeyword);
				break;
			case "title":
			default:
				sql.append("AND A.title LIKE CONCAT('%', ?, '%')", searchKeyword);
				break;
			}
		}

		sql.append("ORDER BY id DESC");
		sql.append("LIMIT ?, ?", limitFrom, limitTake);

		return MysqlUtil.selectRows(sql, Article.class);
	}

	public Article getForPrintArticleById(int id) {
		SecSql sql = new SecSql();
		sql.append("SELECT A.*");
		sql.append("FROM article AS A");
		sql.append("WHERE id = ?", id);

		return MysqlUtil.selectRow(sql, Article.class);
	}

	public int delete(int id) {
		SecSql sql = new SecSql();
		sql.append("DELETE FROM article");
		sql.append("WHERE id = ?", id);

		return MysqlUtil.delete(sql);

	}

	public int modify(int id, String title, String body) {
		SecSql sql = new SecSql();
		sql.append("UPDATE article");
		sql.append("SET updateDate = now()");

		if (title != null) {
			sql.append(", title = ?", title);
		}

		if (body != null) {
			sql.append(", body = ?", body);
		}

		sql.append("WHERE id = ?", id);

		return MysqlUtil.update(sql);

	}

	public int getArticlesCount(String searchKeywordTypeCode, String searchKeyword) {
		SecSql sql = new SecSql();
		sql.append("SELECT COUNT(*) AS cnt");
		sql.append("FROM article AS A");
		sql.append("WHERE 1");

		if (searchKeyword != null && searchKeyword.length() > 0) {
			switch (searchKeywordTypeCode) {
			case "body":
				sql.append("AND A.body LIKE CONCAT('%', ?, '%')", searchKeyword);
				break;
			case "title":
			default:
				sql.append("AND A.title LIKE CONCAT('%', ?, '%')", searchKeyword);
				break;
			}
		}

		return MysqlUtil.selectRowIntValue(sql);
	}
}

