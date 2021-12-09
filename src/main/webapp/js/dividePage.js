$(function(){
	$.ajax({
		url:"GetPhotoServlet",
		data:{"metChoice":"getPhoto"},
		type:"POST",
		success:function(data)
		{
			console.log(data);	
		},
		error:function(){
			
		}
	});
});
