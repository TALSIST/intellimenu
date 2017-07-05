<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
	$(function() {
		
		// 속성 여부 전환
		$('.cat-attr').each(function() {
			if ($(this).val() == '${attr}') {
				$(this).parent().addClass("active");
			}
		});
		$('.cat-attr').parent().click(function() {
			location.href="?attr="+$(this).children('input').val();
		});
		
		// 하위분류 로드
		$('#cat_top').change(function() {
			var cat_top = $(this).val();
			$.ajax({
				type : "post",
				url : "/admin/ingredient/catdata",
				data : {"cat":cat_top},
				success : function(resp) {
					for (var x in resp) {
						$('#cat_sub').html();
					}
				}
			});
		});
		
		// 분류 선택 끝나면 검색
		$('').click(function() {
			// 하위분류가 선택되어 있어야 검색한다.
			
		});
		
		// 속성 부여 버튼
		
		// 속성 삭제 버튼
		
		// 검색 버튼
		
	});
</script>

<div class="row text-center">
	<h1>재료 목록</h1>
</div>

<!-- 상단툴바 -->
<div class="bootstrap-table">
	<div class="fixed-table-toolbar">
		<div class="columns columns-right btn-group pull-right">
			<button id="search-btn" class="btn btn-default">
				<i class="glyphicon glyphicon-search"></i> 검색</button>
		</div>
		<div class="pull-right search">
			<input class="form-control" type="text" placeholder="검색어 입력">
		</div>
		<div class="bs-bars pull-left">
			<div id="toolbar btn-group">

				<!-- 추가입력 modal 시작 -->
				<button data-toggle="modal" data-target="#ingr-add" class="btn btn-primary">
				<i class="glyphicon glyphicon-pencil"></i> 추가</button>
				<div class="modal fade" id="ingr-add" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
				  <div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
							<h3 class="modal-title" id="lineModalLabel">재료 추가</h3>
						</div>
						
						<div class="modal-body">
							<!-- 데이터처리 -->
							<form method="post" action="/admin/ingredient/add">
				              <div class="form-group">
				                <label>재료이름</label>
				                <input type="text" class="form-control" name="name" placeholder="재료이름">
				              </div>
				              <div class="form-group">
				                <label>재료열량</label>
				                <input type="text" class="form-control" name="cal" placeholder="재료열량">
				              </div>
				              <div class="form-group">
				                <label>제철 선택</label>
				                <div>
				               		<input type="checkbox" name="season" value="1">1월&nbsp;
				               		<input type="checkbox" name="season" value="2">2월&nbsp;
				               		<input type="checkbox" name="season" value="3">3월&nbsp;
				               		<input type="checkbox" name="season" value="4">4월&nbsp;
				               		<input type="checkbox" name="season" value="5">5월&nbsp;
				               		<input type="checkbox" name="season" value="6">6월&nbsp;
				               		<input type="checkbox" name="season" value="7">7월&nbsp;
				               		<input type="checkbox" name="season" value="8">8월&nbsp;
				               		<input type="checkbox" name="season" value="9">9월&nbsp;
				               		<input type="checkbox" name="season" value="10">10월&nbsp;
				               		<input type="checkbox" name="season" value="11">11월&nbsp;
				               		<input type="checkbox" name="season" value="12">12월
				                </div>
				              </div>
				              <button type="submit" class="btn btn-default">입력</button>
				            </form>
						</div>
						
						<div class="modal-footer">
							<div class="btn-group" role="group">
								<button type="button" class="btn btn-default" data-dismiss="modal" role="button">닫기</button>
							</div>
						</div>
					</div>
				  </div>
				</div>
				<!-- modal 종료 -->
				
				<button id="remove" class="btn btn-danger">
				<i class="glyphicon glyphicon-remove"></i> 삭제</button>&nbsp;
			</div>
		</div>
		<div class="bs-bars pull-left">
		    <div class="toolbar btn-group" data-toggle="buttons">
                <label class="btn btn-default">
                    <input type="radio" class="cat-attr" value="y">속성보유
                </label> 
                <label class="btn btn-default">
                    <input type="radio" class="cat-attr" value="n">미보유
                </label>&nbsp;
           	</div>
        </div>
        
		<div class="columns columns-left pull-left">
			<select id="cat_top" class="selectpicker" data-width="100px">
			  <option class="cat_top_opt" value="all">전체</option>
			  <option class="cat_top_opt" value="religion">재료 : 종교</option>
			  <option class="cat_top_opt" value="vegeterian">재료 : 채식</option>
			  <option class="cat_top_opt" value="season">재료 : 제철</option>
			</select>
			<select id="cat_sub" class="selectpicker" data-width="150px">
			  <option>하위분류</option>
			</select>
		</div>
		<div class="bs-bars pull-left">
			<div id="toolbar btn-group">
				<button id="remove" class="btn btn-primary">
				<i class="glyphicon glyphicon-th-list"></i>출력</button>
				
				<!-- 속성 미보유 목록 출력시에만 -->
		    	<c:if test="${attr=='n'}">
				<button id="remove" class="btn btn-default">
				<i class="glyphicon glyphicon-plus"></i>속성부여</button>
				</c:if>
				<c:if test="${attr=='y'}">
				<button id="remove" class="btn btn-default">
				<i class="glyphicon glyphicon-remove"></i>속성삭제</button>
				</c:if>
				
			</div>
		</div>
	</div>
</div>

<!-- 본문내용시작 -->
<div class="row">
	<table class="table table-hover">
		<tr class="active">
			<th><input type="checkbox" id="chk-head"></th>
			<th>번호</th>
			<th>재료명</th>
			<th>열량/100g</th>
			<th width="20%">종교</th>
			<th width="20%">채식</th>
			<th width="20%">제철</th>
		</tr>
		<c:forEach var="vo" items="${list}">
		<tr>
			<td><input type="checkbox" class="chk-list" value="${vo.id}"></td>
			<td>${vo.id}</td>
			<td>${vo.name}</td>
			<td>${vo.cal}</td>
			<td>
				<c:forEach var="opt" items="${vo.religion}">
					${opt.name}
				</c:forEach>
			</td>
			<td>
				<c:forEach var="opt" items="${vo.vegeterian}">
					${opt.name}
				</c:forEach>
			</td>
			<td>
				<c:forEach var="opt" items="${vo.season}">
					${opt}
				</c:forEach>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>

<!-- Pagination -->
<div class="fixed-table-pagination" style="display: block;">
	<div class="pull-left pagination-detail">
		<span class="pagination-info">
		<b>${pmgr.start}번</b> 부터 <b>${pmgr.end}번</b> / 전체 게시물 <b>${pmgr.total}건</b>
		</span>
	</div>
	<div class="pull-right pagination">
		<ul class="pagination">
			<li><a href="?cat=${cat}&page=${pmgr.prevBtn}">&laquo;</a></li>
			<c:forEach var="i" begin="${pmgr.startBlock}" end="${pmgr.endBlock}">
				<li <c:if test="${pmgr.page==i}">class="active"</c:if>>
				<a href="?cat=${cat}&page=${i}">${i}</a></li>
			</c:forEach>
			<li><a href="?cat=${cat}&page=${pmgr.nextBtn}">&raquo;</a></li>
		</ul>
	</div>
</div>
