<%@ page language="java" contentType="text/html; charset=EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<script defer type="text/javascript" src="/javascript/common.js"></script>
<script defer type="text/javascript"
	src="/javascript/product/getProduct.js"></script>
<jsp:include page="../toolbar.jsp" flush="true" />
<title>Product Detail</title>
</head>

<body bgcolor="#ffffff" text="#000000">

	<form name="deleteProduct" action="/product/deleteProduct"
		method="POST">
		<input type="hidden" name="prodNo" value="${product.prodNo}" />
	</form>

	<form name="updateProduct" action="/product/updateProductView"
		method="POST">
		<input type="hidden" name="prodNo" value="${product.prodNo}" />
	</form>

	<div id="toolbar"></div>
	<div class="container">
		<br />
		<h1>��ǰ ����</h1>
		<br />
		<div class="row">
			<div class="col-md-6">
				<div id="carouselExampleIndicators" class="carousel slide"
					data-bs-ride="carousel">
					<div class="carousel-indicators">
						<c:set var="no" value="0" />
						<c:forEach var="fileName" items="${product.fileName}">
							<button type="button" data-bs-target="#carouselExampleIndicators"
								data-bs-slide-to="${no}" class="active"
								${no == 0 ? ' aria-current="true"' : ''}></button>
							<c:set var="no" value="${no + 1}" />
						</c:forEach>
					</div>
					<div class="carousel-inner">
						<!-- ���ۿ� �°� ���� : ���� X -->
						<c:set var="no" value="0" />
						<c:forEach var="fileName" items="${product.fileName}">
							<div class="carousel-item ${no == 0 ? ' active' : ''}">
								<img src="/img/uploadFiles/${fileName}" />
								<c:set var="no" value="${no + 1}" />
							</div>
						</c:forEach>
					</div>
					<button class="carousel-control-prev" type="button"
						data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>
			</div>
			<div class="col-md-6 border border-dark">
				<br />
				<h2>�̸� : ${product.prodName}</h2>
				<hr />
				<h2>
					���� : <span id="price">${product.price}</span>��
				</h2>
				<hr />
				<h4>
					���� ���� : <span id="count">${product.count}</span>��
				</h4>
				<hr />
				<h4>���� : ${product.prodDetail}</h4>
				<c:if test="${sessionScope.user.role eq 'admin'}">
					<hr />
					<h6>��ǰ��ȣ : ${product.prodNo}</h6>
					<h6>�������� : ${product.manuDate}</h6>
					<h6>������� : ${product.regDate}</h6>
				</c:if>
			</div>
			<div class="col-md-6"></div>
			<div class="col-md-6">
				<br />
				<h4 class="form-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����</h4>

				<div class="row">
					<div class="col-md-1">
						<button type="button" class="btn btn-secondary" name="down">
							<</button>
					</div>

					<div class="col-md-2">
						<input type="text" name="prodCount" class="form-control"
							value="0" style="text-align: center;" readonly>
					</div>

					<div class="col-md-1">
						<button type="button" class="btn btn-secondary" name="up">
							></button>
					</div>

					<div class="col-md-4">
						<h2>
							�� <span id="totalPrice">0</span>��
						</h2>
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col-md-12">
						<c:if test="${sessionScope.user.role eq 'admin'}">
							<button type="button" name="update"
								class="btn  btn-warning btn-lg">�����ϱ�</button>
							<button type="button" name="delete" class="btn btn-danger btn-lg">�����ϱ�</button>
						</c:if>
						<button type="button" name="purchase"
							class="btn btn-primary btn-lg"
							${empty sessionScope.user ? ' disabled' : '' }>�����ϱ�
							${empty sessionScope.user ? ' (�α��� �ʿ�)' : '' }</button>
						<button type="button" name="back" class="btn btn-secondary btn-lg">���</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Purchase Menu -->
	<div class="container" name = "purchaseMenu">
		<form name = "purchaseForm" action = "/purchase/addPurchase" method = "POST">
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<h2>���� ���� �Է�</h2>
					<input type="hidden" name="prodNo" value="${product.prodNo}" /> <input
						type="hidden" name="userId" value="${sessionScope.user.userId}" />
					<div class="input-group mb-3">
						<span class="input-group-text">������ ID</span> <input type="text"
							class="form-control" value="${sessionScope.user.userId}" readonly />
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">������ �̸�</span> <input type="text"
							class="form-control" name="receiverName" class="ct_input_g"
							value="${sessionScope.user.userName}" />
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">������ ����ó</span> <input type="text"
							class="form-control" name="receiverPhone" class="ct_input_g"
							value="${sessionScope.user.phone}" />
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">���� ���</span> <select
							class="form-select" name="paymentOption">
							<option value="1" selected="selected">���ݱ���</option>
							<option value="2">�ſ뱸��</option>
						</select>
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">���� ����</span> 
						<input type="text" name="prodCount" class="form-control"
							value="0"  readonly>
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">������ �ּ�</span> <input type="text"
							class="form-control" name="dlvyAddr" />
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">���� ��û ����</span> <input type="text"
							class="form-control" name="dlvyRequest" />
					</div>
					<div class="input-group mb-3">
						<span class="input-group-text">��� ����</span> <input type="date"
							class="form-control" name="dlvyDate" value = "2024-01-01" min="2024-01-01"
							max="2030-12-31" />
					</div>
					<button type="button" name="purchaseComplete" class="btn btn-primary btn-lg">���� �Ϸ�</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>