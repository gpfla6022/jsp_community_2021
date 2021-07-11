<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.yhr.proj.proj1.dto.Article" %>
<%
List<Article> articles = (List<Article>)request.getAttribute("articles");
%>
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

			<div class="navbar mb-2 shadow-lg bg-neutral text-neutral-content rounded-box my-4">
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
					<span class="text-lg font-bold"> 게시물 리스트 </span>
				</div>
			</div>
			<div class = "px-4 py-4">
				<% for ( Article article : articles ) { %>
						<div>
							번호 : <%=article.getId()%><br>
							작성 : <%=article.getRegDate()%><br>
							갱신일 : <%=article.getUpdateDate()%><br>
							제목 : <%=article.getTitle()%><br>
							내용 : <%=article.getBody()%><br>
						</div>
						<hr />
					<% } %>		
			</div>
		</div>
	</section>
</body>
</html>