$(function() {
	var check = false;
	function changeVal(el) {
		var qt = parseFloat(el.parent().children(".qt").html());
		var price = parseFloat(el.parent().children(".price").html());
		var eq = Math.round(price * qt * 100) / 100;

		el.parent().children(".full-price").html(eq + "元");
		changeTotal();
	}

	function changeTotal() {

		var price = 0;

		$(".full-price").each(function(index) {
			price += parseFloat($(".full-price").eq(index).html());
		});

		price = Math.round(price * 100) / 100;
		var fullPrice = Math.round((price) * 100) / 100;

		if (price == 0) {
			fullPrice = 0;
		}

		$(".total span").html(fullPrice);
		getIN();

	}

	$(document).ready(function() {

		$(".remove").click(function() {
			var el = $(this);
			el.parent().parent().addClass("removed");
			window.setTimeout(
				function() {
					el.parent().parent().slideUp('fast', function() {
						el.parent().parent().remove();
						if ($(".product").length == 0) {
							if (check) {
								$(".container").hide;
								$("#foodtype").css("display", "none");
							} else {
								$("#cart").html("<br><br><br><br><h2>尚無食材/食譜</h2><br><br><br><br><br><br><br>");
								$("#next").remove();
								$("#del_return").css("display", "block");
							}
						}
					});
				}, 200);
		});


		$(".btn").on("click", function() {
			$("#pay_info").show();
			$(".btn").css("visibility", "hidden");
		})

		$(".fb_btn").on("click", function() {
			getPayDetail();
		});

		$(".qt-plus").click(function() {
			$(this).parent().children(".qt").html(parseInt($(this).parent().children(".qt").html()) + 1);

			$(this).parent().children(".full-price").addClass("added");

			var el = $(this);
			$(".qt").attr("quan", parseInt($(this).parent().children(".qt").html()));
			window.setTimeout(function() { el.parent().children(".full-price").removeClass("added"); changeVal(el); }, 150);
		});

		$(".qt-minus").click(function() {

			child = $(this).parent().children(".qt");

			if (parseInt(child.html()) > 1) {
				child.html(parseInt(child.html()) - 1);
				$(".qt").attr("quan", parseInt($(this).parent().children(".qt").html()));
			}

			$(this).parent().children(".full-price").addClass("minused");

			var el = $(this);
			window.setTimeout(function() { el.parent().children(".full-price").removeClass("minused"); changeVal(el); }, 150);
		});

		window.setTimeout(function() { $(".is-open").removeClass("is-open") }, 1200);

		$(".btn").click(function() {
			check = true;
			$(".remove").click();
		});
	});


	var state = 0;
	var stateMax = 2;

	function next() {
		console.log("Next", state);
		// browser side functions here
	}

	function back() {
		console.log("Back", state);
		// browser side functions here
	}

	$("#next").click(function() {
		if (state < stateMax) {
			next();

			state += 1;

			// Enables 'back' button if disabled
			$("#back").removeClass("disabled");

			// Adds class to make nodes green
			$(".nConfirm" + state).each(function() {
				$(this).addClass("done");
			});

			// Progress bar animation
			var pBar = (state / stateMax) * 100;
			$(".pBar").css("width", `${pBar}%`);

			// Disables 'next' button if end of steps
			if (state == 2) {
				$("#next").addClass("disabled");
			}
		}
	});

	$(".final_btn").click(function() {
		if (state < stateMax) {
			next();

			state += 1;

			// Enables 'back' button if disabled
			$("#back").removeClass("disabled");

			// Adds class to make nodes green
			$(".nConfirm" + state).each(function() {
				$(this).addClass("done");
			});

			// Progress bar animation
			var pBar = (state / stateMax) * 100;
			$(".pBar").css("width", `${pBar}%`);

			// Disables 'next' button if end of steps
			if (state == 2) {
				$("#next").addClass("disabled");
			}
		}
	});

	// $("#back").click(function () {
	// 	if (state > 0) {
	// 		back();

	// 		// Enables 'next' button if disabled
	// 		$("#next").removeClass("disabled");

	// 		// removes class that makes nodes green
	// 		$(".nConfirm" + state).each(function () {
	// 			$(this).removeClass("done");
	// 		});

	// 		state -= 1;

	// 		// Progress bar animation
	// 		var pBar = (state / stateMax) * 100;
	// 		$(".pBar").css("width", `${pBar}%`);

	// 		// Disables 'back' button if end of steps
	// 		if (state == 0) {
	// 			$("#back").addClass("disabled");
	// 		}
	// 	}
	// });

})

var payName;
var payAddr;
var payComm;
var payMode;
var mp = new Map();
var host = window.location.host;
var path = window.location.pathname;
var webContext = path.substring(0, path.indexOf('/', 1));

var getPayDetail = function() {
	payName = $(".payName").val();
	payAddr = $(".payAddr").val();
	payComm = $(".payComm").val();
	if (chk() == "" || payName.trim()== "" || payAddr.trim() == "") {
		return null;
	}
console.log("getPayDetail");

	mp.set("recipient", payName);
	mp.set("recipientAddress", payAddr);
	mp.set("footnote", payComm);
	sendOrderMsg(mp);
	console.log(JSON.stringify(mapToJson(mp)));
	$(".payName").val("");
	$(".payAddr").val("");
	$(".payComm").val("");
	$.ajax({
		url: "http://" + window.location.host + webContext + "/ClearCart",
		data: { "action": "SENDORDER" },
		type: "GET",
		success: function(data) {
//			Swal.fire({
//				position: 'center',
//				icon: 'success',
//				title: '訂單已確認送出!',
//				text: '如欲取消訂單請至「訂單查詢」做取消',
//				showConfirmButton: false,
//				timer: 2000
//			})
			window.location=data;
			//			window.location.href ="http://"+ window.location.host+webContext+"/front_end/cart/shop.jsp";
		}
	})
	//	}else{
	//		return null;
	//	};		
};
// 監視ratio的input動作
var chk = function() {
	$(".rto").on("change", function() {
		payMode = $(this).attr("value");
		console.log(payMode);
		mp.set("payMode", payMode);
	});
	return payMode;
};
// 送出訂單內容資料
var sendOrderMsg = function(orderMp) {
	console.log("送出訂單內容資料!");
	$.ajax({
		url: servletPath(),
		data: {
			"metChoice": "buildOrder",
			"orderObj": JSON.stringify(mapToJson(orderMp))
		},
		type: "POST",
		success: function(data) {
			console.log(data);
		},
		error: function() {
		}
	});

}
// 從map轉物件函式
var mapToJson = function(mapObject) {
	let obj = Object.create(null);
	for (let [k, v] of mapObject) {
		obj[k] = v;
	}
	return obj;
}
// 取得動態路徑
function servletPath() {
	let serverEndPoint = "/finalorder/OrderServlet";
	let endPointURL = "http://" + window.location.host + webContext + serverEndPoint;
	return endPointURL;
}
//var mapP=new Map();
//function getIN(){
//	var idIng=$(".idIngre").attr("value");
//	var quan=$(".qt_all .qt").attr("quan");
//	mapP.set(idIng,quan);
// 	console.log(JSON.stringify(mapToJson(mapP)));
//}
