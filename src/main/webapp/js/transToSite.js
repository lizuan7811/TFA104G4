$(function() {

	var path = window.location.pathname;
	var host = window.location.host;
	var contextPath = path.substring(0, path.indexOf('/', 1));
	var serverEndPoint = "/otherWeb/TransToOther.html";
	console.log(servletPath());

	$(".aboutUs").on("click", function() {
		$.ajax({
			data: { "transToSite":"aboutUs"},
			url: servletPath(),
			type: "POST",
			success: function(data) {
				console.log(data);
					window.location = JSON.parse(data)[1];	
			},
			error: function(data) {
				$.ajax({
					data: { "transToSite": "aboutUs" },
					url:"/TFA104G4/otherWeb/TransToOther.html",
					type: "POST",
					success: function(data) {
						window.location = JSON.parse(data)[1];
					}
				});
			}
		});
	});
	$(".shopCity").on("click", function() {
		$.ajax({
			data: { "transToSite": "shopCity" },
			url: servletPath(),
			type: "POST",
			success: function(data) {
					window.location = JSON.parse(data)[1];
			},
			error: function(data) {
				$.ajax({
					data: { "transToSite": "shopCity" },
					url:"/TFA104G4/otherWeb/TransToOther.html",
					type: "POST",
					success: function(data) {
					window.location = JSON.parse(data)[1];	
					}
				});
			}
		});
	});
	$(".eatLife").on("click", function() {
		$.ajax({
			data: { "transToSite": "eatLife" },
			url: servletPath(),
			type: "POST",
			success: function(data) {
					window.location = JSON.parse(data)[1];	
			},
			error: function(data) {
				$.ajax({
					data: { "transToSite": "eatLife" },
					url:"/TFA104G4/otherWeb/TransToOther.html",
					type: "POST",
					success: function(data) {
					window.location = JSON.parse(data)[1];	
					}
				});
			}
		});
	});
	$(".custLogin").on("click", function() {
		$.ajax({
			data: { "transToSite": "custLogin" },
			url: servletPath(),
			type: "POST",
			success: function(data) {
					window.location = JSON.parse(data)[1];	
			},
			error: function(data) {
				$.ajax({
					data: { "transToSite": "custLogin" },
					url:"/TFA104G4/otherWeb/TransToOther.html",
					type: "POST",
					success: function(data) {
						console.log(data);
					window.location = JSON.parse(data)[1];	
					}
				});
			}
		});
	});
	$(".historyOrder").on("click", function() {
		$.ajax({
			data: { "transToSite": "historyOrder" },
			url: servletPath(),
			type: "POST",
			success: function(data) {
				console.log(data);
					window.location = JSON.parse(data)[1];	
			},
			error: function(data) {
				$.ajax({
					data: { "transToSite": "historyOrder" },
					url: "http://" + host + contextPath + serverEndPoint + "/otherWeb/TransToOther.html",
					type: "POST",
					success: function(data) {
						console.log(data);
					window.location = JSON.parse(data)[1];	
					}
				});
			}
		});
	});

	$(".eatDiary").on("click", function() {
			$.ajax({
				data: { "transToSite":"eatDiary"},
				url: servletPath(),
				type: "POST",
				success: function(data) {
					console.log(data);
						$(".author_name").attr("data-custid", JSON.parse(data)[0].custID);
						window.location = JSON.parse(data)[1];	
				},
				error: function(data) {
					$.ajax({
						data: { "transToSite": "eatDiary" },
						url:"/TFA104G4/otherWeb/TransToOther.html",
						type: "POST",
						success: function(data) {
							window.location = JSON.parse(data)[1];
						}
					});
				}
			});
		});

		$(".myLife").on("click", function() {
				$.ajax({
					data: { "transToSite":"myLife"},
					url: servletPath(),
					type: "POST",
					success: function(data) {
						console.log(data);
							window.location = JSON.parse(data)[1];	
					},
					error: function(data) {
						$.ajax({
							data: { "transToSite": "myLife" },
							url:"/TFA104G4/otherWeb/TransToOther.html",
							type: "POST",
							success: function(data) {
								window.location = JSON.parse(data)[1];
							}
						});
					}
				});
			});

	$(".lookOrder").on("click", function() {
		$.ajax({
			data: { "transToSite":"lookOrder"},
			url: servletPath(),
			type: "POST",
			success: function(data) {
				console.log(data);
					window.location = JSON.parse(data)[1];	
			},
			error: function(data) {
				$.ajax({
					data: { "transToSite": "lookOrder" },
					url:"/TFA104G4/otherWeb/TransToOther.html",
					type: "POST",
					success: function(data) {
						window.location = JSON.parse(data)[1];
					}
				});
			}
		});
	});
	
	$(".toShop").on("click", function() {
		$.ajax({
			data: { "transToSite":"toShop"},
			url: servletPath(),
			type: "POST",
			success: function(data) {
				console.log(data);
					window.location = JSON.parse(data)[1];	
			},
			error: function(data) {
				$.ajax({
					data: { "transToSite": "toShop" },
					url:"/TFA104G4/otherWeb/TransToOther.html",
					type: "POST",
					success: function(data) {
						window.location = JSON.parse(data)[1];
					}
				});
			}
		});
	});

	function servletPath() {
		var realPath = "http://" + host + contextPath + serverEndPoint;
		return realPath;
	}
});