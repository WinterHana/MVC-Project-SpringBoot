<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html lang="en">

<head>
<script defer type="text/javascript" src ="/javascript/user/getUser.js"></script>
<script defer type="text/javascript" src ="/javascript/common.js"></script>
<jsp:include page="../toolbar.jsp" flush="true"/>
<title>User Information</title>
</head>

<body bgcolor="#ffffff" text="#000000">

<form name = "deleteUser" action = "/user/deleteUser" method = "POST">
	<input type ="hidden" name = "userId" value = "${user.userId}"/>
</form>

<form name = "updateUser" action = "/user/updateUserView/${user.userId}" method = "POST"></form>

    <div class="container">
    <br/>
    <h1>����� Ȯ��</h1>
        <div class="row">
            <div class="col-md-5">
                <img src="#" class="img-rounded" width="100%" />
            </div>
            <div class="col-md-7">
                <br /><br />
                <table class="table table-hover">
                    <tr>
                        <td>����</td>
                        <td>����</td>
                    <tr>
                        <td width="150" class="ct_write">���̵�</td>
                        <td id = "userId" class="ct_write01">${user.userId}</td>
                    </tr>
                    <tr>
                        <td width="150" class="ct_write">�̸� </td>
                        <td class="ct_write01">${user.userName}</td>
                    </tr> 
                    <tr>
                        <td width="150" class="ct_write">�ּ�</td>
                        <td class="ct_write01">${user.addr}</td>
                    </tr>
                    <tr>
                        <td width="150" class="ct_write">�޴� ��ȭ ��ȣ</td>
                        <td class="ct_write01">${not empty user.phone ? user.phone : ''}</td>
                    </tr>
                    <tr>
                        <td width="150" class="ct_write">�̸��� </td>
                        <td class="ct_write01">${user.email}</td>
                    </tr> 
                    <tr>
                        <td width="104" class="ct_write">��������</td>
                        <td class="ct_write01">${user.regDate}</td>
                    </tr>
                    <tr>
                        <td width="104" class="ct_write">����</td>
                        <td class="ct_write01">${user.role}</td>
                    </tr>
                </table>

                <button type="button" name = "update" class="btn btn-warning">�����ϱ�</button>

                <button type="button" name = "delete" class="btn btn-danger">�����ϱ�</button>

                <button type="button" name = "back" class="btn btn-success">�ڷΰ���</button>
            </div>
        </div>
    </div>
</body>
</html>