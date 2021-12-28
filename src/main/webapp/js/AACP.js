$(document).ready(function(){
				$.ajax({
					url:"userlist/UserListServlet",
					data:{"metChoice":"getUserList","usPos":"3"},
					type:"post",
					success:function(data){
						var data2=JSON.parse(data);
						var pjArr=new Array();
						$(document).ready(function()
						{
							var i=1;
							for(var key in data2)
							{
								if(data2[key].suspended>0)
								{
								pjArr.push('<tr><td>&nbsp;<input type="checkbox" class="ckBox" name="selectbox" value="">&nbsp;</td><td>'+i+'</td><td>'+data2[key].name+'</td><td>'+data2[key].nickName+'</td><td>'+data2[key].account+'</td><td>'+data2[key].email+'</td><td>0</td><td>'+data2[key].diaryReportedNum+'</td><td>'+data2[key].commentReportedNum+'</td><td>0</td><td><input type="button" name="open" value="解除禁用"/>&nbsp;<input type="button" name="close" value="禁止權限" disabled="disabled"/></td></tr>');									
								}
								else
								{
								pjArr.push('<tr><td>&nbsp;<input type="checkbox" class="ckBox" name="selectbox" value="">&nbsp;</td><td>'+i+'</td><td>'+data2[key].name+'</td><td>'+data2[key].nickName+'</td><td>'+data2[key].account+'</td><td>'+data2[key].email+'</td><td>0</td><td>'+data2[key].diaryReportedNum+'</td><td>'+data2[key].commentReportedNum+'</td><td>0</td><td><input type="button" name="open" value="解除禁用" disabled="disabled"/>&nbsp;<input type="button" name="close" value="禁止權限"/></td></tr>');									
								}
								i=i+1;
							}
							console.log(pjArr);

							for(var j=0;j<pjArr.length;j++){
								$(".mainBox_table").append(pjArr[j]);
								if(j>23)
								{
									$("#mainBox_Content_div").addClass(".autoScrollY");
								}
							}
						});
						
						},
					error:function(data)
					{
					}
				})
			});
		