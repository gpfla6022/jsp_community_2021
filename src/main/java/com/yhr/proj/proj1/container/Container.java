package com.yhr.proj.proj1.container;

import com.yhr.proj.proj1.http.controller.UsrArticleController;
import com.yhr.proj.proj1.http.controller.UsrBoardController;
import com.yhr.proj.proj1.http.controller.UsrMemberController;
import com.yhr.proj.proj1.interceptor.BeforeActionInterceptor;
import com.yhr.proj.proj1.interceptor.NeedLoginInterceptor;
import com.yhr.proj.proj1.interceptor.NeedLogoutInterceptor;
import com.yhr.proj.proj1.repository.ArticleRepository;
import com.yhr.proj.proj1.repository.MemberRepository;
import com.yhr.proj.proj1.service.ArticleService;
import com.yhr.proj.proj1.service.MemberService;

import lombok.Getter;

public class Container {
	
	public static BeforeActionInterceptor beforeActionInterceptor;
	public static NeedLoginInterceptor needLoginInterceptor;
	public static NeedLogoutInterceptor needLogoutInterceptor;
	
	@Getter
	private static MemberRepository memberRepository;
	@Getter
	private static ArticleRepository articleRepository;
	
	@Getter
	private static MemberService memberService;
	@Getter
	private static ArticleService articleService;
	
	@Getter
	private static UsrArticleController usrArticleController;
	@Getter
	private static UsrMemberController usrMemberController;

		public static void init() {
			
			memberRepository = new MemberRepository();
			articleRepository = new ArticleRepository();
			
			memberService = new MemberService();
			articleService = new ArticleService();
			
			beforeActionInterceptor = new BeforeActionInterceptor();
			needLoginInterceptor = new NeedLoginInterceptor();
			needLogoutInterceptor = new NeedLogoutInterceptor();

			usrMemberController = new UsrMemberController();
			usrArticleController = new UsrArticleController();
		}
		
	}
