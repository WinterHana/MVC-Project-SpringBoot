function fncLogin() {
		let id = $("input[name='userId']").val();
		let pw = $("input[name='password']").val();
		
		console.log(id);
		console.log(pw);
		
		if (id == null || id.length < 1) {
			alert('ID �� �Է����� �����̽��ϴ�.');
			$("input[name='userId']").focus();
			return;
		}

		if (pw == null || pw.length < 1) {
			alert('�н����带 �Է����� �����̽��ϴ�.');
			 $("input[name='password']").focus();
			return;
		}

		$("form").attr("method", "POST").attr("action", "/user/login").attr("target", "_parent").submit();
	}

$("#loginButton").on("click", function() {
	console.log("click Button");
	fncLogin();
});

$(".form-control").on("keypress", function(event) {
	if (event.which === 13) {
		event.preventDefault(); // �⺻ ���� ���� (�� ���� ��)
		fncLogin();
	}
});

// check import
console.log("login.js");