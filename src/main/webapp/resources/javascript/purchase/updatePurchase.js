$("button[name='update']").on("click", function() {
	result = window.confirm("�����Ͻðڽ��ϱ�?");
	if(result) {
		$("form[name='updatePurchase']").submit();
	}
})

$("button[name='back']").on("click", function() {
	history.go(-1);
})