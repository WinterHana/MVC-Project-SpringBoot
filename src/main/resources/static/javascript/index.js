function getProductDataIndex() {
	
	$.ajax({
		url : "/rest/product/getWeatherRecommendProduct/3",
		method : "POST",
		dataType : "json",
		contentType : "application/json",
		success : function(JSONData) { 
			console.log(JSONData);
			
			resultList = JSONData.resultList;
			
			$(resultList).each(
				function() {
					renderList(this);
				}
			)
			
			$("#weather").text(JSONData.weather)
		},
		error : function() {
		}, 
		complete : function() {
		}
	})
}

// 각 데이터마다 데이터 랜더링
function renderList(result) {
	let html = 	'<div class="col-sm-4">'
				+ '<div class="card text-center" style="height:40rem;">'
				+ '<img src="/img/uploadFiles/' + (result.fileName != null ? result.fileName[0] : '') +'" class="card-img-top">'
				+ '<div class="card-header">' + result.prodName + '</div>'
				+ '<div class="card-body">'
				+ '<p class="card-text">남은 수량 : ' + result.count + '</p>'
				+ '<p class="card-text">가격 : ' + result.price + '</p>'
				+ '<form action = "/product/getProduct/' +  result.prodNo +'" method = "post">'
				+ '<button class="btn btn-danger">상세 보기</button>'
				+ '</div>'
				+ '<div class = "card-footer">'
				+ '<div id="tag-list' + result.prodNo + '"></div>'
				+ '</div>'
				+ '</form>'
				+ '</div></div></div>'
	
	$("div[name='productList']").append(html);
	addProductTag(result.prodNo);
	
	imageDefault();
}

function addProductTag(prodNo) {
	let count = 0;
     $.ajax({
		url : "/rest/product/getTagFromProduct/" + prodNo,
		method : "POST",
		dataType : "json",
		contentType : "application/json",
		success : function(JSONData) {	
			$(JSONData).each( function() {
				$("#tag-list" + prodNo).append("<span class='badge bg-primary'>"+this.tagName+"</span> ");
				count++;
				if (count >= 3) {
					return false;
				}
			});
		}
	}); 
}

getProductDataIndex();