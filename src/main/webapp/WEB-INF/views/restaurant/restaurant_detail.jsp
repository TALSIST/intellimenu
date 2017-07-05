<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Homepage</title>
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=eF8Ihby9gJ895hs80gs_&submodules=panorama,geocoder"></script>
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
					<c:if test="${vo.img_ori!=null }">
						<img src="${vo.img_ori}" width="350px" height="350px" style="border-radius: 20%;">
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
				<div>
					<textarea class="form-control"
						style="background-color: white; width: 500px;" rows="4"
						id="replytext" placeholder="후기를 작성해주세요"></textarea>
					<img class="fileDrop" type="file"
						src="http://recipe.ezmember.co.kr/img/pic_none3.gif"
						style="height: 93px; border: 1px solid #a0a0a0;" />
					<!-- 파일이 올라갈 영역 -->
					<span class="uploadedList"></span>
				</div>
				파일을 끌어다 놓으세요
				<div>
					<button type="button" class="btn btn-default"
						style="width: 50px; height: 20x;" id="btnReaply">등록</button>
					<button type="button" class="btn btn-default"
						style="width: 50px; height: 20x;" id="btnModify">수정</button>
					<button type="button" class="btn btn-default"
						style="width: 50px; height: 20x;" id="btnRemove">삭제</button>
				</div>
				인풋박스 <a id="" href="javascript:fnUpload('fileUpload');"> <img
					id="recipe_img"
					src="http://recipe.ezmember.co.kr/img/pic_none4.gif"
					class="img-thumbnail" width="200px" height="100px" /></a> <input
					type="file" id="fileUpload" style="display: none"
					onchange="imgChange(this,'recipe_img')" accept=".gif, .jpg, .png">
			</div>

		</section>
	</div>
	<img src="../resources/favicon.ico">
	<div id="" class="col-sm-4"
		style="position: absolute; left: 560px; top: 0px"></div>

	<script id="code">
		var map = new naver.maps.Map("map", {
			center : new naver.maps.LatLng(37.3595316, 127.1052133),
			zoom : 10,
			mapTypeControl : true
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

	<script>
		//C:\sts-bundle\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\intellimenu\resources\restaurant\2017\20170702234525857.79ae4e2fb8f8fca3ad3de3bf96f5a299.JPG
		var oriNames = []; //원래 이름을 저장할 배열
		var newNames = []; //바뀐 이름을 저장할 배열

		$(document).ready(function() { //페이지가 로드되면서 동시에 호출되는 함수
			listReply();
			$("#btnReaply").click(function() {
				console.log("누름");
				insertReply();
			});
		});

		function insertReply() {
			var reply = $("#replytext").val();
			var restaurant_id = $
			{
				vo.id
			}
			;
			var user_id = $
			{
				vo.user_id
			}
			;
			var img_ori = oriNames.toString();
			var img_new = newNames.toString();
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

					//텍스트 박스의 값을 지움
					$("#replytext").val('');
					//띄워진 그림을 지움
					$(".uploadedImg").remove();
					//배열에서 그림이름들을 지움
					for (var i = 0; i < oriNames.length; i++) {
						oriNames.splice(i, 1);
						newNames.splice(i, 1);
					}
					console.log("oriNames:" + oriNames);
					console.log("newNames:" + newNames);
					listReply();
				}
			});
		}

		function listReply() {
			var id = $
			{
				vo.id
			}
			;
			$.ajax({
				type : "get",
				//contentType: "application/json", ==> 생략가능(RestController이기때문에 가능)
				url : "/reply/listJson?restaurant_id=" + id,
				success : function(list) {
					var output;
					for (var i = 0; i < list.length; i++) {
						output += "<div>";
						output += "<hr>";
						output += "<span>" + list[i].user_id + "</span>";
						output += "<span>" + changeDate(list[i].regdate)
								+ "</span>";
						output += "<span>" + list[i].reply + "</span>";
						output += "<div>";
						output += list[i].img_new;
						output += "</div>";
						output += "</div>";
					}
					$("#listReply").html(output);
				}
			});
		}

		function changeDate(date) {
			date = new Date(parseInt(date));
			year = date.getFullYear();
			month = date.getMonth();
			day = date.getDate();
			hour = date.getHours();
			minute = date.getMinutes();
			second = date.getSeconds();
			strDate = year + "-" + month + "-" + day + " " + hour + ":"
					+ minute + ":" + second;
			return strDate;
		}
		//파일 썸내일 생성
		$(document).ready(function() {
			$(".fileDrop").on("dragenter dragover", function(event) {
				event.preventDefault(); // 기본효과를 막음
			});

			$(".fileDrop").on("drop", function(event) {
				event.preventDefault();
				var files = event.originalEvent.dataTransfer.files;
				var file = files[0];
				makeThumbnail(file);
			});
			//이미지 삭제 구현
			$(".uploadedList").on("click", "label", function(event) {
				var that = $(this); // 여기서 this는 클릭한 label태그
				console.log("delete의 that" + that);
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
						for (var i = 0; i < oriNames.length; i++) {
							if (oriNames[i] == that.attr("value")) {
								oriNames.splice(i, 1);
							}
						}
						//바꾼 이름 배열에서 지움
						for (var i = 0; i < newNames.length; i++) {
							if (newNames[i] == that.attr("data-src")) {
								newNames.splice(i, 1);
							}
						}
						console.log("oriNames:" + oriNames);
						console.log("newNames:" + newNames);
						console.log("업로드한 파일 개수는=" + oriNames.length);
						if (result == "deleted") {
							// 클릭한 label태그가 속한 span를 제거 , div로 하면 문단이 나눠지면서 그림이 아래로 내려가서 span로 묶음
							that.parent("span").remove();
						}
					}
				});
			});
		});
		// 이미지파일 형식을 체크하기 위해
		function checkImageType(fileName) {
			// i : ignore case(대소문자 무관)
			var pattern = /jpg|gif|png|jpeg/i; // 정규표현식
			return fileName.match(pattern); // 규칙이 맞으면 true
		}
		function makeThumbnail(file) {
			var fileName = file.name;
			if (oriNames.length >= 4) {
				alert("파일은 4개 까지만 올릴 수 있습니다.");
			} else {
				if (checkImageType(fileName) != null) {//해당확장자를 반환, 이미지 파일이 아니면  null 반환
					var formData = new FormData();
					formData.append("file", file);
					$
							.ajax({
								type : "post",
								url : "/upload/uploadAjax", //요청을 보내는 주소
								data : formData, //Specifies data to be sent to the server , 파일로 보낼때 반드시 지정해줘야
								//dataType: "text",
								contentType : false, //	The content type used when sending data to the server. Default is: "application/x-www-form-urlencoded"
								processData : false,
								// 업로드 성공하면
								success : function(data) {
									oriNames.push(fileName);
									newNames.push(data);
									console.log("oriNames:" + oriNames);
									console.log("newNames:" + newNames);
									console.log("업로드한 파일 개수는="
											+ oriNames.length);
									var str = "<span class='uploadedImg'><img src='/upload/displayFile?fileName="
											+ data + "' width=100px;></a>";
									str += "<label data-src="+data+" value="+fileName+" font-size=15px;>X</label></span>"; // 삭제 버튼
									$(".uploadedList").append(str);
								}
							});
				} else {
					alert("이미지 파일만 업로드 가능합니다.");
				}
			}
		}
	</script>
</body>
</html>