function fncAddProduct(){
	//Form ��ȿ�� ����
	let name = $("input[name='prodName']").val();
	let detail = $("input[name='prodDetail']").val();
	let manuDate = $("input[name='manuDate']").val();
	let price = $("input[name='price']").val();
	let count = $("input[name='count']").val();

	if(name == null || name.length<1){
		alert("��ǰ���� �ݵ�� �Է��Ͽ��� �մϴ�.");
		return;
	}

	if(detail == null || detail.length<1){
		alert("��ǰ�������� �ݵ�� �Է��Ͽ��� �մϴ�.");
		return;
	}

	if(manuDate == null || manuDate.length<1){
		alert("�������ڴ� �ݵ�� �Է��ϼž� �մϴ�.");
		return;
	}

	if(price == null || price.length<1){
		alert("������ �ݵ�� �Է��ϼž� �մϴ�.");
		return;
	}

	if(count == null || parseInt(count) < 0){
		alert("������ �ݵ�� �Է��ؾ� �մϴ�. �Ǵ� ������� �մϴ�.");
		return;
	}
	
	result = window.confirm("��ǰ�� �߰��Ͻðڽ��ϱ�?");
	if(result) {
		$("form").submit();
	}
}


$("button[name='add']").on("click", function() {
	fncAddProduct();
});

$("button[name='back']").on("click", function() {
	history.go(-1);
})

console.log("addProduct.js");