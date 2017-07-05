<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>





<!-- 
                     name                     : value                                          종류
  카테고리            카테고리서브               top_category                                       select
                  카테고리 탑               sub_category                                    select
                  인원                     reqmember                                       select   
                  시간                     time                                             select
                  난이도                     level                                             select
                  레시피 이미지               recipe_img                                       img
                      레시피제목               recipe_title                                       text
                  요리소개                  summary                                       textarea
                  테그                     tag

                 재료                        ingr (다중값)                                 txt
 (레시피 재조순서)   순서내용               step1_content   (1번 step1_~ /2번 step2_~)   textarea
       재조이미지  순서이미지               step1_img                                          img

-->





<!-- Latest compiled and minified CSS -->



<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.0/jquery.validate.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"></script>

<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>

<link rel="stylesheet"
   href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css">
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>





<link rel="stylesheet"
   href="//cdn.jsdelivr.net/bootstrap.tagsinput/0.4.2/bootstrap-tagsinput.css" />
<script
   src="//cdn.jsdelivr.net/bootstrap.tagsinput/0.4.2/bootstrap-tagsinput.min.js"></script>









<style>


.bootstrap-tagsinput .tag {
   font-size: 15px;
}





</style>
<script>




function fnUpload(fileid){


   $('#'+fileid).click();
   

}
function imgChange(imgsrc,id){
   var reader = new FileReader();
   
   
    reader.onload = function (e) {
         $('#'+id).attr('src', e.target.result); 
     };
     reader.readAsDataURL(imgsrc.files[0]);
 
}





function addStep(step){
   
   var stepimg="stepsImg["+step+"]";      //이미지
   var stepfile="stepsFile["+step+"]";      //파일
   var stepFid="stepsfile"+step;
   var strpIid="stepsimg"+step;
   var content="content["+step+"]";


   $('#steps').append(
      
      
      '<div id=\"steps'+step+'\" class=\"form-group\" style=\"background-color:white\">'+
      '<label  class=\"col-sm-2 control-label\">Step'+(step+1)+'</label>'+
      '<div class=\"col-sm-6\">'+
      '<textarea name='+content+' class=\"form-control \" rows=\"9\"  placeholder=\"첫단계\" style=\"background-color: lightgray\"></textarea>'+
      '</div>'+
      '<a id="" href=\"javascript:fnUpload(\''+stepFid+'\');\">'+
      '<img id='+strpIid+'  src=\"http://recipe.ezmember.co.kr/img/pic_none3.gif\"  class=\"img-thumbnail\" width=\"150px\" height=\"100px\" name='+stepimg+'>'+
      '</a>'+
      '<input type="file" id="'+stepFid+'" style="display:none" onchange="imgChange(this,\''+strpIid+'\')"/ accept=".gif, .jpg, .png" name='+stepfile+'>'+
      
      '</div>'   
         
      );
}


//<a id="" href="javascript:fnUpload('fileUpload');">
//<img id="recipe_img" src="http://recipe.ezmember.co.kr/img/pic_none4.gif"  class="img-thumbnail" width="200px" height="100px" /></a>
//<input type="file" id="fileUpload" style="display:none" onchange="imgChange(this,'recipe_img')"/ accept=".gif, .jpg, .png">
//</div>   


function addIngr(Ingr,str){
   $('#sorts').append(
   '<div id=\"ingr'+Ingr+'\" class=\"row\" style=\"margin-bottom:5px\">'+ 
   '<div class=\"col-sm-4\">'+
   '<input class=\"form-control\"  type=\"text\" name=\"ingrv['+Ingr+']\" value='+str+'></div>'+
   '<div class=\"col-sm-4\">'+
   '<input class=\"form-control\"  type=\"text\" name=\"ingrg['+Ingr+']\" placeholder=\"중량입력\"></div>'+
   '<button  type=\"button\"  class=\"btn btn-default btn-md\" onClick="btn_Drop('+Ingr+')">제거</button>'+
   '</div>'
   );
}
function btn_Drop(s){
   $('#ingr'+s).remove();
}

function stepCk(step){
   if(step<0)step=0;
   if(step>4)step=4;
}


