<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html lang="en">

<head>
<script defer type="text/javascript" src ="/javascript/common.js"></script>
<script defer type="text/javascript" src ="/javascript/product/listCartProduct.js"></script>
<jsp:include page="../toolbar.jsp" flush="true"/>
<title>Purchase List</title>
</head>

<body bgcolor="#ffffff" text="#000000">
    <div class="container">      
        <br/>
    	<h1>찜 목록</h1>
        <table class="table table-bordered">
            <thead class="table-light ">
                <tr>
                    <td width = "30%" align = "center">제품 이름</td>
                    <td width = "20%" align = "center">가격</td>
                    <td width = "20%" align = "center">개수</td>
                    <td width = "20%" align = "center">총 구매 금액</td>
                </tr>
            </thead>
            <tbody>
            	<c:forEach var = "product" items = "${map.productList}">
	               <tr>
	                   <td class="align-middle text-center" width = "30%" >
	                   		<a href = "/product/getProduct/${product.prodNo}">${product.prodName}</a>
	                   </td>
	                   <td class="align-middle text-center" width = "20%" >${product.price}</td>
	                   <td class="align-middle text-center" width = "20%" >
  							<input type="text" class="form-control" name = "count" placeholder="개수 입력" >
	                   </td>
	                   <td class="align-middle text-center" width = "20%" ><span id = "${product.prodNo}totalPrice">0</span></td>
	               </tr>
	            </c:forEach>
            </tbody>
          </table>
     </div>
     
     <div class="container">
		<form name = "purchaseForm" action = "/purchase/addPurchase" method = "POST">
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<h2>구매 정보 입력</h2>
					<input type="hidden" name="prodNo" value="${product.prodNo}" /> <input
						type="hidden" name="userId" value="${sessionScope.user.userId}" />
					<div class="input-group mb-3">
						<span class="input-group-text">구매자 ID</span> <input type="text"
							class="form-control" value="${sessionScope.user.userId}" readonly />
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">구매자 이름</span> <input type="text"
							class="form-control" name="receiverName" class="ct_input_g"
							value="${sessionScope.user.userName}" />
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">구매자 연락처</span> <input type="text"
							class="form-control" name="receiverPhone" class="ct_input_g"
							value="${sessionScope.user.phone}" />
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">구매 방법</span> <select
							class="form-select" name="paymentOption">
							<option value="1" selected="selected">현금구매</option>
							<option value="2">신용구매</option>
						</select>
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">구매 개수</span> 
						<input type="text" name="prodCount" class="form-control"
							value="0"  readonly>
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">총 가격</span> 
						<input type="text" name="totalPrice" class="form-control"
							value="0"  readonly>
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">구매자 주소</span> <input type="text"
							class="form-control" name="dlvyAddr" />
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">구매 요청 사항</span> <input type="text"
							class="form-control" name="dlvyRequest" />
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">배송 일자</span> <input type="date"
							class="form-control" name="dlvyDate" value = "2024-01-01" min="2024-01-01"
							max="2030-12-31" />
					</div>
					<button type="button" name="purchaseComplete" class="btn btn-primary btn-lg">구매 완료</button>
				</div>
			</div>
		</form>
</body>
</html>