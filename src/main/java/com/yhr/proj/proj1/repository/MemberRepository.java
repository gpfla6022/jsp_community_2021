package com.yhr.proj.proj1.repository;

import com.yhr.mysqliutil.MysqlUtil;
import com.yhr.mysqliutil.SecSql;
import com.yhr.proj.proj1.dto.Member;

public class MemberRepository {

	public Member getMemberByLoginId(String loginId) {
		SecSql sql = new SecSql();
		sql.append("SELECT M.*");
		sql.append("FROM member AS M");
		sql.append("WHERE M.loginId = ?", loginId);

		return MysqlUtil.selectRow(sql, Member.class); // 객체 -> xml  ->  json -> db  -> xml  ->  json  ->  객체(member.class)
	}

	public int join(String loginId, String loginPw, String name, String nickname, String email, String cellphoneNo) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO member");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", loginId = ?", loginId);
		sql.append(", loginPw = ?", loginPw);
		sql.append(", name = ?", name);
		sql.append(", nickname = ?", nickname);
		sql.append(", email = ?", email);
		sql.append(", cellphoneNo = ?", cellphoneNo);
		sql.append(", authLevel = 3");
		
		int member = MysqlUtil.insert(sql);
		
		return member;
	}
}
