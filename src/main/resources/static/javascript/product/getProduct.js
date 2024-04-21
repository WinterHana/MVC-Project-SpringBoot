function purchaseMenu() {
	// 유효성 확인
	let count = parseInt($("#count").text());

	if(count <= 0) {
		alert("상품이 매진되었습니다!");
		return;
	}
	
	$("div[name='purchaseMenu']").css("display", "block");
}

// 가격 업데이트
function updateTotalPrice() {
	let price = Number($("#price").text());
	let purchaseCount = Number($("input[name='prodCount']").val());
	$("#totalPrice").text(price * purchaseCount);
	$("input[name='totalPrice']").val(price * purchaseCount);
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
	result = window.confirm("정말로 삭제하시겠습니까?");
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
	let count = parseInt($("#count").text());
	let prodCount = parseInt($("input[name='prodCount']").val());
	let totalPrice = parseInt($("#totalPrice").text());
	let userMileage = parseInt($("#userMileage").text());
	
	if(prodCount <= 0) {
		alert("구매할 상품 개수를 입력해주세요!");
		return;
	}
	
	if(prodCount > count) {
		alert("현재 있는 재고보다 많이 구입할 수 없습니다!");
		return;
	}
	
	if(totalPrice > userMileage) {
		alert("잔액이 부족합니다!");
		return;
	}
	
	$("form[ name = 'purchaseForm']").submit();
});

// Cart에 대한 상호작용
// 1. addCart
$("button[name='cart']").on('click', function() {
	let prodNo = parseInt($("input[name='prodNo']").val());
	let prodCount = parseInt($("input[name='prodCount']").val());
	let userId = $("input[name='userId']").val();
	let price = parseInt($("#price").text());
	
/*	console.log("price : " + price);
	console.log("userId : " + userId);
	console.log("prodCount : " + prodCount);
	console.log("prodNo : " + prodNo);*/
	
	let obj = {
		'userId' : userId,
		'prodNo' : prodNo,
		'price' : price,
		'count' : prodCount
	};
	
	if($(this).text().includes("찜하기")) {
		let success = false;
		
		$.ajax({
			url : "/rest/product/addCart",
			method : "POST",
			dataType : "json",		
			contentType : "application/json",		
			data : JSON.stringify(obj),
			

		});
		
		alert("찜 등록이 완료되었습니다!");
		$(this).text("찜 취소하기");
		
	} else {
		$.ajax({
			url : "/rest/product/deleteCart",
			method : "POST",
			dataType : "json",
			contentType : "application/json",
			data : JSON.stringify(obj),
			
			error : function() {
				alert("찜 삭제에 실패했습니다..");
				return;
			}
		});
		
		alert("찜 등록이 해제되었습니다!");
		$(this).text("찜하기");
	}
});
