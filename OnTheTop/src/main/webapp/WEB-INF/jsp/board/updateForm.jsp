<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시판 글수정</h1>
	<hr>
	<form action="update?pageNum=${param.pageNum}" method="post">
		<input type="hidden" name="num" value="${board.num}">
		<table border="1">
			<tr>
				<th>글쓴이</th>
				<td><input type="text" name="name" value="${board.num}"></td>
			</tr>
			<tr>
				<th>패스워드</th>
				<td><input type="password" name="passwd""></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject" value="${board.subject}"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="13" cols="40" name="content">${board.content}</textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="글수정">
					<!-- <input type="reset" value="초기화"> -->
					<input type="button" value="글목록"
					onclick="location.href='list?pageNum=${param.pageNum}'"></td>
			</tr>
		</table>
	</form>
</body>
</html>