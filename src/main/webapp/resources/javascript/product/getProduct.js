function purchaseMenu() {
	// ��ȿ�� Ȯ��
	let count = parseInt($("#count").text());

	if(count <= 0) {
		alert("��ǰ�� �����Ǿ����ϴ�!");
		return;
	}
	
	$("div[name='purchaseMenu']").css("display", "block");
}

// ���� ������Ʈ
function updateTotalPrice() {
	let price = Number($("#price").text());
	let purchaseCount = Number($("input[name='prodCount']").val());
	$("#totalPrice").text(price * purchaseCount);
}

$("button[name='down']").on("click", function() {
	previousNum = Number($("input[name='prodCount']").val());
	if(previousNum > 0) {
		$("input[name='prodCount']").val(previousNum - 1);
	}
	
	updateTotalPrice();
});

$("button[name='up']").on("click", function() {
	previousNum = Number($("input[name='prodCount']").val());
	$("input[name='prodCount']").val(previousNum + 1);
	
	updateTotalPrice();
});

// Button Control
$("button[name='back']").on("click", function() {
	history.go(-1);
});

$("button[name='delete']").on("click", function() {
	result = window.confirm("������ �����Ͻðڽ��ϱ�?");
	if(result) {
		$("form[name='deleteProduct']").submit();
	}
});

$("button[name='update']").on("click", function() {
	$("form[name='updateProduct']").submit();
});

$("button[name='purchase']").on("click", function() {
	purchaseMenu();
});

// PurchaseMenu
$("div[name='purchaseMenu']").css("display", "none");

$("button[name='purchaseComplete']").on("click", function() {
	let count = parseInt($("input[name='prodCount']").val());

	if(count <= 0) {
		alert("������ ��ǰ ������ �Է����ּ���!");
		return;
	}
	
	$("form[ name = 'purchaseForm']").submit();
});