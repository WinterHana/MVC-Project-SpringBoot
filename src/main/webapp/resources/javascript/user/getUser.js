$("button[name='update']").on("click", function() {
	$("form[name='updateUser']").submit();
});

$("button[name='back']").on("click", function() {
	history.go(-1);
});

$("button[name='delete']").on("click", function() {
	result = window.confirm("������ Ż���Ͻðڽ��ϱ�?");
	if(result) {
		let url = "/user/deleteUser";
		$("form[name='deleteUser']").attr("method", "POST").attr("action", url).submit();
	}
});

console.log("getUser.js");