<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP COMMUNITY</title>

<!--  모바일에서 디자인이 축소되지 않게 하기 위한 코드 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 폰트어썸 -->

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />

<!-- DaysyUI & Tailwind -->

<link
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.1/dist/tailwind.min.css"
	rel="stylesheet" type="text/css" />
<link href="https://cdn.jsdelivr.net/npm/daisyui@1.7.0/dist/full.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resource/font.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resource/common.css" />
</head>
<body>
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
</body>
</html>