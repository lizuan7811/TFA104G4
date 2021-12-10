$(function() {
	chk();
	getPayDetail();
});

var payName;
var payAddr;
var payComm;
var payMode;
var mp = new Map();

var getPayDetail = function() {
	$(".final_btn").click(function() {
		payName = $(".payName").val();
		payAddr = $(".payAddr").val();
		payComm = $(".payComm").val();
		chk();
		mp.set("recipient", payName);
		mp.set("recipientAddress", payAddr);
		mp.set("footnote", payComm);
		sendOrderMsg(mp);
		console.log(JSON.stringify(mapToJson(mp)));
		

	})
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
	$.ajax({
		url: servletPath(),
		data: {
			"metChoice": "buildOrder",
			"orderObj": JSON.stringify(mapToJson(orderMp))
			},
			type: "POST",
			success: function(data) {
				// alert("sendMsg");
				// console.log(data);
			},
			error: function() {
			}
		});

}
// 從map轉物件函式
var mapToJson = function(mapObject) {
	let obj = Object.create(null);
	for (let [k, v] of mp) {
		obj[k] = v;
	}
	return obj;
}
// 取得動態路徑
function servletPath() {
	let host = window.location.host;
	let path = window.location.pathname;
	let webContext = path.substring(0, path.indexOf('/', 1));
	let serverEndPoint = "/finalorder/OrderServlet";
	let endPointURL = "http://" + window.location.host + webContext + serverEndPoint;
	return endPointURL;
} 