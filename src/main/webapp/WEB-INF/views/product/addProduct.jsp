<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	 	<h1>제품 추가</h1>
        <form action = "/product/addProduct" method="post" enctype="multipart/form-data">
            <div class="row">
                <div class="col-md-7">
                    <div class="input-group input-group-lg flex-nowrap mb-3">
                        <span class="input-group-text" id="addon-wrapping">상품 이름</span>
                        <input class="form-control" type="text" name="prodName" value="${product.prodName}">
                    </div>

                    <div class="input-group input-group-lg flex-nowrap mb-3">
                        <span class="input-group-text" id="addon-wrapping">상품 상세 정보</span>
                        <input class="form-control" type="text" name="prodDetail" value="${product.prodDetail}">
                    </div>

                    <div class="input-group input-group-lg flex-nowrap mb-3">
                        <span class="input-group-text" id="addon-wrapping">제조 일자</span>
                        <input class="form-control" type="date" name="manuDate" value="${product.manuDate}">
                    </div>

                    <div class="input-group input-group-lg flex-nowrap mb-3">
                        <span class="input-group-text" id="addon-wrapping">가격</span>
                        <input class="form-control" type="text" name="price" value="${product.price}">
                    </div>

                    <div class="input-group input-group-lg flex-nowrap mb-3">
                        <span class="input-group-text" id="addon-wrapping">상품 개수</span>
                        <input class="form-control" type="text" name="count" value="${product.count}">
                    </div>
                    
                    <div class="input-group input-group-lg flex-nowrap mb-3">
                        <span class="input-group-text" id="addon-wrapping">상품 이미지</span>
                        <input class="form-control" type="file" name="multipartFile" id="formFileMultiple" multiple/>
                    </div> 
                    
                    <div class="input-group input-group-lg flex-nowrap mb-3">
                        <span class="input-group-text" id="addon-wrapping">태그</span>	
                        <input class="form-control" type="text" name ="inputTag" size="10" placeholder="엔터로 해시태그 등록" />
                    </div>
                    
                    <div class="input-group input-group-lg flex-nowrap mb-3">
                   		<input type="hidden" name="tagList"  value = ""/>
                    	<div id="tag-list"></div>
                    </div>
                    
                    
                    <button type="button" name="add" class="btn btn-warning">추가하기</button>

                    <button type="button" name="back" class="btn btn-success">뒤로가기</button>
                </div>
            </div>
        </form>
      </div> 
</body>
</html>