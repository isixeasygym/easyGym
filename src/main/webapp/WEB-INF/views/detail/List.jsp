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
	$(document).ready(function() {
	    // AJAX 요청 중복 방지
	    $(".favorite-button").each(function() {
	        var button = this;
	        var companyId = $(button).find('.companyId').val();
	        var userId = $(button).find('.userId').val();

	        $.ajax({
	            type: "GET",
	            url: "/getFavoriteStatus",
	            data: { companyId: companyId, userId: userId },
	            success: function(data) {
	                updateFavoriteButton(button, data);
	            },
	            error: function(xhr, status, error) {
	                console.error("Error: " + error);
	            }
	        });
	    });

	    $(".favorite-button").click(function(event) {
	        var button = this;

	        if ($(button).data('requestInProgress')) return; // 요청이 진행 중이면 무시

	        $(button).data('requestInProgress', true); // 요청 진행 중으로 설정
	        $(button).addClass('loading'); // 로딩 상태 CSS 적용

	        var companyId = $(button).find('.companyId').val();
	        var userId = $(button).find('.userId').val();

	        $.ajax({
	            type: "GET",
	            url: "/addFavorite",
	            data: { companyId: companyId, userId: userId },
	            success: function(data) {
	                if (data == "insert" || data == "delete") {
	                    alert(data == "insert" ? "찜 목록에 추가되었습니다." : "찜 목록에서 삭제되었습니다.");
	                    updateFavoriteButton(button, data);
	                } else if (data.startsWith("redirect:")) {
	                    window.location.href = data.substring(9);
	                } else {
	                    alert("알 수 없는 오류가 발생했습니다.");
	                }
	                $(button).data('requestInProgress', false); // 요청 완료로 설정
	                $(button).removeClass('loading'); // 로딩 상태 CSS 해제
	            },
	            error: function(xhr, status, error) {
	                console.error("Error: " + error);
	                alert(xhr + status + "오류가 발생했습니다." + error);
	                $(button).data('requestInProgress', false); // 요청 완료로 설정
	                $(button).removeClass('loading'); // 로딩 상태 CSS 해제
	            }
	        });

	        event.stopPropagation();
	    });

	    function updateFavoriteButton(button, status) {
	        var newSrc = (status == "insert") ? '${contextPath}/images/detail/detailpage/pickDibs.png' : '${contextPath}/images/detail/detailpage/dibs.png';
	        $(button).find('.dibs').attr('src', newSrc);
	    }
	});
	
</script>
</head>
<body>
    <div class="search-container">
        <button type="button" class="back-button" onclick="goBack()">&lt;</button>
        <form action="/detail/search.do" method="get" class="search-form">
            <input type="text" name="query" placeholder="검색어를 입력하세요..." class="search-input">
			<input type="hidden" name="detailClassification" value="health">
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