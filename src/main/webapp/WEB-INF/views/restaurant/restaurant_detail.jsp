<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="container">
		<div id="map" style="width: 100%; height: 300px;"></div>
				<hr>
		<section class="main-section contact" id="contact">
			<div class="row">
				<div style="float:left; margin-left:100px; margin-bottom:50px;width:40%;">
			<div style="font-size:32px;color:black;margin:auto;">
			<img src="/img/spaguetti.png" width="45px">&nbsp;${vo.name }<span style="font-size:25px;color:Orange;">&nbsp;&nbsp;${vo.score }</span>
			</div>
					<div style="margin:auto;">
						<hr>
						<span> 주소:</span><span style="color:black;"> ${vo.sigun } ${vo.address2 }</span><p>
						<c:if test="${vo.tel!=null }">
							<span>전화번호:</span><span style="color:black;"> ${vo.tel }</span><p>
						</c:if>
						<c:if test="${vo.category!=null }">
							<span>음식종류:</span><span style="color:black;"> ${vo.category }</span><p>
						</c:if>
						<c:if test="${vo.parking!=null }">
							<span>주차:</span><span style="color:black;"> ${vo.parking }</span><p>
						</c:if>
						<c:if test="${vo.busihour!=null }">
							<span>영업시간:</span><span style="color:black;"> ${vo.busihour }</span><p>
						</c:if>
						<c:if test="${vo.holiday!=null }">
							<span>휴일:</span><span style="color:black;"> ${vo.holiday }</span><p>
						</c:if>
					</div>
				</div>
				<div style="float:right; margin-bottom:50px;width:40%;">
					<c:if test="${vo.img_ori!=null&&vo.img_new==null }">
						<img src="${vo.img_ori}" width="350px" height="350px" style="border-radius: 20%;">
					</c:if>
					<c:if test="${vo.img_ori!=null&&vo.img_new!=null}">
			<!-- 		C:\springDev\springStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\wtpwebapps\webproject\resources\restaurant\2017 -->
						<img src="/resources/restaurant/2017/${vo.img_new}" width="350px" height="350px" style="border-radius: 20%;">
					</c:if>
				</div>
			</div>
				<hr>
			<!-- row div-->
			<div class="container">
			<div style="font-size:25px;color:black;margin:auto;text-align:center;">
				<img src="/img/review.png" width="35px">&nbsp;${vo.name }의 리뷰
			</div>
				<!-- 댓글 리스트 -->
				<div class="row" id="listReply"></div>
				   
				<!-- 댓글 작성 -->
			   <hr> 
				<form>
				    <textarea id="replytext" class="form-control" style="background-color:white;width:500px;" rows="4" placeholder="후기를 작성해주세요"></textarea>				
					평점 :  <input type="radio" name="scores" value="1"> 1
						  <input type="radio" name="scores" value="2"> 2
						  <input type="radio" name="scores" value="3" checked> 3
						  <input type="radio" name="scores" value="4"> 4
						  <input type="radio" name="scores" value="5"> 5
			 		<div>
							<label for="uploadedImages">
						        <img src="http://recipe.ezmember.co.kr/img/pic_none3.gif" 
						         class="fileDrop" style="height:100px;width:100px;border:1px solid #a0a0a0"/>
						    </label>
						    	<input multiple onChange="readURL(this.files);" id="uploadedImages" name="pictures[]" class="fileDrop"  
						     	 type="file" style="display: none">  
						  <!-- 이미지가 생성되는 영역 -->    
						    <span id ="up_images"></span> 
					</div>
					<button type="button" class="btn btn-default" style="width:50px;height:20x;" id="btnReaply">등록</button>
								
				</form>
  		 	</div>
		</section>	
	</div>

<!-- modal view 구현 -->
<script>
//C:\springDev\springStudy3\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\controller\resources\restaurant\2017\20170707101759741.eff889ffe9c1f83d9a4faa9f6e859680.jpg
//<img src="../resources/restaurant/2017/20170707.dd4ada27dc453d6170b9779b076e50f6.jpg">
//<img src="/resources/restaurant/2017/20170707.dd4ada27dc453d6170b9779b076e50f6.jpg">

