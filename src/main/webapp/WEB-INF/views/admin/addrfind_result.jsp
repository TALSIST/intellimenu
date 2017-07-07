<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function (){
	$('a').click(function(){
		var addr=$(this).attr("param1");
		var id=$(this).attr("param2");
		
		$('#addr').val(addr);
		$('#address1').val(id);
	});
});
</script>
	<hr>
	<c:if test="${count==0 }">
		<div class="form-group"
			style="min-width: 0px; background-color: white">
			<span>검색결과가 없습니다</span>
			</div>
	</c:if>
	<c:if test="${count>0 }">
		<div class="col-xs-6" style="width:600px;">
			<label for="inputPassword" class="col-xs-2 control-label"
								style="width:600px;">주소</label>
		</div>
		<hr>
		<c:forEach var="vo" items="${vo}">
		<div class="col-sm-6" style="width:300px;">
		<span>
			<a data-dismiss="modal" param1="${vo.sigun}" param2="${vo.id}">${vo.sigun}</a>
		</span>
		</div>
		</c:forEach>
	</c:if>