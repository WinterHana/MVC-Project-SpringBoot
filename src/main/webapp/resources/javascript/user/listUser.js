// autoComplete
$("input[name='searchKeyword']").on("keydown", function() {
	let requestURL = ""
	
	if($("select[name='searchCondition']").val() === "userId") {
		requestURL = "/rest/user/getUserIds";
	} else {
		requestURL = "/rest/user/getUserNames";
	}
	
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

// ���� ��ũ��
let lastScroll = 0;			// ��ũ���� �Ʒ��� �������� ���� �ø����� Ȯ��
let page = 1;				// ó�� page 1�� �ҷ����� ���� ������ �ҷ�����
let isGetList = true;
let isGetDataFinish = true;	// �����͸� ��� �� ���� Ȯ��

function getUserData(searchCondition, searchKeyword) {
	
	console.log("searchCondition : " + searchCondition);
	console.log("searchKeyword : " + searchKeyword);
	
	// ���� �� �� �� �ϳ��� null �̸� (null, null)�� �˻��Ѵ�.
	if(searchCondition == null || searchKeyword == null) {
		searchCondition = null;
		searchKeyword = null;
	}
	
	let obj = {
		"searchCondition" : searchCondition,
   		"searchKeyword" : searchKeyword
	}
	
	$.ajax({
		url : "/rest/user/listUser/" + page,
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
	let html = '<div class="row">'
				+ '<div class = "col-md-3"></div>'
				+ '<div class="col-md-2 bg-primary p-2 text-dark bg-opacity-25 border border-dark">'
				+ '<div class="card" style="width: 12rem; height: 12rem;">'
				+ '<img src="#" class="card-img-top" alt="" width="200" height="150" />'
				+ '</div></div>'
				+ '<div class="col-md-4 bg-primary p-2 text-dark bg-opacity-25 border border-dark">'
				+ '<div class="card-body">'
				+ '<br />'
				+ '<h5 class="card-title">���̵� : ' + result.userId + '</h5>'
				+ '<h5 class="card-text">�̸� : ' + result.userName + '</h5>'
				+ '<br /><br />'
				+ '<form action = "/user/getUser/' +  result.userId +'" method = "post">'
				+ '<button class="btn btn-danger">�� ����</button>'
				+ '</form>'
				+ '</div></div></div><br/>'
				
	// Debug
	// console.log(html);
	
	$("div.userList").append(html);
	
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
/*		console.log("���� ��ũ�� ��ġ : " + currentScroll);
		console.log("������ â�� ���� : " + height);
		console.log("��ü ������ ���� : " +  documentHeight);*/
		if(currentScroll + height + 1 >= documentHeight 
		&& isGetList === true
		&& isGetDataFinish === true) {
			console.log("��!");
			isGetDataFinish = false;			// ������ �������� ��� ����
			getUserData($("select[name='searchCondition']").val(), $("input[name='searchKeyword']").val());
		}
	}
	
	lastScroll = currentScroll;
});


// ù ���� : page 1
getUserData(null ,null);

// Searching
function searchList() {
	$("div.userList").html("");
	page = 1;
	isGetList = true;
	getUserData($("select[name='searchCondition']").val(), $("input[name='searchKeyword']").val());
}

$("button.btn:contains('�˻�')").on("click", function() {
	searchList()
})

// Enter�� �Է�
$("input[name='searchKeyword']").on("keypress", function(event) {
	if (event.which === 13) {
		event.preventDefault(); // �⺻ ���� ���� (�� ���� ��)
		searchList();
	}
});

// Debug
console.log("listUser.js");


