<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
    Object member=session.getAttribute("member");
    request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
<link rel="stylesheet" href="${contextPath}/css/detail/list.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="${contextPath}/js/detail/list.js"></script>
<script>
    function goBack() {
        window.history.back();
    }
</script>
</head>
<body>
    <div class="search-container">
        <button type="button" class="back-button" onclick="goBack()">&lt;</button>
        <form action="${contextPath}/search" method="get" class="search-form">
            <input type="text" name="query" placeholder="검색어를 입력하세요..." class="search-input">
            <button type="submit" class="search-button">검색</button>
        </form>
    </div>
    <div class="content">    
        <c:choose>
            <c:when test="${!empty allList}">
                <c:forEach var="allList" items="${allList}">   
                    
                        <div class="contentRange" onclick="goToDetail(${allList.detailNo})">
                           <div class="imgRange">
                               <img class="img" src="${contextPath}/images/detail/${allList.detailClassification}/${allList.detailBusinessEng}/${allList.detailBusinessEng}1.PNG" alt="">
                           </div>
                            <div class="buttonRange">
                                   <button class="favorite-button" >
                                        <input  type="hidden" class="userId" value="${member.memberNo}">
                                        <input  type="hidden" class="companyId" value="${allList.detailNo}">
                                       <img class="dibs" src="${contextPath}/images/detail/detailpage/dibs.png" alt="Favorite">
                                   </button>
                            </div>
                           <div class="infoRange">
                               <h6 class="classification">${allList.detailKoClassification}</h6>
                               <h4 class="name">${allList.detailBusinessName}</h4>
                               <h6 class="address">${allList.detailRoadAddress}</h6>
                           </div>
                           <div class="ticketRange">
                               <div class="dailyTicket"><p class="boxText">일일권</p></div>
                               <div class="memberTicket"><p class="boxText">이지짐회원가</p></div>
                           </div>
                           <div class="priceRange">
                               <p class="price">${allList.detailMonthlyTicket}</p><p class="month">/월</p>
                           </div>
                           
                           <div class="serviceRange">
                               <p class="freeService">무료 서비스</p>
                               <p class="provide">${allList.detailFreeService}</p>
                           </div>
                           <div class="contentBorder"></div>
                       </div>        
                    </form>                             
                </c:forEach>              
            </c:when>                
        </c:choose>        
    </div>        
</body>
</html>