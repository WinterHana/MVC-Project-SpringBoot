<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
<script defer type="text/javascript" src ="/javascript/user/signup.js"></script>
<script defer type="text/javascript" src ="/javascript/common.js"></script>
<jsp:include page="../toolbar.jsp" flush="true"/>
<title>sign up</title>
</head>

<body>
    <div class="container">
        <br />
        <form class="row g-3">
            <h1 class="bg-primary text-center">ȸ �� �� ��</h1>
            <div class="col-md-4">
                <label for="userId" class="col-sm-offset-1 col-sm-3 control-label">�� �� ��</label>
                <div>
                    <input type="text" class="form-control" id="userId" name="userId" placeholder="�ߺ� Ȯ�� �ʿ�">
                </div>
                <span id="helpBlock" class="help-block">
                    <strong class="text-danger" id = "duplicationResult">�Է��� �ߺ�Ȯ�� ����..</strong>
                </span>
            </div>

            <div class="col-md-6">
                <br />
                <button type="button" class="btn btn-info">�ߺ� Ȯ��</button>
            </div>
            
            <div class="col-md-6">
                <label for="password" class="col-sm-offset-1 col-sm-3 control-label">��й�ȣ</label>
                <div class="col-sm-8">
                    <input type="password" class="form-control" id="password" name="password" placeholder="��й�ȣ">
                </div>
                
            </div>

            <div class="col-md-6">
                <label for="password2" class="col-sm-offset-1 col-sm-3 control-label">��й�ȣ Ȯ��</label>
                <div class="col-sm-8">
                    <input type="password" class="form-control" id="password2" name="password2" placeholder="��й�ȣ Ȯ��">
                </div>
            </div>

            <div class="col-md-6">
                <label for="userName" class="col-sm-offset-1 col-sm-3 control-label">�̸�</label>
                <div class="col-sm-8">
                    <input type="password" class="form-control" id="userName" name="userName" placeholder="ȸ���̸�">
                </div>
            </div>

            <div class="col-md-6">
                <label for="ssn" class="col-sm-offset-1 col-sm-3 control-label">�ֹι�ȣ</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="ssn" name="ssn" placeholder="�ֹι�ȣ">
                    <span id="helpBlock" class="help-block">
                        <strong class="text-danger">" - " ���� 13�ڸ��Է��ϼ���</strong>
                    </span>
                </div>
            </div>
            <div class="col-md-4">
                <label for="ssn" class="col-sm-offset-1 col-sm-3 control-label">��ȭ��ȣ</label>
                <select class="form-control" name="phone1" id="phone1">
                    <option value="010">010</option>
                    <option value="011">011</option>
                    <option value="016">016</option>
                    <option value="018">018</option>
                    <option value="019">019</option>
                </select>
            </div>
            <div class="col-md-2">
                <br />
                <input type="text" class="form-control" id="phone2" name="phone2" placeholder="��ȣ">
            </div>

            <div class="col-md-2">
                <br />
                <input type="text" class="form-control" id="phone3" name="phone3" placeholder="��ȣ">
            </div>
            <div class="form-group">
                <label for="ssn" class="col-sm-offset-1 col-sm-3 control-label">�̸���</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="email" name="email" placeholder="�̸���">
                </div>
            </div>

            <div class="col-12">
                <button type="button" class="btn btn-primary">�����ϱ�</button>
                <a class="btn btn-primary btn" href="/user/login.jsp" role="button">���</a>
            </div>
        </form>
    </div>
</body>
</html>
<!-- 
<body bgcolor="#ffffff" text="#000000">

<form name="detailForm"  method="post" >

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">ȸ�� ����</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:13px;">
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			���̵� <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="105">
						<input type="text" name="userId" class="ct_input_bg" style="width:100px; height:19px"  maxLength="20" >
					</td>
					<td>
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="4" height="21">
									<img src="/images/ct_btng01.gif" width="4" height="21"/>
								</td>
								<td 	align="center" background="/images/ct_btng02.gif" class="ct_btn" 
										style="padding-top:3px;">
									ID �ߺ� Ȯ��
								</td>
								<td width="4" height="21">
									<img src="/images/ct_btng03.gif" width="4" height="21">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			��й�ȣ <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="password" name="password" class="ct_input_g" 
							style="width:100px; height:19px"  maxLength="10" minLength="6"  />
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			��й�ȣ Ȯ�� <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="password" name="password2" class="ct_input_g" 
							style="width:100px; height:19px"  maxLength="10" minLength="6"  />
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			�̸�<img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="userName" class="ct_input_g" 
							style="width:100px; height:19px"  maxLength="50" >
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">�ֹι�ȣ</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="ssn" class="ct_input_g" 
							style="width:100px; height:19px" onChange="javascript:checkSsn();"  maxLength="13" >
			-����, 13�ڸ� �Է�
		</td>
	</tr>

	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">�ּ�</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input		type="text" name="addr" class="ct_input_g" 
							style="width:370px; height:19px"  maxLength="100"/>
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">�޴���ȭ��ȣ</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<select 	name="phone1" class="ct_input_g" style="width:50px">
				<option value="010" >010</option>
				<option value="011" >011</option>
				<option value="016" >016</option>
				<option value="018" >018</option>
				<option value="019" >019</option>
			</select>
			<input type="text" name="phone2" class="ct_input_g" 
						style="width:100px; height:19px"  maxLength="9" />
			- 
			<input type="text" name="phone3" class="ct_input_g" 
						style="width:100px; height:19px"  maxLength="9" />
			<input type="hidden" name="phone" class="ct_input_g"  >
		</td>
	</tr>

	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">�̸��� </td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="26">
						<input type="text" name="email" class="ct_input_g"  style="width:100px; height:19px"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td width="53%"></td>
		<td align="right">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						����
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
					</td>
					<td width="30"></td>					
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						���
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

</form>
</body>
</html>
 -->