$(function(){
   var step=0;
   var ingr=0;
   var subcate=$('#sub_category');
   addStep(step);
   
   $("#ingr_main").click(function(){
	   return;
   });
   
   
   //탑카테고리
   $('#top_category').change(function(){
      var id=$("#top_category").val();
      $.ajax({   type:'POST',
         url:"/recipe/getsubcategory",
         data:{"id":id},
         //dataType:"json",   
         success:function(json){
            //alert(json[0].name);
            subcate.find('option').remove();
            var len=json.length;
            //alert(json.data[0].id);   
            for(var i=0;i<len;i++){
         
            subcate.append("<option value="+json[i].id+">"+json[i].name+"</option>");
            }
   
            
   
   
         
            
         }
      });
   });
   
   
   
   //자동완서
   $('#ingr_main').autocomplete({
      //var val=$('#ingr_main').val();
      source:function(request,response ){
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
                  
      }
      
      ,
      change: function (event, ui) {
           if(!ui.item){
               $(event.target).val("");
           }
       }
      
      
      
   
               
               
   })


   

   $('#ingrAddBtn').click(function(){
      
      str=$('#ingr_main').val();
      
      $.ajax({  
    	  type:'POST',
          url:"/recipe/ingck",
          data:{"value":str},
        	success:function(ck){
            	if(ck==0){
            	
            	}
           }
    
        });
      
      if(str==""){
         return;
      }
      $('#ingr_main').val("");
      addIngr(ingr,str);
      ingr++;
   
   });
   
   
   $("#ingr_main").keydown(function(key) {

      if (key.keyCode == 13) {

             $("#ingrAddBtn").trigger('click');
             return;

      }

      });



   
   
   $('#addStepBtn').click(function(){
      step++;
      if(step<5)
      {
         addStep(step);
      }
      else{
      alert("더 이상추가 할수 없습니다");
      step=4;
      
      }
      
   });

    $('#removeStepBtn').click(function(){
          
      if(step<1)return;
        $('#steps'+step).remove();
        step--;
        
        stepCk(step);
       
   });


   $("#sorts").sortable({
      axis: "y",
      containment: "parent",
      update: function (event, ui) {
         var order = $(this).sortable('toArray', {
            attribute: 'data-order'
         });
         console.log(order);
      }
   });
});
$('#insertf').bootstrapValidator({
	feedbackIcons : {
		valid : 'glyphicon glyphicon-ok',
		invalid : 'glyphicon glyphicon-remove',
		validating : 'glyphicon glyphicon-refresh'
	},
	fields :{
		ingr_main:{
			
			validators:{
				callback: {
					message: '이미 존재하는 닉네임입니다',
	                callback: function() {
	                    $.ajax({  
	                  	 	type:'POST',
	                     	url:"/recipe/ingck",
	                        data:{"value":$('ingr_main').val()},
	                      	success:function(ck){
	                          	if(ck==0){
	                          		alert("하이욤");
	                          		return false;
	                          		
	                          	}
	                         }
	                  
	                     });
	                	return true;
	                }
	            }
			}
		}
	
	}
	


});





</script>



