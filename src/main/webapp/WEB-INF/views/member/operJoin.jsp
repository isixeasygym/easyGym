<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<div class="container mt-5">
	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="card">
				<div class="card-body">
					<form class="row g-3">
						<div class="text-center mb-3">
							<img src="/images/member/fitness.png" class="img-fluid" alt="Fitness Image" style="width: 100px; height: auto;">
						</div>
						<div class="form-floating mb-3">
							<input type="text" class="form-control" id="floatingName" placeholder="이름"> 
							<label for="floatingName">이름</label>
						</div>

						<div class="mb-3 d-flex align-items-center">
							<div class="flex-grow-1 me-2">
								<div class="form-floating">
									<input type="text" class="form-control" id="floatingUsername" placeholder="아이디"> 
									<label for="floatingUsername">아이디</label>
								</div>
							</div>
							<div>
								<button class="btn btn-outline-secondary btn-sm" type="button" id="button-addon2" style="font-size: 12px;">중복확인</button>
							</div>
						</div>

						<div class="form-floating mb-3">
							<input type="password" class="form-control" id="floatingPassword" placeholder="비밀번호"> 
							<label for="floatingPassword">비밀번호</label>
						</div>
						<div class="form-floating mb-3">
							<input type="password" class="form-control" id="confirmPassword" placeholder="비밀번호 확인"> 
							<label for="confirmPassword">비밀번호 확인</label>
						</div>
						<div class="form-floating mb-3">
							<input type="email" class="form-control" id="floatingEmail" placeholder="name@example.com"> 
							<label for="floatingEmail">이메일</label>
						</div>
						<div class="form-floating mb-3">
							<input type="tel" class="form-control" id="floatingPhone" placeholder="010-0000-0000"> 
							<label for="floatingPhone">전화번호</label>
						</div>

						<div class="mb-3 d-flex align-items-center">
							<div class="flex-grow-1 me-2">
								<label for="floatingBizNum3">사업자등록번호</label>
								<div class="input-group">
									<input type="text" class="form-control" name="bizNum1" placeholder="" maxlength="3"> 
									<span class="input-group-text">-</span> 
									<input type="text" class="form-control" name="bizNum2_1" placeholder="" maxlength="2"> 
									<span class="input-group-text">-</span>
									<input type="text" class="form-control" name="bizNum2_2" placeholder="" maxlength="6">
								</div>
							</div>
						</div>

						<div class="mb-3">
							<label for="inputGroupFile04">사업자등록증</label>
							<div class="input-group">
								<input type="file" class="form-control" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04" aria-label="Upload">
								<button class="btn btn-outline-secondary" type="button" id="inputGroupFileAddon04">업로드</button>
							</div>
						</div>

						<div class="d-grid">
							<button class="btn btn-primary" type="submit" onclick="redirectToPage()">가입하기</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
  function redirectToPage() {
    // 여기서 'redirect.jsp'는 이동할 JSP 페이지의 경로입니다.
    window.location.href = 'afterEntJoin';
  }
</script>

<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
