<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function pass()
	{
		var pwd1=document.getElementsByName("pwd");
		var pwd2=document.getElementsByName("password");
	}
</script>
<body>
<section class="main-section" id="service"><!--main-section-start-->
	<div class="container">
    	<h2>회원가입</h2>
    	<h6>개인정보를 소중하게 보호하겠습니다.</h6>
        <div class="row " > 
        	<form action="regist_ok" method="post" role="form" class="regist">       	
            <div class="col-lg-6 col-lg-offset-3 col-sm-6 col-sm-offset-3wow fadeInUp delay-05s">
            	<div class="form">                	
                    <div id="sendmessage">Your message has been sent. Thank you!</div>
                    <div id="errormessage"></div>
                    <h4>필수정보</h4>
                    
                       
                        <div class="form-group">
                            <input type="email" class="form-control input-text" name="email" id="user_email" placeholder="이메일을 입력해주세요" data-rule="email" data-msg="Please enter a valid email" />
                            <div class="validation"></div>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control input-text" name="pwd" id="password" placeholder="암호를 입력해주세요" data-rule="minlen:4" data-msg="Please enter at least 8 chars of subject" />
                            <div class="validation"></div>
                        </div>
                        <div class="form-group">
                            <input type="text" name="name" class="form-control input-text" id="user_name" placeholder="이름을 입력해주세요" data-rule="minlen:4" data-msg="Please enter at least 4 chars" />
                            <div class="validation"></div>
                        </div>
                        <div class="form-group">
                            <input type="text" name="nickname" class="form-control input-text" id="nickname" placeholder="닉네임을 입력해주세요" data-rule="minlen:4" data-msg="Please enter at least 4 chars" />
                            <div class="validation"></div>
                        </div>
                        
                        <br>
                        <h4>선택정보</h4>
                    
                        <div class="form-group">
                        	<input type="radio" name="sex" value="남자" checked/>남자
							<input type="radio" name="sex" value="여자" />여자
                        </div>
                      
                        <div class="form-group">
                            <input type="text" name="phone" class="form-control input-text" id="phone" placeholder="전화번호를 입력해주세요" data-rule="minlen:4" data-msg="Please enter at least 4 chars" />
                            <div class="validation"></div>
                        </div>
                        <div class="form-group">
                        	
                        	<div class="col-lg-10 col-sm-9">
                           	 	<input type="text" name="address1" class="form-control input-text "   id="address1" placeholder="주소를 검색하세요" data-rule="minlen:4" data-msg="Please enter at least 4 chars" />
                            </div>
                            <div class="col-lg-2 col-sm-3">
                            	<input type="button" name="addrinput" class="form-control input-button"  id="addrinput" data-rule="minlen:4" data-msg="Please enter at least 4 chars" value="검색"/>
                            </div>
                            <div class="validation"></div>
                        </div>
                        <div class="form-group">
                            <input type="text" name="address2" class="form-control input-text" id="address2" placeholder="세부주소를 입력해주세요" data-rule="minlen:4" data-msg="Please enter at least 4 chars" />
                            <div class="validation"></div>
                        </div>                     
                        <div class="form-group">
                            <input type="text" name="ingr" class="form-control input-text" id="ingr" placeholder="좋아하는 음식을 적어주세요" data-rule="minlen:4" data-msg="Please enter at least 4 chars" />
                            <div class="validation"></div>
                        </div>
                        <div class="form-group">
                            <input type="text" name="ingr" class="form-control input-text" id="ingr" placeholder="싫어하는 음식을 적어주세요" data-rule="minlen:4" data-msg="Please enter at least 4 chars" />
                            <div class="validation"></div>
                        </div>
                        <div class="form-group">
                        	<select name="" id="">
                        		<option value="">종교</option>
                        		<option value="">불교</option>
                        		<option value="">이슬람</option>
                        		<option value=""></option>
                        		<option value=""></option>
                        	</select>
                        	<select name="" id="">
                        		<option value="">채식</option>
                        		<option value="">완전</option>
                        		<option value="">유제품까지</option>
                        		<option value="">생선까지</option>
                        		<option value=""></option>
                        	</select>
                        </div>
                    
                </div>	
	            <div class="text-center"><button type="submit" class="input-btn">가입하기</button></div>
            </div>
            </form>
            </div>
            
        
        
	</div>
</section><!--main-section-end-->
</body>
</html>