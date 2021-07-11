<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP COMMUNITY</title>
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
	<section class="section section-article-write">
		<div class="container mx-auto">
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
			<form action="../article/doWrite" method = "POST" onsubmit = "ArticleWrite__submit(this); return false;">
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
					<textarea name="body" maxlength="2000" class="textarea textarea-bordered h-60" placeholder="내용을 입력해주세요."></textarea>
				</div>
				<div class="btns">
					<button type = "submit" class="btn btn-ghost">작성</button>
					<button type = "button" class="btn btn-ghost">취소</button>  
				</div>
			</form>
		</div>
	</section>
</body>
</html>