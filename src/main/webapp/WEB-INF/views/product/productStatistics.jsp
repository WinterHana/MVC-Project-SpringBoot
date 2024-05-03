<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script defer type="text/javascript" src ="/javascript/common.js"></script>
<script defer type="text/javascript" src ="/javascript/product/productStatistics.js" charset="utf-8"></script>
<jsp:include page="../toolbar.jsp" flush="true"/>
<title>Product Statistics</title>
<style>
.canvas-container {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
</head>

<body>
	<br/>
	<h1 style = "text-align : center;">사이트 관련 통계</h1>
	<br/><br/>
	<div class="container">
		<h1 style = "text-align : center;">1. 태그 별 제품 개수</h1>
		<div class="canvas-container">
		  <canvas id="productCountByTagName"  style="height:60vh; width:40vw"></canvas>
		</div>
	</div>
	<br/><br/><br/>
	<div class="container" >
		<h1 style = "text-align : center;">2. 많은 선택을 받은 제품들</h1>
		<div class="canvas-container">
		  <canvas id="productCountByTransaction"  style="height:60vh; width:40vw"></canvas>
		</div>
	</div>
 	<br/><br/><br/>
	<div class="container" >
		<h1 style = "text-align : center;">3. 일별 매출</h1>
		<div class="canvas-container">
		  <canvas id="transactionTotalPriceByOrderDate"  style="height:60vh; width:40vw"></canvas>
		</div>
	</div>
	 <br/><br/><br/>
</body>
</html>