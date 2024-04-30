function fncAddProduct(){
	//Form 유효성 검증
	let name = $("input[name='prodName']").val();
	let detail = $("input[name='prodDetail']").val();
	let manuDate = $("input[name='manuDate']").val();
	let price = $("input[name='price']").val();
	let count = $("input[name='count']").val();

	if(name == null || name.length<1){
		alert("상품명은 반드시 입력하여야 합니다.");
		return;
	}

	if(detail == null || detail.length<1){
		alert("상품상세정보는 반드시 입력하여야 합니다.");
		return;
	}

	if(manuDate == null || manuDate.length<1){
		alert("제조일자는 반드시 입력하셔야 합니다.");
		return;
	}

	if(price == null || price.length<1){
		alert("가격은 반드시 입력하셔야 합니다.");
		return;
	}

	if(count == null || parseInt(count) < 0){
		alert("개수는 반드시 입력해야 합니다. 또는 양수여야 합니다.");
		return;
	}
	
	result = window.confirm("제품을 추가하시겠습니까?");
	if(result) {
		$("form").submit();
	}
}

// 태그 추가
var tag = {};
var counter = 0;

// 입력한 값을 태그로 생성한다.
function addTag (value) {
    tag[counter] = value;
    counter++; // del-btn 의 고유 id 가 된다.
}

// tag 안에 있는 값을 array type 으로 만들어서 넘긴다.
function marginTag () {
    return Object.values(tag).filter(function (word) {
        return word !== ""; // 빈거 아닌거만 걸러내기
    });
}

 $("input[name='inputTag']").on("keypress", function (e) {
     let self = $(this);
     
     //엔터나 스페이스바 눌렀을때 실행
     if (e.key === "Enter" || e.keyCode == 32) {

        let tagValue = self.val(); // 값 가져오기
        
        // 해시태그 값 없으면 실행X
        if (tagValue !== "") {

            // 같은 태그가 있는지 검사한다. 있다면 해당값이 array 로 return 된다.
            let result = Object.values(tag).filter(function (word) {
                return word === tagValue;
            })
            
            // 해시태그가 중복되었는지 확인 : result
            if (result.length == 0) { 
                $("#tag-list").append("<li class='badge bg-primary'>"+tagValue+"<span class='del-btn' idx='"+counter+"'> x </span></li> ");
                addTag(tagValue);
                self.val("");
            } else {
                alert("태그값이 중복됩니다.");
            }
        }
        
     	let value = marginTag(); // return array
     	console.log(value);
     	$("input[name='tagList']").val(value);
     	
     	e.preventDefault(); // SpaceBar 시 빈공간이 생기지 않도록 방지
    }
});

// 삭제 버튼 
// 인덱스 검사 후 삭제
$(document).on("click", ".del-btn", function () {
	let tagValue = $(this).closest("li").text().trim().split(" ")[0]; // 값 가져오기
	console.log(tagValue);
	
	var index = $(this).attr("idx");
	
    tag[index] = "";
    $(this).closest("li").remove();
});


$("button[name='add']").on("click", function() {
	fncAddProduct();
});

$("button[name='back']").on("click", function() {
	history.go(-1);
})

console.log("addProduct.js");