<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>       
<!DOCTYPE html><html><head>
<meta charset="EUC-KR"><title>게시물 목록 보기</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/main.css">
</head><body>
<table>
  <caption>${boardname}</caption>
  <c:if test="${boardcount == 0 }" >
  <tr><td colspan="5">등록된 게시글이 없습니다.</td></tr>
 </c:if>
 <c:if test="${boardcount != 0 }" >
  <tr><td colspan="5" style="text-align:right">글개수:${boardcount}</td></tr>
  <tr><th width="8%">번호</th><th width="50%">제목</th>
      <th width="14%">작성자</th><th width="17%">등록일</th>
      <th width="11%">조회수</th></tr>
<c:forEach   var="b"   items="${list}">
   <tr><td>${boardnum}</td><td style="text-align: left">
   <c:set var="boardnum"  value="${boardnum-1 }" />
   	<c:if test="${b.file1 != null && !b.file1.trim() eq ''}">
      <a href="upfile/${b.file1}" style="text-decoration: none;">@</a>
      </c:if>
    <c:if test="${b.file1 == null || b.file1.trim() eq ''}">
     &nbsp;&nbsp;&nbsp; </c:if>
     
        <c:if test="${b.reflevel > 0}">
	       <c:forEach var="i"  begin="1"  end="${b.reflevel-1 }">
		  
	              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	      
	      </c:forEach>	       
	  └</c:if>   
	  <a href="info?num=${b.num}" >${b.subject}</a>
		</td><td>${b.name}</td>
		<td>
		<c:if test="${b.today}">
		 <fmt:formatDate type = "time" 
         value = "${b.regdate}" />
		</c:if>
		<c:if test="${!b.today}">
		 <fmt:formatDate type = "date" 
         value = "${b.regdate}" />
		</c:if>
		
		
		
		</td>
		<td>${b.readcnt}</td></tr>
		</c:forEach>
		
   <tr><td colspan="5">
   	<c:if test="${startpage <= bottomLine}">[이전] </c:if>
     <c:if test="${startpage > bottomLine}">
      <a href="list?page=${startpage - bottomLine}">[이전]</a></c:if>
      
      <c:forEach  var="a"  begin="${startpage}"   end="${endpage}">
      
      <c:if test="${a==pageNum }">[${a}] </c:if>
       <c:if test="${a!=pageNum }">
        
           <a href="list?page=${a}">[${a}]</a>
         </c:if>
         </c:forEach>
        <c:if test="${endpage >= maxpage}">  [다음]</c:if>
       <c:if test="${endpage < maxpage}">
          <a href="list?page=${startpage + bottomLine}">[다음]</a></c:if>
    </td></tr>  
  
   
</c:if>


  <tr><td colspan="5" style="text-align:right">
     <a href="writeForm">[글쓰기]</a></td></tr>
</table></body></html>		
   
   
    
    

		