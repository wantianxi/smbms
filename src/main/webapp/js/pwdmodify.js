var oldpassword = null;
var newpassword = null;
var rnewpassword = null;
var saveBtn = null;

$(function(){
	oldpassword = $("#oldpassword");
	newpassword = $("#newpassword");
	rnewpassword = $("#rnewpassword");
	saveBtn = $("#save");
	
	oldpassword.next().html("*");
	newpassword.next().html("*");
	rnewpassword.next().html("*");
	
	oldpassword.on("blur",function(){
		let pwd = $(this).val();

		if (pwd==""){
			$("input[name='oldpassword']").next("font").html("原始密码不能为空");
			return
		}else {
			$("input[name='oldpassword']").next("font").html("");//如果密码不是空，则错误信息为空

		}
		$.ajax({
			url:"checkPwd",
			type:"post",
			data:"userpassword="+$("input[name='oldpassword']").val(),
			async :false,//同步请求
			dataType:"text",
			"success":function (result) {
				console.log($("input[name='oldpassword']").val())
				console.log(result)
				if (result=="true"){
					$("input[name='oldpassword']").next("font").html("");

					flag=0;
				}else if (result=="false") {
					$("input[name='oldpassword']").next("font").html("原始密码输入错误");
					flag=1;
				}
			},
			"error":function () {
				alert("服务器错误!")
			}
		})






	}).on("focus",function(){
		validateTip(oldpassword.next(),{"color":"#666666"},"* 请输入原密码",false);
	});
	
	newpassword.on("focus",function(){
		validateTip(newpassword.next(),{"color":"#666666"},"* 密码长度必须是大于6小于20",false);
	}).on("blur",function(){
		if(newpassword.val() != null && newpassword.val().length > 6
				&& newpassword.val().length < 20 ){
			validateTip(newpassword.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(newpassword.next(),{"color":"red"},imgNo + " 密码输入不符合规范，请重新输入",false);
		}
	});
	
	
	rnewpassword.on("focus",function(){
		validateTip(rnewpassword.next(),{"color":"#666666"},"* 请输入与上面一致的密码",false);
	}).on("blur",function(){
		if(rnewpassword.val() != null && rnewpassword.val().length > 6
				&& rnewpassword.val().length < 20 && newpassword.val() == rnewpassword.val()){
			validateTip(rnewpassword.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(rnewpassword.next(),{"color":"red"},imgNo + " 两次密码输入不一致，请重新输入",false);
		}
	});
	
	
	saveBtn.on("click",function(){
		oldpassword.blur();
		newpassword.blur();
		rnewpassword.blur();
		if(oldpassword.attr("validateStatus") == "true" 
			&& newpassword.attr("validateStatus") == "true"
			&& rnewpassword.attr("validateStatus") == "true"){
			if(confirm("确定要修改密码？")){
				$("#userForm").submit(function () {
					return true;
				});


			}
		}
		
	});
});