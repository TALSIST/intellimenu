<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function ok(id,addr)
{
	parent.form1.address.value=addr;
	parent.form1.address1.value=id;
}
</script>
</head>
<body>
	<c:if test="${count==0 }">
		<div class="form-group"
			style="min-width: 0px; background-color: white">
			<span>검색결과가 없습니다</span>
			</div>
	</c:if>
	<c:if test="${count>0 }">
		<div class="col-xs-6">
			<label for="inputPassword" class="col-xs-2 control-label"
								style="min-width: 0px;">주소</label>
		</div>
		<c:forEach var="vo" items="${list }">
		<div>
		<span>
		<a href="javascript:ok('${vo.id}','${vo.sigun }')">${vo.sigun}</a>
		</span>
		</div>
		</c:forEach>
	</c:if>
</body>
</html>