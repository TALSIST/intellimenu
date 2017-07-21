<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
      <c:forEach var="vo" items="${list}">
        <div class="col-sm-4 text-center">
          <a href="/restaurant/restaurant/detail?id=${vo.id}"> <c:if
              test="${vo.img_ori!=null&&vo.img_new==null  }">
              <img class="img-responsive" src="${vo.img_ori}"
                width="750px" alt="">
            </c:if> <c:if test="${vo.img_ori!=null&&vo.img_new!=null}">
              <img class="img-responsive"
                src="/resources/restaurant/2017/${vo.img_new}"
                width="750px" alt="">
            </c:if>
          </a>
          <h3>
            ${vo.name } <br> <small>${vo.score }</small>
          </h3>

        </div>
      </c:forEach>
      <div class="clearfix"></div>
    </div>
    
    <!-- Pagination -->
    <div class="fixed-table-pagination" style="display: block;">
        <div class="pull-left pagination-detail">
            <span class="pagination-info">
            <b>${pmgr.start}번</b> 부터 <b>${pmgr.end}번</b> / 전체 게시물 <b>${pmgr.total}건</b>
            </span>
        </div>
        <div class="pull-right pagination">
            <ul class="pagination">
                <li><a href="?page=${pmgr.prevBtn}">&laquo;</a></li>
                <c:forEach var="i" begin="${pmgr.startBlock}" end="${pmgr.endBlock}">
                    <li <c:if test="${pmgr.page==i}">class="active"</c:if>>
                    <a href="?page=${i}">${i}</a></li>
                </c:forEach>
                <li><a href="?page=${pmgr.nextBtn}">&raquo;</a></li>
            </ul>
        </div>
    </div>

  </div>

</div>
