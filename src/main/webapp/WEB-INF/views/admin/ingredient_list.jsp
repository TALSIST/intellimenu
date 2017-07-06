<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
	$(function() {
		var attrVal = '${attr}';
		var catTopVal = '${top}';
		var catSubVal = '${sub}';
		
		// 속성 여부 전환
		$('.cat-attr').each(function() {
			if ($(this).val() == attrVal) {
				$(this).parent().addClass("active");
			}
		});
		$('.cat-attr').parent().click(function() {
			attrVal=$(this).children('input').val();
		});
		
		// 상위 분류 로드
		var topHtml = [
			{"name":"재료 : 종교","value":"religion"},
			{"name":"재료 : 채식","value":"vegeterian"},
			{"name":"재료 : 제철","value":"season"}
		];
		var topSelect = '<option class="cat_top_opt" value="all">전체</option>';
		for (var i=0; i<topHtml.length; i++) {
			if (topHtml[i].value==catTopVal && catSubVal!='') {
				topSelect += '<option class="cat_top_opt" value="'+topHtml[i].value+'">'+topHtml[i].name+'</option>';
			} else if (catSubVal=='') {
				topSelect += '<option class="cat_top_opt" value="'+topHtml[i].value+'">'+topHtml[i].name+'</option>';
			}
		}
		$('#cat_top').html(topSelect);
		$('#cat_top').selectpicker('refresh');
		
		// 하위분류 로드
		$('#cat_top').on("change", function() {
			catTopVal = $(this).val();
			if (catTopVal==="all") {
				location.href='/admin/ingredient/list';
				return;
			}
			$.ajax({
				type : "post",
				url : "/admin/ingredient/catdata",
				data : {"cat":catTopVal},
				success : function(resp) {
					var result = '';
					for (var i=0; i<resp.length; i++) {
						if (resp[i].id=='${sub}' && '${sub}'!='') {
							result += '<option class="cat_sub_opt" value="'+resp[i].id+'">';
							result += resp[i].name;
							result += '</option>';
							catSubVal = resp[i].id;
							break;
						} else if ('${sub}'=='') {
							result += '<option class="cat_sub_opt" value="'+resp[i].id+'">';
							result += resp[i].name;
							result += '</option>';
							catSubVal = resp[0].id;
						}
					}
					$('#cat_sub').html(result);
					$('#cat_sub').selectpicker('refresh');
					// 속성 출력 여부에 따라 다른 버튼을 보여준다
					if (attrVal=='y'&& '${sub}'!='') {
						$('#attr_remove').show();
					} else if (attrVal=='n'&& '${sub}'!='') {
						$('#attr_add').show();
					}
				}
			});
		});
		
		$('#cat_sub').change(function() {
			catSubVal = $(this).val();
			console.log("catsub"+catSubVal);
		});
		
		var chkCount = function() {
			var values = $('input:checkbox:checked.chk-list').map(function () {
				  return this.value;
				}).get();
			return values;
		}
		
		// 속성 부여 버튼
		$('#attr_add').click(function() {
			var values = chkCount();
			if (values.length===0) {
				alert("대상을 선택해 주세요");
				return;
			}
			$.ajax({
				type : "post",
				url : "/admin/ingredient/addattr",
				data : {"list":values.toString(),"top":catTopVal,"sub":catSubVal},
				success : function(resp) {
					if (resp.result==="y") {
						location.reload();
					} else {
						alert("속성 추가 실패");
					}
				}
			});
		});
		
		// 속성 삭제 버튼
		$('#attr_remove').click(function() {
			var values = chkCount();
			if (values.length===0) {
				alert("대상을 선택해 주세요");
				return;
			}
			$.ajax({
				type : "post",
				url : "/admin/ingredient/rmattr",
				data : {"list":values.toString(),"top":catTopVal,"sub":catSubVal},
				success : function(resp) {
					if (resp.result==="y") {
						location.reload();
					} else {
						alert("속성 제거 실패");
					}
				}
			});
		});
		
		// 검색 버튼
		var search = function() {
			var keyword = $('#search-form').val().trim();
			location.href='/admin/ingredient/list?page=1&attr='+attrVal+'&top='+catTopVal+'&sub='+catSubVal+'&keyword='+keyword;
		}
		$('#search-btn').click(function() {
			search();
		});
		$('#search-form').keypress(function(e) {
			if (e.keyCode == '13') { search(); }
		});
		
		// 재료 삭제
		$('#remove').click(function() {
			if (confirm("선택한 재료를 삭제 하시겠습니까?")) {
				var values = chkCount();
				$.ajax({
					type : "post",
					url : "/admin/ingredient/remove",
					data : "list="+values.toString(),
					success : function(resp) {
						if (resp.result==="y") {
							location.reload()
						} else {
							alert("삭제 실패! - 연결되어 있는 데이터가 있습니다");
						}
					}
				});
			}
		});
		
	});
</script>

<div class="row text-center">
	<h1>재료 목록
	<c:if test="${pmgr.keyword!=null}">
		<small>&nbsp;:&nbsp;${pmgr.keyword}&nbsp;검색결과 ${pmgr.total}건</small>
	</c:if></h1>
	<c:if test="${subcatname!=null}">
	<h2><small>&nbsp;${top}&nbsp;:&nbsp;${subcatname}
		
		</small></h2>
	</c:if>
</div>

<!-- 상단툴바 -->
<div class="bootstrap-table">
	<div class="fixed-table-toolbar">
		<div class="columns columns-right btn-group pull-right">
			<button id="search-btn" class="btn btn-primary">
				<i class="glyphicon glyphicon-th-list"></i>출력</button>
		</div>
		<div class="bs-bars pull-right search">
			<input id="search-form" class="form-control" type="text" placeholder="검색어 입력" style="height:36px;">
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
        
		<div class="columns columns-left pull-right">
			<select id="cat_top" class="selectpicker" data-width="100px">
			  <option class="cat_top_opt" value="all">전체</option>
			</select>
			<select id="cat_sub" class="selectpicker" data-width="150px">
			  <option>하위분류</option>
			</select>
		</div>
		
		<div class="bs-bars pull-right">
		    <div class="toolbar btn-group" data-toggle="buttons">
                <label class="btn btn-default">
                    <input type="radio" class="cat-attr" value="y">속성보유
                </label> 
                <label class="btn btn-default">
                    <input type="radio" class="cat-attr" value="n">미보유
                </label>&nbsp;
           	</div>
        </div>
        
        <div class="bs-bars pull-right">
			<div id="toolbar btn-group">
				<!-- 속성 미보유 목록 출력시에만 -->
				<button id="attr_add" class="btn btn-default" style="display:none;">
				<i class="glyphicon glyphicon-plus"></i>속성부여</button>
				<button id="attr_remove" class="btn btn-default" style="display:none;">
				<i class="glyphicon glyphicon-remove"></i>속성삭제</button>
				&nbsp;&nbsp;
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
			<li><a href="?page=${pmgr.prevBtn}&attr=${attr}&top=${top}&sub=${sub}">&laquo;</a></li>
			<c:forEach var="i" begin="${pmgr.startBlock}" end="${pmgr.endBlock}">
				<li <c:if test="${pmgr.page==i}">class="active"</c:if>>
				<a href="?page=${i}&attr=${attr}&top=${top}&sub=${sub}">${i}</a></li>
			</c:forEach>
			<li><a href="?page=${pmgr.nextBtn}&attr=${attr}&top=${top}&sub=${sub}">&raquo;</a></li>
		</ul>
	</div>
</div>
