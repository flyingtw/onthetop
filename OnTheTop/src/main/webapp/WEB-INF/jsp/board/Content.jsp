<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>글 내용보기</h1>
	<hr>
	<table border="1">
		<tr>
			<th>글번호</th>
			<td>${board.num}</td>
			<th>조회수</th>
			<td>${board.readcount}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.name}</td>
			<th>작성일</th>
			<td><fmt:formatDate value="${board.reg_date}"
					pattern="yyyy-MM-dd" /></td>
		</tr>
		<tr>
			<th>글제목</th>
			<td colspan="3">${board.subject}</td>
		</tr>
		<tr>
			<th>파일</th>
			<td colspan="3"><a href="upload/${board.filename}">${board.filename}</a>
				<img src="upload/${board.filename}" width="50" height="50"></td>
		</tr>
		<tr>
			<th>글내용</th>
			<td colspan="3"><pre>${board.num}</pre></td>
		</tr>
		<tr>
			<td colspan="4"><input type="button" value="글 수정"
				onclick="location.href='update?num=${board.num}&pageNum=${param.pageNum}'">
				<input type="button" value="글 삭제"
				onclick="location.href='delete?num=${board.num}&pageNum=${param.pageNum}'">
				<input type="button" value="답글쓰기"
				onclick="location.href='reAdd?re_ref=${board.re_ref}&re_lev=${board.re_lev}&re_seq=${board.re_seq}&pageNum=${param.pageNum}'">
				<input type="button" value="글 목록"
				onclick="location.href='list?pageNum=${param.pageNum}'"></td>
		</tr>
	</table>
</body>
</html>