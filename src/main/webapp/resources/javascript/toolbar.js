// toolbar�� form�� ���� name�� ó���Ѵ�.
$("li.dropdown-item:contains('�� ���� ����')").on("click", function() {
	$("form[name='getUser']").submit();
})

// product autoComplete
$("#searchKeyword").on("keydown", function() {
	let requestURL = "/rest/product/getProdNames";
	
	$.ajax({
		url : requestURL,
		method : "POST",
		dataType : "json",
		contentType : "application/json",
		success : function(JSONData, status) {
			$("#searchKeyword").autocomplete({
				source : JSONData
			});
		}
	}); 
});

console.log("toolbar.js");