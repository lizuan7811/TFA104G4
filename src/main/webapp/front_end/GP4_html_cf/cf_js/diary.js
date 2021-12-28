$(function() {

	$("a.tab").on("click", function(e) {
		e.preventDefault();

		/* 將頁籤列表移除所有 -on，再將指定的加上 -on */
		$(this).closest("ul").find("a.tab").removeClass("-on");
		$(this).addClass("-on");

		/* 找到對應的頁籤內容，加上 -on 來顯示 */
		$("div.tab").removeClass("-on");
		$("div.tab." + $(this).attr("data-target")).addClass("-on");
	});

	//所有文章
	function ServletPath_1() {
		var host = window.location.host;
		var path = window.location.pathname;
		var webCtx = path.substring(0, path.indexOf('/', 1));
		var endPointURL = "http://" + window.location.host + webCtx + "/MainDiaryServlet";
		return endPointURL;
	}

	//尋找日誌分類的文章
	function ServletPath_2() {
		var host = window.location.host;
		var path = window.location.pathname;
		var webCtx = path.substring(0, path.indexOf('/', 1));
		var endPointURL = "http://" + window.location.host + webCtx + "/MainDiaryTypeServlet";
		return endPointURL;
	}


	$.ajax({
		type: 'POST',
		url: ServletPath_1(),
		data: { "action": "ALL_DIARY" },
		success: function(data) {
			// console.log(data);
			var array = JSON.parse(data);
			//                console.log(array);
			for (var key in array) {
				var photoStr = array[key].Photo_Video_1;
				var diaryid = array[key].DiaryID;
				var subject = array[key].Subject;


				var aStr = "<div class='pic_link' id='pic_link' data-id='" + diaryid + "' >";
				var phoStr = "<img class='link' id='img' src='data:image/jpg;base64," + photoStr + "'>";
				var ttp = " <div class='item_desc'>" + subject + "</div>";
				$(".tab1").append(aStr + phoStr + ttp);
			}

			// 圖片點擊後連結單一文章
			clickPic();

		},
		error: function(data) {
			$.ajax({
				type: 'POST',
				url: "TFA104G4/MainDiaryServlet",
				data: { "action": "ALL_DIARY" },
				success: function(data) {
					// console.log(data);
					var array = JSON.parse(data);
					//                console.log(array);
					for (var key in array) {
						var photoStr = array[key].Photo_Video_1;
						var diaryid = array[key].DiaryID;
						var subject = array[key].Subject;


						var aStr = "<div class='pic_link' id='pic_link' data-id='" + diaryid + "' >";
						var phoStr = "<img class='link' id='img' src='data:image/jpg;base64," + photoStr + "'>";
						var ttp = " <div class='item_desc'>" + subject + "</div>";
						$(".tab1").append(aStr + phoStr + ttp);
					}

					// 圖片點擊後連結單一文章
					clickPic();

				}

			});
		}

	});

	function clickPic() {
		$(".pic_link").on("click", function() {
			//  alert("ok");
			let id = this.getAttribute("data-id");
			$.ajax({
				type: 'POST',
				url: ServletPath_1(),
				dataType: 'json',
				data: { "action": "GET_DIARY", "diaryid": id },
				success: function(data) {
					if (data.msg == "success") {
						// alert("okkkkk");
						location.href = 'gorup4_diary_page.html?diaryid=' + id;
					}

				},
				error: function(xhr) {
					$.ajax({
						type: 'POST',
						url: "/TFA104G4/MainDiaryServlet",
						dataType: 'json',
						data: { "action": "GET_DIARY", "diaryid": id },
						success: function(data) {
							if (data.msg == "success") {
								// alert("okkkkk");
								location.href = 'gorup4_diary_page.html?diaryid=' + id;
							}

						}

					})

				}


			})

		})
	}

	// -----------以下是不同的日誌分類 點擊後換分類-----------        
	$("#tab_1").on("click", function() {
		if ($(".tab").hasClass("-on")) {
			// alert("data");
			$.ajax({
				type: 'POST',
				url: ServletPath_2(),
				data: { "diaryType": $(".tab2").attr("data-value") },

				success: function(data) {
					// console.log(data);
					var array = JSON.parse(data);

					console.log(array);
					//每次重點進來就清空又進來一次 故設定html 為空
					$(".tab2").html("");
					for (var i in array) {
						for (var y in array[i]) {
							// console.log(array[i][y]);

							var photoStr = array[i][y]["Photo_Video_1"];
							var diaryid = array[i][y].DiaryID;
							var subject = array[i][y].Subject;

							var aStr = "<a href=''>" + "<div class='pic_link' id='" + diaryid + "' >";
							var phoStr = "<img src='data:image/jpg;base64," + photoStr + "'>" + "</a>";
							var ttp = " <div class='item_desc'>" + subject + "</div>";

							$(".tab2").append(aStr + phoStr + ttp);

						}

					}

				},
				error: function() {
					$.ajax({
						type: 'POST',
						url: "/TFA104G4/MainDiaryTypeServlet",
						data: { "diaryType": $(".tab2").attr("data-value") },

						success: function(data) {
							// console.log(data);
							var array = JSON.parse(data);

							console.log(array);
							//每次重點進來就清空又進來一次 故設定html 為空
							$(".tab2").html("");
							for (var i in array) {
								for (var y in array[i]) {
									// console.log(array[i][y]);

									var photoStr = array[i][y]["Photo_Video_1"];
									var diaryid = array[i][y].DiaryID;
									var subject = array[i][y].Subject;

									var aStr = "<a href=''>" + "<div class='pic_link' id='" + diaryid + "' >";
									var phoStr = "<img src='data:image/jpg;base64," + photoStr + "'>" + "</a>";
									var ttp = " <div class='item_desc'>" + subject + "</div>";

									$(".tab2").append(aStr + phoStr + ttp);

								}

							}

						}
					});
				}
			});

		}

	})

	$("#tab_2").on("click", function() {
		if ($(".tab").hasClass("-on")) {
			// alert("data");
			$.ajax({
				type: 'POST',
				url: ServletPath_2(),
				data: { "diaryType": $(".tab3").attr("data-value") },

				success: function(data) {
					// console.log(data);
					var array = JSON.parse(data);

					console.log(array);
					//每次重點進來就清空又進來一次 故設定html 為空
					$(".tab3").html("");
					for (var i in array) {
						for (var y in array[i]) {
							// console.log(array[i][y]);

							var photoStr = array[i][y]["Photo_Video_1"];
							var diaryid = array[i][y].DiaryID;
							var subject = array[i][y].Subject;

							var aStr = "<a href=''>" + "<div class='pic_link' id='" + diaryid + "' >";
							var phoStr = "<img src='data:image/jpg;base64," + photoStr + "'>" + "</a>";
							var ttp = " <div class='item_desc'>" + subject + "</div>";

							$(".tab3").append(aStr + phoStr + ttp);

						}

					}

				},
				error: function(data) {
					$.ajax({
						type: 'POST',
						url: "/TFA104G4/MainDiaryTypeServlet",
						data: { "diaryType": $(".tab3").attr("data-value") },

						success: function(data) {
							// console.log(data);
							var array = JSON.parse(data);

							console.log(array);
							//每次重點進來就清空又進來一次 故設定html 為空
							$(".tab3").html("");
							for (var i in array) {
								for (var y in array[i]) {
									// console.log(array[i][y]);

									var photoStr = array[i][y]["Photo_Video_1"];
									var diaryid = array[i][y].DiaryID;
									var subject = array[i][y].Subject;

									var aStr = "<a href=''>" + "<div class='pic_link' id='" + diaryid + "' >";
									var phoStr = "<img src='data:image/jpg;base64," + photoStr + "'>" + "</a>";
									var ttp = " <div class='item_desc'>" + subject + "</div>";

									$(".tab3").append(aStr + phoStr + ttp);

								}

							}

						}
					});
				}
			});

		}

	})

	$("#tab_3").on("click", function() {
		if ($(".tab").hasClass("-on")) {
			// alert("data");
			$.ajax({
				type: 'POST',
				url: ServletPath_2(),
				data: { "diaryType": $(".tab4").attr("data-value") },

				success: function(data) {
					// console.log(data);
					var array = JSON.parse(data);

					console.log(array);
					//每次重點進來就清空又進來一次 故設定html 為空
					$(".tab4").html("");
					for (var i in array) {
						for (var y in array[i]) {
							// console.log(array[i][y]);

							var photoStr = array[i][y]["Photo_Video_1"];
							var diaryid = array[i][y].DiaryID;
							var subject = array[i][y].Subject;

							var aStr = "<a href=''>" + "<div class='pic_link' id='" + diaryid + "' >";
							var phoStr = "<img src='data:image/jpg;base64," + photoStr + "'>" + "</a>";
							var ttp = " <div class='item_desc'>" + subject + "</div>";

							$(".tab4").append(aStr + phoStr + ttp);

						}

					}

				},
				error: function(data) {
					$.ajax({
						type: 'POST',
						url: "/TFA104G4/MainDiaryTypeServlet",
						data: { "diaryType": $(".tab4").attr("data-value") },

						success: function(data) {
							// console.log(data);
							var array = JSON.parse(data);

							console.log(array);
							//每次重點進來就清空又進來一次 故設定html 為空
							$(".tab4").html("");
							for (var i in array) {
								for (var y in array[i]) {
									// console.log(array[i][y]);

									var photoStr = array[i][y]["Photo_Video_1"];
									var diaryid = array[i][y].DiaryID;
									var subject = array[i][y].Subject;

									var aStr = "<a href=''>" + "<div class='pic_link' id='" + diaryid + "' >";
									var phoStr = "<img src='data:image/jpg;base64," + photoStr + "'>" + "</a>";
									var ttp = " <div class='item_desc'>" + subject + "</div>";

									$(".tab4").append(aStr + phoStr + ttp);
								}
							}
						}
					});
				}
			});
		}
	})

	$("#tab_4").on("click", function() {
		if ($(".tab").hasClass("-on")) {
			// alert("data");
			$.ajax({
				type: 'POST',
				url: ServletPath_2(),
				data: { "diaryType": $(".tab5").attr("data-value") },

				success: function(data) {
					// console.log(data);
					var array = JSON.parse(data);

					console.log(array);
					//每次重點進來就清空又進來一次 故設定html 為空
					$(".tab5").html("");
					for (var i in array) {
						for (var y in array[i]) {
							// console.log(array[i][y]);

							var photoStr = array[i][y]["Photo_Video_1"];
							var diaryid = array[i][y].DiaryID;
							var subject = array[i][y].Subject;

							var aStr = "<a href=''>" + "<div class='pic_link' id='" + diaryid + "' >";
							var phoStr = "<img src='data:image/jpg;base64," + photoStr + "'>" + "</a>";
							var ttp = " <div class='item_desc'>" + subject + "</div>";

							$(".tab5").append(aStr + phoStr + ttp);
						}
					}
				},
				error: function(data) {
					$.ajax({
						type: 'POST',
						url: "/TFA104G4/MainDiaryTypeServlet",
						data: { "diaryType": $(".tab5").attr("data-value") },

						success: function(data) {
							// console.log(data);
							var array = JSON.parse(data);

							console.log(array);
							//每次重點進來就清空又進來一次 故設定html 為空
							$(".tab5").html("");
							for (var i in array) {
								for (var y in array[i]) {
									// console.log(array[i][y]);

									var photoStr = array[i][y]["Photo_Video_1"];
									var diaryid = array[i][y].DiaryID;
									var subject = array[i][y].Subject;

									var aStr = "<a href=''>" + "<div class='pic_link' id='" + diaryid + "' >";
									var phoStr = "<img src='data:image/jpg;base64," + photoStr + "'>" + "</a>";
									var ttp = " <div class='item_desc'>" + subject + "</div>";

									$(".tab5").append(aStr + phoStr + ttp);

								}

							}

						}
					});

				}
			})
			}
			});

			$("#tab_5").on("click", function() {
				if ($(".tab").hasClass("-on")) {
					// alert("data");
					$.ajax({
						type: 'POST',
						url: ServletPath_2(),
						data: { "diaryType": $(".tab6").attr("data-value") },

						success: function(data) {
							// console.log(data);
							var array = JSON.parse(data);

							console.log(array);
							//每次重點進來就清空又進來一次 故設定html 為空
							$(".tab6").html("");
							for (var i in array) {
								for (var y in array[i]) {
									// console.log(array[i][y]);

									var photoStr = array[i][y]["Photo_Video_1"];
									var diaryid = array[i][y].DiaryID;
									var subject = array[i][y].Subject;

									var aStr = "<a href=''>" + "<div class='pic_link' id='" + diaryid + "' >";
									var phoStr = "<img src='data:image/jpg;base64," + photoStr + "'>" + "</a>";
									var ttp = " <div class='item_desc'>" + subject + "</div>";

									$(".tab6").append(aStr + phoStr + ttp);

								}

							}

						},
						error: function(data) {
							$.ajax({
								type: 'POST',
								url: "/TFA104G4/MainDiaryTypeServlet",
								data: { "diaryType": $(".tab6").attr("data-value") },

								success: function(data) {
									// console.log(data);
									var array = JSON.parse(data);

									console.log(array);
									//每次重點進來就清空又進來一次 故設定html 為空
									$(".tab6").html("");
									for (var i in array) {
										for (var y in array[i]) {
											// console.log(array[i][y]);

											var photoStr = array[i][y]["Photo_Video_1"];
											var diaryid = array[i][y].DiaryID;
											var subject = array[i][y].Subject;

											var aStr = "<a href=''>" + "<div class='pic_link' id='" + diaryid + "' >";
											var phoStr = "<img src='data:image/jpg;base64," + photoStr + "'>" + "</a>";
											var ttp = " <div class='item_desc'>" + subject + "</div>";

											$(".tab6").append(aStr + phoStr + ttp);

										}

									}

								}
							});
						}
					});

				}

			})

			$("#tab_6").on("click", function() {
				if ($(".tab").hasClass("-on")) {
					// alert("data");
					$.ajax({
						type: 'POST',
						url: ServletPath_2(),
						data: { "diaryType": $(".tab7").attr("data-value") },

						success: function(data) {
							// console.log(data);
							var array = JSON.parse(data);

							console.log(array);
							//每次重點進來就清空又進來一次 故設定html 為空
							$(".tab7").html("");
							for (var i in array) {
								for (var y in array[i]) {
									// console.log(array[i][y]);

									var photoStr = array[i][y]["Photo_Video_1"];
									var diaryid = array[i][y].DiaryID;
									var subject = array[i][y].Subject;

									var aStr = "<a href=''>" + "<div class='pic_link' id='" + diaryid + "' >";
									var phoStr = "<img src='data:image/jpg;base64," + photoStr + "'>" + "</a>";
									var ttp = " <div class='item_desc'>" + subject + "</div>";

									$(".tab7").append(aStr + phoStr + ttp);

								}

							}

						},
						error: function(data) {
							$.ajax({
								type: 'POST',
								url: "/TFA104G4/MainDiaryTypeServlet",
								data: { "diaryType": $(".tab7").attr("data-value") },

								success: function(data) {
									// console.log(data);
									var array = JSON.parse(data);

									console.log(array);
									//每次重點進來就清空又進來一次 故設定html 為空
									$(".tab7").html("");
									for (var i in array) {
										for (var y in array[i]) {
											// console.log(array[i][y]);

											var photoStr = array[i][y]["Photo_Video_1"];
											var diaryid = array[i][y].DiaryID;
											var subject = array[i][y].Subject;

											var aStr = "<a href=''>" + "<div class='pic_link' id='" + diaryid + "' >";
											var phoStr = "<img src='data:image/jpg;base64," + photoStr + "'>" + "</a>";
											var ttp = " <div class='item_desc'>" + subject + "</div>";

											$(".tab7").append(aStr + phoStr + ttp);

										}

									}

								}
							});
						}
					});
				}
			});

			$("#tab_7").on("click", function() {
				if ($(".tab").hasClass("-on")) {
					// alert("data");
					$.ajax({
						type: 'POST',
						url: ServletPath_2(),
						data: { "diaryType": $(".tab8").attr("data-value") },

						success: function(data) {
							// console.log(data);
							var array = JSON.parse(data);

							console.log(array);
							//每次重點進來就清空又進來一次 故設定html 為空
							$(".tab8").html("");
							for (var i in array) {
								for (var y in array[i]) {
									// console.log(array[i][y]);

									var photoStr = array[i][y]["Photo_Video_1"];
									var diaryid = array[i][y].DiaryID;
									var subject = array[i][y].Subject;

									var aStr = "<a href=''>" + "<div class='pic_link' id='" + diaryid + "' >";
									var phoStr = "<img src='data:image/jpg;base64," + photoStr + "'>" + "</a>";
									var ttp = " <div class='item_desc'>" + subject + "</div>";

									$(".tab8").append(aStr + phoStr + ttp);
								}
							}
						},
						error: function(data) {
							$.ajax({
								type: 'POST',
								url: "/TFA104G4/MainDiaryTypeServlet",
								data: { "diaryType": $(".tab8").attr("data-value") },

								success: function(data) {
									// console.log(data);
									var array = JSON.parse(data);

									console.log(array);
									//每次重點進來就清空又進來一次 故設定html 為空
									$(".tab8").html("");
									for (var i in array) {
										for (var y in array[i]) {
											// console.log(array[i][y]);

											var photoStr = array[i][y]["Photo_Video_1"];
											var diaryid = array[i][y].DiaryID;
											var subject = array[i][y].Subject;

											var aStr = "<a href=''>" + "<div class='pic_link' id='" + diaryid + "' >";
											var phoStr = "<img src='data:image/jpg;base64," + photoStr + "'>" + "</a>";
											var ttp = " <div class='item_desc'>" + subject + "</div>";

											$(".tab8").append(aStr + phoStr + ttp);
										}
									}
								}
							});
						}
					});
				}
			});
		});
