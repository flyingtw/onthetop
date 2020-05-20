<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function check() {
		if (frm.name.value == '') {
			alert('글쓴이를 입력하세요.');
			frm.name.focus();
			return false;
		}
		if (frm.passwd.value == '') {
			alert('패스워드를 입력하세요.');
			frm.passwd.focus();
			return false;
		}
		if (frm.subject.value == '') {
			alert('제목을 입력하세요.');
			frm.subject.focus();
			return false;
		}
		if (frm.content.value == '') {
			alert('내용을 입력하세요.');
			frm.content.focus();
			return false;
		}
	}
</script>
</head>
<body>
	<h1>게시판 파일 업로드 글쓰기</h1>
	<form action="add?pageNum=${param.pageNum}" method="post"
		enctype="multipart/form-data" onsubmit="return check()" name="frm">
		<table border="1">
			<tr>
				<th>글쓴이</th>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<th>패스워드</th>
				<td><input type="password" name="passwd"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject"></td>
			</tr>
			<tr>
				<th>파일</th>
				<td><input type="file" name="file"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="13" cols="40" name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="글쓰기"> <input
					type="reset" value="초기화"> <input type="button" value="글목록"
					onclick="list?pageNum=${param.pageNum}"></td>
			</tr>
		</table>
	</form>
</body>
</html>