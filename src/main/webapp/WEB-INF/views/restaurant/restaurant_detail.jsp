<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">

</head>
<body>
	<div class="container">
		<section class="main-section contact" id="contact">
			<h2>${vo.name }</h2>
			<h6>${vo.score }</h6>
			<div class="row">
				<div class="col-lg-6 col-sm-7 wow fadeInLeft">
					<div class="contact-info-box address clearfix">
						<h3>
							<i class=" icon-map-marker"></i>주소:
						</h3>
						<span> ${vo.sigun } ${vo.address2 }</span>
					</div>
					<div class="contact-info-box phone clearfix">
						<c:if test="${vo.tel!=null }">
							<h3>
								<i class="fa-phone"></i>전화번호:
							</h3>
							<span>${vo.tel }</span>
						</c:if>
					</div>
					<div class="contact-info-box email clearfix">
						<c:if test="${vo.category!=null }">
							<h3>
								<i class="fa-pencil"></i>음식종류:
							</h3>
							<span>${vo.category }</span>
						</c:if>
					</div>
					<div class="contact-info-box email clearfix">
						<c:if test="${vo.parking!=null }">
							<h3>
								<i class="fa-pencil"></i>주차:
							</h3>
							<span>${vo.parking }</span>
						</c:if>
					</div>
					<div class="contact-info-box hours clearfix">
						<c:if test="${vo.busihour!=null }">
							<h3>
								<i class="fa-clock-o"></i>영업시간:
							</h3>
							<span>${vo.busihour }</span>
						</c:if>
					</div>
					<div class="contact-info-box hours clearfix">
						<c:if test="${vo.holiday!=null }">
							<h3>
								<i class="fa-clock-o"></i>휴일:
							</h3>
							<span>${vo.holiday }</span>
						</c:if>
					</div>
					<!-- 현재는 역지로 링크를 넣어준것이라 지도API와 연동필요-->
					<div id="map" style="width:100%;height:600px;">
				    </div>
				</div>
			</div>
			<!-- row div-->
			<div class="container">
				<h2>${vo.name }의 리뷰</h2>

				<!-- 댓글 리스트 -->
				<div class="row" id="listReply"></div>
				   
				<!-- 댓글 작성 -->
			   <hr> 
				<div id="fileBox">
				    <textarea id="replytext" class="form-control" style="background-color:white;width:500px;" rows="4" placeholder="후기를 작성해주세요"></textarea>				
					<form action="">
						  <input type="radio" name="gender" value="1"> 1
						  <input type="radio" name="gender" value="2"> 2
						  <input type="radio" name="gender" value="3"> 3
						  <input type="radio" name="gender" value="3"> 4
						  <input type="radio" name="gender" value="3"> 5
						</form>
					<a href="javascript:$('#uploadedImages').click();">
					<img id="recipe_img2" src="http://recipe.ezmember.co.kr/img/pic_none3.gif"
						class="fileDrop" style="height:100px;width:100px;border:1px solid #a0a0a0" />
					</a> 
					 <input multiple="1" onchange="readURL(this.files);" id="uploadedImages" name="pictures[]" class="fileDrop"  
					      type="file" style="display: none">
					      
	                 <span id ="up_images"></span>
				</div>
				파일을 끌어다 놓으세요
				<div>
					<button type="button" class="btn btn-default" style="width:50px;height:20x;" id="btnReaply">등록</button>
				</div>

	
  		 	</div>
		</section>
	</div>	
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=eF8Ihby9gJ895hs80gs_&submodules=panorama,geocoder"></script>
<script type="text/javascript">
	var newNames =[]; //바뀐 이름을 저장할 배열
	var oriNames =[]; //바뀐 이름을 저장할 배열
	var upfiles; //파일배열을 저장하는 전역변슈
	//드래그앤 드롭으로 이벤트 구현
	$(document).ready(function() {
		listReply();
		$("#btnReaply").click(function() {
			if($("#replytext").val()==""){
				alert("후기 내용을 입력해주세요");
			}else{
				console.log("등록버튼 누름");
				insertFile(); //ajax반응속도가 느려 파일을 먼저 업로드하고 insertFile내에서 insert 호출
			}
		});
		
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
   var readURL = function(files) {
	      upfiles=files;
	      $('#up_images').empty();   
	      var number = 0;
	      $.each(files, function(value) {
	          var reader = new FileReader();
	          reader.onload = function (e) {
	              var id = (new Date).getTime();
	              number++;
	              $('#up_images').prepend('<img class=\'all_images\' id='+id+' src='+e.target.result+' width="100px" height="100px" data-index='+number+' onclick="removePreviewImage('+id+')"/>')
	          };
	          reader.readAsDataURL(files[value]);
	       });
      }  
	//server 폴더에 파일 업로드
	function insertFile(){
		console.log("파일업로드");
		console.log(upfiles[0]);
		var formData = new FormData();
 	    $.each(upfiles, function(key, file) {
	       formData.append(file.name, file);
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
				insertReply(); //타이밍 문제로 여기서 호출;;;
			}
		});
	}
	function insertReply() {
		var reply = $("#replytext").val();
		var restaurant_id = ${vo.id};
		var user_id = ${vo.user_id};
		var img_ori = oriNames.toString();
		var img_new = newNames.toString();
		console.log("reply="+reply);
		console.log("restaurant_id="+restaurant_id);
		console.log("user_id="+user_id);
		console.log("img_ori="+img_ori);
		console.log("img_new="+img_new);
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
				score : 5,
				img_ori : img_ori,
				img_new : img_new,
			}),
			success : function() {
				alert("댓글이 등록되었습니다.");
				//resetFileBox();
				//텍스트 박스의 값을 지움
				$("#replytext").val('');
				//띄워진 그림을 지움
				$(".all_images").remove();
				//배열에서 그림이름들을 지움
				newNames=[];
			/* 	for(var i=0;i<newNames.length+1;i++){
					//delete newNames[i];
					newNames.splice(i, 1);
				} */
				console.log("newNames:"+newNames);
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
				var output;	
				for (var i=0;i<list.length;i++) {
					output += "<div>";
					output += "<hr>";
					output += "<span> 작성자ID : " + list[i].user_id +"  </span>";
					output += "<span> ( " + changeDate(list[i].regdate) +") </span>";
					output += "<span> 평점 : " + list[i].score +"  </span>";
					output += "<button onClick=\"report("+list[i]+")\">신고</button><br>";
					output += "<span>" + list[i].reply +"</span>";
					output += "<div>";
					output += list[i].img_new; 
					output += "</div>";
					output += "</div>";
				}
				$("#listReply").html(output);
			}
		});
	}
	function report(vo){
		console.log("신고된 댓글 번호는 : "+vo.id);
		console.log("신고된 댓글 스코어는는 : "+vo.score);
	}
