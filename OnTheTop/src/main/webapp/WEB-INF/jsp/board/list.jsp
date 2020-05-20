<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	width: 90px;
	margin-left: auto;
	margin-right: auto;
}

table .subject {
	width: 400ps;
}

div#PageBlock {
	width: 800px;
	margin-left: auto;
	margin-right: auto;
	text-align: center;
}
</style>
</head>
<body>
	<h1>글목록(전체글: ${pageInfo.count})</h1>
	<hr>
	<h3>
		<a href="add?pageNum=${pageInfo.pageNum}">파일업로드 글쓰기</a>
	</h3>
	<table border="1">
		<tr>
			<th>번호</th>
			<th class="subject">제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>IP</th>
		</tr>
		<c:choose>
			<c:when test="${not empty list}">
				<c:forEach var="board" items="${list}">
					<tr>
						<td>${board.num}</td>
						<td><c:if test="${board.re_lev gt 0}">
								<c:set var="wid" value="${board.re_lev * 10}"></c:set>
								<img src="../images/level.gif"
									style="width: ${wid}px; height:16px;">
								<img src="../images/re.gif">
							</c:if> <a href="detail?num=${board.num}&pageNum=${pageInfo.pageNum}">${board.subject}</a>
						</td>
						<td>${board.name}</td>
						<td><fmt:formatDate value="${board.re_date}"
								pattern="yyyy-MM-dd" /></td>
						<td>${board.readcount}</td>
						<td>${board.ip}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="6">게시판 글 없음</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>

	<c:if test="${pageInfo.count gt 0}">
		<div id="PageBlock">
			<c:if test="${pageInfo.startPage gt pageInfo.pageBlock}">
				<a href="list?pageNum=${pageInfo.startPage-PageInfo.pageBlock}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${pageInfo.startPage}"
				end="${pageInfo.endPage}" step="1">
				<c:choose>
					<c:when test="${i eq pageInfo.pageNum}">
						<a href="list?pageNum=${i}"><b>[${i}]</b></a>
					</c:when>
					<c:otherwise>
						<a href="list?pageNum=${i}">[${i}]</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${pageInfo.endPage lt pageInfo.pageCount}">
				<a href="list?pageNum=${pageInfo.startPage+pageInfo.pageBlock}">[다음]</a>
			</c:if>
		</div>
	</c:if>
</body>
</html>