$(document).ready(function(){
				$.ajax({
					url:"ajaxServlet",
					data:{},
					type:"post",
					success:function(data){
						// console.log(data);
						data2=JSON.parse(data);
						$(document).ready(function(){
							// for(var )
							var strTemp=strTemp+"<tr><input>";
							for(var key in data2)
							{
								strTemp=strTemp+"<td>"+data2[key]+"</td>";
							}
							strTemp=strTemp+"</tr>";
							$(".mainBox_table tr").after(strTemp);
						});
					},
					error:function(data)
					{
						
					}
				})
				
			})
		