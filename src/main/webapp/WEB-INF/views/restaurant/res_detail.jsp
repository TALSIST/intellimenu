<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, maximum-scale=1">

<title>Homepage</title>
<link rel="icon" href="favicon.png" type="image/png">
<link rel="shortcut icon" href="favicon.ico" type="img/x-icon">

<link href='https://fonts.googleapis.com/css?family=Montserrat:400,700'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,800italic,700italic,600italic,400italic,300italic,800,700,600'
	rel='stylesheet' type='text/css'>

<link href="/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="/css/style.css" rel="stylesheet" type="text/css">
<link href="/css/font-awesome.css" rel="stylesheet" type="text/css">
<link href="/css/responsive.css" rel="stylesheet" type="text/css">
<link href="/css/animate.css" rel="stylesheet" type="text/css">

<!--[if IE]><style type="text/css">.pie {behavior:url(PIE.htc);}</style><![endif]-->

<script type="text/javascript" src="/js/jquery.1.8.3.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.js"></script>
<script type="text/javascript" src="/js/jquery-scrolltofixed.js"></script>
<script type="text/javascript" src="/js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="/js/jquery.isotope.js"></script>
<script type="text/javascript" src="/js/wow.js"></script>
<script type="text/javascript" src="/js/classie.js"></script>
<script src="/contactform/contactform.js"></script>

<!-- =======================================================
    Theme Name: Knight
    Theme URL: https://bootstrapmade.com/knight-free-bootstrap-theme/
    Author: BootstrapMade
    Author URL: https://bootstrapmade.com
======================================================= -->
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=eF8Ihby9gJ895hs80gs_"></script>

<!--
참고 사이트 목록 :3 ...아아아악ㄱ ~!!

 http://blog.naver.com/weekamp/220861272054 -->
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
					<div id="map" style="width: 400px; height: 300px;"></div>

					<script type="text/javascript">
						var mapOptions = {

							center : new naver.maps.LatLng(37.3595704,
									127.105399),

							zoom : 10

						}

						var map = new naver.maps.Map('map', mapOptions);
					</script>
				</div>
			</div>
			<!-- row div-->
			<div class="container">
				<h2>${vo.name }의리뷰</h2>
				<h6>맛있다 | 별로다 | 추천</h6>
			    <div style="width:650px; text-align: center;">
			
			        <textarea rows="5" cols="80" id="replytext" placeholder="댓글을 작성해주세요??"></textarea>
			
			        <button type="button" id="btnReply">댓글 작성</button>
			        
			        <div id="listReply"></div> 
			    </div>
			
			</div>

		</section>
	</div>

<script  type="text/javascript">

$(document).ready(function(){
	listReply2(); // ** json 리턴방식
    // ** 댓글 쓰기 버튼 클릭 이벤트 (ajax로 처리)
    $("#btnReply").click(function(){
    	console.log("누름");
        //reply(); // 폼데이터로 입력
        replyJson(); // json으로 입력
    });

});
// ** 댓글 쓰기 (json방식)
function replyJson(){
    var reply=$("#replytext").val();
    console.log(reply);
    var restaurantid=${vo.id};

    $.ajax({                
        type: "post",
        url: "${path}/reply/insertRest",
        headers: {
            "Content-Type" : "application/json"
        },
        dateType: "text",
        // param형식보다 편하다.
        data: JSON.stringify({
        	userid : 1,
        	restaurantid : restaurantid,
            reply : reply,
            score : 5,
        }),
        success: function(){
            alert("댓글이 등록되었습니다.");
            listReply2();
        }
    });
}

function listReply2(){
    $.ajax({
        type: "get",
        //contentType: "application/json", ==> 생략가능(RestController이기때문에 가능)
        url: "${path}/reply/listJson?restaurantid="+${vo.id},
        //url: "${path}/reply/listJson?restaurantid=1",
        success: function(result){
            var output = "<table>";
            for(var i in result){
                output += "<tr>";
                output += "<td>"+result[i].userId;
                output += "("+changeDate(result[i].regdate)+")     ";
                output += result[i].reply+"</td>";
                output += "<tr>";
            }
            output += "</table>";
            $("#listReply").html(output);
        }
    });
}

function changeDate(date){
    date = new Date(parseInt(date));
    year = date.getFullYear();
    month = date.getMonth();
    day = date.getDate();
    hour = date.getHours();
    minute = date.getMinutes();
    second = date.getSeconds();
    strDate = year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
    return strDate;
}
</script>
</body>
</html>

