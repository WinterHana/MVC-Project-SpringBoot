<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">

<head>
<script defer type="text/javascript" src ="/javascript/common.js"></script>
<script defer type="text/javascript" src ="/javascript/product/addProduct.js"></script>
<jsp:include page="../toolbar.jsp" flush="true"/>
<title>Product Update</title>
</head>

<body bgcolor="#ffffff" text="#000000">
	<div class="container">
		<br/>
	 	<h1>��ǰ �߰�</h1>
        <form action = "/product/addProduct" method="post" enctype="multipart/form-data">
            <div class="row">
                <div class="col-md-7">
                    <div class="input-group input-group-lg flex-nowrap mb-3">
                        <span class="input-group-text" id="addon-wrapping">��ǰ �̸�</span>
                        <input class="form-control" type="text" name="prodName" value="${product.prodName}">
                    </div>

                    <div class="input-group input-group-lg flex-nowrap mb-3">
                        <span class="input-group-text" id="addon-wrapping">��ǰ �� ����</span>
                        <input class="form-control" type="text" name="prodDetail" value="${product.prodDetail}">
                    </div>

                    <div class="input-group input-group-lg flex-nowrap mb-3">
                        <span class="input-group-text" id="addon-wrapping">���� ����</span>
                        <input class="form-control" type="date" name="manuDate" value="${product.manuDate}">
                    </div>

                    <div class="input-group input-group-lg flex-nowrap mb-3">
                        <span class="input-group-text" id="addon-wrapping">����</span>
                        <input class="form-control" type="text" name="price" value="${product.price}">
                    </div>

                    <div class="input-group input-group-lg flex-nowrap mb-3">
                        <span class="input-group-text" id="addon-wrapping">��ǰ ����</span>
                        <input class="form-control" type="text" name="count" value="${product.count}">
                    </div>
                    
                    <div class="input-group input-group-lg flex-nowrap mb-3">
                        <span class="input-group-text" id="addon-wrapping">��ǰ �̹���</span>
                        <input class="form-control" type="file" name="multipartFile" id="formFileMultiple" multiple/>
                    </div>

                    <button type="button" name="add" class="btn btn-warning">�߰��ϱ�</button>

                    <button type="button" name="back" class="btn btn-success">�ڷΰ���</button>
                </div>
            </div>
        </form>
      </div> 
</body>
</html>