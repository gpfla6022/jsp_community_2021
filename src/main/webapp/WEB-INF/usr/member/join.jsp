<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="회원가입" />
<%@ include file="../part/header.jspf"%>
<section class="section section-member-join px-4">
	<div class="container mx-auto">

		<div
			class="navbar mb-2 shadow-lg bg-neutral text-neutral-content rounded-box">
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
				<span class="text-lg font-bold">회원가입</span>
			</div>
		</div>
		<div class="px-4 py-4">
			<script>
			    // 제출 버튼을 여러번 눌러도 한번만 제출이 되도록 만듬
 				let MemberLogin__submitDone == false;
				function MemberLogin__submit(form){
					
					if( MemberLogin__submitDone ){
						return;
					}
					
					if( form.loginId.value.length == 0 ){
						alert("아이디를 입력해주세요.");
						form.loginId.focus();
						
						return;
					}
					
					if( form.loginPw.value.length == 0 ){
						alert("비밀번호를 입력해주세요.");
						form.loginPw.focus();
						
						return;
					}
					if( form.loginPw.value.length == 0 ){
						alert("비밀번호를 입력해주세요.");
						form.loginPw.focus();
						
						return;
					}
					if( form.name.value.length == 0 ){
						alert("이름을 입력해주세요.");
						form.name.focus();
						
						return;
					}
					if( form.email.value.length == 0 ){
						alert("이메일을 입력해주세요.");
						form.email.focus();
						
						return;
					}
					if( form.cellphoneNo.value.length == 0 ){
						alert("전화번호를 입력해주세요.");
						form.cellphoneNo.focus();
						
						return;
					}
					form.submit();
					MemberLogin__submitDone = true;
				}
			</script>
			<form action="../member/doJoin" method="POST"
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
				</div>
				<div class="form-control">
					<label class="label">
						<span class="label-text">이름</span>
					</label>
					<div>
						<input class="input input-bordered w-full" maxlength="100"
							name="name" type="text" placeholder="이름을 입력해주세요." />
					</div>
				</div>
				<div class="form-control">
					<label class="label">
						<span class="label-text">별명</span>
					</label>
					<div>
						<input class="input input-bordered w-full" maxlength="100"
							name="nickname" type="text" placeholder="별명을 입력해주세요." />
					</div>
				</div>
				<div class="form-control">
					<label class="label">
						<span class="label-text">이메일</span>
					</label>
					<div>
						<input class="input input-bordered w-full" maxlength="100"
							name="email" type="text" placeholder="이메일을 입력해주세요." />
					</div>
				</div>
				<div class="form-control">
					<label class="label">
						<span class="label-text">전화번호</span>
					</label>
					<div>
						<input class="input input-bordered w-full" maxlength="100"
							name="cellphoneNo" type="text" placeholder="전화번호를 입력해주세요." />
					</div>
				</div>
				<div class="btns">
					<button type="submit" class="btn btn-ghost">회원가입</button>
				</div>
			</form>
		</div>
	</div>
</section>
<%@ include file="../part/footer.jspf"%>