<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="${contextPath}/css/detail/list.css">
<script src="${contextPath}/js/detail/list.js"></script>
</head>
<body>
	<div class="content">	
		<c:choose>
			<c:when test="${!empty allList}">
				<c:forEach var="allList" items="${allList}" varStatus="list">		
					<div class="contentRange">
						<div id="imgRange">
			         		<img class="img" src="${contextPath}/images/detail/${allList.wholeClassification}/${allList.wholeBusinessEng}/${allList.wholeBusinessEng}1.PNG" alt="${allList.wholeBusinessEng}">
			         	</div>
						<form>
				            <div class="buttonRange">
					               <button class="favorite-button" type="submit">
									<input type="hidden" id="userId" value="{member.memberNo}">
									<input type="hidden" id="compayId" value="{allList.detailNo}">
					                   <img class="dibs" src="${contextPath}/images/detail/detailpage/dibs.png" alt="Favorite">
					               </button>
				            </div>
					   </form>
			           <div class="infoRange">
			               <h6 class="classification">${allList.wholeKoClassification}</h6>
			               <h4 class="name">${allList.wholeBusinessName}</h4>
			               <h6 class="address">${allList.wholeRoadAddress}</h6>
			           </div>
			           <div class="ticketRange">
			               <div class="dailyTicket"><p class="boxText">일일권</p></div>
			               <div class="memberTicket"><p class="boxText">이지짐회원가</p></div>
			           </div>
			           <div class="priceRange">
			               <p class="price">${allList.wholeMonthlyTicket}</p><p class="month">/월</p>
			           </div>
			           <div class="serviceRange">
			               <p class="freeService">무료 서비스</p>
			               <p class="provide">${allList.wholeFreeService}</p>
			           </div>
			           <div class="contentBorder"></div>
					    <form action="/detail.do" method="post">
							<input type="hidden" class="wholeNo" name="wholeNo" value="${allList.wholeNo}">	
						</form>
			       </div>						    				
				</c:forEach>			   
			</c:when>				
		</c:choose>		
	</div>		
</body>
</html>