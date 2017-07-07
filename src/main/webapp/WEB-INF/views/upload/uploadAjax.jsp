<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
    .fileDrop {
        width:600px; height: 200px; border: 1px dotted blue;
    }

    small {
        margin-left: 3px;
        font-weight: bold;
        color: gray;
    }
</style>
<script>
//파일 개수를 제한하기 위한 전역변수
var count=0;
//화면상에서 삭제할 파일을 지정하기 위해 고유 아이디를 만들어줌
var id=0;
    $(document).ready(function(){
        $(".fileDrop").on("dragenter dragover", function(event){
            event.preventDefault(); // 기본효과를 막음
        });
		$(".fileDrop").on("drop", function(event) {
            event.preventDefault(); 
            var files = event.originalEvent.dataTransfer.files;
            var file = files[0];
            var fileName=file.name;
            if(count>=4){
            	alert("파일은 4개 까지만 올릴 수 있습니다.");
            } else {          
	            if(checkImageType(fileName)!=null){//이지미는 해당확장자를 반환, 아니면  null 반환
	                var formData = new FormData();
	                formData.append("file", file);
	                $.ajax({
	                    type: "post",
	                    url: "/upload/uploadAjax", //요청을 보내는 주소
	                    data: formData, //Specifies data to be sent to the server , 파일로 보낼때 반드시 지정해줘야
	                    //dataType: "text",
	                    contentType: false, //	The content type used when sending data to the server. Default is: "application/x-www-form-urlencoded"
	                    processData: false,
	                    // 업로드 성공하면
	                    success: function(data) {
	                    	count++;
	                    	console.log("업로드한 파일 개수는="+count);
	                        var str = "<span id="+(id++)+"><img src='${path}/upload/displayFile?fileName="+data+"' width=100px;></a>";
	                        // 삭제 버튼
	                        str += "<label data-src="+data+" font-size=15px;>X</label></span>";
	                        $(".uploadedList").append(str);
	                    }
	                });
	            }else {
	            	alert("이미지 파일만 업로드 가능합니다.");
	            }
            }
        });
        //이미지 삭제 구현
        $(".uploadedList").on("click", "label", function(event){
            alert("이미지 삭제")
            var that = $(this); // 여기서 this는 클릭한 label태그
            $.ajax({
                url: "/upload/deleteFile",
                type: "post",
                // data: "fileName="+$(this).attr("date-src") = {fileName:$(this).attr("data-src")}
                // 태그.attr("속성")
                data: {fileName:$(this).attr("data-src")}, // json방식
                dataType: "text",
                success: function(result){
                	count--;
                	console.log("업로드한 파일 개수는="+count);
                    if( result == "deleted" ){
                        // 클릭한 label태그가 속한 span를 제거 //div로 하면 문단이 나눠지면서 그림이 아래로 내려가서 span로 묶음
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
</script>
</head>
<body>
    <h2>AJAX File Upload</h2>
    <!-- 파일을 업로드할 영역 -->
    <div class="fileDrop"></div>
    <!-- 업로드된 파일 목록 -->
    <div class="uploadedList"></div>
</body>
</html>