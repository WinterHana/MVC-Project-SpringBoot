function limitUpdate(tranNo, tranCode) {
	if(tranCode == '002' || tranCode == '003') {
		alert("��ǰ�� ������̰ų� ��� �Ϸ��� �� ������ �� �����ϴ�.");
		return;
	} else {
		let url = "/purchase/updatePurchaseView/" + tranNo;
		$("form[name='updatePurchase']").submit();
	}
}
	
$("button[name='update']").on("click", function() {
    var tranNo = $("input[name = 'tranNo']").val();
    var tranCode = $("input[name = 'tranCode']").val();
	limitUpdate(tranNo, tranCode);
})
	
$("button[name='delete']").on("click", function() {
	result = window.confirm("������ �����Ͻðڽ��ϱ�?");
	if(result) {
		let url = "/purchase/deletePurchase";
		$("form[name='deletePurchase']").attr("method", "POST").attr("action", url).submit();
	}
})

$("button[name='back']").on("click", function() {
	history.go(-1);
})
// https://horangi.tistory.com/417

		