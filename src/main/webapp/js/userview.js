var backBtn = null;

$(function(){
	backBtn = $("#back");
	backBtn.on("click",function(){
		// alert("view : "+referer);
		// type:"POST";
		// if(referer != undefined
		// 	&& null != referer
		// 	&& "" != referer
		// 	&& "null" != referer
		// 	&& referer.length > 4){
		 window.location.href = 'user_list';
		// }else{
		// 	history.back(-1);
		// }
	})
});