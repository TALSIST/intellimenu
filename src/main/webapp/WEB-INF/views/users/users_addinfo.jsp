<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src='/js/bootstrapvalidator.js'></script>
<script type="text/javascript">
	
	$(document).on("click", ".btn-remove", function() {
		var num = $(this).val();
		console.log(num);
		$('#ingr'+num).remove();
	});

    $(document).ready(function() {
        $('#defaultForm').bootstrapValidator();

        $("input:text").keydown(function(evt) {
        	if (evt.keyCode == 13) return false; });

        //자동완성
        var ingr = 0;
   		var ck = 0;
 		var autoCom = false;
		function addIngr(Ingr,str,num) {
			$('#sorts').append(
			'<div id=\"ingr'+Ingr+'\">'+ 
			'<div class=\"col-lg-6\">'+
			'<input class=\"form-control\"  type=\"text\"  value='+str+' style=\"width:100px\" readonly></div>'+
			'<input type=\"hidden\" name=\"ingrv['+Ingr+']\" value='+num+'>'+
			'<div class=\"col-lg-6\">'+
			'<button  type=\"button\"  class=\"btn btn-default btn-md btn-remove\" value=\"'+Ingr+'">제거</button>'+
			'</div>'
			);
		}
		
 	   	$('#ingr_main').autocomplete({
 	    	//var val=$('#ingr_main').val();
 	      	source : function(request,response ){
 	    	autoCom = true;
 	    	  
			$.ajax({
			      type:'POST',
			      url:"/recipe/geting",
			      data:{"value":request.term},
			      dataType:"json",
			      success:function(json){
			         response($.map(json,function(item){
			            return{
			               label:item.name,
			               value:item.name
			            };
			         }));
			      }
			});
			},
			focus: function( event, ui ) {
				return false;
				},
			change: function (event, ui) {
			    if(!ui.item){ $(event.target).val(""); }
			    },
			autoFocus:true
			});
 	
 	  	 $('#ingrAddBtn').click(function() {
			str=$('#ingr_main').val();
			$.ajax({  
				type:'POST',
			    url:"/recipe/ingck",
			    data:{"value":str},
			    success:function(cks){
			    	ck=cks;
				}
	 	    });
	 	    if (ck==0) { return; }
	 	    if (str==""){ return; }
	 	      
	 	    $('#ingr_main').val("");
	 	    if(autoCom==true) {
				addIngr(ingr,str,ck);
				ingr++;
	 	     	}
	 	    autoCom=false;
 	  	 });
 	   
 	   	$("#ingr_main").keydown(function(key) {
 	    	if (key.keyCode == 13) {
 	        $("#ingrAddBtn").trigger('click');
 	        	return;
 	      	}
 	    });
    });
    
</script>

<form id="defaultForm" method="post" class="form-horizontal"
	action="/signup/addinfo/apply" enctype="multipart/form-data">
<div class="container">
	<div class="row">
			<div class="col-lg-10 col-lg-offset-2">
				<div class="page-header text-center">
					<h2>추가정보 입력</h2>
					<h3>취향에 맞는 레시피를 찾으실 수 있어요</h3>
				</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">프로필사진</label>
						<div class="col-lg-5">
							<input type="file" name="img" class="btn btn-default">
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">종교</label>
						<div class="col-lg-5">
							<select class="form-control" name="religion">
								<option value="0">종교를 선택하세요</option>
								<c:forEach var="vo" items="${religion}">
									<option value="${vo.id}">${vo.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">채식</label>
						<div class="col-lg-5">
							<select class="form-control" name="vegeterian">
								<option value="0">채식주의 유형을 선택하세요</option>
								<c:forEach var="vo" items="${vegeterian}">
									<option value="${vo.id}">${vo.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">성별</label>
						<div class="col-lg-5">
							<div class="radio">
								<label> <input type="radio" name="gender" value="남성" /> 남성
								</label>
							</div>
							<div class="radio">
								<label> <input type="radio" name="gender" value="여성" /> 여성
								</label>
							</div>
							<div class="radio">
								<label> <input type="radio" name="gender" value="기타" /> 기타
								</label>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">주소</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="address" disabled>
							<input type="hidden" name="address1" value="15">
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">상세주소</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="address2">
						</div>
					</div>

					<div class="form-group">
						<div class="form-group" style="background-color: white">

							<label class="col-sm-2 col-sm-offset-2 control-label">기피재료</label>

							<div class="col-sm-2">
								<a data-toggle="값을 입력해주세요" title="값을 입력해주세요">
									<input id="ingr_main" class="form-control" type="text" placeholder="재료입력">
								</a>
							</div>
							<div class="col-sm-2">
								<button id="ingrAddBtn" type="button" class="btn btn-default btn-md">추가</button>
							</div>
						</div>
					</div>

					<div class="form-group" style="background-color: white">
						<div id="sorts" class="col-lg-4 col-lg-offset-4">
							<!-- 재료 -->
						</div>
					</div>
				<div class="form-group">
					<div class="col-lg-6 col-lg-offset-6">
						<button type="submit" class="btn btn-primary">추가정보 입력하기</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
