<%@ page language="java" contentType="text/html; charset=EUC-KR"%>

<!DOCTYPE html>
<html lang="en">

<head>
<script defer type="text/javascript" src ="/javascript/common.js"></script>
<script defer type="text/javascript" src ="/javascript/purchase/updatePurchase.js"></script>
<jsp:include page="../toolbar.jsp" flush="true"/>
<title>Purchase List</title>
</head>

<body bgcolor="#ffffff" text="#000000">

	<div class="container">
		<br/>
	 	<h1>���� ����</h1>
        <form name="updatePurchase" method="post" action="/purchase/updatePurchase">
        	<input type="hidden" name="buyerId" value="${purchase.buyer.userId}">
			<input type="hidden" name="tranNo" value="${purchase.tranNo}">
            <div class="row">
                <div class="col-md-7">
                    <div class="input-group input-group-lg flex-nowrap mb-3">
                        <span class="input-group-text" id="addon-wrapping">������ ���̵�</span>
                      	<span class="form-control" id="addon-wrapping" readonly>${purchase.buyer.userId}</span>
                    </div>
                    
                    <div class="input-group input-group-lg flex-nowrap mb-3">
                        <span class="input-group-text" id="addon-wrapping">���� ���</span>
                        <select class="form-control" 	name="paymentOption" >
							<option value="1" selected="selected">���ݱ���</option>
							<option value="2">�ſ뱸��</option>
						</select>
                    </div>

                    <div class="input-group input-group-lg flex-nowrap mb-3">
                        <span class="input-group-text" id="addon-wrapping">������ �̸�</span>
                        <input class="form-control" type="text" name="receiverName" value="${purchase.receiverName}">
                    </div>
                    
                    <div class="input-group input-group-lg flex-nowrap mb-3">
                        <span class="input-group-text" id="addon-wrapping">������ ����ó</span>
                        <input class="form-control" type="text" name="receiverPhone" value="${purchase.receiverPhone}">
                    </div>
                    
                    <div class="input-group input-group-lg flex-nowrap mb-3">
                        <span class="input-group-text" id="addon-wrapping">������ �ּ�</span>
                        <input class="form-control" type="text" name="dlvyAddr" value="${purchase.dlvyAddr}">
                    </div>

                    <div class="input-group input-group-lg flex-nowrap mb-3">
                        <span class="input-group-text" id="addon-wrapping">���� ��û ����</span>
                        <input class="form-control" type="text" name="dlvyRequest" value="${purchase.dlvyRequest}">
                    </div>
                    
                    <div class="input-group input-group-lg flex-nowrap mb-3">
                        <span class="input-group-text" id="addon-wrapping">��� ��� ����</span>
                       <input class="form-control" type="date" name="dlvyDate" value="${purchase.dlvyDate}" />
                    </div>
                </div>
            </div>
        </form>
        <button type="button" name="update" class="btn btn-warning">�����ϱ�</button>
        <button type="button" name="back" class="btn btn-success">����ϱ�</button>
      </div> 
      
<%-- <form name="updatePurchase" method="post"	action="/purchase/updatePurchase">
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td width="53%"></td>
		<td align="right">
		<table border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="17" height="23">
					<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
				</td>
				<td background="/images/ct_btnbg02.gif" class="ct_btn01"	style="padding-top: 3px;">
					<input type="submit" value="����"/>
				</td>
				<td width="14" height="23">
					<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
				</td>
				<td width="30"></td>
				<td width="17" height="23">
					<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
				</td>
				<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
					<a href="javascript:history.go(-1)">���</a>
				</td>
				<td width="14" height="23">
					<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form> --%>

</body>
</html>