</script>

<script id="code">
var map = new naver.maps.Map("map", {
    center: new naver.maps.LatLng(37.3595316, 127.1052133),
    zoom: 10,
    mapTypeControl: true
});
var infoWindow = new naver.maps.InfoWindow({
    anchorSkew: true
});
map.setCursor('pointer');
// result by latlng coordinate
function searchAddressToCoordinate(address) {
    naver.maps.Service.geocode({
        address: address
    }, function(status, response) {
        if (status === naver.maps.Service.Status.ERROR) {
            return alert('Something Wrong!');
        }

        var item = response.result.items[0],
            addrType = item.isRoadAddress ? '[도로명 주소]' : '[지번 주소]',
            point = new naver.maps.Point(item.point.x, item.point.y);

        infoWindow.setContent([
                '<div style="padding:10px;min-width:200px;line-height:130%;">',
                '<h4 style="margin-top:5px;">검색 주소 : '+ response.result.userquery +'</h4><br />',
                addrType +' '+ item.address +'<br />',
                '</div>'
            ].join('\n'));
        map.setCenter(point);
        infoWindow.open(map, point);
    });
}

function initGeocoder() {
    searchAddressToCoordinate("${vo.address2}");
}
naver.maps.onJSContentLoaded = initGeocoder;
</script>

<script>
//C:\sts-bundle\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\intellimenu\resources\restaurant\2017\20170702234525857.79ae4e2fb8f8fca3ad3de3bf96f5a299.JPG



	
		//이미지 삭제 구현
		$(".uploadedList").on("click","label",function(event) {
			var that = $(this); // 여기서 this는 클릭한 label태그
			console.log("delete의 that"+that);
			$.ajax({
				url : "/upload/deleteFile",
				type : "post",
				// data: "fileName="+$(this).attr("date-src") = {fileName:$(this).attr("data-src")}
				data : {
					fileName : $(this).attr("data-src")
				}, // json방식
				
				dataType : "text",
				success : function(result) {	
					//원본이름 배열에서 지움
					for(var i=0;i<oriNames.length;i++){
						if(oriNames[i]==that.attr("value")){
							oriNames.splice(i,1);
						}
					}
					//바꾼 이름 배열에서 지움
					for(var i=0;i<newNames.length;i++){
						if(newNames[i]==that.attr("data-src")){
							newNames.splice(i,1);
						}
					}
					console.log("oriNames:"+oriNames);
					console.log("newNames:"+newNames);
					console.log("업로드한 파일 개수는="+ oriNames.length);
					if (result == "deleted") {
						// 클릭한 label태그가 속한 span를 제거 , div로 하면 문단이 나눠지면서 그림이 아래로 내려가서 span로 묶음
						that.parent("span").remove();
					}
				}
			});
		});

	// 이미지파일 형식을 체크하기 위해
	function checkImageType(fileName) {
		// i : ignore case(대소문자 무관)
		var pattern = /jpg|gif|png|jpeg/i; // 정규표현식
		return fileName.match(pattern); // 규칙이 맞으면 true
	}

	function imgChange(file,id){
		var reader = new FileReader();
		 reader.onload = function (e) {
	         $('#'+id).attr('src', e.target.result); 
	     };
	     reader.readAsDataURL(file);
	     insertFile(file);
	     console.log(file.name);
	     file=this.file;
	     
	     console.log("file="+file);
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