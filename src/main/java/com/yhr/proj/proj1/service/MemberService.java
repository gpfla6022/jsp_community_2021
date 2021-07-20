package com.yhr.proj.proj1.service;

import com.yhr.proj.proj1.container.Container;
import com.yhr.proj.proj1.dto.Member;
import com.yhr.proj.proj1.dto.ResultData;
import com.yhr.proj.proj1.repository.MemberRepository;
import com.yhr.proj.proj1.util.Ut;

public class MemberService {
	
	private MemberRepository memberRepository;
	
	public MemberService() {
		
		memberRepository = Container.getMemberRepository();
	}
	

	public ResultData login(String loginId, String loginPw) {
		
		Member member = memberRepository.getMemberByLoginId(loginId);

		if (member == null) {
			return ResultData.from("F-1", "존재하지 않는 회원의 로그인아이디 입니다.");
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			return ResultData.from("F-2", "비밀번호가 일치하지 않습니다.");
		}

		return ResultData.from("S-1", "환영합니다.", "member", member);
	}


	public ResultData join(String loginId, String loginPw, String name, String nickname, String email, String cellphoneNo) {
		
		int member = memberRepository.join(loginId, loginPw, name, nickname, email, cellphoneNo);
		
		return ResultData.from("S-1", Ut.f("%S님 환영합니다.", nickname), "member", member);
		
		
		
	}



}
