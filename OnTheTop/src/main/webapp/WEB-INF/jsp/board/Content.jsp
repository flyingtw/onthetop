<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		if (frm.detail.value == '') {
			alert('내용을 입력하세요.');
			frm.content.focus();
			return false;
		}
	}
</script>
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
			<td><fmt:formatDate value="${board.regDate}"
					pattern="yyyy-MM-dd" /></td>
		</tr>
		<tr>
			<th>글제목</th>
			<td colspan="3">${board.subject}</td>
		</tr>
		<tr>
			<th>파일</th>
			<td colspan="3"><c:choose>
					<c:when test="${not empty board.filename}">
						<a href="upload/${board.filename}">${board.filename}</a>
						<img src="upload/${board.filename}" width="50" height="50">
					</c:when>
					<c:otherwise>첨부파일 없음</c:otherwise>
				</c:choose></td>
		</tr>
		<tr>
			<th>글내용</th>
			<td colspan="3"><pre>${board.content}</pre></td>
		</tr>
		<tr>
			<td colspan="4"><input type="button" value="글 수정"
				onclick="location.href='update?num=${board.num}&pageNum=${param.pageNum}'">
				<input type="button" value="글 삭제"
				onclick="location.href='delete?num=${board.num}&pageNum=${param.pageNum}'">
				<%-- <input type="button" value="답글쓰기"
				onclick="location.href='reAdd?re_ref=${board.re_ref}&re_lev=${board.re_lev}&re_seq=${board.re_seq}&pageNum=${param.pageNum}'"> --%>
				<input type="button" value="글 목록"
				onclick="location.href='list?pageNum=${param.pageNum}'"></td>
		</tr>
	</table>
	<br>
	<div>
		<ol>
			<c:forEach var="re" items="${reply}">
				<li>
					<p>작성자: ${re.name}</p>
					<p>
						작성날짜:
						<fmt:formatDate value="${re.regDate}" pattern="yyyy-MM-dd" />
					</p>

					<p>${re.detail}</p>

					<p>
						<input type="button" value="댓글 삭제"
							onclick="location.href='reDelete?reNum=${re.reNum}&pageNum=${param.pageNum}'">
					</p>
				</li>
			</c:forEach>
		</ol>
	</div>
	<form action="reAdd" method="post" onsubmit="return check()" name="frm">
		<input type="hidden" id="boardNum" name="boardNum"
			value="${board.num}" />
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
				<th>내용</th>
				<td><input type="text" name="detail"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="글쓰기"></td>
			</tr>
		</table>
	</form>
</body>
</html>