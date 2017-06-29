<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
     <h3>식당 목록</h3>
     <table id="table_content" width=700>
      <tr>
       <td align=left>
        <a href="#insert">식당 추가</a>
       </td>
      </tr>
     </table>
     <table id="table_content" width=700 class="mytable">
       <tr>
        <th width=10%>번호</th>
        <th width=45%>제목</th>
        <th width=15%>종류</th>
        <th width=20%>작성일</th>
        <th width=10%>조회수</th>
       </tr>
       <c:forEach var="vo" items="${list }">
         <tr class="dataTr">
          <td width=10% align=center>${vo.id }</td>
          <td width=45%>
           <a href="res_detail?id=${vo.id }">
            ${vo.name }</a>
          </td>
          <td width=15% align=center>${vo.category }</td>
          <td width=20% align=center>
           <fmt:formatDate value="${vo.regdate }" 
            pattern="yyyy-MM-dd"
           />
          </td>
          <td width=10% align=center>${vo.hit }</td>
         </tr>
       </c:forEach>
     </table>
     <table border=0 width=700>
       <tr>
        <td align=center>
        <c:if test="${curpage>1}">
        	<a href="/restaurant/restaurant_admin_list?page=${curpage-1 }">다음</a>
        	</c:if>
        <c:if test="${curpage<totalpage}">
       	 <a href="/restaurant/restaurant_admin_list?page=${curpage+1 }">이전</a>
        	</c:if>
        </td>
       </tr>
     </table>
   </center>
</body>
</html>