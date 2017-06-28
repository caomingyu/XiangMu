/**
 * 构建XMLHttpRequest对象并请求服务器
 * @param reqType：请求类型（GET或POST）
 * @param url：服务器地址
 * @param async：是否异步请求
 * @param resFun：响应的回调函数
 * @param parameter :请求参数
 * @return
 */

function httpRequest(reqType,url,async,resFun,parameter){
	var request = null;
	if(window.XMLHttpRequest){			//非IE浏览器
		request = new XMLHttpRequest();
	}else if(window.ActiveXObject){		//IE浏览器  
		request = new ActiveXObject("Microsoft.XMLHTTP");
	}
	request.open(reqType, url, true);	//打开服务器连接
	//设置请求头
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.onreadystatechange = resFun;//设置处理响应的回调函数
	parameter = encodeURI(parameter);	//将参数字符串进行编码
	request.send(parameter);			//发送请求
	
	return request;
}