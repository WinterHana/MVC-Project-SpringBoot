function getProductCountByTagName() {
	let requestURL = "/rest/product/getProductCountByTagName";
	
	$.ajax({
		url : requestURL,
		method : "POST",
		dataType : "json",
		contentType : "application/json",
		success : function(JSONData) {
				console.log(JSONData);
				let count = 0;
				let tagNames = [];
				let counts = [];
				let colors = [];

				JSONData.forEach(item => {
					tagNames.push(item.tagName);
					counts.push(item.count);
					
					let rgbString;
					if(count % 3 == 0) {
						rgbString = 'rgb(255, 99, 132)';
					} else if (count % 3 == 1) {
						rgbString = 'rgb(54, 162, 235)';
					} else if (count % 3 == 2) {
						rgbString = 'rgb(255, 205, 86)';
					}
					
					count++;
					colors.push(rgbString);
				})
				
				console.log(tagNames);
				console.log(counts);
				console.log(colors);
				
				let target =  document.getElementById('productCountByTagName');
				new Chart(target, {
					type : 'pie',
					data : {
						labels : tagNames,
						datasets:[{
							label: 'tag',
							data : counts,
							backgroundColor : colors
						}]
					},
					hoverOffset: 4,
					options : {
						responsive : false
					}
				});
			}
		});
}

function getProductCountByTransaction() {
	let requestURL = "/rest/product/getProductCountByTransaction";
	
	$.ajax({
		url : requestURL,
		method : "POST",
		dataType : "json",
		contentType : "application/json",
		success : function(JSONData) {
				console.log(JSONData);
				let count = 0;
				let prodNames = [];
				let counts = [];
				let colors = [];

				JSONData.forEach(item => {
					prodNames.push(item.prodName);
					counts.push(item.count);
					
					let rgbString;
					if(count % 3 == 0) {
						rgbString = 'rgb(255, 99, 132)';
					} else if (count % 3 == 1) {
						rgbString = 'rgb(54, 162, 235)';
					} else if (count % 3 == 2) {
						rgbString = 'rgb(255, 205, 86)';
					}
					
					count++;
					colors.push(rgbString);
				})
				
				let target =  document.getElementById('productCountByTransaction');
				new Chart(target, {
					type : 'polarArea',
					data : {
						labels : prodNames,
						datasets:[{
							label: 'name',
							data : counts,
							backgroundColor : colors
						}]
					},
					hoverOffset: 4,
					options : {
						responsive : false
					}
				});
			}
		});
}

function getTransactionTotalPriceByOrderDate() {
	let requestURL = "/rest/purchase/getTransactionTotalPriceByOrderDate";
	
	$.ajax({
		url : requestURL,
		method : "POST",
		dataType : "json",
		contentType : "application/json",
		success : function(JSONData) {
				console.log(JSONData);
				let count = 0;
				let orderDates = [];
				let totalPrices = [];
				let rgbString = 'rgb(255, 99, 132)';
				
				JSONData.forEach(item => {
					orderDates.push(item.orderDate);
					totalPrices.push(item.totalPrice);
				})
				
				let target =  document.getElementById('transactionTotalPriceByOrderDate');
				new Chart(target, {
					type : 'line',
					data : {
						labels : orderDates,
						datasets:[{
							label: 'totalPrice',
							data : totalPrices,
							fill : true,
							borderColor : rgbString
						}]
					},
					options : {
						responsive : false
					}
				});
			}
		});
}

$(document).ready(function() {
	getProductCountByTagName();
	getProductCountByTransaction();
	getTransactionTotalPriceByOrderDate();
});
