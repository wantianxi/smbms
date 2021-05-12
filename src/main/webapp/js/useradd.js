var userCode = null;
var userName = null;
var userPassword = null;
var ruserPassword = null;
var phone = null;
var birthday = null;
var userRole = null;
var addBtn = null;
var backBtn = null;


$(function(){
	userCode = $("#userCode");
	userName = $("#userName");
	userPassword = $("#userPassword");
	ruserPassword = $("#ruserPassword");
	phone = $("#phone");
	birthday = $("#birthday");
	userRole = $("#userRole");
	addBtn = $("#add");
	backBtn = $("#back");
	//初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
	userCode.next().html("*");
	userName.next().html("*");
	userPassword.next().html("*");
	ruserPassword.next().html("*");
	phone.next().html("*");
	birthday.next().html("*");
	userRole.next().html("*");




	/*
	 * 验证
	 * 失焦\获焦
	 * jquery的方法传递
	 */
	userCode.bind("blur",function(){
		//ajax后台验证--userCode是否已存在
		//user.do?method=ucexist&userCode=**
		// $.ajax({
		// 	type:"GET",//请求类型
		// 	url:path+"userCode_add",//请求的url
		// 	data:"userCode="+$("#userCode").val(),//请求参数
		// 	dataType:"text",//ajax接口（请求url）返回的数据类型
		// 	success:function(result){//data：返回数据（json对象）
		// 		if(result == "true"){//账号已存在，错误提示
		// 			validateTip(userCode.next(),{"color":"red"},imgNo+ " 该用户账号已存在",false);
		// 		}else{//账号可用，正确提示
		// 			validateTip(userCode.next(),{"color":"green"},imgYes+" 该账号可以使用",true);
		// 		}
		// 	},
		// 	error:function(){//当访问时候，404，500 等非200的错误状态码
		// 		validateTip(userCode.next(),{"color":"red"},imgNo+" 您访问的页面不存在",false);
		// 	}

		var userCode = $(this).val();
		if (userCode==""){
			$(this).next("font").html("请输入用户编码");
		}else {
			$.ajax({
				url: "userCode_add",
				type: "GET",
				data: "userCode=" + $("#userCode").val(),
				dataType: "text",
				"success": function (result) {
					if (result == "true") {
						$("#userCode").next("font").html("用户编码不可用");
						res = 0;
					} else if (result == "false") {
						validateTip($("#userCode").next(),{"color":"green"},imgYes+" 该账号可以使用",true);
						res = 1;

					}
				},
				"error": function () {
					alert("服务器错误！")
				}


			});
		}

	}).bind("focus",function(){
		//显示友情提示
		validateTip(userCode.next(),{"color":"#666666"},"* 用户编码是您登录系统的账号",false);
	}).focus();

	userName.bind("focus",function(){
		validateTip(userName.next(),{"color":"#666666"},"* 用户名长度必须是大于1小于10的字符",false);
	}).bind("blur",function(){
		if(userName.val() != null && userName.val().length > 1
			&& userName.val().length < 10){
			validateTip(userName.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(userName.next(),{"color":"red"},imgNo+" 用户名输入的不符合规范，请重新输入",false);
		}

	});

	userPassword.bind("focus",function(){
		validateTip(userPassword.next(),{"color":"#666666"},"* 密码长度必须是大于6小于20",false);
	}).bind("blur",function(){

			validateTip(userPassword.next(),{"color":"green"},imgYes,true);

	});

	ruserPassword.bind("focus",function(){
		validateTip(ruserPassword.next(),{"color":"#666666"},"* 请输入与上面一只的密码",false);
	}).bind("blur",function(){
		if(ruserPassword.val() != null && userPassword.val() == ruserPassword.val()){
			validateTip(ruserPassword.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(ruserPassword.next(),{"color":"red"},imgNo + " 两次密码输入不一致，请重新输入",false);
		}
	});


	birthday.bind("focus",function(){
		validateTip(birthday.next(),{"color":"#666666"},"* 点击输入框，选择日期",false);
	}).bind("blur",function(){
		if(birthday.val() != null && birthday.val() != ""){
			validateTip(birthday.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(birthday.next(),{"color":"red"},imgNo + " 选择的日期不正确,请重新输入",false);
		}
	});

	phone.bind("focus",function(){
		validateTip(phone.next(),{"color":"#666666"},"* 请输入手机号",false);
	}).bind("blur",function(){
		var patrn=/^(13[0-9]|15[0-9]|18[0-9])\d{8}$/;
		if(phone.val().match(patrn)){
			validateTip(phone.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(phone.next(),{"color":"red"},imgNo + " 您输入的手机号格式不正确",false);
		}
	});

	userRole.bind("focus",function(){
		validateTip(userRole.next(),{"color":"#666666"},"* 请选择用户角色",false);
	}).bind("blur",function(){
		if(userRole.val() != null && userRole.val() > 0){
			validateTip(userRole.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(userRole.next(),{"color":"red"},imgNo + " 请重新选择用户角色",false);
		}
	});

	addBtn.bind("click",function(){
		if(userCode.attr("validateStatus") != "true"){
			userCode.blur();
		}else if(userName.attr("validateStatus") != "true"){
			userName.blur();
		}else if(userPassword.attr("validateStatus") != "true"){
			userPassword.blur();
		}else if(ruserPassword.attr("validateStatus") != "true"){
			ruserPassword.blur();
		}else if(birthday.attr("validateStatus") != "true"){
			birthday.blur();
		}else if(phone.attr("validateStatus") != "true"){
			phone.blur();
		}else if(userRole.attr("validateStatus") != "true"){
			userRole.blur();
		}else{
//
// 			//提交按钮绑定点击事件
// 				//通过jquery的方法获取表单数据
// 				//var params = $("#newsAddForm").serialize();//key=value&key2=value2&....
//
// 				//创建表单数据，通过键值对来添加一系列表单控件，支持异步上传二进制文件
// 				var formData =$("#userForm").
// //        formData.append("file", $("#pic")[0].files[0]);//添加属性
// //        console.log(formData.get("file"));//获取属性
// 				$.ajax({
// 					"url": "user_add_2",
// 					"type": "post",
// 					"data": formData,
// 					"dataType": "text",
// 					"contentType": false,
// 					"processData": false,
//
// 					"success": function (result) {
// 						if (result == "true") {
// 							alert("添加新闻成功");
// 							location.href = "${pageContext.servletContext.contextPath}/news.do?opr=news_list";
// 						} else {
// 							alert("添加新闻失败");
// 						}
// 					},
// 					"error": function () {
// 						alert("服务器异常，请稍后重试。。")
// 					}
// 				});
//
//






			if(confirm("是否确认提交数据")){
				$("#userForm").submit();
			}
		}
	});

	backBtn.on("click",function(){
		if(referer != undefined
			&& null != referer
			&& "" != referer
			&& "null" != referer
			&& referer.length > 4){
			window.location.href = referer;
		}else{
			history.back(-1);
		}
	});
});


//给file元素绑定change事件,实现显示客户端选中的图片
$("#idPicPath").change(function () {
	//获取file文件对象
	var file = $(this)[0].files[0];
	//等价于document.getElementById("pic").files[0];
	//获取一个指向该元素的地址
	var path = window.URL.createObjectURL(file);
	console.log(path);
	$("#uploadImg").attr('src', path);
})


