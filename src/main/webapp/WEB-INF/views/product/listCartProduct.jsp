<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html lang="en">

<head>
<script defer type="text/javascript" src ="/javascript/common.js"></script>
<!-- <script defer type="text/javascript" src ="/javascript/purchase/listPurchase.js"></script> -->
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
                    <td width = "40%" align = "center">제품 이름</td>
                    <td width = "20%" align = "center">가격</td>
                    <td width = "20%" align = "center">개수</td>
                    <td width = "20%" align = "center">총 구매 금액</td>
                </tr>
            </thead>
            <tbody>
            	<c:forEach var = "product" items = "${map.productList}">
	               <tr>
	                   <td width = "40%" align = "center">${product.prodName}</td>
	                   <td width = "20%" align = "center">${product.price}</td>
	                   <td width = "20%" align = "center">0</td>
	                   <td width = "20%" align = "center">0</td>
	               </tr>
	            </c:forEach>
            </tbody>
          </table>
     </div>
</body>
</html>