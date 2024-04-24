// form을 jquery로 변환
$.fn.serializeObject = function(){
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
    	var name = $.trim(this.name),
    		value = $.trim(this.value);
    	
        if (o[name]) {
            if (!o[name].push) {
                o[name] = [o[name]];
            }
            o[name].push(value || '');
        } else {
            o[name] = value || '';
        }
    });
    return o;
};
	

$(document).ready(function() {
    $('input[name="count"]').on('input', function() {
        let count = parseInt($(this).val());
        let price = parseInt($(this).closest('tr').find('.align-middle.text-center:eq(1)').text());
        let remainCount = parseInt($(this).closest('tr').find('.align-middle.text-center:eq(3)').text());
        
        let prodNo = $(this).closest('tr').find('span').attr('id');
        
		if(count > remainCount) {
			alert("남은 개수보다 많이 구입할 수 없습니다!");
			$(this).val(0);
			count = 0;
		}
		
		let totalPrice = count * price;
		
		if(isNaN(totalPrice)) {
			totalPrice = 0;
		}
		
        $('#' + prodNo).text(totalPrice);
        
        calcTotalTransactionPrice();
    });
});

function calcTotalTransactionPrice() {
	    let totalTransactionPrice = 0;
	    let totalCount = 0;
	    
        $("input[name='count']").each(function() {
			    let eachCount = parseInt($(this).val());
        		let eachPrice = parseInt($(this).closest('tr').find('.align-middle.text-center:eq(1)').text());
        		let eachTotalPrice = eachCount * eachPrice;
        		
        		if(isNaN(eachTotalPrice)) {
					eachTotalPrice = 0;
				}
				
				if(isNaN(eachCount)) {
					eachCount = 0;
				}
				
        		totalTransactionPrice += eachTotalPrice;
        		totalCount += eachCount;
		});
		
		console.log("totalTransactionPrice : " + totalTransactionPrice);
		console.log("totalCount : " + totalCount);
		
		if(isNaN(totalTransactionPrice)) {
			totalTransactionPrice = 0;
		}
		
		if(isNaN(totalCount)) {
			totalCount = 0;
		}
		
		$("input[name='totalPrice']").val(totalTransactionPrice);
		$("input[name='prodCount']").val(totalCount);
}


$("button[name='purchaseComplete']").on("click", function() {
	let prodCount = parseInt($("input[name='prodCount']").val());
	let totalPrice = $("input[name='totalPrice']").val();
	let userMileage = parseInt($("#userMileage").text());
	
	if(prodCount <= 0) {
		alert("구매할 상품 개수를 입력해주세요!");
		return;
	}
	
	if(totalPrice > userMileage) {
		alert("잔액이 부족합니다!");
		return;
	}
	
	// 구매 정보 로직 저장 시작
	// 1. 구매 정보 저장
	let purchaseFormData = $("form[name='purchaseForm']").serializeObject();
	
	let addTransactionListData = []
	$("form[name='addTransactionList']").each(function() {
		console.log($(this).serializeObject())
		addTransactionListData.push($(this).serializeObject());
	});
	
	console.log(purchaseFormData);
	console.log(addTransactionListData);
	
	let requestData = {
		"purchase" : purchaseFormData,
		"transactionLists" :  addTransactionListData
	}
	
	// 2. 전송
	$.ajax({
		url : "/rest/purchase/addPurchase",
		method : "POST",
		dataType : "json",
		contentType : "application/json",
		data : JSON.stringify(requestData),
		success : function(JSONData) {
			alert("구매 정보 저장에 성공했습니다!");
			console.log(requestData);
		}, 
		error : function() {
			alert("구매 정보 저장에 실패했습니다..");
		}
	});
	
	// 3. 새로고침
	location.reload(true);
});