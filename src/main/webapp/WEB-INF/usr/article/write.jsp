<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var = "pageTitle" value = "게시물 작성"/>
<%@ include file = "../part/header.jspf"%>
	<section class="section section-article-write px-4">
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
					<span class="text-lg font-bold"> 게시물 작성 </span>
				</div>
			</div>
			<div class = "px-4 py-4">
				<script>
			    // 제출 버튼을 여러번 눌러도 한번만 제출이 되도록 만듬
 				let ArticleWrite__submitDone == false;
				function ArticleWrite__submit(form){
					
					if( ArticleWrite__submitDone ){
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
					ArticleWrite__submitDone = true;
				}
			</script>
				<form action="../article/doWrite" method="POST"
					onsubmit="ArticleWrite__submit(this); return false;">
					<input type="hidden" name = "redirectUri" value = "../article/detail?id=[NEW_ID]" />
					<div class="form-control">
						<label class="label">
							<span class="label-text">제목</span>
						</label>
						<div>
							<input class="input input-bordered w-full" maxlength="100"
								name="title" type="text" placeholder="제목을 입력해주세요." />
						</div>
					</div>
					<div class="form-control">
						<label class="label">
							<span class="label-text">내용</span>
						</label>
						<textarea name="body" maxlength="2000"
							class="textarea textarea-bordered h-60" placeholder="내용을 입력해주세요."></textarea>
					</div>
					<div class="btns">
						<button type="submit" class="btn btn-ghost">작성</button>
						<button type="button" class="btn btn-ghost">취소</button>
					</div>
				</form>
			</div>
		</div>
	</section>
<%@ include file = "../part/footer.jspf"%>