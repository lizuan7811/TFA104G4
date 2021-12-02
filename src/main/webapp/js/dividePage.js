$(function() {
	///當前頁數
	var nowPage = 1;
	///總的頁數
	var totalPage = 4;
	////每行限制數///
	var limitRows = 4;
	/////總的行數
	var totalRows = 0;
	////開始的行數
	var startRows = 0;
	////結束的行數,在資料庫中是用limit實現的,不需要此變數
	var endRows = 4;
	////存所有行的陣列
	var list = new Array();
	///////找表格元素及其它各元素///////
	var tab = $('#info');
	var now = $('#now');
	var total = $('#total');
	///////1.初始化資料,運用jQuery的get請求///////
	$.get("json/dividePage.json", function(data, stauts) {
		///獲取所需資料/////
		///獲取最大頁數,js除時要取小數,此處要取整
		var tg = Math.floor(data.length / limitRows);
		if (Math.floor(data.length % limitRows) == 0) {
		totalPage = tg;
		} else {
		totalPage = tg + 1;
		}
		///存入到陣列////
		list = data;
		/////初始化顯示資料///
		now.html(nowPage);
		total.html(totalPage);
		disp(nowPage, list);
	});

	// ///2.按頁號顯示資料
	function disp(n, d) {
	///先刪除之前的,再建立新的
		$("tr").remove(".hqy");
		////求每頁從1開始,要得到陣列開始下標要減一為從0開始
		startRows = (n - 1) * limitRows;
		endRows = startRows + limitRows;
		for (var i = startRows; i < endRows; i++) {
			var t = "<tr class='hqy'>" + "<td>" + (101 + i) + "</td>" +
			"<td>" + d[i].name + "</td>" +
			"<td>" + d[i].age + "</td>" + "</tr>"
			tab.append(t);
	} //for
	//$("tr").remove(".hqy");
	} //disp
	// //3.下一頁////
	$("#next").click(function() {
		++nowPage;
		//alert(nowPage)
		if (nowPage > totalPage) {
		////此處減一是因為陣列下標是最大長度減一的
		nowPage = totalPage;
		}
		//先顯示頁數後展示數
		now.html(nowPage);
		///要後顯示,否則頁數切換不正確
		disp(nowPage, list);
	});
	///////4.上一頁/////

	$("#first").click(function() {
		--nowPage;
		if (nowPage < 1) {
		////此處為0,傳遞的是陣列的最小標
		nowPage = 1;
		}
		//先顯示頁數後展示資料
		now.html(nowPage);
		disp(nowPage, list);
	});
	/////5.首頁
	$("#start").click(function() {
		//先顯示頁數後展示資料
		nowPage = 1; //注意變數的值要設
		now.html(nowPage);
		disp(nowPage, list);
	});

	/////6.尾頁

	$("#end").click(function() {
		//先顯示頁數後展示資料
		nowPage = totalPage; //注意變數的值要設
		now.html(nowPage);
		disp(nowPage, list);
	});
	//////7.跳轉到某一頁
	$("#go").click(function() {
		var j = $("#inp").val().trim();
		//alert(j=='NaN');
		if (j == "" || j == null || j == 'NaN') { //使用者可能沒輸入
		nowPage = 1;
		} else {//不是空則轉換為整型數
		nowPage = parseInt(j);
		}
		//先顯示頁數後展示資料,注意變數的值要設
		if (nowPage > totalPage) {
		nowPage = totalPage;
		}
		$("#inp").val(nowPage);
		now.html(nowPage);
		disp(nowPage, list);
	});
});