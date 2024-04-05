// autoComplete
$("input[name='searchKeyword']").on("keydown", function() {
	let requestURL = "/rest/product/getProdNames";
	
	$.ajax({
		url : requestURL,
		method : "POST",
		dataType : "json",
		contentType : "application/json",
		success : function(JSONData, status) {
			$("input[name='searchKeyword']").autocomplete({
				source : JSONData
			});
		}
	}); 
});

// �˻� ���� ��ȭ
window.onload = showContentBySelectBox;

function showContentBySelectBox() {
	let selectOption = $("select[name='searchCondition']").val();

	if(selectOption === "price") {
		$("div[name='prodNameContent']").css("display", "none");
		$("div[name='priceContent']").css("display", "block");
	} 

	else {
		$("div[name='prodNameContent']").css("display", "block");
		$("div[name='priceContent']").css("display", "none");
	}
}
$("select[name='searchCondition']").on("change", function() {
	showContentBySelectBox();
})

function fncGetProductList(currentPage) {
	let url = '/product/listUserProduct/' + currentPage;	
		$("form").attr("method", "POST").attr("action", url).submit();
}

function submitDetailForm() {
	$("form").attr("method", "POST").submit();
}

// ���� ��ũ��
let lastScroll = 0;			// ��ũ���� �Ʒ��� �������� ���� �ø����� Ȯ��
let page = 1;					// ó�� page 1�� �ҷ����� ���� ������ �ҷ�����
let isGetList = true;
let isGetDataFinish = true;	// �����͸� ��� �� ���� Ȯ��

function getProductData(
	searchCondition, searchKeyword, 
	searchKeywordSub, searchKeywordThird, sortCondition) {
	
	console.log("searchCondition : " + searchCondition);
	console.log("searchKeyword : " + searchKeyword);
	console.log("searchKeywordSub : " + searchKeywordSub);
	console.log("searchKeywordThird : " + searchKeywordThird);
	console.log("sortCondition : " + sortCondition);
	
	let obj = {
		"searchCondition" : searchCondition,
   		"searchKeyword" : searchKeyword,
   		"searchKeywordSub": searchKeywordSub,
        "searchKeywordThird": searchKeywordThird,
        "sortCondition": sortCondition,
	}
	
	$.ajax({
		url : "/rest/product/listProduct/" + page,
		method : "POST",
		dataType : "json",
		contentType : "application/json",
		data : JSON.stringify(obj),
		success : function(JSONData) {
			let resultList = JSONData.list; 
			
			console.log(resultList);
			
			// �� �̻� ������ ������ ���� �� ���� ����
			if(resultList == null || resultList.length <= 0) {
				isGetList = false;
				return;
			}
			
			$(resultList).each(
				function() {
					renderList(this);
				}
			)
			// �ٽ� ������ ��� ����
			isGetDataFinish = true;
		},
		error : function() {
			page = page;
			
		}, 
		complete : function() {
			page = page + 1;
		}
	})
}

// �� �����͸��� ������ ������
function renderList(result) {
	let html = 	'<div class="col-sm-3">'
				+ '<div class="card text-center" style="width: 20rem;">'
				+ '<img src="/img/uploadFiles/' + (result.fileName != null ? result.fileName[0] : '') +'" class="card-img-top">'
				+ '<div class="card-body">'
				+ '<h5 class="card-title">' + result.prodName +'</h5>'
				+ '<p class="card-text">���� ���� : ' + result.count + '</p>'
				+ '<p class="card-text">���� : ' + result.price + '</p>'
				+ '<form action = "/product/getProduct/' +  result.prodNo +'" method = "post">'
				+ '<button class="btn btn-danger">�� ����</button>'
				+ '</form>'
				+ '</div></div></div>'
				
	// Debug
	// console.log(html);
	
	$("div[name='productList']").append(html);
	
	imageDefault();
}

// ��ũ�� ����
$(document).scroll(function(){
	// ���� ��ũ�� ��ġ
	let currentScroll = $(window).scrollTop();
	
	// ������ â�� ����
	let height = $(window).height();
	
	// ��ü ������ ����
	let documentHeight = $(document).height();
	
	// ��ũ���� �Ʒ��� �������� ��
	if(currentScroll > lastScroll) {
		console.log("���� ��ũ�� ��ġ : " + currentScroll);
		console.log("������ â�� ���� : " + height);
		console.log("��ü ������ ���� : " +  documentHeight);
		if(currentScroll + height + 1 >= documentHeight 
			&& isGetList === true 
			&& isGetDataFinish === true) {
			console.log("��!");
			isGetDataFinish = false;			// ������ �������� ��� ����
			getProductData(
			$("select[name='searchCondition']").val(), 
			$("input[name='searchKeyword']").val(),
			$("input[name='searchKeywordSub']").val(),
			$("input[name='searchKeywordThird']").val(),
			$("select[name='sortCondition']").val());
		}
	}
	
	lastScroll = currentScroll;
});


// ù ���� : page 1
getProductData("prodName", "", "", "", "prodName");

// Searching
function searchList() {
	$("div[name='productList']").html("");
	page = 1;
	isGetList = true;
	getProductData(
		$("select[name='searchCondition']").val(), 
		$("input[name='searchKeyword']").val(),
		$("input[name='searchKeywordSub']").val(),
		$("input[name='searchKeywordThird']").val(),
		$("select[name='sortCondition']").val());
}

$("button.btn:contains('�˻�')").on("click", function() {
	searchList()
})

// Enter�� ���� ����
$("input").on("keypress", function(event) {
	if(event.which === 13) {
		event.preventDefault(); // �⺻ ���� ���� (�� ���� ��)
		searchList()
	}
})

console.log("listProduct.js");