function onClick(element) {
  document.getElementById("img01").src = element.src;
  document.getElementById("modal01").style.display = "block";
}
</script>	
<!-- 지도구현 -->
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=eF8Ihby9gJ895hs80gs_&submodules=panorama,geocoder"></script>
<script id="code">
var map = new naver.maps.Map("map", {
    center: new naver.maps.LatLng(37.3595316, 127.1052133),
    zoom: 10,
    mapTypeControl: true
});
var marker = new naver.maps.Marker({
    position : new naver.maps.LatLng(37.3595704, 127.105399),
    map : map
});

map.setCursor('pointer');
// result by latlng coordinate
function searchAddressToCoordinate(address) {
    naver.maps.Service
            .geocode(
                    {
                        address : address
                    },
                    function(status, response) {
                        if (status === naver.maps.Service.Status.ERROR) {
                            return alert('Something Wrong!');
                        }
                        var item = response.result.items[0], addrType = item.isRoadAddress ? '[도로명 주소]'
                                : '[지번 주소]', point = new naver.maps.Point(
                                item.point.x, item.point.y);
                        map.setCenter(point);
                        marker.setPosition(point);
                    });
}
function initGeocoder() {
    searchAddressToCoordinate("${vo.address2}");
}
naver.maps.onJSContentLoaded = initGeocoder;
</script>	
<!-- 파일 업로드구현 -->	
<script type="text/javascript">
	var newNames =[]; //바뀐 이름을 저장할 배열
	var oriNames =[]; //바뀐 이름을 저장할 배열
	var upfiles; //파일배열을 저장하는 전역변슈
	
	$(document).ready(function() {
		listReply();
		$("#btnReaply").click(function() {
			if($("#replytext").val()==""){
				alert("후기 내용을 입력해주세요");
			}else{
				console.log("upfiles="+upfiles);
				if(upfiles==undefined){
					insertReply(); //파일이 없으면 바로 삽입
				}else{
					insertFile(); //파일있으면 파일을 먼저 업로드하고 insertFile내에서 insert 호출
				}
			}
		});
		//드래그앤 드롭으로 이벤트 구현
		$(".fileDrop").on("dragenter dragover", function(event) {
			event.preventDefault(); // 기본효과를 막음
		});
		$(".fileDrop").on("drop",function(event) {
			event.preventDefault();
			var files = event.originalEvent.dataTransfer.files;
			var file = files[0];
			if (checkImageType(file.name) != null) {
				readURL(files);
			} else {
				alert("이미지 파일만 업로드 가능합니다.");
			}
		});
	});	   
   //썸네일 생성
   var readURL=function(files) {
	      upfiles=files;
	      $('#up_images').empty();   
	      var number = 0;
	      $.each(files, function(value) {
	          var reader = new FileReader();
	          reader.onload = function (e) {
	              var id = (new Date).getTime();
	              number++;
	              $('#up_images').prepend('<img class=\'all_images\' id='+id+' src='+e.target.result+' style="width:100px;height=100px;" data-index='+number+' onclick="removePreviewImage('+id+')"/>')
	          };
	          reader.readAsDataURL(files[value]);
	       });
      }  
	//server 폴더에 파일 업로드
	function insertFile(){
		var formData = new FormData();
 	    $.each(upfiles, function(key, file) {
	       formData.append(file.name, file);
	       console.log(file.name);
	       oriNames.push(file.name);
	       console.log("oriNames:"+oriNames);
	    }); 
		$.ajax({
			type : "post",
			url : "/upload/uploadAjax", //요청을 보내는 주소
			data : formData, //Specifies data to be sent to the server , 파일로 보낼때 반드시 지정해줘야
			dataType: "text",
			contentType : false, //	The content type used when sending data to the server. Default is: "application/x-www-form-urlencoded"
			processData : false,
			// 업로드 성공하면
			success : function(data) {
				var list = JSON.parse(data);
				for(var i=0;i<list.length;i++){
					newNames.push(list[i]);
					console.log("newNames:"+newNames);
					console.log("업로드한 파일 개수는="+ newNames.length); 
				}
				insertReply();
			}
		});
	}
	function insertReply() {
		var reply = $("#replytext").val();
		var restaurant_id = ${vo.id};
		var user_id = ${vo.user_id};
		var score=$('input[name="scores"]:checked').val();
		var img_ori = oriNames.toString();
		var img_new = newNames.toString();
/* 		console.log("reply="+reply);
		console.log("restaurant_id="+restaurant_id);
		console.log("user_id="+user_id);
		console.log("img_ori="+img_ori);
		console.log("img_new="+img_new);
		console.log("score"+score); */
 		$.ajax({
			type : "post",
			url : "/reply/insertRest",
			headers : {
				"Content-Type" : "application/json"
			},
			dateType : "text",
			data : JSON.stringify({
				user_id : user_id,
				restaurant_id : restaurant_id,
				reply : reply,
				score : score,
				img_ori : img_ori,
				img_new : img_new,
			}),
			success : function() {
				alert("댓글이 등록되었습니다.");;
				$("#uploadedImages").val('');//fileLoad박스에 있던 값지움 
				$("#replytext").val(''); //텍스트 박스의 값을 지움		
				$(".all_images").remove(); //띄워진 그림을 지움
				newNames=[]; //배열에서 그림이름들을 지움
				oriNames=[];
				console.log("newNames:"+newNames);
				console.log("oriNames:"+oriNames);
				listReply();
			}
		}); 
	}
	function listReply() {
		var id = ${vo.id};
		$.ajax({
			type : "get",
			//contentType: "application/json", ==> 생략가능(RestController이기때문에 가능)
			url : "/reply/listJson?restaurant_id=" + id,
			success : function(list) {
				console.log("등록");
				var output="";	
				for (var i=0;i<list.length;i++) {
					if(list[i].report<5){
						//console.log("list[i].img_new="+list[i].img_new);
						var imgs=list[i].img_new.split(",");
						output += "<div>";
						output += "<hr>";
						output += "<span> 작성자ID : " + list[i].user_id +"  </span>";
						output += "<span> ( " + changeDate(list[i].regdate) +") </span>";
						output += "<span> 평점 : " + list[i].score +"  </span>";
						output += "<button onClick=\"report("+list[i].id+")\">신고</button><br>";
						output += "<span>" + list[i].reply +"</span>";
						output += "<div class=\"w3-row-padding\">";
						for (var j in imgs){
							output += "<img src=\"/resources/restaurant/2017/"+imgs[j]+"\" hspace=\"5\"";
							output += "style=\"width:100px;height:100px;cursor:pointer\" onclick=\"onClick(this)\" class=\"w3-hover-opacity\">";
						}
						output += "</div>";
						output += "<div id=\"modal01\" class=\"w3-modal\" onclick=\"this.style.display='none'\">";
						output += "<div><img id=\"img01\" style=\"margin:auto;height:400px;display:block;position:relative;top:50px;\"></div></div>";						
						output += "</div>";
					}else{
						output += "<div><hr><span>관리자에 의해 차단된 댓글입니다.</span></div>";
					}
				}
				$("#listReply").html(output);
			}
		});
	}
	function report(id){
		console.log("신고된 댓글 번호는 : "+id);
		$.ajax({
			type : "get",
			url:"/reply/report?id="+id,
			success : function(){
				alert("신고되었습니다.");
			}
		});
		//id와 연동 후 한 아이디당 한번만 신고할 수 있도록 수정필요
	}
		// 이미지파일 형식을 체크하기 위해
	function checkImageType(fileName) {
		// i : ignore case(대소문자 무관)
		var pattern = /jpg|gif|png|jpeg/i; // 정규표현식
		return fileName.match(pattern); // 규칙이 맞으면 true
	}
	function changeDate(date) {
		date = new Date(parseInt(date));
		year = date.getFullYear();
		month = date.getMonth()+1;
		day = date.getDate();
		hour = date.getHours();
		minute = date.getMinutes();
		second = date.getSeconds();
		strDate = year + "-" + month + "-" + day + " " + hour + ":" + minute;
		return strDate;
	}
</script>
</body>
</html>