$(function(){
	//点击更换验证码
	$(".captcha-filed>img").on('click', function(e){
		$(".captcha-filed>img").attr("src", "/web/common/getCaptcha?rdm="+Math.random());
	});
});
