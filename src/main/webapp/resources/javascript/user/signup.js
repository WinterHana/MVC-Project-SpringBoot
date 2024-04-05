function fncAddUser() {
	// Form ��ȿ�� ����
	let id = $("input[name='userId']").val();
	let pw = $("input[name='password']").val();
	let pw_confirm = $("input[name = 'password2']").val();
	let name = $("input[name = 'userName']").val();
	
	// Validation Check
	if(id == null || id.length <1) {
		alert("���̵�� �ݵ�� �Է��ϼž� �մϴ�.");
		return;
	}
	
	if(pw == null || pw.length <1){
		alert("�н������  �ݵ�� �Է��ϼž� �մϴ�.");
		return;
	}
	
	if(pw_confirm == null || pw_confirm.length <1){
		alert("�н����� Ȯ����  �ݵ�� �Է��ϼž� �մϴ�.");
		return;
	}
	
	if(name == null || name.length <1){
		alert("�̸���  �ݵ�� �Է��ϼž� �մϴ�.");
		return;
	}
	
	if(pw != pw_confirm) {
		alert("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		$("input:text[name='password2']").focus();
		return;
	}
	
	// ��ȭ��ȣ �Է� ���ο� ���Ͽ�
	let value = "";
	if($("input:text[name='phone2']").val() != "" 
			&& $("input:text[name='phone3']").val() != "") {
		value = $("option:selected").val() 
		+ "-" + $("input[name='phone2']").val()
		+ "-" + $("input[name='phone3']").val();
	}
	
	$("input:hidden[name='phone']").val(value);
	
	$("form").attr("method", "POST").attr("action", "/user/addUser").submit();
};

function fncCheckDuplication() {
	if($("#userId").val() != null && $("#userId").val().length > 0) {
		$("form").attr("method", "POST");
		$("form").submit();
	} else {
		alert("���̵�� �ݵ�� �Է��ϼž� �մϴ�.");
	}
	$("#userId").focus();
};

function PortalJuminCheck(fieldValue){
    var pattern = /^([0-9]{6})-?([0-9]{7})$/; 
	var num = fieldValue;
    if (!pattern.test(num)) return false; 
    num = RegExp.$1 + RegExp.$2;

	var sum = 0;
	var last = num.charCodeAt(12) - 0x30;
	var bases = "234567892345";
	for (var i=0; i<12; i++) {
		if (isNaN(num.substring(i,i+1))) return false;
		sum += (num.charCodeAt(i) - 0x30) * (bases.charCodeAt(i) - 0x30);
	}
	var mod = sum % 11;
	return ((11 - mod) % 10 == last) ? true : false;
}

$("button.btn:contains('�����ϱ�')").on("click", function() {
	fncAddUser();
});

$("input[name='email']").on("change", function() {
	let email = $("input[name='email']").val();
	
	if(email != "" && (email.indexOf("@") < 1 || email.indexOf(".") == -1)) {
		alert("�̸��� ������ �ƴմϴ�.");
		$("input[name='email']").val("");
	}
});

$("button.btn:contains('�ߺ� Ȯ��')").on("click", function() {
	if($("#userId").val() == null || $("#userId").val().length <= 0) {
		alert("���̵�� �ݵ�� �Է��ϼž� �մϴ�.");
		return;
	}
	
	let requestURL = "/rest/user/checkDuplication";
	let obj =  {
		"userId" : 	$("#userId").val()
	}
	
	$.ajax({
		url : requestURL,
		method : "POST",
		contentType : "application/json",
		data : JSON.stringify(obj),
		success : function(JSONData, status) {
			let result = JSONData.result;
			if(result == true) {
				$("#duplicationResult").html("��� �����մϴ�.");
			} else {
				$("#duplicationResult").html("��� �Ұ����մϴ�.");
			}
		}
	}); 
});

$("input[name='ssn']").on("change", function() {
	var ssn1, ssn2; 
	var nByear, nTyear; 
	var today; 

	// ssn = document.detailForm.ssn.value;
	ssn = $("input[name='ssn']").val();
	
	// ��ȿ�� �ֹι�ȣ ������ ��츸 ���� ��� ����
	// PortalJuminCheck �Լ��� CommonScript.js �� ���� �ֹι�ȣ üũ �Լ��� 
	if(!PortalJuminCheck(ssn)) {
		alert("�߸��� �ֹι�ȣ�Դϴ�.");
		return false;
	}
});



/* function fncCheckDuplication() {
	popWin = window.open("/user/checkDuplication.jsp","popWin", "left=300,top=200,width=300,height=200,marginwidth=0,marginheight=0,scrollbars=no,scrolling=no,menubar=no,resizable=no");
} */

console.log("signup.js")