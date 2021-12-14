$(function() {
	getPayDetail();
});

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
		if(chk()=="" || payName.trim() == "" || payAddr.trim()== "" || payComm.trim()== "")
		{
			return null;
		}
		mp.set("recipient", payName);
		mp.set("recipientAddress", payAddr);
		mp.set("footnote", payComm);
		sendOrderMsg(mp);
		console.log(JSON.stringify(mapToJson(mp)));
		$(".payName").val("");
		$(".payAddr").val("");
		$(".payComm").val("");
		$.ajax({
			url:"http://"+ window.location.host+webContext+"/ClearCart",
			data:{"action":"SENDORDER"},
			type:"GET",
			success:function(){
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
	for (let [k, v] of mp) {
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