<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
	var catList = new Array();
	
	$(document).on('click', '.input-remove-row', function(added){ 
		
		for (var i=0; i<catList.length; i++) {
			if ($(this).parent().find('.input-cat-data').text()==catList[i].name) {
				catList.splice(i,1);
			}
		}
		catList.push({"id":$(this).parent().find('.input-no').text(),"name":""});
		
		var tr = $(this).closest('tr');
	    tr.fadeOut(200, function() {
	    	tr.remove();
		});
	});
	
	var fillcontent = function(no, cat) {
        var form_data = {};
        form_data["no"] = no;
        form_data["cat-data"] = cat; 
        form_data["remove-row"] = '<span class="glyphicon glyphicon-remove"></span>';
        var row = $('<tr></tr>');
        $.each(form_data, function( type, value ) {
            $('<td class="input-'+type+'"></td>').html(value).appendTo(row);
        });
        $('.preview-table > tbody:last').append(row);
	};
	
	var add_btn = function() {
		var add_btn = $('.category-form input[name="cat-data"]');
    	var name = add_btn.val();
    	
    	if (name.trim().length == 0) {
    		alert("내용을 입력하세요");
    	} else {
	    	catList.push({"id":"","name":name});
			fillcontent(undefined, name);
    	}
    	
		add_btn.val("");
		add_btn.focus();
	};
	
	// onLoad
	$(function() {
		<c:forEach var="vo" items="${list}">
			fillcontent('<c:out value="${vo.id}"/>', '<c:out value="${vo.name}"/>');
		</c:forEach>
		$('#list-area').show();
		
		$('.ingr-cat').each(function() {
			if ($(this).val() == '<c:out value="${cat}"/>') {
				$(this).parent().addClass("active");
			}
		});
		$('.ingr-cat').parent().click(function() {
			location.href="?cat="+$(this).children('input').val();
		});
		
	    $('.preview-add-button').click(function() {
	    	add_btn();
	    });
	    
		$('#cat-data').keypress(function(e) {
			if (e.keyCode == '13') {
				add_btn();
			}
		});
	    
	    $("#cat-data-submit").click(function() {
	    	if (!confirm("삭제를 실행하면 관련된 자료가 모두 사라질 수 있습니다.\n진행하시겠습니까?")) {
	    		return;
	    	}
	    	var json = {"cat":'<c:out value="${cat}"/>',"list":catList};
	    	console.log(JSON.stringify(json));
	    	$.ajax({
	    		type : "POST",
	    		contentType : "application/json; charset=UTF-8",
	    		url : "/admin/ingredient/category/mod",
	    		data : JSON.stringify(json),
	    		success : function(response) {
	    			if (response.result=="y") {
	    				alert("변경내용이 저장되었습니다");
	    				location.href="/admin/ingredient/category?cat=${cat}";
	    			} else {
	    				alert("수정 실패! - 삭제하려는 자료 중 연결된 데이터가 있습니다.");
	    			}
	    		}
	    	});
	    });
	    
	});
</script>

<div class="row text-center">
	<h1>재료 분류 관리</h1>
</div>
<div class="row" id="list-area" style="display: none;">
       <div class="col-sm-12">
       	<div class="btn-group" data-toggle="buttons">
               <label class="btn btn-default">
                   <input type="radio" value="religion" class="ingr-cat">종교분류 관리
               </label>
               <label class="btn btn-default">
                   <input type="radio" value="vegeterian" class="ingr-cat">채식분류 관리
               </label>&nbsp;
          	</div>
       </div>
       <div class="col-xs-12" style="height:30px;"></div>
       <!-- 정보입력 -->
       <div class="col-sm-5">
           <h4>정보 입력:</h4>
           <div class="panel panel-default">
               <div class="panel-body form-horizontal category-form">
                   <div class="form-group">
                       <label for="concept" class="col-sm-3 control-label">유형</label>
                       <div class="col-sm-9">
                           <input type="text" class="form-control" id="cat-data" name="cat-data">
                       </div>
                   </div>
                   <div class="form-group">
                       <div class="col-sm-12 text-right">
                           <button type="button" class="btn btn-default preview-add-button">
                               <span class="glyphicon glyphicon-plus"></span>추가
                           </button>
                       </div>
                   </div>
               </div>
           </div>            
       </div>
       <div class="col-sm-7">
           <h4>전체 목록:</h4>
           <div class="row">
               <div class="col-xs-12">
                   <div class="table-responsive">
                       <table class="table preview-table">
                           <thead>
                               <tr>
                                   <th>번호</th>
                                   <th>이름</th>
                                   <th width="10%">삭제</th>
                               </tr>
                           </thead>
                           <tbody>
						<!-- preview content goes here-->                            
                           </tbody> 
                       </table>
                   </div>                            
               </div>
           </div>
           <div class="row">
               <div class="col-xs-12">
                   <hr style="border:1px dashed #dddddd;">
                   <button type="button" id="cat-data-submit" class="btn btn-primary btn-block">변경사항 저장</button>
               </div>                
           </div>
       </div>
</div>
