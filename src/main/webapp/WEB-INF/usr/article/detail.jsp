<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="게시물 상세페이지" />
<%@ include file="../part/header.jspf"%>
<section class="section section-article-detail px-4">
	<div class="container mx-auto">

		<div
			class="navbar mb-2 shadow-lg bg-neutral text-neutral-content rounded-box my-4">
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
				<span class="text-lg font-bold"> 게시물 상세페이지 </span>
			</div>
		</div>
		<div class="px-4 py-4">
				<div>
						번호 : ${article.id}
						<br>
						제목 : ${article.title}
						<br>
						작성자 : ${article.memberId}
						<br>
						작성 : ${article.regDate}
						<br>
						갱신일 : ${article.updateDate}
						<br>
						내용 : ${article.body}
						<br>
				</div>
				<div class="btns mt-3">
						<a href = "../article/modify?id=${article.id}" class="btn btn-ghost">
						<span><i class="fas fa-edit"></i></span>
						<span>수정</span>
						</a>
						<a onclick="if(!confirm('정말로 삭제 하시겠습니까?')) return false;" href = "../article/DoDelete?id=${article.id}" class="btn btn-ghost">
						<span><i class="fas fa-trash-alt"></i></span>
						<span>삭제</span>
						</a>
					</div>
				<hr />
		</div>
	</div>
</section>
<%@ include file="../part/footer.jspf"%>