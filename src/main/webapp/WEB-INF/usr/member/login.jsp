<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var = "pageTitle" value = "로그인"/>
<%@ include file = "../part/header.jspf"%>
	<section class="section section-member-login px-4">
		<div class="container mx-auto">

			<div class="navbar mb-2 shadow-lg bg-neutral text-neutral-content rounded-box">
				<div class="flex-none">
					<button class="btn btn-square btn-ghost" onclick="history.back();">
						<svg xmlns="http://www.w3.org/2000/svg" fill="none"
							viewBox="0 0 24 24"
							class="inline-block w-6 h-6 stroke-current text-success">            
       						 <path stroke-linecap="round" stroke-linejoin="round"
								stroke-width="2" d="M10 19l-7-7m0 0l7-7m-7 7h18"></path>              
      					</svg>
					</button>
				</div>
				<div class="flex-1 px-2 mx-2">
					<span class="text-lg font-bold">로그인</span>
				</div>
			</div>
			<div class = "px-4 py-4">
				<script>
			    // 제출 버튼을 여러번 눌러도 한번만 제출이 되도록 만듬
 				let MemberLogin__submitDone == false;
				function MemberLogin__submit(form){
					
					if( MemberLogin__submitDone ){
						return;
					}
					
					if( form.title.value.length == 0 ){
						alert("제목을 입력해주세요.");
						form.title.focus();
						
						return;
					}
					
					if( form.body.value.length == 0 ){
						alert("내용을 입력해주세요.");
						form.body.focus();
						
						return;
					}
					form.submit();
					MemberLogin__submitDone = true;
				}
			</script>
			<form action="../member/doLogin" method="POST"
					onsubmit="MemberLogin__submit(this); return false;">
			
					<div class="form-control">
						<label class="label">
							<span class="label-text">아이디</span>
						</label>
						<div>
							<input class="input input-bordered w-full" maxlength="100"
								name="loginId" type="text" placeholder="아이디를 입력해주세요." />
						</div>
					</div>
					<div class="form-control">
						<label class="label">
							<span class="label-text">비밀번호</span>
						</label>
						<div>
							<input class="input input-bordered w-full" maxlength="100"
								name="loginPw" type="password" placeholder="비밀번호를 입력해주세요." />
						</div>
					<div class="btns">
						<button type="submit" class="btn btn-ghost">로그인</button>
					</div>
					</div>
				</form>
			</div>
		</div>
	</section>
<%@ include file = "../part/footer.jspf"%>