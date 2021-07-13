package com.yhr.proj.proj1.service;

import com.yhr.proj.proj1.container.Container;
import com.yhr.proj.proj1.dto.Member;
import com.yhr.proj.proj1.dto.ResultData;
import com.yhr.proj.proj1.repository.MemberRepository;

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

		return ResultData.from("S-1", "환영합니다.");
	}
}
