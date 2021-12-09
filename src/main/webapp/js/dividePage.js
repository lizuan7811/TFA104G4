$(function(){
	$.ajax({
		url:"GetPhotoServlet",
		data:{"metChoice":"getPhoto"},
		type:"POST",
		success:function(data)
		{
			console.log(data);
			var daObj=JSON.parse(data);
			for(var key in daObj)
			{
				// console.log("data[key]\t"+daObj[key])
				var picStr="data:image/jpg;base64,"+daObj[key];
				var divblock="<img class='inWinPic'src=\""+picStr+"\"></div>";
				$(".winCenter").after(divblock);
			}
		},
		error:function(){
			
		}
	});
});
