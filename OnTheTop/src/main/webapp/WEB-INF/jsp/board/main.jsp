<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>메인페이지</h1>
	<hr>
	${id}님이 로그인 하셨습니다.
	<br>
	<input type="button" value="로그아웃" onclick="location.href='logout'">
	<br>
	<a href="get">회원정보조회</a>
	<br>
	<a href="update">회원정보수정</a>
	<br>
	<a href="delete">회원정보삭제</a>
	<br>

	<c:if test="${id eq 'admin }">
		<a href="list">전체회원목록</a>
		<br>
	</c:if>

	<a href="../board/add">개사펀 글쓰기</a>
	<br>
	<a href="../board/list">게시판 글목록</a>
	<br>
</body>
</html>