// toolbar의 form은 전부 name로 처리한다.
$("li.dropdown-item:contains('내 정보 보기')").on("click", function() {
	console.log("확인");
	$("form[name='getUser']").submit();
})


console.log("toolbar.js");