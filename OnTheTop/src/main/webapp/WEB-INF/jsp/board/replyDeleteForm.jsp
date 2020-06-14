<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시판 글 삭제</h1>
	<hr>
	<form action="delete?pageNum=${param.pageNum}" method="post">
		<input type="hidden" name="reNum" value="${param.num}">
		<table border="1">
			<tr>
				<th>패스워드</th>
				<td><input type="password" name="passwd"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="글삭제">
					<!-- <input type="reset" value="초기화"> -->
					<input type="button" value="글목록"
					onclick="location.href='list?pageNum=${param.pageNum}'"></td>
			</tr>
		</table>
	</form>
</body>
</html>