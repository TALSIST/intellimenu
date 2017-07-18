<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<body>
	<div class="container">
		<div class="row">
			<div class="box">
				<div class="col-lg-12">
					<hr>
					<h2 class="intro-text text-center">
						<strong>지역별 식당</strong>
					</h2>
					<hr>
				</div>
				<c:forEach var="vo" items="${list}" begin="50" end="52">
					<div class="col-sm-4 text-center">
						<a href="/restaurant/restaurant/detail?id=${vo.id}"> <c:if
								test="${vo.img_ori!=null&&vo.img_new==null  }">
								<img class="img-responsive" src="${vo.img_ori}" width="750px"
									alt="">
							</c:if> <c:if test="${vo.img_ori!=null&&vo.img_new!=null}">
								<img class="img-responsive"
									src="/resources/restaurant/2017/${vo.img_new}" width="750px"
									alt="">
							</c:if>
						</a>
					<h3>
                        ${vo.name } <br> <small>${vo.score }</small>
                    </h3>
					
					</div>
				</c:forEach>
				<div class="clearfix"></div>
			</div>
		</div>

	</div>
	<!-- /.container -->
</body>
</html>