<div style="background-color: lightgray">
   <div class="container" style="background-color: white">
      <br>
      <form id="insertf" class="form-horizontal" method="post"
         action="/recipe/recipie_test" enctype="multipart/form-data" onsubmit="return false;">
         <div class="panel panel-default" style="background-color: white">

            <div class="panel-heading">
               <h4>레시피등록</h4>

            </div>
            <div class="panel-body">
               <div class="form-group " style="background-color: white">
                  <label class="col-sm-2 control-label">레시피제목</label>
                  <div class="col-sm-6">
                     <input name="title" class="form-control" type="text"
                        style="background-color: lightgray" placeholder="레시피를 입력해주세요">
                     <div id="" class="col-sm-4"
                        style="position: absolute; left: 560px; top: 0px">



                        <a id="" href="javascript:fnUpload('fileUpload');"> <img
                           id="recipe_img"
                           src="http://recipe.ezmember.co.kr/img/pic_none4.gif"
                           class="img-thumbnail" width="200px" height="100px" /></a> <input
                           type="file" id="fileUpload" style="display: none"
                           onchange="imgChange(this,'recipe_img')"
                           accept=".gif, .jpg, .png" name="mainFile">

                     </div>
                     <!-- 

                  <img name="recipe_img" src="http://recipe.ezmember.co.kr/img/pic_none4.gif"  class="img-thumbnail" width="200px" height="100px"></div>
                  -->
                  </div>


               </div>
               <div class="form-group" style="background-color: white">
                  <label for="inputPassword" class="col-sm-2 control-label">요리소개</label>
                  <div class="col-sm-6">
                     <textarea name="summary" class="form-control" rows="5"
                        placeholder="요리를 소개해주세요" style="background-color: lightgray"></textarea>
                  </div>
               </div>
               <div class="form-group" style="background-color: white">
                  <label for="inputPassword" class="col-sm-2 control-label"
                     style="margin-right: 15px">카테고리</label>
                  <div class="col-sm-1">
                     <select name="top_category" id="top_category"
                        class="form-control" style="width: 100px">
                        <c:forEach var="vo" items="${toplist }">
                           <option value="${vo.id}">${vo.name }</option>
                        </c:forEach>
                     </select>
                  </div>
                  <div class="col-sm-1">
                     <select name="cat_sub_id" id="sub_category" class="form-control"
                        style="width: 100px; margin-left: 10px">
                        <option>초기값</option>
                     </select>
                  </div>


               </div>
               <div class="form-group" style="background-color: white">
                  <label for="inputPassword" class="col-sm-2 control-label"
                     style="margin-right: 15px">요리정보</label>
                  <div class=col-sm-2>
                     <label>인원</label> <select id="reqmember" name="reqmember"
                        class="selectpicker " data-width="fit">

                        <option value=1>1명</option>
                        <option value=2>2명</option>
                        <option value=3>3명</option>
                        <option value=4>4명</option>
                        <option value=5>5명</option>
                        <option value=6>6명 이상</option>

                     </select>
                  </div>


                  <div class=col-sm-2 style="margin-left: -30px">
                     <label>난이도</label> <select id="lvl" name="lvl"
                        class="selectpicker" data-width="fit">
                        <option value="하">하</option>
                        <option value="중">중</option>
                        <option value="상">상</option>
                     </select>
                  </div>


               </div>

               <div class="col-sm-2" style="margin-left: 20px">
                  <label>걸리는 시간(분단위)</label>
               </div>
               <div class="col-sm-3">
                  <div class="col-sm-4">
                     <input type=text id="time" name="time" class="form-control"
                        style="width: 70px">
                  </div>
                  <div class="col-sm-1" style="margin-top: 5px">
                     <label>분</label>
                  </div>
               </div>
            </div>
            </div>
            <div class="panel panel-default">


               <div class="panel-body">

                  <div class="form-group" style="background-color: white">
                     <label class="col-sm-2 control-label">재료</label>

                     <div class="col-sm-2">
                        <a  data-toggle="값을 입력해주세요" title="값을 입력해주세요"> <input
                           id="ingr_main" class="form-control" type="text"
                           placeholder="재료입력">
                        </a>


                     </div>
                     <div class="col-sm-2" style="padding-left: -0px">
                        <button id="ingrAddBtn" type="button"
                           class="btn btn-default btn-md">추가</button>

                     </div>


                     <div class="col-sm-5"></div>

                     <div id="sorts" class="col-sm-4">
                        <!-- 재료 -->
                     </div>
                  </div>
               </div>
            






            </div>

         


         <div class="panel panel-default">
            <div class="col-sm-12"
               style="background-color: white; margin-top: 10px">

               <h4 style="">요리순서</h4>


            </div>


            <div class="col-sm-12">
               <p style="color: gray">요리의 맛이 좌우될 수 있는 중요한 부분은 빠짐없이 적어주세요.</p>


               <div class="col-sm-12" style="margin-left: 10px">
                  <p style="color: gray">예) 10분간 익혀주세요 ▷ 10분간 약한불로 익혀주세요.</p>
               </div>
            </div>




            <div class="panel-body">

               <div id=steps></div>
            </div>
            <div align=center>
               <button id="addStepBtn" type="button"
                  class="btn btn-default btn-lg">추가</button>
               <button id="removeStepBtn" type="button"
                  class="btn btn-default btn-lg">제거</button>

               <br>


               <!--
                  <div class="form-group " style="background-color:white">
                        <label   class="col-sm-2 control-label">요리완성사진</label>
                        <div class="col-sm-2">
                     <img src="http://recipe.ezmember.co.kr/img/pic_none3.gif"  class="img-thumbnail" width="150px" height="100px">
                     </div>
                           <div class="col-sm-2"  >
                     <img src="http://recipe.ezmember.co.kr/img/pic_none3.gif"  class="img-thumbnail" width="150px" height="100px">
                           
                        </div>
                           <div class="col-sm-2"  >
                     <img src="http://recipe.ezmember.co.kr/img/pic_none3.gif"  class="img-thumbnail" width="150px" height="100px">
                        </div>
                           <div class="col-sm-2"  >
                     <img src="http://recipe.ezmember.co.kr/img/pic_none3.gif"  class="img-thumbnail" width="150px" height="100px">
                        </div>
                     </div>
                     
                     


             </div>
               -->
            </div>
         </div>


         <!--
               <div class="panel panel-default">


                  <div class="panel-body">
                  <div class="form-group" style="background-color:white">
                        <label for="inputPassword" class="col-sm-2 control-label">요리팁</label>
                        <div class="col-sm-10" >
                       <textarea class="form-control" rows="5"  placeholder="요리를 소개해주세요" style="background-color: lightgray"></textarea>
                        </div>
                   </div>
             </div>
         </div>
-->

         <div class="panel panel-default">



            <div class="panel-body">
               <div class="form-group " style="background-color: white">
                  <label class="col-sm-2 control-label">태그</label>
                  <div class="col-lg-10">
                     <input type="text" name="tags" id="aa" class="form-control"
                        value="" data-role="tagsinput" style="font-size: 100px" />
                  </div>
               </div>
               <div class="col-sm-2"></div>
               <div class="cols-sm-10">
                  <h5>
                     주재료, 목적, 효능, 대상 등을 태그로 남겨주세요. <small>예) 돼지고기, 다이어트, 비만,
                        칼슘, 감기예방, 이유식, 초간단</small>
                  </h5>
               </div>

            </div>

         </div>

         <div class="panel">
            <center>
               <button type="button" class="btn btn-default btn-lg">저장</button>
               <button type="submit" class="btn btn-default btn-lg">등록완료</button>
               <button type="reset" class="btn btn-default btn-lg">취소</button>
            </center>
         </div>

      </form>
      <!-- 레시피등록 완료 -->

   </div>



   <!-- https://silviomoreto.github.io/bootstrap-select/examples/ -->
</div>
