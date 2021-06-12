<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html><html><head><meta charset="EUC-KR"><title>게시물 상세보기</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/main.css">
</head><body>	<table>
		<caption>MODEL1 게시물 상세 보기</caption>
		<tr>	<td width="20%">글쓴이</td>
			<td width="80%" style="text-align: left">${board.name}</td>		</tr>
		<tr>
			<td>제목</td>
			<td style="text-align: left">${board.subject}</td>
		</tr>		<tr>
			<td>내용</td>
			<td>
				<table style="width: 100%; height: 250px;">
					<tr><td
							style="border-width: 0px; vertical-align: top; text-align: left;">
							${board.content}</td></tr>				</table>
			</td>		</tr>		<tr>
			<td>첨부파일</td>
			<td style="text-align: left;">
			<c:if test="${ board.file1 == null || board.file1.trim() eq ''}">
				&nbsp; </c:if> 
		<c:if test="${ board.file1 != null && board.file1.trim() ne ''}">		
	 <a href="<%=request.getContextPath() %>/upfile/${board.file1}">${board.file1}</a>
				</c:if>	</td></tr>		<tr>
			<td colspan="2"><a href="replyForm?num=${num}">[답변]</a> <a
				href="updateForm?num=${num}">[수정]</a> <a
				href="deleteForm?num=${num}">[삭제]</a> <a href="list">[목록]</a>
			</td>		</tr>	</table></body></html>