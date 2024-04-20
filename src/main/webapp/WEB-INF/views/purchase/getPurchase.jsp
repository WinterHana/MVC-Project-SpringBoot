<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html lang="en">

<head>
<script defer type="text/javascript" src ="/javascript/common.js"></script>
<script defer type="text/javascript" src ="/javascript/purchase/getPurchase.js"  charset="utf-8"></script>
<jsp:include page="../toolbar.jsp" flush="true"/>
<title>Purchase Detail</title>
</head>

<body bgcolor="#ffffff" text="#000000">

<form name = "deletePurchase" action = "/purchase/deletePurchase" method = "POST">
	<input type ="hidden" name = "tranNo" value = "${purchase.tranNo}"/>
	<input type ="hidden" name = "tranCode" value = "${purchase.tranCode}"/>
	<input type ="hidden" name = "totalPrice" value = "${purchase.totalPrice}"/>
	
	<input type ="hidden" name = "prodNo" value = "${purchase.purchaseProd.prodNo}"/>
	<input type ="hidden" name = "prodCount" value = "${purchase.prodCount}">
	
	<input type ="hidden" name = "userId" value = "${purchase.buyer.userId}"/>
</form>

<form name = "updatePurchase" action = "/purchase/updatePurchaseView/${purchase.tranNo}" method = "POST">
</form>

    <div class="container">
    <br/>
    <h1>구매 내역 확인</h1>
        <div class="row">
            <div class="col-md-7">
                <br /><br />
                <table class="table table-hover">
                    <tr>
                        <td>제목</td>
                        <td>내용</td>
                    <tr>
                        <td width="150" class="ct_write">구매 번호</td>
                        <td id = "userId" class="ct_write01">${purchase.tranNo}</td>
                    </tr>
                    <tr>
                        <td width="150" class="ct_write">구매자 아이디</td>
                        <td class="ct_write01">${purchase.buyer.userId}</td>
                    </tr> 
                    <tr>
                        <td width="150" class="ct_write">구매자 이름</td>
                        <td class="ct_write01">${purchase.receiverName}</td>
                    </tr>
                    <tr>
                        <td width="150" class="ct_write">구매자 연락처</td>
                        <td class="ct_write01">${purchase.receiverPhone}</td>
                    </tr>
                    <tr>
                        <td width="150" class="ct_write">구매자 주소</td>
                        <td class="ct_write01">${purchase.dlvyAddr}</td>
                    </tr> 
                    <tr>
                        <td width="104" class="ct_write">구매 방법</td>
                        <td class="ct_write01">${paymentOption}</td>
                    </tr>
                    <tr>
                        <td width="104" class="ct_write">제품 이름</td>
                        <td class="ct_write01">${purchase.purchaseProd.prodName}</td>
                    </tr>
                     <tr>
                        <td width="104" class="ct_write">개수</td>
                        <td class="ct_write01">${purchase.prodCount}</td>
                    </tr>
                    <tr>
                        <td width="104" class="ct_write">총 구매 금액</td>
                        <td class="ct_write01">${purchase.totalPrice}</td>
                    </tr>
                    <tr>
                        <td width="104" class="ct_write">구매 요청 사항</td>
                        <td class="ct_write01">${purchase.dlvyRequest}</td>
                    </tr>
                    <tr>
                        <td width="104" class="ct_write">배송 희망일</td>
                        <td class="ct_write01">${purchase.dlvyDate}</td>
                    </tr>
                    <tr>
                        <td width="104" class="ct_write">주문일</td>
                        <td class="ct_write01">${purchase.orderDate}</td>
                    </tr>
                </table>

                <button type="button" name = "update" class="btn btn-warning">수정하기</button>

                <button type="button" name = "delete" class="btn btn-danger">취소하기</button>

                <button type="button" name = "back" class="btn btn-success">뒤로가기</button>
            </div>
            <div class="col-md-5">
                <img src="/img/sleep.jpg" class="img-rounded" width="100%" />
         	</div>
         </div>

     </div>
</body>
</html>