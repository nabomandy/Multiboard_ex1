<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html><head><meta charset="EUC-KR">
<title>게시판 답글 쓰기</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/main.css"></head>
<body><form action="reply" method="post" name="f">
<%-- 원글 정보 :num  ref reflevel  refstep--%>
  <input type="hidden" name="num" value="${board.num}">
  <input type="hidden" name="ref" value="${board.ref}" >
  <input type="hidden" name="reflevel" value="${board.reflevel}" >
  <input type="hidden" name="refstep" value="${board.refstep}" >
  <table><caption>MODEL1 게시판 답글 등록</caption>
  <tr><td>글쓴이</td><td><input type="text" name="name"></td></tr>
  <tr><td>비밀번호</td><td><input type="password" name="pass"></td></tr>
  <tr><td>제목</td><td><input type="text" name="subject" 
      value="RE:${board.subject}"></td></tr>
  <tr><td>내용</td>
      <td><textarea name="content" rows="15"></textarea></td></tr>
  <tr><td colspan="2">
  <a href="javascript:document.f.submit()">[답변글등록]</a></td></tr>    
  </table>
</form>
</body>
</html>